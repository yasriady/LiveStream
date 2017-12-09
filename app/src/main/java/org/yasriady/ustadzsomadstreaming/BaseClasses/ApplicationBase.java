package org.yasriady.ustadzsomadstreaming.BaseClasses;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.yasriady.ustadzsomadstreaming.Utility.MySnackbar;
import org.yasriady.ustadzsomadstreaming.Utility.MyToast;

/**
 * Created by dedy on 11/8/17.
 */

public class ApplicationBase extends Application {

    public void toast(String message) {
        MyToast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //public void toast(Context context, String message) {
    //    MyToast.makeText(context, message, Toast.LENGTH_LONG).show();
    //}

    public void snackbar(Context context, String message) {
        //MainActivity mainActivity = (MainActivity) context;
        //CoordinatorLayout coordinatorLayout = mainActivity.findViewById(R.id.coordinatorLayout);
        //View view = mainActivity.findViewById(R.id.coordinatorLayout);
        //Snackbar snackbar = Snackbar.make(/*coordinatorLayout*/view, message, Snackbar.LENGTH_LONG);

        AppCompatActivity activity= (AppCompatActivity) context;
        View view = activity.findViewById(android.R.id.content);
        MySnackbar snackbar = MySnackbar.make(/*coordinatorLayout*/view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    // SharedPreference :: begin ---------------------------------------------------------------

    private final String MY_PREFS_NAME = "MyPrefsFile";

    public SharedPreferences sharedPrefs() {
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

    public int getSharedPrefInt(final String prefName, int var2) {
        int result = sharedPrefs().getInt(prefName, var2);
        return result;
    }

    public void setSharedPrefInt(String prefName, int value) {
        SharedPreferences.Editor editor = sharedPrefsEditor();
        editor.putInt(prefName, value);
        editor.apply();
    }


    // SharedPreferences :: end ----------------------------------------------------------------

}
