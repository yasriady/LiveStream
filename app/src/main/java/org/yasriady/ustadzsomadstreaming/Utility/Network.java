package org.yasriady.ustadzsomadstreaming.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by dedy on 12/5/17.
 * Purpose:
 */

public class Network {

    public Network(){}

    public static boolean isOnline(Context context) {

        boolean result = false;

        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
        if (netInfo != null) {
            if (netInfo.isConnected()) {
                // Internet Available
                result = true;
            }else {
                //No internet
                result = false;
            }
        } else {
            //No internet
            result = false;
        }
        return result;
    }

}
