package org.yasriady.ustadzsomadstreaming.Model.Model4;

/**
 * Created by dedy on 11/27/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoModel4 {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("app_id")
    @Expose
    private String appId;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("is_live")
    @Expose
    private String isLive;
    @SerializedName("page_name")
    @Expose
    private Object pageName;
    @SerializedName("video_id")
    @Expose
    private Object videoId;
    @SerializedName("video_url")
    @Expose
    private Object videoUrl;


    @SerializedName("video_caption")
    @Expose
    private String videoCaption;

    @SerializedName("video_description")
    @Expose
    private String videoDescription;


    @SerializedName("video_datetime")
    @Expose
    private Object videoDatetime;
    @SerializedName("video_view")
    @Expose
    private Object videoView;
    @Expose
    private Object videoRating;
    @SerializedName("video_duration")
    @Expose
    private Object videoDuration;
    @SerializedName("video_like")
    @Expose
    private Object videoLike;
    @SerializedName("video_dislike")
    @Expose
    private Object videoDislike;
    @SerializedName("ts")
    @Expose
    private String ts;
    @SerializedName("owner_name")
    @Expose
    private Object ownerName;
    @SerializedName("owner_channel_id")
    @Expose
    private Object ownerChannelId;
    @SerializedName("ownner_url")
    @Expose
    private Object ownnerUrl;
    @SerializedName("remark")
    @Expose
    private Object remark;
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("type")
    @Expose
    private String type;


    public VideoModel4(final String type) {
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getProvider() {
        provider = provider == null ? "" : provider;
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    public Object getPageName() {
        return pageName;
    }

    public void setPageName(Object pageName) {
        this.pageName = pageName;
    }

    public Object getVideoId() {
        videoId = videoId == null ? "" : videoId;
        return videoId;
    }

    public void setVideoId(Object videoId) {
        this.videoId = videoId;
    }

    public Object getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(Object videoUrl) {
        this.videoUrl = videoUrl;
    }


    public String getVideoCaption() {
        return videoCaption;
    }

    public void setVideoCaption(String videoCaption) {
        this.videoCaption = videoCaption;
    }





    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }






    public Object getVideoDatetime() {
        return videoDatetime;
    }

    public void setVideoDatetime(Object videoDatetime) {
        this.videoDatetime = videoDatetime;
    }

    public Object getVideoView() {
        return videoView;
    }

    public Object getVideoRating() {
        return videoRating;
    }

    public void setVideoView(Object videoView) {
        this.videoView = videoView;
    }

    public void setVideoRating(Object videoRating) {
        this.videoRating = videoRating;
    }

    public Object getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(Object videoDuration) {
        this.videoDuration = videoDuration;
    }

    public Object getVideoLike() {
        return videoLike;
    }

    public void setVideoLike(Object videoLike) {
        this.videoLike = videoLike;
    }

    public Object getVideoDislike() {
        return videoDislike;
    }

    public void setVideoDislike(Object videoDislike) {
        this.videoDislike = videoDislike;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Object getOwnerName() {
        ownerName = ownerName == null ? "" : ownerName;
        return ownerName;
    }

    public void setOwnerName(Object ownerName) {
        this.ownerName = ownerName;
    }

    public Object getOwnerChannelId() {
        return ownerChannelId;
    }

    public void setOwnerChannelId(Object ownerChannelId) {
        this.ownerChannelId = ownerChannelId;
    }

    public Object getOwnnerUrl() {
        return ownnerUrl;
    }

    public void setOwnnerUrl(Object ownnerUrl) {
        this.ownnerUrl = ownnerUrl;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

