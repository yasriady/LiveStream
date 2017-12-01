package org.yasriady.livestream.Login;

import android.support.v4.app.Fragment;

import org.yasriady.livestream.Login.Utils.UserModel;
import org.yasriady.livestream.MyApp;

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

}
