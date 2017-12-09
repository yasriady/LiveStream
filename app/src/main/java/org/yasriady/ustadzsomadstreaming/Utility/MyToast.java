package org.yasriady.ustadzsomadstreaming.Utility;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import org.yasriady.ustadzsomadstreaming.R;

/**
 * Created by dedy on 12/5/17.
 * Purpose:
 * Source : https://stackoverflow.com/a/9432923/3626789
 * Toasty : https://github.com/GrenderG/Toasty
 */

public class MyToast {

    private Context m_context;
    //private Toast m_toast;

    private CharSequence m_text = "";
    private int m_resId;
    private int m_duration;

    public MyToast() {
    }

    public static MyToast makeText(Context context, CharSequence text, int duration) {
        MyToast myToast = new MyToast();
        myToast.m_context = context;
        myToast.m_text = text;
        myToast.m_duration = duration;
        return myToast;
    }

    public static MyToast makeText(Context context, int resId, int duration) {
        MyToast myToast = new MyToast();
        myToast.m_context = context;
        myToast.m_resId = resId;
        myToast.m_duration = duration;
        return myToast;
    }

    public void show() {

        Toast toast = null;
        if (m_text.length() > 0) {
            toast = Toast.makeText(m_context, m_text, m_duration);
        } else {
            toast = Toast.makeText(m_context, m_resId, m_duration);
        }

        // Ini ok
        //TextView tv = toast.getView().findViewById(android.R.id.message);
        //tv.setTextColor(Color.WHITE);
        ////toast.getView().setBackgroundColor(Color.RED);
        ////toast.getView().setBackground(m_context.getDrawable(R.drawable.background_splash));
        //toast.getView().setBackground(m_context.getResources() .getDrawable(R.drawable.background_splash) );

        toast.show();

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


// Toast with layout, https://stackoverflow.com/a/6687695/3626789

//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        android:id="@+id/toast_layout_root"
//        android:orientation="horizontal"
//        android:layout_width="fill_parent"
//        android:layout_height="fill_parent"
//        android:padding="10dp"
//        android:background="#DAAA"
//        >
//<ImageView android:id="@+id/image"
//        android:layout_width="wrap_content"
//        android:layout_height="fill_parent"
//        android:layout_marginRight="10dp"
//        />
//<TextView android:id="@+id/text"
//        android:layout_width="wrap_content"
//        android:layout_height="fill_parent"
//        android:textColor="#FFF"
//        />
//</LinearLayout>
//
//        -
//
//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.toast_layout,
//        (ViewGroup) findViewById(R.id.toast_layout_root));
//
//        ImageView image = (ImageView) layout.findViewById(R.id.image);
//        image.setImageResource(R.drawable.android);
//        TextView text = (TextView) layout.findViewById(R.id.text);
//        text.setText("Hello! This is a custom toast!");
//
//        Toast toast = new Toast(getApplicationContext());
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(layout);
//        toast.show();