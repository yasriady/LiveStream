package org.yasriady.livestream.Login;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.yasriady.livestream.Category.OnFragmentInteractionListener;
import org.yasriady.livestream.Login.Facebook.FacebookLoginFragment;
import org.yasriady.livestream.R;

public class LoginActivity extends AppCompatActivity /*FragmentActivity*/   implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        begin();
    }

    private void begin() {

        // load fragment
        Base fragment = FacebookLoginFragment.newInstance("", "");
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.loginFragmentHere, fragment);
        fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fm.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
