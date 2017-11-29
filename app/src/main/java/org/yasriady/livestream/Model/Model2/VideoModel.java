package org.yasriady.livestream.Model.Model2;

/**
 * Created by dedy on 11/26/17.
 */

import java.io.Serializable;

/**
 * Created by sab99r
 */
public class VideoModel implements Serializable {

    public String title;
    public String rating;
    public String type;
    public String table_name;

    public VideoModel(String type) {
        this.type = type;
    }



}