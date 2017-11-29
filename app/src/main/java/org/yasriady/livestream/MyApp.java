package org.yasriady.livestream;

import android.content.Context;

import org.yasriady.livestream.BaseClasses.ApplicationBase;
import org.yasriady.livestream.Utility.RemoteConfig;

/**
 * Created by dedy on 11/14/17.
 */

public class MyApp extends ApplicationBase {

    private static MyApp m_instance;
    private MyDB m_db;
    private MyPref m_pref;
    protected RemoteConfig m_rc;

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

    public MyPref getPref(/*final String keyPrefix*/) {
        if (m_pref == null) {
            m_pref = new MyPref(m_db/*, keyPrefix*/);
        }
        return m_pref;
    }

// x_
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

}
