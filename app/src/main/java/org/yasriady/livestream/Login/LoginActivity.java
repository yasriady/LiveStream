package org.yasriady.livestream.Login;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.yasriady.livestream.Category.OnFragmentInteractionListener;
import org.yasriady.livestream.Login.Facebook.FacebookLoginFragment;
import org.yasriady.livestream.MyApp;
import org.yasriady.livestream.R;

public class LoginActivity extends AppCompatActivity /*FragmentActivity*/ implements OnFragmentInteractionListener {

    OnFragmentInteractionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        begin();
    }

    private void begin() {

        // ambil kembali extraData yang berisi object class User
        Intent intent = getIntent();
        User user = (User) intent.getParcelableExtra("userClass");  //intent.getSerializableExtra("userClass");
        //String str = this.getCallingActivity().getClassName();

        if (user instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) user;
        } else {
            throw new RuntimeException(user.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        // load fragment
        Base fragment = FacebookLoginFragment.newInstance("", "");
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.loginFragmentHere, fragment);
        fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fm.commit();

    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        FacebookLoginFragment f = null;
        if (fragment instanceof FacebookLoginFragment) {
            //showMessage("FacebookLoginFragment ok()!");
            f = (FacebookLoginFragment) fragment;
            mListener.onFragmentInteraction(f);
            finish();       // Ddy: Stop this LoginActivity
        }
    }

    private void showMessage(String message) {
        MyApp.getInstance().snackbar(this, message);
    }
}
