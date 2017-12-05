package org.yasriady.ustadzsomadstreaming.Login;

import android.support.v4.app.Fragment;

import org.yasriady.ustadzsomadstreaming.Login.Utils.UserModel;
import org.yasriady.ustadzsomadstreaming.MyApp;

/**
 * Created by dedy on 12/1/17.
 */

public class Base extends Fragment {

    protected UserModel m_userModel;

    public Base() {
        m_userModel = new UserModel();
    }

    protected void showMessage(String message) {
        MyApp.getInstance().snackbar(getContext(), message);
    }

    public UserModel getUserModel() {
        return m_userModel;
    }

}
