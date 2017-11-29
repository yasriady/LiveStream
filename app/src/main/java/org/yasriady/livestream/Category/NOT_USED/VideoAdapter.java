package org.yasriady.livestream.Category.NOT_USED;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.yasriady.livestream.Model.Model1.Video;
import org.yasriady.livestream.R;

import java.util.List;

/**
 * Created by dedy on 11/23/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    // Store a member variable for the videos
    private List<Video> m_videos;

    // Pass in the video array into the constructor
    public VideoAdapter(List<Video> videos) {
        this.m_videos = videos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView m_ivThumbnail;
        public TextView m_tvCaption;
        public TextView m_tvChannel;
        public TextView m_tvViews;

        public ViewHolder(View itemView) {
            super(itemView);
            m_ivThumbnail = (ImageView) itemView.findViewById(R.id.imageView);
            m_tvCaption = itemView.findViewById(R.id.tvCaption);
            m_tvChannel = itemView.findViewById(R.id.tvChannel);
            m_tvViews = itemView.findViewById(R.id.tvView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View videoView = inflater.inflate(R.layout.video_thumbnail, parent, false);
        ViewHolder viewHolder = new ViewHolder(videoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder viewHolder, int position) {

        Video video = m_videos.get(position);

        ImageView ivThumbnail = viewHolder.m_ivThumbnail;
        TextView tvCaption = viewHolder.m_tvCaption;
        TextView tvChannel = viewHolder.m_tvChannel;
        TextView tvViews = viewHolder.m_tvViews;

        //final Bitmap thumbnail;
        final String caption = video.getM_video_caption();
        final String channel = video.getM_owner_channel_id();
        final String views = String.valueOf(video.getM_video_view());

        //ivThumbnail.setImageBitmap( bitmap );
        tvCaption.setText(caption);
        tvChannel.setText(channel);
        tvViews.setText(views);

    }

    @Override
    public int getItemCount() {
        return m_videos.size();
    }


}
