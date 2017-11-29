package org.yasriady.livestream.BaseClasses;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import org.yasriady.livestream.MainActivity;
import org.yasriady.livestream.MyApp;
import org.yasriady.livestream.R;
import org.yasriady.livestream.Utility.RemoteConfig;

/**
 * Created by dedy on 11/8/17.
 */

public class ApplicationBase extends Application {

    public void toast(String message) {
        final int DURATION = Toast.LENGTH_LONG;
        Toast.makeText(getApplicationContext(), message, DURATION).show();
    }

    public void snackbar(Context context, String message) {
        MainActivity mainActivity = (MainActivity) context;
        CoordinatorLayout coordinatorLayout = mainActivity.findViewById(R.id.coordinatorLayout);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    // SharedPreference :: begin ---------------------------------------------------------------

    private final String MY_PREFS_NAME = "MyPrefsFile";

    private SharedPreferences sharedPrefs() {
        SharedPreferences sharedPrefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return sharedPrefs;
    }

    private SharedPreferences.Editor sharedPrefsEditor() {
        return sharedPrefs().edit();
    }

    public boolean getSharedPrefBoolean(final String prefName, boolean var2) {
        boolean result = sharedPrefs().getBoolean(prefName, var2);
        return result;
    }

    public void setSharedPrefBoolean(String prefName, boolean value) {
        SharedPreferences.Editor editor = sharedPrefsEditor();
        editor.putBoolean(prefName, value);
        editor.apply();
    }

    // SharedPreferences :: end ----------------------------------------------------------------

}