package org.yasriady.livestream.Category.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.yasriady.livestream.Model.Model4.VideoModel4;
import org.yasriady.livestream.MyApp;
import org.yasriady.livestream.R;
import org.yasriady.livestream.Utility.ItemDialog;
import org.yasriady.livestream.Utility.RemoteConfig;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by dedy on 11/26/17.
 */

public class VideosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int TYPE_VIDEO = 0;
    public final int TYPE_LOAD = 1;

    static Context m_context;
    List<VideoModel4> m_videos;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;
    /*
    * isLoading - to set the remote loading and complete status to fix back to back load more call
    * isMoreDataAvailable - to set whether more data from server available or not.
    * It will prevent useless load more request even after all the server data loaded
    * */

    //private final View.OnClickListener m_onItemClickListener = new MyItemClickListener();
    private OnItemClickListener m_listener;

    public VideosAdapter(Context context, List<VideoModel4> videos) {
        this.m_context = context;
        this.m_videos = videos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Numpang
        if (m_context instanceof OnItemClickListener) {
            m_listener = (OnItemClickListener) m_context;
        } else {
            throw new RuntimeException(m_context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        View view;
        LayoutInflater inflater = LayoutInflater.from(m_context);

        if (viewType == TYPE_VIDEO) {

            view = inflater.inflate(R.layout.row_video, parent, false);
            final VideoHolder videoHolder = new VideoHolder(view);

            //view.setOnClickListener(m_onItemClickListener);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //m_listener.onItemClicked( view  );
                    m_listener.onItemClicked(videoHolder);
                }
            });

            return videoHolder;// new VideoHolder(view);

        } else {
            view = inflater.inflate(R.layout.row_load, parent, false);
            return new LoadHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
        if (getItemViewType(position) == TYPE_VIDEO) {
            ((VideoHolder) holder).bindData(m_videos.get(position));
        }
        //No else part needed as load holder doesn't bind any data
    }

    @Override
    public int getItemViewType(int position) {
        //if (!m_videos.get(position).type.equals("load")) {
        if (!m_videos.get(position).getType().equals("load")) {
            return TYPE_VIDEO;
        } else {
            return TYPE_LOAD;
        }
    }

    @Override
    public int getItemCount() {
        return m_videos.size();
    }

    /* VIEW HOLDERS */

    public static class VideoHolder extends RecyclerView.ViewHolder {

        // Untuk apa ini?
        public VideoModel4 m_videoModel;

        ImageView imageView;
        TextView tvTitle;
        TextView tvDuration;
        TextView tvView;
        TextView tvRating;
        TextView tvChannel;
        TextView tvVideoId;
        TextView tvDescription;

        //TextView tvLive;
        TextView tvPagar1, tvPagar2, tvSlash1;

        ImageButton m_btnMore;

        public VideoHolder(View itemView) {

            super(itemView);

            // Do initial setup

            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.title);
            tvDuration = itemView.findViewById(R.id.duration);
            tvView = itemView.findViewById(R.id.views);
            tvRating = itemView.findViewById(R.id.rating);
            tvChannel = itemView.findViewById(R.id.channel);
            tvVideoId = itemView.findViewById(R.id.video_id);
            //tvLive = itemView.findViewById(R.id.tvLive);
            tvDescription = itemView.findViewById(R.id.description);

            tvPagar1 = itemView.findViewById(R.id.pagar1);
            tvPagar2 = itemView.findViewById(R.id.pagar2);
            tvSlash1 = itemView.findViewById(R.id.slash1);

            m_btnMore = itemView.findViewById(R.id.btnMenu);
            if (MyApp.getInstance().getUser().isLoggedIn()) {
                m_btnMore.setVisibility(View.VISIBLE);
            } else {
                m_btnMore.setVisibility(View.INVISIBLE);

            }

            // Visibility
            //tvTitle
            //tvDuration
            //tvPagar1
            //tvView
            tvPagar2.setVisibility(View.GONE);
            tvRating.setVisibility(View.GONE);
            //tvChannel
            tvSlash1.setVisibility(View.GONE);
            tvVideoId.setVisibility(View.GONE);
            //tvLive.setVisibility(View.GONE);
            //m_btnMore.setVisibility(View.GONE);
            tvDescription.setVisibility(View.GONE);

            m_btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBtnMoreMenu();
                }
            });

        }

        private void onBtnMoreMenu() {
            //Toast.makeText(m_context, "VideoHolder::onImageButtonClicked()", Toast.LENGTH_SHORT).show();
            ItemDialog dlg = new ItemDialog(m_context);
            dlg.show();
        }

        private boolean haveData(Object obj) {
            boolean result = false;
            if (obj != null) {
                if (!obj.toString().trim().isEmpty()) {
                    result = true;
                }
            }
            return result;
        }

        void bindData(VideoModel4 videoModel) {
            //tvTitle.setText(videoModel.title);
            //tvRating.setText("Rating " + videoModel.rating);

            m_videoModel = videoModel;
            Object obj;
            String videoId = "";
            String provider = "";

            obj = videoModel.getProvider();
            if (haveData(obj)) {
                provider = obj.toString();
            }

            // Title aka Caption
            obj = videoModel.getVideoCaption();
            if (haveData(obj)) {
                String videoTitle = obj.toString();
                tvTitle.setText(videoTitle);
            }

            // Duration
            obj = videoModel.getVideoDuration();
            if (haveData(obj)) {
                String strDuration = getTvDuration(obj.toString());
                tvDuration.setText(strDuration);
                //tvDuration.setVisibility(View.VISIBLE);
            } else {
                //tvDuration.setVisibility(View.GONE);
                //tvPagar1.setVisibility(View.GONE);
            }

            // Video Datetime
            obj = videoModel.getVideoDatetime();
            if (haveData(obj)) {
                String strDatetime = obj.toString();
                tvView.setText(strDatetime);
                //tvView.setVisibility(View.VISIBLE);
            } else {
                //tvView.setVisibility(View.GONE);
                //tvPagar2.setVisibility(View.GONE);
            }

            // Views
            obj = videoModel.getVideoView();
            if (haveData(obj)) {
                String videoView = obj.toString();
                tvView.setText(videoView + " views");
                //tvView.setVisibility(View.VISIBLE);
            } else {
                //tvView.setVisibility(View.GONE);
                //tvPagar2.setVisibility(View.GONE);
            }

            // Channel
            obj = videoModel.getOwnerName();
            if (haveData(obj)) {
                String channel = obj.toString();
                tvChannel.setText(channel);
                //tvChannel.setVisibility(View.VISIBLE);
            } else {
                //tvChannel.setVisibility(View.GONE);
                //tvSlash1.setVisibility(View.GONE);
            }
            tvChannel.setVisibility(View.GONE);

            // Description
            obj = videoModel.getVideoDescription();
            if (haveData(obj)) {
                String description = obj.toString();
                tvDescription.setText(description);
                //tvDescription.setVisibility(View.VISIBLE);
            } else {
                //tvDescription.setVisibility(View.GONE);
            }


//            obj = videoModel.getProvider();
//            if (obj != null) {
//                provider = obj.toString();
//            }

//            obj = videoModel.getVideoCaption();
//            if (obj != null) {
//                tvTitle.setText(obj.toString());
//            }

//            obj = videoModel.getVideoDuration();
//            if (obj != null) {
//                tvTitle.setText(obj.toString());
//            }

//            obj = videoModel.getVideoView();
//            if (obj != null) {
//                tvTitle.setText(obj.toString());
//            }

            obj = videoModel.getVideoRating();
            if (obj != null) {
                tvRating.setText("Rating " + obj.toString());
            }

//            obj = videoModel.getOwnerName();
//            if (obj != null) {
//                tvChannel.setText(obj.toString());
//            }

            // Video_Id
            obj = videoModel.getVideoId();
            if (obj != null) {
                videoId = obj.toString();
                tvVideoId.setText(videoId);
            }

            obj = videoModel.getIsLive();
            if (obj != null) {
                int i = Integer.valueOf(obj.toString());
                if (i == 1) {
                    //tvLive.setText("LIVE");
                    //tvLive.setVisibility(View.VISIBLE);
                    setDurationToLive();
                }
            }

            if (!videoId.isEmpty()) {
                final String imgUrl = makeImageUrl(videoId, provider);
                Picasso.with(m_context).load(imgUrl).into(imageView);
            }

        }

        private String getTvDuration(String s) {
            String str = "__:__:__";
            Double dbl = Double.valueOf(s);
            BigDecimal dec = BigDecimal.valueOf(dbl);
            final int[] i = splitToComponentTimes(dec);
            str = String.format("%02d:%02d:%02d", i[0], i[1], i[2]);
            return str;
        }

        // https://stackoverflow.com/a/6118964/3626789, masih ada kesahalah hitung. Perlu diperiksa lagi nanti
        public int[] splitToComponentTimes(BigDecimal biggy) {
            long longVal = biggy.longValue();
            int hours = (int) longVal / 3600;
            int remainder = (int) longVal - hours * 3600;
            int mins = remainder / 60;
            remainder = remainder - mins * 60;
            int secs = remainder;

            int[] ints = {hours, mins, secs};
            return ints;
        }

        private void setDurationToLive() {
            tvDuration.setText("LIVE NOW");
            tvDuration.setBackground(ContextCompat.getDrawable(m_context, R.drawable.border_live));
            tvDuration.setTextColor(Color.RED);
        }

        private String makeImageUrl(final String videoId, final String provider) {

            String imgUrl = "";

            switch (provider) {

                case "youtube":

                    // "http://i3.ytimg.com/vi/" + videoId + "/1.jpg";
                    // Gambar, check disini caranya https://stackoverflow.com/a/2068371/3626789
                    // https://img.youtube.com/vi/d5bmuChnOP8/default.jpg
                    // http://i3.ytimg.com/vi/d5bmuChnOP8/1.jpg
                    // http://i3.ytimg.com/vi/Ksu_46uLJiQ/1.jpg
                    // https://youtu.be/Ksu_46uLJiQ
                    // i3.ytimg.com works in place of img.youtube.com in the example urls above.

                    //imgUrl = "http://i3.ytimg.com/vi/" + videoId + "/default.jpg";
                    RemoteConfig rc = MyApp.getInstance().getRc();
                    imgUrl = rc.youtubeThumbnail();
                    imgUrl = imgUrl.replace("{videoId}", videoId);

                    break;

                case "facebook":
                    break;
                case "instagram":
                    break;
                default:
                    break;
            }

            return imgUrl;
        }

    }

    static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
         */

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

//    private class MyItemClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
//            //Toast.makeText(m_context, "MyOnClickListener::onClick()", Toast.LENGTH_SHORT).show();
//            m_listener.onItemClicked(this);
//        }
//    }

    public interface OnItemClickListener {
        void onItemClicked(VideoHolder videoHolder);
    }

}
