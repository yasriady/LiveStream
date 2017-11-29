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

import org.yasriady.livestream.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dedy on 11/22/17.
 */

// http://imandroidguru.blogspot.co.id/2016/06/android-requesting-multiple-permission.html
public class Permission {

    public static final int PERMISSIONS_REQUEST = 123;
    private Context m_context;

    public Permission(Context context) {
        this.m_context = context;
    }

    public void checkPermission() {

        if (Build.VERSION.SDK_INT < 23) {
            //Do not need to check the permission
        } else {
            if (checkAndRequestPermissions()) {
                //If you have already permitted the permission
            }
        }
    }

    private boolean checkAndRequestPermissions() {

        int permissionINTERNET = ContextCompat.checkSelfPermission(m_context,
                Manifest.permission.INTERNET);
        int permissionACCESS_NETWORK_STATE = ContextCompat.checkSelfPermission(m_context,
                Manifest.permission.ACCESS_NETWORK_STATE);
        int permissionREAD_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(m_context,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(m_context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionINTERNET != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (permissionACCESS_NETWORK_STATE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        if (permissionREAD_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionWRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((MainActivity) m_context,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSIONS_REQUEST);
            return false;
        }

        return true;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        MainActivity mainActivity = (MainActivity) m_context;
        View view = mainActivity.getWindow().getDecorView().findViewById(android.R.id.content); // https://stackoverflow.com/a/5273508/3626789

        switch (requestCode) {
            case Permission.PERMISSIONS_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission Granted Successfully. Write working code here.
                    Snackbar.make(view, "Permission Granted, Now you can access location data.", Snackbar.LENGTH_LONG).show();
                } else {
                    //You did not accept the request can not use the functionality.
                    Snackbar.make(view, "Permission denied!", Snackbar.LENGTH_LONG).show();
                }
                break;
        }

    }

}
