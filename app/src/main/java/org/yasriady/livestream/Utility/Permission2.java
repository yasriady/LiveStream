package org.yasriady.livestream.Utility;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import org.yasriady.livestream.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dedy on 11/22/17.
 */

// http://imandroidguru.blogspot.co.id/2016/06/android-requesting-multiple-permission.html
public class Permission2 {

    private final static int REQUEST = 112;
    public static final int PERMISSIONS_REQUEST = 123;
    private Context m_context;

    public Permission2(Context context) {
        this.m_context = context;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        MainActivity mainActivity = (MainActivity) m_context;
        View view = mainActivity.getWindow().getDecorView().findViewById(android.R.id.content); // https://stackoverflow.com/a/5273508/3626789

        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                    Toast.makeText(m_context, "Permission granted!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(m_context, "The app was not allowed to write in your storage", Toast.LENGTH_LONG).show();
                }
            }
        }

        //        switch (requestCode) {
//            case Permission.PERMISSIONS_REQUEST:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //Permission Granted Successfully. Write working code here.
//                    Snackbar.make(view, "Permission Granted, Now you can access location data.", Snackbar.LENGTH_LONG).show();
//                } else {
//                    //You did not accept the request can not use the functionality.
//                    Snackbar.make(view, "Permission denied!", Snackbar.LENGTH_LONG).show();
//                }
//                break;
//        }

    }

    public void checkPermission() {

        // make permission, https://stackoverflow.com/a/43385945/3626789
        if (Build.VERSION.SDK_INT >= 23) {
            //String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE};
            if (!hasPermissions(m_context, PERMISSIONS)) {
                ActivityCompat.requestPermissions((MainActivity) m_context, PERMISSIONS, REQUEST);
            } else {
                //do here
            }
        } else {
            //do here
        }

    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


}
