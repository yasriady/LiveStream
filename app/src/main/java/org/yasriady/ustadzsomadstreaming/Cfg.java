package org.yasriady.ustadzsomadstreaming;

/**
 * Created by dedy on 11/22/17.
 */

public class Cfg {

    public static final String REMOTE_CONFIG_SERVER = "https://dbstore.sourceforge.io";
    //public static final String REMOTE_PREF_FILE = "https://raw.githubusercontent.com/yasriady/dbstore/master/app/LiveStream/preference.json";
    public static final String REMOTE_PREF_FILE = "https://dbstore.sourceforge.io/app/LiveStream/x_preference.json"; // x_

    public static final String SERVER_DIR = "UstadzSomadStreaming";
    public static final int NUM_VIDEOS = 25;
    public static String DBNAME = "db.sqlite";
    public static final String DEVELOPMENT_MODE = "developmentMode";
    public static final String NEXT_REMINDER = "nextReminder";
    public static final String SERVER_INDEX = "serverIndex";
    public static final String TEST_DEVICE_ID = "629A74973A0DBC5A96E944CA1C0AE432";
    public static final String MYTAG = "MYTAG";

    // requestCode
    public static final int RC_IM_LOGIN = 101;
    public static final int RC_CHANGE_PREFERENCE = 102;


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
