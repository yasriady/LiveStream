package org.yasriady.ustadzsomadstreaming.Utility.RemoteConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dedy on 12/7/17.
 * Purpose:
 */

public class RemoteConfigModel {

    private ApplicationAbout applicationAbout;
    private String versionName;
    private Boolean forceUpdate;
    private String updateUrl;
    private Integer adsType;
    private Integer adsLocation;
    private List<String> srvList = null;
    private Category catHome;
    private Category catNewest;
    private Category catLive;
    private Category catPopular;
    private Category catEditorChoice;
    private Category catSearch;
    private Category catMoreUstadz;
    private Integer initialCategoryTab;
    private Integer imageSliderDuration;
    private Boolean imageSliderShowDescription;
    private String youtubeThumbnail;
    private ListItem listItem;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ApplicationAbout getApplicationAbout() {
        return applicationAbout;
    }

    public void setApplicationAbout(ApplicationAbout applicationAbout) {
        this.applicationAbout = applicationAbout;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Boolean getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public Integer getAdsType() {
        return adsType;
    }

    public void setAdsType(Integer adsType) {
        this.adsType = adsType;
    }



    public Integer getAdsLocation() {
        return adsLocation;
    }

    public void setAdsLocation(Integer adsLocation) {
        this.adsLocation= adsLocation;
    }




    public List<String> getSrvList() {
        return srvList;
    }

    public void setSrvList(List<String> srvList) {
        this.srvList = srvList;
    }

    public Category getCatHome() {
        return catHome;
    }

    public void setCatHome(Category catHome) {
        this.catHome = catHome;
    }

    public Category getCatNewest() {
        return catNewest;
    }

    public void setCatNewest(Category catNewest) {
        this.catNewest = catNewest;
    }

    public Category getCatLive() {
        return catLive;
    }

    public void setCatLive(Category catLive) {
        this.catLive = catLive;
    }

    public Category getCatPopular() {
        return catPopular;
    }

    public void setCatPopular(Category catPopular) {
        this.catPopular = catPopular;
    }

    public Category getCatEditorChoice() {
        return catEditorChoice;
    }

    public void setCatEditorChoice(Category catEditorChoice) {
        this.catEditorChoice = catEditorChoice;
    }

    public Category getCatSearch() {
        return catSearch;
    }

    public void setCatSearch(Category catSearch) {
        this.catSearch = catSearch;
    }

    public Category getCatMoreUstadz() {
        return catMoreUstadz;
    }

    public void setCatMoreUstadz(Category catMoreUstadz) {
        this.catMoreUstadz = catMoreUstadz;
    }

    public Integer getInitialCategoryTab() {
        return initialCategoryTab;
    }

    public void setInitialCategoryTab(Integer initialCategoryTab) {
        this.initialCategoryTab = initialCategoryTab;
    }

    public Integer getImageSliderDuration() {
        return imageSliderDuration;
    }

    public void setImageSliderDuration(Integer imageSliderDuration) {
        this.imageSliderDuration = imageSliderDuration;
    }

    public Boolean getImageSliderShowDescription() {
        return imageSliderShowDescription;
    }

    public void setImageSliderShowDescription(Boolean imageSliderShowDescription) {
        this.imageSliderShowDescription = imageSliderShowDescription;
    }

    public String getYoutubeThumbnail() {
        return youtubeThumbnail;
    }

    public void setYoutubeThumbnail(String youtubeThumbnail) {
        this.youtubeThumbnail = youtubeThumbnail;
    }

    public ListItem getListItem() {
        return listItem;
    }

    public void setListItem(ListItem listItem) {
        this.listItem = listItem;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

