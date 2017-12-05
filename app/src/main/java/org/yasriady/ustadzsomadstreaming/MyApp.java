package org.yasriady.ustadzsomadstreaming;

import android.content.Context;

import org.yasriady.ustadzsomadstreaming.BaseClasses.ApplicationBase;
import org.yasriady.ustadzsomadstreaming.Login.User;
import org.yasriady.ustadzsomadstreaming.Utility.RemoteConfig.RemoteConfig;
//import org.yasriady.ustadzsomadstreaming.NOT_USED.RemoteConfig;

/**
 * Created by dedy on 11/14/17.
 */

public class MyApp extends ApplicationBase {

    private static MyApp m_instance;
    private MyDB m_db;
    private MyPref2 m_pref;
    protected RemoteConfig m_rc;
    private User m_user;

    private User.OnUserLoggedInListener m_onUserLoggedInListener;

    public MyApp() {
        //Permission1.setPermission(this, Cfg.PERMISSIONS);  // TAK BISA
        m_instance = this;
        //m_db = new MyDB(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MyApp getInstance() {
        return m_instance;
    }

    public MyDB getDb() {
        if (m_db == null) {
            m_db = new MyDB(this);
        }
        return m_db;
    }

    public MyPref2 getPref(Context context) {

        MainActivity mainActivity = (MainActivity) context;
        if (m_pref == null || mainActivity.isFinishing()) {
            m_pref = new MyPref2(context, getDb());
        }

        return m_pref;
    }

// x_ TAK BOLEH DIGUNAKAN PADA class Application
//    @Override
//    public void onTerminate() {
//        super.onTerminate();
//    }

    public RemoteConfig getRc() {
        return m_rc;
    }

    public void setRc(RemoteConfig rc) {
        this.m_rc = rc;
    }

    public static Context getAppContext() {
        return MyApp.getAppContext();
    }

    public void setUser(User user) {
        m_user = user;
    }

    public User getUser() {
        return m_user;
    }

    public String getRole() {
        final String role = m_user.getRole();
        return role;
    }

    public void setOnUserLoggedInListener(User.OnUserLoggedInListener listener) {
        m_onUserLoggedInListener = listener;
    }

    public User.OnUserLoggedInListener getOnUserLoggedInListener() {
        return m_onUserLoggedInListener;
    }

}
