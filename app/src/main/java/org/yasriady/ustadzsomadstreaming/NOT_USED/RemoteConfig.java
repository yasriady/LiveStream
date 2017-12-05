package org.yasriady.ustadzsomadstreaming.NOT_USED;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.yasriady.ustadzsomadstreaming.Ads.Ads;
import org.yasriady.ustadzsomadstreaming.Cfg;
import org.yasriady.ustadzsomadstreaming.Utility.DownloadFileFromUrl;

import java.util.ArrayList;

/**
 * Created by dedy on 11/22/17.
 */

// OBSOLETE
public class RemoteConfig {

    private String m_versionName;
    private String m_updateUrl;
    private boolean m_forceUpdate;
    //private String m_serverAddress = "127.0.0.1";   // x_
    //x_ private boolean m_showBanner = false;
    private ArrayList<String> m_serverList;
    private Ads.Adstype m_adsType;
    //x_ private String m_introUrl;
    private boolean m_showCategory_HOME;
    private boolean m_showCategory_LIVE;
    private boolean m_showCategory_NEWEST;
    private boolean m_showCategory_POPULAR;
    private boolean m_showCategory_EDITOR_CHOICE;
    private boolean m_showCategory_SEARCH;
    private boolean m_showCategory_MORE_USTADZ;
    private int m_initialCategoryTab;
    private int m_imageSliderDuration;
    private boolean m_imageSliderShowDescription;
    private String m_youtubeThumbnail;

    RemoteConfigListener m_listener;
    private DownloadFileFromUrl m_downloader;
    private Context m_context;

    public RemoteConfig(Context context, RemoteConfigListener listener) {
        m_context = context;
        this.m_listener = listener;
    }

    public void begin() {
        DownloadListener downloadListener = new DownloadListener();
        m_downloader = new DownloadFileFromUrl(downloadListener);
        m_downloader.execute(Cfg.REMOTE_PREF_FILE);
    }

    class DownloadListener implements DownloadFileFromUrl.DownloadFileFromURLListener {
        @Override
        public void onFileDownloaded(String jsonString) {
            parseJson(jsonString);
        }
    }

