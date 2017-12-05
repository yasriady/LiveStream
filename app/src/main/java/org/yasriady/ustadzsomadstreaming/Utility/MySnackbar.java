package org.yasriady.ustadzsomadstreaming.Utility;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.yasriady.ustadzsomadstreaming.R;

/**
 * Created by dedy on 12/5/17.
 * Purpose:
 * Source : https://stackoverflow.com/a/9432923/3626789
 * Toasty : https://github.com/GrenderG/Toasty
 */

public class MySnackbar {

    private View m_view;

    private CharSequence m_text = "";
    private int m_resId;
    private int m_duration;

    public MySnackbar() {
    }


    public static MySnackbar make(@NonNull View view, @NonNull CharSequence text, @BaseTransientBottomBar.Duration int duration) {
        MySnackbar mySnackbar = new MySnackbar();
        mySnackbar.m_view = view;
        mySnackbar.m_text = text;
        mySnackbar.m_duration = duration;
        return mySnackbar;
    }

    public static MySnackbar make(@NonNull View view, @StringRes int resId, @BaseTransientBottomBar.Duration int duration) {
        MySnackbar mySnackbar = new MySnackbar();
        mySnackbar.m_view = view;
        mySnackbar.m_resId = resId;
        mySnackbar.m_duration = duration;
        return mySnackbar;
    }

    public void show() {

        Snackbar snackbar = null;
        if (m_text.length() > 0) {
            snackbar = Snackbar.make(m_view, m_text, m_duration);
        } else {
            snackbar = Snackbar.make(m_view, m_resId, m_duration);
        }

        TextView tv = snackbar.getView().findViewById(android.R.id.message);
        tv.setTextColor(Color.WHITE);
        //snackbar.getView().setBackgroundColor(Color.RED);
        //snackbar.getView().setBackground(m_context.getDrawable(R.drawable.background_splash));
        snackbar.getView().setBackground(m_view.getContext().getResources().getDrawable(R.drawable.background_splash));

        snackbar.show();

    }

}


//        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
//        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
//        v.setTextColor(Color.WHITE);
//        toast.getView().setBackgroundColor(Color.RED);
//        toast.show();
//        TextView v = toast.getView().findViewById(android.R.id.message);
//        v.setTextColor(Color.WHITE);
//        toast.getView().setBackgroundColor(Color.RED);
//toast.show();
//final int DURATION = Toast.LENGTH_LONG;
//Toast.makeText(getApplicationContext(), message, DURATION).show();
