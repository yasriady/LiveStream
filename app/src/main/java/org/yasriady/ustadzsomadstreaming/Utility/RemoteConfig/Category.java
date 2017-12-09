package org.yasriady.ustadzsomadstreaming.Utility.RemoteConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dedy on 12/7/17.
 * Purpose:
 */

public class Category {

    private Boolean show;
    private String label;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
