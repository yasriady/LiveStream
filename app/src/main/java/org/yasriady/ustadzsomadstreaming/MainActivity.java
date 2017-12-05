package org.yasriady.ustadzsomadstreaming;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import org.yasriady.ustadzsomadstreaming.Ads.Ads;
import org.yasriady.ustadzsomadstreaming.Category.EditorChoiceFragment;
import org.yasriady.ustadzsomadstreaming.Category.LiveFragment;
import org.yasriady.ustadzsomadstreaming.Category.MoreUstadzFragment;
import org.yasriady.ustadzsomadstreaming.Category.NOT_USED.CustomViewPager;
import org.yasriady.ustadzsomadstreaming.Category.NOT_USED.MyPagerAdapter;
import org.yasriady.ustadzsomadstreaming.Category.NewestFragment;
import org.yasriady.ustadzsomadstreaming.Category.OnFragmentInteractionListener;
import org.yasriady.ustadzsomadstreaming.Category.PopularFragment;
import org.yasriady.ustadzsomadstreaming.Category.RecyclerView.VideosAdapter;
import org.yasriady.ustadzsomadstreaming.Category.SearchFragment;
import org.yasriady.ustadzsomadstreaming.Content.SlidingTabLayout;
import org.yasriady.ustadzsomadstreaming.Login.User;
import org.yasriady.ustadzsomadstreaming.Model.Model4.VideoModel4;
import org.yasriady.ustadzsomadstreaming.OnlineCount.OnlineCount;
import org.yasriady.ustadzsomadstreaming.Player.FacebookFragment;
import org.yasriady.ustadzsomadstreaming.Player.Intro.IntroFragment;
import org.yasriady.ustadzsomadstreaming.Player.YoutubeFragment;
import org.yasriady.ustadzsomadstreaming.Utility.MyImageButton;
import org.yasriady.ustadzsomadstreaming.Utility.Network;
import org.yasriady.ustadzsomadstreaming.Utility.PermissionsUtils;
import org.yasriady.ustadzsomadstreaming.Utility.RemoteConfig.RemoteConfig;
import org.yasriady.ustadzsomadstreaming.Utility.Statusbar;

//import org.yasriady.ustadzsomadstreaming.NOT_USED.RemoteConfig;

// Thumbnail youtube is here https://stackoverflow.com/a/2068371/3626789
// https://img.youtube.com/vi/<insert-youtube-video-id-here>/hqdefault.jpg
public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener,
        VideosAdapter.OnItemClickListener, IntroFragment.OnIntroFragmentLoadedListener, PermissionsUtils.OnPermissionListener {

    private Button m_btnPlayFacebook, m_btnPlayYoutube;
    private YoutubeFragment m_youtubeFragment;
    private FacebookFragment m_facebookFragment;

    private CustomViewPager m_viewPager;
    private MyPagerAdapter m_pagerAdapter;
    private SparseArray<Fragment> m_pages = new SparseArray<>();
    private SlidingTabLayout m_tab;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    private static final int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_WIFI_STATE};

    //private Permission2 m_permission;
    private PermissionsUtils m_permission;

    private String m_serverAddress;
    private boolean m_showBanner;
    private Ads m_ads;
    //private String m_introUrl;

    private RemoteConfig m_rc;
    private Statusbar m_statusbar;
    private MyImageButton m_btnMore;
    private OnlineCount m_onlineCount;
    private User m_user;
    private Drawable m_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_background = getWindow().getDecorView().getBackground();
        showView(false);

        // End application if internet not available
        if (!Network.isOnline(this)) {
            MyApp.getInstance().toast("Internet connection is not available...");
            finish();
        }
        //MyApp.getInstance().toast(this, "Internet connection available...");
        MyApp.getInstance().snackbar (this, "Internet connection available...");
        m_onlineCount = new OnlineCount();

        // TODO 1
        checkPermission();
    }

    private void checkPermission() {
        m_permission = new PermissionsUtils(this);
        boolean res = m_permission.requestPermissions1();   // jika permission Denied maka direquest pada class PermissionsUtils
        int permission;
        if (res) {
            //Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
            permission = PackageManager.PERMISSION_GRANTED;
        } else {
            //Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            permission = PackageManager.PERMISSION_DENIED;
        }
        // TODO 2
        onPermissionListener(permission);
    }

    @Override
    public void onPermissionListener(int permission) {
        // TODO 3
        switch (permission) {
            case PackageManager.PERMISSION_GRANTED:
                //Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                onPermissionGranted();
                break;
            case PackageManager.PERMISSION_DENIED:
                onPermissionDenied();
                break;
        }
    }

    // TODO : sequence 4
    private void onPermissionGranted() {

        m_user = new User(this);
        MyApp.getInstance().setUser(m_user);

        m_statusbar = findViewById(R.id.statusbar);
        m_statusbar.setVisibility(View.GONE);

        m_btnMore = findViewById(R.id.btnMore);
        m_btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnMore();
            }
        });

        MyRemoteConfigListener listener = new MyRemoteConfigListener();
        //m_rc = new RemoteConfig(listener);
        //m_rc.begin();
        (new RemoteConfig(this, listener)).begin();

    }

    private void onPermissionDenied() {
        Toast.makeText(this, "Insufficient permission, please restart application!", Toast.LENGTH_LONG).show();
    }

