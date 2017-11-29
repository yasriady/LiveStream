package org.yasriady.livestream;

/**
 * Created by dedy on 11/22/17.
 */

public class Cfg {

    public static final String REMOTE_PREF_FILE = "https://raw.githubusercontent.com/yasriady/dbstore/master/app/LiveStream/preference.json";
    public static final int NUM_VIDEOS = 25;
    public static String DBNAME = "db.sqlite";

    public static final String DEVELOPMENT_MODE = "developmentMode";

    public static enum Category {

        LIVE("live", 0),
        NEWEST("newest", 1),
        POPULAR("popular", 2),
        EDITOR_CHOICE("editor_choice", 3),
        SEARCH("editor_choice", 4);

        private String stringValue;
        private int intValue;

        Category(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }

        // Ddy:
        public int toInt() {
            return this.intValue;
        }

    }


}
