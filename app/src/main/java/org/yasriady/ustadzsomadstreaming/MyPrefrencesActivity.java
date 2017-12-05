package org.yasriady.ustadzsomadstreaming;

import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.widget.LoginButton;

import org.yasriady.ustadzsomadstreaming.Login.User;

// Ddy: https://alvinalexander.com/android/android-tutorial-preferencescreen-preferenceactivity-preferencefragment
// https://stackoverflow.com/a/3026922/3626789
public class MyPrefrencesActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    public static final int RESULT_CODE_PREFERENCES_CHANGED = 2434;
    private PreferenceScreen m_screenPreference;
    private PreferenceCategory m_adminPreference, m_generalPreference;

    private LoginButton m_loginButton;
    //CallbackManager m_callbackManager;
    private AccessTokenTracker m_accessTokenTracker;
    private User m_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        setContentView(R.layout.main);
        //getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        begin();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
//        setResult(RESULT_CODE_PREFERENCES_CHANGED);
//        finish();
//        startActivity(getIntent());
        return true;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //m_callbackManager.onActivityResult(requestCode, resultCode, data);
//    }

    private void begin() {

        //m_callbackManager = CallbackManager.Factory.create();
        m_loginButton = findViewById(R.id.btnLogin);
        m_accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if( currentAccessToken==null )
                    // user logout
                    finish();
            }
        };

        setupUi();

//        m_loginButton.registerCallback(m_callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(Cfg.MYTAG, "onSuccess()");
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(Cfg.MYTAG, "onCancel()");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(Cfg.MYTAG, "onError()");
//            }
//        });


    }


//    public static class MyPreferenceFragment extends PreferenceFragment
//    {
//        @Override
//        public void onCreate(final Bundle savedInstanceState)
//        {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.preferences);
//        }
//    }

    private void setupUi() {

        m_screenPreference = (PreferenceScreen) findPreference("screenPreference");
        m_adminPreference = (PreferenceCategory) findPreference("catAdministrator");

        final String role = user().getRole();
        switch (role) {

            case "root":
                break;
            case "admin":
                break;
            default:
                m_screenPreference.removePreference(m_adminPreference);

        }
    }

    private User user() {
        m_user = MyApp.getInstance().getUser();
        return m_user;
    }

}
