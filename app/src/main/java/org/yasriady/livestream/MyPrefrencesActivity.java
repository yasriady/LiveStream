package org.yasriady.livestream;

import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// Ddy: https://alvinalexander.com/android/android-tutorial-preferencescreen-preferenceactivity-preferencefragment
// https://stackoverflow.com/a/3026922/3626789
public class MyPrefrencesActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    public static final int RESULT_CODE_PREFERENCES_CHANGED = 2434;
    private PreferenceScreen m_screenPreference;
    private PreferenceCategory m_adminPreference, m_generalPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        //setContentView(R.layout.main);
        //getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        test();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        setResult(RESULT_CODE_PREFERENCES_CHANGED);
        finish();
        startActivity(getIntent());
        return true;
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

    private void test() {

        m_screenPreference = (PreferenceScreen) findPreference("screenPreference");
        m_adminPreference = (PreferenceCategory) findPreference("catAdministrator");


        final String role = "admin";

        switch (role) {

            case "root":
                break;
            case "admin":
                break;
            default:
                m_screenPreference.removePreference(m_adminPreference);

        }



    }

}
