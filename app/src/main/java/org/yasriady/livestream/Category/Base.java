package org.yasriady.livestream.Category;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.yasriady.livestream.Model.Model4.VideoModel4;
import org.yasriady.livestream.MyApp;
import org.yasriady.livestream.R;
import org.yasriady.livestream.Category.RecyclerView.OnLoadMoreListener;
import org.yasriady.livestream.Category.RecyclerView.ServiceGenerator;
import org.yasriady.livestream.Category.RecyclerView.VerticalLineDecorator;
import org.yasriady.livestream.Category.RecyclerView.VideosAdapter;
import org.yasriady.livestream.Category.RecyclerView.VideosApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by dedy on 11/25/17.
 * Derived by following category:
 * - Live
 * - Newest
 * - Popular
 * - Editor Choice
 */

public class Base extends Fragment{

    RecyclerView m_recyclerView;
    List<VideoModel4> m_videos;
    VideosAdapter m_adapter;
    VideosApi m_api;
    String TAG = "CategoryFragment - ";
    Context m_context;

    private String m_tableName;
    View m_view;

    protected void begin(View view, final String tableName) {

        showProgressBar( view);

        m_context = getContext();
        m_recyclerView = view.findViewById(R.id.rvVideo);
        m_videos = new ArrayList<>();
        m_view = view;

        m_tableName = tableName;

        m_adapter = new VideosAdapter(m_context, m_videos);
        m_adapter.setLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                m_recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        int index = m_videos.size() - 1;
                        loadMore(index);
                    }
                });
                //Calling loadMore function in Runnable to fix the
                // java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling error
            }
        });
        m_recyclerView.setHasFixedSize(true);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(m_context));
        m_recyclerView.addItemDecoration(new VerticalLineDecorator(2));
        m_recyclerView.setAdapter(m_adapter);

        String server = MyApp.getInstance().getRc().getServer();

        m_api = ServiceGenerator.createService(VideosApi.class, server);
        load(0);

    }

    private void load(int index) {

        Call<List<VideoModel4>> call = m_api.getVideos(index, m_tableName);

        call.enqueue(new Callback<List<VideoModel4>>() {
            @Override
            public void onResponse(Call<List<VideoModel4>> call, Response<List<VideoModel4>> response) {

                if (response.isSuccessful()) {
                    m_videos.addAll(response.body());
                    m_adapter.notifyDataChanged();
                    hideProgressBar(m_view);
                } else {
                    Log.e(TAG, "Response Error" + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<VideoModel4>> call, Throwable t) {
                Log.e(TAG, " Response Error " + t.getMessage());
            }
        });
    }

    private void loadMore(int index) {

        // add loading progress view
        m_videos.add(new VideoModel4("load"));
        m_adapter.notifyItemInserted(m_videos.size() - 1);

        Call<List<VideoModel4>> call = m_api.getVideos(index, m_tableName);
        call.enqueue(new Callback<List<VideoModel4>>() {
            @Override
            public void onResponse(Call<List<VideoModel4>> call, Response<List<VideoModel4>> response) {

                if (response.isSuccessful()) {
                    // remove loading view
                    m_videos.remove(m_videos.size() - 1);

                    List<VideoModel4> result = response.body();
                    if (result.size() > 0) {
                        // add loaded data
                        m_videos.addAll(result);

                    } else { // result size 0 mean there is no more data available at server
                        m_adapter.setMoreDataAvailable(false);
                        // telling adapter to stop calling load more as no more server data available
                        //Toast.makeText(m_context, "No More Data Available", Toast.LENGTH_LONG).show();

                    }
                    m_adapter.notifyDataChanged();
                    // should call the custom method adapter.notifyDataChanegd here to get the correct loading status
                } else {
                    Log.e(TAG, " Load More Response Error " + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<VideoModel4>> call, Throwable t) {
                Log.e(TAG, " Load More Response Error " + t.getMessage());
            }
        });

    }

    private void showProgressBar(View view) {
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(View view) {
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }


}
