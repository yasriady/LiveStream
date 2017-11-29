package org.yasriady.livestream;

import android.content.Context;
import android.text.format.Time;

import org.yasriady.livestream.BaseClasses.DBBase;
import org.yasriady.livestream.Model.Model1.Video;

/**
 * Created by dedy on 11/14/17.
 */

public class MyDB extends DBBase {

    public MyDB(Context context) {
        super(context);
    }


    public Video getVideo(final Cfg.Category category, final int offset, final int limit) {

        String sql = "";

        final int which = category.toInt();
        Cfg.Category whichCategory = Cfg.Category.values()[which];
        switch (whichCategory) {

            case LIVE:
                sql = "";
                break;
            case NEWEST:
                sql = "";
                break;
            case POPULAR:
                sql = "";
                break;
            case EDITOR_CHOICE:
                sql = "";
                break;
            default:
        }

        Video video = new Video();

        int id;
        int app_id;
        String provider;
        int is_live;
        String page_name;
        String video_id;
        String video_url;
        String video_caption;
        Time video_datetime;
        int video_view;
        int video_duration;
        int video_like;
        int video_dislike;
        Time ts;
        String owner_name;
        String owner_channel_id;
        String owner_url;
        String remark;

        // -------------------------------------------------------

        id = 0;
        app_id = 0;
        provider = "";
        is_live = 0;
        page_name = "";
        video_id = "";
        video_url = "";
        video_caption = "Video caption - offset " + offset;
        video_datetime = new Time();
        video_view = 23000;
        video_duration = 12;
        video_like = 1243;
        video_dislike = 23;
        ts = new Time();
        owner_name = "Channel name";
        owner_channel_id = category.toString();
        owner_url = "";
        remark = "";

        // -------------------------------------------------------

        video.setM_id(id);
        video.setM_app_id(app_id);
        video.setM_provider(provider);
        video.setM_is_live(is_live);
        video.setM_page_name(page_name);
        video.setM_video_id(video_id);
        video.setM_video_url(video_url);
        video.setM_video_caption(video_caption);
        video.setM_video_datetime(video_datetime);
        video.setM_video_view(video_view);
        video.setM_video_duration(video_duration);
        video.setM_video_like(video_like);
        video.setM_video_dislike(video_dislike);
        video.setM_ts(ts);
        video.setM_owner_name(owner_name);
        video.setM_owner_channel_id(owner_channel_id);
        video.setM_owner_url(owner_url);
        video.setM_remark(remark);

        // -------------------------------------------------------

        return video;
    }
}
