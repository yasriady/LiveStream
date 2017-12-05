package org.yasriady.ustadzsomadstreaming.Ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.yasriady.ustadzsomadstreaming.Cfg;
import org.yasriady.ustadzsomadstreaming.MyApp;
import org.yasriady.ustadzsomadstreaming.R;

/**
 * Created by dedy on 11/22/17.
 */

public class Ads extends LinearLayout {

    private AdView m_adView;

    public static enum Adstype {

        GONE("gone", 0),
        ADMOB_BANNER("admobBanner", 1),
        ADMOB_INTERSTITIAL("admobInterstitial", 2),
        ADMOB_NATIVE("admobRewardedVideo", 3),
        DMOB_NATIVE("admobNative", 4),
        DDY_BANNER("ddyBanner", 5),
        DDY_RANDOM_BANNER("ddyRandomBanner", 6);

        private String stringValue;
        private int intValue;

        Adstype(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }

        public int toInt() {
            return intValue;
        }
    }

    public Ads(Context context) {
        super(context);
    }

    public Ads(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Ads(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public Ads(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    public void begin() {

        // 11-29 08:29:53.563 3686-3686/org.yasriady.livestream I/Ads:
        // Use AdRequest.Builder.addTestDevice("629A74973A0DBC5A96E944CA1C0AE432") to get test ads on this device.

        inflate(getContext(), R.layout.banner, this);
        setVisibility(View.VISIBLE);

        m_adView = findViewById(R.id.adView);
        m_adView.setAdListener(new MyAdMobListener());

        boolean bDevelMode;
        //bDevelMode = MyApp.getInstance().getPref(getContext()).get(Cfg.DEVELOPMENT_MODE, true);  //MyApp.getInstance().getSharedPrefBoolean(Cfg.DEVELOPMENT_MODE, true);
        // pindah menggunakan preference activity
        SharedPreferences SP =
                PreferenceManager.getDefaultSharedPreferences(getContext());
        bDevelMode = SP.getBoolean(Cfg.DEVELOPMENT_MODE, true);

        AdRequest adRequest = null;
        final String TESTDEVICESTR = Cfg.TEST_DEVICE_ID;  // "629A74973A0DBC5A96E944CA1C0AE432";// From my device Advance i5C

        //AdRequest adRequest = new AdRequest.Builder().build();
        if (bDevelMode) {   // Development mode
            adRequest = new AdRequest.Builder()
                    .addTestDevice(TESTDEVICESTR)
                    .build();
        } else {            // Production mode
            adRequest = new AdRequest.Builder()
                    .build();
        }

        String msg = "Ads displayed in production mode.";
        if (adRequest.isTestDevice(getContext())) {
            msg = "Ads displayed in development mode.";
        }
        MyApp.getInstance().snackbar(getContext(), msg);

        m_adView.loadAd(adRequest);
    }

    public void onDestroy() {
        if (m_adView != null) {
            m_adView.destroy();
        }
    }

    public void onPause() {
        if (m_adView != null) {
            m_adView.pause();
        }
    }

    public void onResume() {
        if (m_adView != null) {
            m_adView.resume();
        }
    }

    class MyAdMobListener extends AdListener {

        public MyAdMobListener() {
            super();
        }

        @Override
        public void onAdClosed() {
            //super.onAdClosed();
            showMessage("onAdClosed()");
        }

        @Override
        public void onAdFailedToLoad(int i) {
            //super.onAdFailedToLoad(i);
            showMessage("onAdFailedToLoad()");
        }

        @Override
        public void onAdLeftApplication() {
            //super.onAdLeftApplication();
            showMessage("onAdLeftApplication()");
        }

        @Override
        public void onAdOpened() {
            //super.onAdOpened();
            showMessage("onAdOpened()");
        }

        @Override
        public void onAdLoaded() {
            //super.onAdLoaded();
            showMessage("onAdLoaded()");
        }

        @Override
        public void onAdClicked() {
            //super.onAdClicked();
            showMessage("onAdClicked()");
        }

        @Override
        public void onAdImpression() {
            //super.onAdImpression();
            showMessage("onAdImpression()");
        }
    }

    private void showMessage(final String message) {
        //Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        //MyApp.getInstance().toast("Show the : " + message);
        //MyApp.getInstance().snackbar(getContext(), message);
    }

}