    private ArrayList<String> makeServerAddresses(final String jsonString) {  // array of json

        ArrayList<String> stringArray = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0, count = jsonArray.length(); i < count; i++) {
                try {
                    String str = jsonArray.getString(i);
                    stringArray.add(str);
                    // x_ JSONObject jsonObject = jsonArray.getJSONObject(i);
                    // x_ stringArray.add(jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringArray;
    }

    private void parseJson(String jsonString) {
        //Log.d("MYTAG", jsonString);

        String varName = "";

        try {

            JSONObject obj = new JSONObject(jsonString);

            varName = "versionName";
            if (!obj.isNull(varName)) {
                m_versionName = (obj.getString(varName)).trim();
            }

            varName = "updateUrl";
            if (!obj.isNull(varName)) {
                m_updateUrl = (obj.getString(varName)).trim();
            }

            varName = "forceUpdate";
            if (!obj.isNull(varName)) {
                m_forceUpdate = obj.getBoolean(varName);
            }

            varName = "srvList";
            if (!obj.isNull(varName)) {
                String str = (obj.getString(varName)).trim();
                m_serverList = makeServerAddresses(str);
            }

            varName = "adsType";
            if (!obj.isNull(varName)) {
                int val = obj.getInt(varName);
                m_adsType = Ads.Adstype.values()[val];
            }

            varName = "showHOME";
            if (!obj.isNull(varName)) {
                m_showCategory_HOME = obj.getBoolean(varName);
            }

            varName = "showNEWEST";
            if (!obj.isNull(varName)) {
                m_showCategory_NEWEST = obj.getBoolean(varName);
            }

            varName = "showLIVE";
            if (!obj.isNull(varName)) {
                m_showCategory_LIVE = obj.getBoolean(varName);
            }

            varName = "showPOPULAR";
            if (!obj.isNull(varName)) {
                m_showCategory_POPULAR = obj.getBoolean(varName);
            }

            varName = "showEDITOR_CHOICE";
            if (!obj.isNull(varName)) {
                m_showCategory_EDITOR_CHOICE = obj.getBoolean(varName);
            }

            varName = "showSEARCH";
            if (!obj.isNull(varName)) {
                m_showCategory_SEARCH = obj.getBoolean(varName);
            }

            varName = "showMORE_USTADZ";
            if (!obj.isNull(varName)) {
                m_showCategory_MORE_USTADZ = obj.getBoolean(varName);
            }

            varName = "initialCategoryTab";
            if (!obj.isNull(varName)) {
                m_initialCategoryTab = obj.getInt(varName);
            }

            varName = "imageSliderDuration";
            if (!obj.isNull(varName)) {
                m_imageSliderDuration = obj.getInt(varName);
            }

            varName = "imageSliderShowDescription";
            if (!obj.isNull(varName)) {
                m_imageSliderShowDescription = obj.getBoolean(varName);
            }

            varName = "youtubeThumbnail";
            if (!obj.isNull(varName)) {
                m_youtubeThumbnail = (obj.getString(varName)).trim();
            }

            //Log.d("MYTAG", "test");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        m_listener.onRemoteConfigArrive(this);
    }

    // /data/user/0/org.yasriady.livestream/shared_prefs/org.yasriady.livestream_preferences.xml
    public ArrayList<String> getServerList() {
        return m_serverList;
    }

    public final String getServer() {
        int serverIdx;
        //serverIdx = (int) MyApp.getInstance().getPref(m_context).get(Cfg.SERVER_INDEX, 0);
        // diganti menggunakan PreferenceActivity
        SharedPreferences SP =
                PreferenceManager.getDefaultSharedPreferences(m_context);
        String str = SP.getString(Cfg.SERVER_INDEX, "0");
        serverIdx = Integer.parseInt(str);

        if (serverIdx < 0 || serverIdx >= m_serverList.size()) {
            serverIdx = 0;
        }
        return m_serverList.get(serverIdx);
    }

    public Ads.Adstype getAdsType() {
        return m_adsType;
    }

    public interface RemoteConfigListener {
        void onRemoteConfigArrive(RemoteConfig remoteConfig);
    }

// x_
//    public String getIntroUrl() {
//        return m_introUrl;
//    }

    public boolean showCategory_HOME() {
        return m_showCategory_HOME;
    }

    public boolean showCategory_NEWEST() {
        return m_showCategory_NEWEST;
    }

    public boolean showCategory_LIVE() {
        return m_showCategory_LIVE;
    }

    public boolean showCategory_POPULAR() {
        return m_showCategory_POPULAR;
    }

    public boolean showCategory_EDITOR_CHOICE() {
        return m_showCategory_EDITOR_CHOICE;
    }

    public boolean showCategory_SEARCH() {
        return m_showCategory_SEARCH;
    }

    public boolean showCategory_MORE_USTADZ() {
        return m_showCategory_MORE_USTADZ;
    }

    public int initialCategoryTab() {
        return m_initialCategoryTab;
    }

    public int imageSliderDuration() {
        return m_imageSliderDuration;
    }

    public boolean imageShowDescription() {
        return m_imageSliderShowDescription;
    }

    public String youtubeThumbnail() {
        return m_youtubeThumbnail;
    }

    private String versionName() {
        return m_versionName;
    }

    public boolean versionValid() {

        boolean result = false;
        String versionName = "";

        try {
            PackageInfo pInfo = m_context.getPackageManager().getPackageInfo(m_context.getPackageName(), 0);
            versionName = pInfo.versionName;
            result = versionName.equals(m_versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String updateUrl() {
        return m_updateUrl;
    }

    public boolean forceUpdate() {
        return m_forceUpdate;
    }


}

