package org.yasriady.ustadzsomadstreaming.Utility.RemoteConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dedy on 12/7/17.
 * Purpose:
 */

public class ApplicationAbout {

    private String title;
    private String subTitle;
    private String moreInfo1;
    private String moreInfo2;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getMoreInfo1() {
        return moreInfo1;
    }

    public void setMoreInfo1(String moreInfo1) {
        this.moreInfo1 = moreInfo1;
    }

    public String getMoreInfo2() {
        return moreInfo2;
    }

    public void setMoreInfo2(String moreInfo2) {
        this.moreInfo2 = moreInfo2;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}