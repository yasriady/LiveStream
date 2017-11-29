package org.yasriady.livestream.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.yasriady.livestream.Ads.Ads;
import org.yasriady.livestream.Cfg;

import java.util.ArrayList;

/**
 * Created by dedy on 11/22/17.
 */

public class RemoteConfig {

    //private String m_serverAddress = "127.0.0.1";   // x_
    //x_ private boolean m_showBanner = false;
    private ArrayList<String> m_serverList;
    private Ads.Adstype m_adsType;
    //x_ private String m_introUrl;
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

    public RemoteConfig(RemoteConfigListener listener) {
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

            varName = "srvList";
            if (!obj.isNull(varName)) {
                String str = obj.getString(varName);
                m_serverList = makeServerAddresses(str);
            }

// x_
//            varName = "showBanner";
//            if (!obj.isNull(varName)) {
//                int val = obj.getInt(varName);
//                m_showBanner = val == 1 ? true : false;
//            }

            varName = "adsType";
            if (!obj.isNull(varName)) {
                int val = obj.getInt(varName);
                m_adsType = Ads.Adstype.values()[val];
            }
// x_
//            varName = "introUrl";
//            if (!obj.isNull(varName)) {
//                m_introUrl = obj.getString(varName);
//            }

            varName = "showLIVE";
            if (!obj.isNull(varName)) {
                m_showCategory_LIVE = obj.getBoolean(varName);
            }

            varName = "showNEWEST";
            if (!obj.isNull(varName)) {
                m_showCategory_NEWEST = obj.getBoolean(varName);
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
                m_youtubeThumbnail = obj.getString(varName);
            }

            //Log.d("MYTAG", "test");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        m_listener.onRemoteConfigArrive(this);
    }

//    public String getServerAddress() {
//        return m_serverAddress;
//    }

// x_
//    public boolean showBanner() {
//        return m_showBanner;
//    }

    public ArrayList<String> getServerList() {
        return m_serverList;
    }

    public final String getServer() {
        return m_serverList.get(0);
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

    public boolean showCategory_LIVE() {
        return m_showCategory_LIVE;
    }

    public boolean showCategory_NEWEST() {
        return m_showCategory_NEWEST;
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


}





