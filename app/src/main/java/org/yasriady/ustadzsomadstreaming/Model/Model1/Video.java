package org.yasriady.ustadzsomadstreaming.Model.Model1;

import android.text.format.Time;

import org.yasriady.ustadzsomadstreaming.MyApp;
import org.yasriady.ustadzsomadstreaming.MyDB;
import org.yasriady.ustadzsomadstreaming.Cfg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dedy on 11/23/17.
 */

// https://gist.github.com/rogerhu/17aca6ad4dbdb3fa5892
public class Video {

    private int m_id;
    private int m_app_id;
    private String m_provider;
    private int m_is_live;
    private String m_page_name;
    private String m_video_id;
    private String m_video_url;
    private String m_video_caption;
    private Time m_video_datetime;
    private int m_video_view;
    private int m_video_duration;
    private int m_video_like;
    private int m_video_dislike;
    private Time m_ts;
    private String m_owner_name;
    private String m_owner_channel_id;
    private String m_owner_url;
    private String m_remark;

    // ----------------------------------------------------------------------------------------------

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public int getM_app_id() {
        return m_app_id;
    }

    public void setM_app_id(int m_app_id) {
        this.m_app_id = m_app_id;
    }

    public String getM_provider() {
        return m_provider;
    }

    public void setM_provider(String m_provider) {
        this.m_provider = m_provider;
    }

    public int getM_is_live() {
        return m_is_live;
    }

    public void setM_is_live(int m_is_live) {
        this.m_is_live = m_is_live;
    }

    public String getM_page_name() {
        return m_page_name;
    }

    public void setM_page_name(String m_page_name) {
        this.m_page_name = m_page_name;
    }

    public String getM_video_id() {
        return m_video_id;
    }

    public void setM_video_id(String m_video_id) {
        this.m_video_id = m_video_id;
    }

    public String getM_video_url() {
        return m_video_url;
    }

    public void setM_video_url(String m_video_url) {
        this.m_video_url = m_video_url;
    }

    public String getM_video_caption() {
        return m_video_caption;
    }

    public void setM_video_caption(String m_video_caption) {
        this.m_video_caption = m_video_caption;
    }

    public Time getM_video_datetime() {
        return m_video_datetime;
    }

    public void setM_video_datetime(Time m_video_datetime) {
        this.m_video_datetime = m_video_datetime;
    }

    public int getM_video_view() {
        return m_video_view;
    }

    public void setM_video_view(int m_video_view) {
        this.m_video_view = m_video_view;
    }

    public int getM_video_duration() {
        return m_video_duration;
    }

    public void setM_video_duration(int m_video_duration) {
        this.m_video_duration = m_video_duration;
    }

    public int getM_video_like() {
        return m_video_like;
    }

    public void setM_video_like(int m_video_like) {
        this.m_video_like = m_video_like;
    }

    public int getM_video_dislike() {
        return m_video_dislike;
    }

    public void setM_video_dislike(int m_video_dislike) {
        this.m_video_dislike = m_video_dislike;
    }

    public Time getM_ts() {
        return m_ts;
    }

    public void setM_ts(Time m_ts) {
        this.m_ts = m_ts;
    }

    public String getM_owner_name() {
        return m_owner_name;
    }

    public void setM_owner_name(String m_owner_name) {
        this.m_owner_name = m_owner_name;
    }

    public String getM_owner_channel_id() {
        return m_owner_channel_id;
    }

    public void setM_owner_channel_id(String m_owner_channel_id) {
        this.m_owner_channel_id = m_owner_channel_id;
    }

    public String getM_owner_url() {
        return m_owner_url;
    }

    public void setM_owner_url(String m_owner_url) {
        this.m_owner_url = m_owner_url;
    }

    public String getM_remark() {
        return m_remark;
    }

    public void setM_remark(String m_remark) {
        this.m_remark = m_remark;
    }

    // ----------------------------------------------------------------------------------------------

    private String m_name;
    private boolean m_online;

    public Video() {
    }

    public Video(int id, int app_id, String provider, int is_live, String page_name,
                 String video_id, String video_url, String video_caption, Time video_datetime,
                 int video_view, int video_duration, int video_like, int video_dislike, Time ts,
                 String owner_name, String owner_channel_id,
                 String owner_url, String remark
    ) {
//        this.m_video_caption = caption;
//        this.m_owner_channel_id = channel;
//        this.m_video_view = views;

        this.m_id = id;
        this.m_app_id = app_id;
        this.m_provider = provider;
        this.m_is_live = is_live;
        this.m_page_name = page_name;
        this.m_video_id = video_id;
        this.m_video_url = video_url;
        this.m_video_caption = video_caption;
        this.m_video_datetime = video_datetime;
        this.m_video_view = video_view;
        this.m_video_duration = video_duration;
        this.m_video_like = video_like;
        this.m_video_dislike = video_dislike;
        this.m_ts = ts;
        this.m_owner_name = owner_name;
        this.m_owner_channel_id = owner_channel_id;
        this.m_owner_url = owner_url;
        this.m_remark = remark;

    }

    private static int m_lastVideoId = 0;

    public static List<Video> createVideosList(final Cfg.Category category, final int numVideos, final int offset) {

        List<Video> videos = new ArrayList<Video>();

        for (int i = 1; i <= numVideos; i++) {

            // m_db.getDataHERE
            int limit = 0;
            Video v = getDb().getVideo(category, offset, limit);

            //final String caption = "Video " + ++m_lastVideoId + " offset: " + offset;
            //final String channel = "Tafaqquh";
            //final int views = 123;

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

            id = v.getM_id();
            app_id = v.getM_app_id();
            provider = v.getM_provider();
            is_live = v.getM_is_live();
            page_name = v.getM_page_name();
            video_id = v.getM_video_id();
            video_url = v.getM_video_url();
            video_caption = v.getM_video_caption();
            video_datetime = new Time();
            video_view = v.getM_video_view();
            video_duration = v.getM_video_duration();
            video_like = v.getM_video_like();
            video_dislike = v.getM_video_dislike();
            ts = v.getM_ts();
            owner_name = v.getM_owner_name();
            owner_channel_id = v.getM_owner_channel_id();
            owner_url = v.getM_owner_url();
            remark = v.getM_remark();

            // -------------------------------------------------------

            videos.add(
                    new Video(
                            id,
                            app_id,
                            provider,
                            is_live,
                            page_name,
                            video_id,
                            video_url,
                            video_caption,
                            video_datetime,
                            video_view,
                            video_duration,
                            video_like,
                            video_dislike,
                            ts,
                            owner_name,
                            owner_channel_id,
                            owner_url,
                            remark
                    )
            );

        }

        return videos;
    }

    private static MyDB getDb() {
        return MyApp.getInstance().getDb();
    }

}
