package org.yasriady.ustadzsomadstreaming.Utility.RemoteConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dedy on 12/7/17.
 * Purpose:
 */

public class ListItem {

    private Integer titleVisibility;
    private Integer idVisibility;
    private Integer descriptionVisibility;
    private Integer publishedAtVisibility;
    private Integer channelTitleVisibility;
    private Integer durationVisibility;
    private Integer viewCountVisibility;
    private Integer likeCountVisibility;
    private Integer dislikeCountVisibility;
    private Integer favoriteCountVisibility;
    private Integer commentCountVisibility;
    private Integer ratingVisibility;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getTitleVisibility() {
        return titleVisibility;
    }

    public void setTitleVisibility(Integer titleVisibility) {
        this.titleVisibility = titleVisibility;
    }

    public Integer getIdVisibility() {
        return idVisibility;
    }

    public void setIdVisibility(Integer idVisibility) {
        this.idVisibility = idVisibility;
    }

    public Integer getDescriptionVisibility() {
        return descriptionVisibility;
    }

    public void setDescriptionVisibility(Integer descriptionVisibility) {
        this.descriptionVisibility = descriptionVisibility;
    }

    public Integer getPublishedAtVisibility() {
        return publishedAtVisibility;
    }

    public void setPublishedAtVisibility(Integer publishedAtVisibility) {
        this.publishedAtVisibility = publishedAtVisibility;
    }

    public Integer getChannelTitleVisibility() {
        return channelTitleVisibility;
    }

    public void setChannelTitleVisibility(Integer channelTitleVisibility) {
        this.channelTitleVisibility = channelTitleVisibility;
    }

    public Integer getDurationVisibility() {
        return durationVisibility;
    }

    public void setDurationVisibility(Integer durationVisibility) {
        this.durationVisibility = durationVisibility;
    }

    public Integer getViewCountVisibility() {
        return viewCountVisibility;
    }

    public void setViewCountVisibility(Integer viewCountVisibility) {
        this.viewCountVisibility = viewCountVisibility;
    }

    public Integer getLikeCountVisibility() {
        return likeCountVisibility;
    }

    public void setLikeCountVisibility(Integer likeCountVisibility) {
        this.likeCountVisibility = likeCountVisibility;
    }

    public Integer getDislikeCountVisibility() {
        return dislikeCountVisibility;
    }

    public void setDislikeCountVisibility(Integer dislikeCountVisibility) {
        this.dislikeCountVisibility = dislikeCountVisibility;
    }

    public Integer getFavoriteCountVisibility() {
        return favoriteCountVisibility;
    }

    public void setFavoriteCountVisibility(Integer favoriteCountVisibility) {
        this.favoriteCountVisibility = favoriteCountVisibility;
    }

    public Integer getCommentCountVisibility() {
        return commentCountVisibility;
    }

    public void setCommentCountVisibility(Integer commentCountVisibility) {
        this.commentCountVisibility = commentCountVisibility;
    }

    public Integer getRatingVisibility() {
        return ratingVisibility;
    }

    public void setRatingVisibility(Integer ratingVisibility) {
        this.ratingVisibility = ratingVisibility;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