//    private void begin_1_OK() { // No longer used
//
//        showView(false);
//        m_onlineCount = new OnlineCount();
//
//        m_user = new User(this);
//        MyApp.getInstance().setUser(m_user);
//
//        //m_permission = new Permission2(this);
//        //m_permission.checkPermission();
//
//        MyRemoteConfigListener listener = new MyRemoteConfigListener();
//        //m_rc = new RemoteConfig(listener);
//        //m_rc.begin();
//        (new RemoteConfig(this, listener)).begin();
//
//        m_statusbar = findViewById(R.id.statusbar);
//        m_statusbar.setVisibility(View.GONE);
//
//        m_btnMore = findViewById(R.id.btnMore);
//        m_btnMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBtnMore();
//            }
//        });
//
//    }


    private void showView(final boolean show) {
        CoordinatorLayout layout = findViewById(R.id.coordinatorLayout);
        int background, view;
        if (show) {
            // Restore background to normal one
            //background = R.drawable.background_normal;
            getWindow().setBackgroundDrawable(m_background);
            view = View.VISIBLE;
        } else {
            // Set splash background
            background = R.drawable.background_splash;
            getWindow().setBackgroundDrawableResource(background);
            view = View.INVISIBLE;
        }
        //getWindow().setBackgroundDrawableResource(background);
        layout.setVisibility(view);
    }

    private void onBtnMore() {

        if (!m_user.isLoggedIn()) {  // User not login yet
            showLogin();
        } else {                    // User already logging in, show preferences
            showPreference();
        }

        //m_user.test();//
    }

    private void showLogin() {
        MyApp.getInstance().setOnUserLoggedInListener(new MyOnUserLoggedIn()); // listener ditumpangkan dulu
        m_user.login(new MyOnUserLoggedIn()/*this*/);
    }

    private void showPreference() {

//        MyPref2 pref = MyApp.getInstance().getPref(this);
//        pref.show(new PrefDialog.DismissHandler() {
//            @Override
//            public void onDialogDismissed() {
//                onPreferenceChanged();
//            }
//        });

        Intent i = new Intent(MainActivity.this, MyPrefrencesActivity.class);
        //startActivity(i);
        startActivityForResult(i, Cfg.RC_CHANGE_PREFERENCE);

    }

    private void toggleDevelMode() {
        boolean bVal = MyApp.getInstance().getSharedPrefBoolean(Cfg.DEVELOPMENT_MODE, true);
        boolean bDevelMode = !bVal;
        MyApp.getInstance().setSharedPrefBoolean(Cfg.DEVELOPMENT_MODE, bDevelMode);
        //setDevelMode();
    }

    // x_
    private void setDevelMode() {

        MyPref2 pref = MyApp.getInstance().getPref(this);

        boolean develMode = pref.get(Cfg.DEVELOPMENT_MODE, true);//MyApp.getInstance().getSharedPrefBoolean(Cfg.DEVELOPMENT_MODE, true);
        if (develMode) {
            m_btnMore.setBackgroundColor(getResources().getColor(R.color.red));
        } else {
            m_btnMore.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

//    @Override
//    public void onItemClicked(View view) {
//
//        //TextView tvChannel = view.findViewById(R.id.channel);
//        //TextView tvVideoId = view.findViewById(R.id.video_id);
//        //final String pageName = String.valueOf(tvChannel.getText());
//        //final String videoId = String.valueOf(tvVideoId.getText());
//
//        VideosAdapter.VideoHolder vh = (VideosAdapter.VideoHolder) view;
//        VideoModel4 videoModel =
//
//        playYoutube(pageName, videoId);
//
//    }

    @Override
    public void onItemClicked(VideosAdapter.VideoHolder videoHolder) {

        VideoModel4 videoModel = videoHolder.m_videoModel;
        final String provider = videoModel.getProvider();

        final String pageName = String.valueOf(videoModel.getOwnerName());
        final String videoId = String.valueOf(videoModel.getVideoId());

        switch (provider) {
            case "facebook":
                playFacebook(pageName, videoId);
                break;
            case "youtube":
                playYoutube(pageName, videoId);
                break;
            case "instagram":
                //playInstagram(pageName, videoId);
                break;
            default:
        }

    }

    @Override
    public void onIntroFragmentLoaded() {
        //MyApp.getInstance().toast("InfroLoaded()");

    }

    class MyRemoteConfigListener implements RemoteConfig.RemoteConfigListener {
        @Override
        public void onRemoteConfigArrive(RemoteConfig remoteConfig) {
            m_rc = remoteConfig;
            MyApp.getInstance().setRc(m_rc);
            begin();
        }
    }

    private void begin() {

        if (!m_rc.versionValid()) {
            alertToUpdate();
        } else {
            mulai();
        }

    }

    private void alertToUpdate() {

        //AlertDialog dialog = new AlertDialog.Builder(this)
        //        .setTitle("New version available")
        //        .setMessage("Please, update application to new version to continue.")
        //        .setPositiveButton("Update",
        //                new DialogInterface.OnClickListener() {
        //                    @Override
        //                    public void onClick(DialogInterface dialog, int which) {
        //                        //redirectStore(updateUrl);
        //                        updateFromStore();
        //                        finish();
        //                    }
        //                }).setNegativeButton("No, thanks",
        //                new DialogInterface.OnClickListener() {
        //                    @Override
        //                    public void onClick(DialogInterface dialog, int which) {
        //                        if (m_rc.forceUpdate()) {
        //                            finish();
        //                        } else {
        //                            mulai();
        //                        }
        //                    }
        //                }).create();
        //
        //dialog.show();

        AlertDialog dlg = new AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update application to new version to continue.")
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //redirectStore(updateUrl);
                                updateFromStore();
                                finish();
                            }
                        }).create();

        if (!m_rc.forceUpdate()) {
            dlg.setButton(DialogInterface.BUTTON_NEGATIVE, "No, thanks",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (m_rc.forceUpdate()) {
                                finish();
                            } else {
                                mulai();
                            }
                        }
                    });
        }

        dlg.show();

    }

    private void mulai() {
        Ads.Adstype adsType = m_rc.getAdsType();
        if (adsType != Ads.Adstype.GONE) {
            m_ads = findViewById(R.id.myAds);
            m_ads.begin();
        }
        //m_btnPlayYoutube.performClick();
        //playIntro(m_rc.getIntroUrl());

        createTestButton();
        createCategory();
        playIntro("");

        showView(true);

        // Joint firebase cloud messaging
        FirebaseMessaging.getInstance().subscribeToTopic("LiveStreamUser");

    }

    private void updateFromStore() {

        if (m_rc.updateUrl().isEmpty()) {
            redirectToStore();
        } else {
            redirectToStore(m_rc.updateUrl());
        }

    }

    private void redirectToStore() {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    private void redirectToStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //MyApp.getInstance().snackbar(this, "test");

        switch (requestCode) {

            case Cfg.RC_CHANGE_PREFERENCE:
                if (resultCode == MyPrefrencesActivity.RESULT_CODE_PREFERENCES_CHANGED) {
                    restartActivity(MainActivity.this);
                }
                break;

            case Cfg.RC_IM_LOGIN:
                //User user = (User) data.getParcelableExtra("userClass");
                Log.d(Cfg.MYTAG, "onActivityResult()");
                break;

        }
//        if (requestCode == REQUEST_PREFERENCE) {
//            if (resultCode == MyPrefrencesActivity.RESULT_CODE_PREFERENCES_CHANGED) {
//                restartActivity(MainActivity.this);
//            }
//        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        m_permission.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void onBtnPlayFacebookClicked() {
        //https://web.facebook.com/pejuangsubuhtbh/videos/468460280216884/
        //https://web.facebook.com/UstadzAbdulSomad/videos/1970418406503823/
        //https://web.facebook.com/buyayahya.albahjah/videos/1523326724387024/
        final String pageName = "buyayahya.albahjah";
        final String videoId = "1523326724387024";
        playFacebook(pageName, videoId);
    }

    private void onBtnPlayYoutubeClicked() {
        /*
        https://www.youtube.com/watch?v=let-NLDJ_fk
        https://www.youtube.com/watch?v=dr3c949eLgo
        https://www.youtube.com/watch?v=oYvpXPqOM2Q
        https://www.youtube.com/watch?v=LXdbgaP_-00&t=20s
        https://www.youtube.com/watch?v=lhA8ayqibK0
        https://www.youtube.com/watch?v=Q0tnOtJn2KY
        https://www.youtube.com/watch?v=RHF5xvW4ZbU
        https://www.youtube.com/watch?v=let-NLDJ_fk
        */

        final String pageName = "";
        final String videoId = "oYvpXPqOM2Q"; //WwafGRDNzas
        playYoutube(pageName, videoId);
    }

    private void playIntro(final String url) {
        IntroFragment fragment = IntroFragment.newInstance("url", "");
        fragment.setRemoteConfig(m_rc);
        loadFragment(fragment);
    }

    private void playYoutube(final String pageName, final String videoId) {
        YoutubeFragment fragment = YoutubeFragment.newInstance(pageName, videoId);
        loadFragment(fragment);
        //if (m_youtubeFragment == null) {
        //    m_youtubeFragment = YoutubeFragment.newInstance(pageName, videoId);
        //}
        //loadFragment(m_youtubeFragment);
    }

    private void playFacebook(final String pageName, final String videoId) {
        FacebookFragment fragment = FacebookFragment.newInstance(pageName, videoId);
        loadFragment(fragment);
        //if (m_facebookFragment == null) {
        //    m_facebookFragment = FacebookFragment.newInstance(pageName, videoId);
        //}
        //loadFragment(m_youtubeFragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.playerFragment, fragment, "FRAGMENT_TAG");
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {

    }

    private void createCategory() {

        m_viewPager = findViewById(R.id.viewPager);
        m_pagerAdapter = new MyPagerAdapter(getSupportFragmentManager() /*getFragmentManager()*/);
        setPages();
        m_pagerAdapter.setPages(m_pages);
        m_viewPager.setAdapter(m_pagerAdapter);
        //m_viewPager.setOffscreenPageLimit(3);
        m_viewPager.setCurrentItem(m_rc.initialCategoryTab());
        //viewPager.addOnPageChangeListener(this);
        m_tab = findViewById(R.id.tab);
        m_tab.setViewPager(m_viewPager);

        //MyPageChangeListener myPageChangeListener = new MyPageChangeListener();
        //mSlidingTabLayout.setOnPageChangeListener(myPageChangeListener);

    }

    private void setPages() {

        int i = m_pages.size();

        if (m_rc.showCategory_HOME()) {
            m_pages.put(i++, NewestFragment.newInstance( m_rc.getModel().getCatHome().getLabel()  /*getResources().getString(R.string.home)*/, ""));
        }
        if (m_rc.showCategory_NEWEST()) {
            m_pages.put(i++, NewestFragment.newInstance( m_rc.getModel().getCatNewest().getLabel() /*getResources().getString(R.string.newest)*/, ""));
        }
        if (m_rc.showCategory_LIVE()) {
            m_pages.put(i++, LiveFragment.newInstance( m_rc.getModel().getCatLive().getLabel() /*getResources().getString(R.string.live_now)*/, Cfg.Category.LIVE));
        }
        if (m_rc.showCategory_POPULAR()) {
            m_pages.put(i++, PopularFragment.newInstance( m_rc.getModel().getCatPopular().getLabel()   /*getResources().getString(R.string.popular)*/, ""));
        }
        if (m_rc.showCategory_MORE_USTADZ()) {
            m_pages.put(i++, MoreUstadzFragment.newInstance(  m_rc.getModel().getCatMoreUstadz().getLabel()   /*getResources().getString(R.string.more_ustadz)*/, ""));
        }
        if (m_rc.showCategory_EDITOR_CHOICE()) {
            m_pages.put(i++, EditorChoiceFragment.newInstance(m_rc.getModel().getCatEditorChoice().getLabel()   /*getResources().getString(R.string.editor_choice)*/, ""));
        }
        if (m_rc.showCategory_SEARCH()) {
            m_pages.put(i++, SearchFragment.newInstance( m_rc.getModel().getCatSearch().getLabel()  /*getResources().getString(R.string.search)*/, ""));
        }

    }

    private void createTestButton() {
        m_btnPlayYoutube = findViewById(R.id.btnPlayYoutube);
        m_btnPlayYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnPlayYoutubeClicked();
            }
        });
        m_btnPlayFacebook = findViewById(R.id.btnPlayFacebook);
        m_btnPlayFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnPlayFacebookClicked();
            }
        });
    }

    private void stbAddText(String text) {
        m_statusbar.setText(text);
    }

    @Override
    protected void onDestroy() {
        if (m_ads != null) m_ads.onDestroy();
        m_onlineCount.count("down");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (m_ads != null) m_ads.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_onlineCount.count("up");
        if (m_ads != null) m_ads.onResume();
    }

    private void onPreferenceChanged() {

        // Alert, please restart application due to preference altered !
        //new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText(getString(R.string.alert_prefence_changed)).show();

        // Restart application
        restartActivity(MainActivity.this);
    }

    private void restartActivity(Activity activity) {
//        if (Build.VERSION.SDK_INT >= 11) {
//            activity.recreate();
//        } else {
//            activity.finish();
//            activity.startActivity(activity.getIntent());
//        }
        // KARENA SERING crash, maka diganti menjadi:
        // https://stackoverflow.com/a/15564838/3626789
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

    class MyOnUserLoggedIn implements User.OnUserLoggedInListener {
        @Override
        public void onUserLoggedIn(User user) {
            //Toast.makeText(getBaseContext(), "MyOnUserLoggedIn implements User.OnUserLoggedIn ", Toast.LENGTH_LONG).show();
            onUserLoggedIn2(user);
        }
    }

    private void onUserLoggedIn2(User user) {
        m_user = user;

        String str;

        str = user.name();
        Log.d(Cfg.MYTAG, str);

        str = m_user.name();
        Log.d(Cfg.MYTAG, str);

    }

    /* Ddy: This class intended to prepare of application launching. Some task were performed in this class are:
    - Check and request permission
    - Get RemoteConfig
    - Check valid version, redirect to playstore for update
     */
    class PrepareApplicationToBegin {

    }

}

