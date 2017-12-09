package org.yasriady.ustadzsomadstreaming.Utility;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
    private LayoutInflater m_layoutInflater;
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

    public void show_OK() {

        Snackbar snackbar = null;
        if (m_text.length() > 0) {
            snackbar = Snackbar.make(m_view, m_text, m_duration);
        } else {
            snackbar = Snackbar.make(m_view, m_resId, m_duration);
        }

        //TextView tv = snackbar.getView().findViewById(android.R.id.message);  // TAK BISA
        //tv.setTextColor(Color.WHITE);                                         // TAK BISA
        //snackbar.getView().setBackgroundColor(Color.RED);
        //snackbar.getView().setBackground(m_context.getDrawable(R.drawable.background_splash));
        snackbar.getView().setBackground(m_view.getContext().getResources().getDrawable(R.drawable.background_splash));

        snackbar.show();
    }

    public void show() {

        // Create the Snackbar
        Snackbar snackbar = null;
        if (m_text.length() > 0) {
            snackbar = Snackbar.make(m_view, m_text, m_duration);
        } else {
            snackbar = Snackbar.make(m_view, m_resId, m_duration);
        }

        // Ini ok
        //--------------
        //// Get the Snackbar's layout view
        //Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        //// Hide the text
        //TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        //textView.setVisibility(View.INVISIBLE);
        //
        //// Inflate our custom view
        //m_layoutInflater = (LayoutInflater) m_view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View snackView = m_layoutInflater .inflate(R.layout.my_snackbar, null);
        //// Configure the view
        //ImageView imageView = (ImageView) snackView.findViewById(R.id.image);
        ////Bitmap image = BitmapFactory.
        ////imageView.setImageBitmap(image);
        //TextView textViewTop = (TextView) snackView.findViewById(R.id.text);
        //textViewTop.setText(m_text);
        //textViewTop.setTextColor(Color.WHITE);
        //// Add the view to the Snackbar's layout
        //layout.addView(snackView, 0);

        // Show the Snackbar
        snackbar.show();

    }


}

// Snackbar custom layout, https://stackoverflow.com/a/33441214/3626789

//    // Create the Snackbar
//    Snackbar snackbar = Snackbar.make(containerLayout, "", Snackbar.LENGTH_LONG);
//    // Get the Snackbar's layout view
//    Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
//    // Hide the text
//    TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
//textView.setVisibility(View.INVISIBLE);
//
//// Inflate our custom view
//        View snackView = mInflater.inflate(R.layout.my_snackbar, null);
//// Configure the view
//        ImageView imageView = (ImageView) snackView.findViewById(R.id.image);
//        imageView.setImageBitmap(image);
//        TextView textViewTop = (TextView) snackView.findViewById(R.id.text);
//        textViewTop.setText(text);
//        textViewTop.setTextColor(Color.WHITE);
//
//// Add the view to the Snackbar's layout
//        layout.addView(snackView, 0);
//// Show the Snackbar
//        snackbar.show();