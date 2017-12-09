package org.yasriady.ustadzsomadstreaming.Player;

import android.support.v4.app.Fragment;

import org.yasriady.ustadzsomadstreaming.Utility.RemoteConfig.RemoteConfig;


/**
 * Created by Dedy Yasriady on 11/25/17.
 * Additional notes:
 * - Need to check this url http://ableplayer.github.io/ableplayer/
 */

public class Base extends Fragment {

    protected RemoteConfig m_remoteConfig;

    public void setRemoteConfig(RemoteConfig remoteConfig) {
        this.m_remoteConfig = remoteConfig;
    }
}
