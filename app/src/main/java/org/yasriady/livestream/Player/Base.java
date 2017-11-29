package org.yasriady.livestream.Player;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import org.yasriady.livestream.Utility.RemoteConfig;


/**
 * Created by dedy on 11/25/17.
 */

public class Base extends Fragment {

    protected RemoteConfig m_remoteConfig;

    public void setRemoteConfig(RemoteConfig remoteConfig) {
        this.m_remoteConfig = remoteConfig;
    }
}
