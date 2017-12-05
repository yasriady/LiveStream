package org.yasriady.ustadzsomadstreaming.Utility.RemoteConfig;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;

import org.yasriady.ustadzsomadstreaming.Ads.Ads;
import org.yasriady.ustadzsomadstreaming.Cfg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.content.ContentValues.TAG;

/**
 * Created by Dedy Yasriady on 12/5/17.
 * Purpose:
 */

// 1
public class RemoteConfig {

    RemoteConfigListener m_listener;
    private Context m_context;
    private RemoteConfigModel m_rcModel;
    private String server;
    //private Ads.Adstype adsType;

    public RemoteConfig(Context context, RemoteConfigListener listener) {
        m_context = context;
        this.m_listener = listener;
    }

    public RemoteConfig() {
    }

    public void begin() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<RemoteConfigModel> call = apiInterface.getPreferences();
        call.enqueue(new Callback<RemoteConfigModel>() {
            @Override
            public void onResponse(Call<RemoteConfigModel> call, Response<RemoteConfigModel> response) {
                String value = response.body().getVersionName();
                Log.d("MYTAG", "Currently user online are: " + value);
                m_rcModel = response.body();
                m_listener.onRemoteConfigArrive(RemoteConfig.this);
            }

            @Override
            public void onFailure(Call<RemoteConfigModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    public boolean versionValid() {
        boolean result = false;
        String versionName = "";
        try {
            PackageInfo pInfo = m_context.getPackageManager().getPackageInfo(m_context.getPackageName(), 0);
            versionName = pInfo.versionName;
            result = versionName.equals(m_rcModel.getVersionName());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean forceUpdate() {
        return m_rcModel.getForceUpdate();
    }

    public Ads.Adstype getAdsType() {
        final int i = m_rcModel.getAdsType();
        Ads.Adstype adsType = Ads.Adstype.values()[i];
        return adsType;
    }

    public String updateUrl() {
        return m_rcModel.getUpdateUrl();
    }

    public int initialCategoryTab() {
        return m_rcModel.getInitialCategoryTab();
    }

    public boolean showCategory_HOME() {
        return m_rcModel.getCatHome().getShow();
    }

    public boolean showCategory_NEWEST() {
        return m_rcModel.getCatNewest().getShow();
    }

    public boolean showCategory_LIVE() {
        return m_rcModel.getCatLive().getShow();
    }

    public boolean showCategory_POPULAR() {
        return m_rcModel.getCatPopular().getShow();
    }

    public boolean showCategory_MORE_USTADZ() {
        return m_rcModel.getCatMoreUstadz().getShow();
    }

    public boolean showCategory_EDITOR_CHOICE() {
        return m_rcModel.getCatEditorChoice().getShow();
    }

    public boolean showCategory_SEARCH() {
        return m_rcModel.getCatSearch().getShow();
    }

    public String getServer() {
        List<String> srvList = m_rcModel.getSrvList();
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(m_context);
        String str = SP.getString(Cfg.SERVER_INDEX, "0");
        int idx = Integer.parseInt(str);
        if (idx < 0 || idx >= srvList.size()) {
            idx = 0;
        }
        return srvList.get(idx);
    }

    public int imageSliderDuration() {
        return m_rcModel.getImageSliderDuration();
    }

    public boolean imageShowDescription() {
        return m_rcModel.getImageSliderShowDescription();
    }

    public String youtubeThumbnail() {
        return m_rcModel.getYoutubeThumbnail();
    }

    public RemoteConfigModel getModel() {
        return m_rcModel;
    }

    // 2
    static class ApiClient {

        public static final String BASE_URL = Cfg.REMOTE_CONFIG_SERVER + "/app/UstadzSomadStreaming/";   // JANGAN LUPA SLASH diakhir
        private static Retrofit retrofit = null;

        public static Retrofit getClient() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }

    }

    // 3
    interface ApiInterface {

        @GET("preferences.json")
            // Pakai s preferences.json
        Call<RemoteConfigModel> getPreferences();

        // contoh
        @GET("preferences.json")
        Call<RemoteConfigModel> getOnlineCount(@Query("direction") String direction);

        // contoh
        @GET("movie/{id}")
        Call<RemoteConfigModel> x_getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    }

    // 4
    public class RemoteConfigModel {

        private String versionName;
        private Boolean forceUpdate;
        private String updateUrl;
        private Integer adsType;
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
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        private String getVersionName() {
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

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

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

    public interface RemoteConfigListener {
        void onRemoteConfigArrive(RemoteConfig remoteConfig);
    }

}



