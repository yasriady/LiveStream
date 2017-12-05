package org.yasriady.ustadzsomadstreaming.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

import org.yasriady.ustadzsomadstreaming.Category.OnFragmentInteractionListener;
import org.yasriady.ustadzsomadstreaming.Cfg;
import org.yasriady.ustadzsomadstreaming.Login.Facebook.FacebookLoginFragment;
import org.yasriady.ustadzsomadstreaming.Login.Utils.UserModel;
import org.yasriady.ustadzsomadstreaming.MainActivity;
import org.yasriady.ustadzsomadstreaming.MyApp;

/**
 * Created by dedy on 12/1/17.
 */

public class User implements OnFragmentInteractionListener, Parcelable {

    private UserModel m_userModel;
    private Profile m_profile;

    Context m_context;

    OnUserLoggedInListener mListener;

    public User(Context context) {
        m_context = context;
    }

    protected User(Parcel in) {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // ?
    public final String getRole() {

        String result = "guest";
        m_profile = Profile.getCurrentProfile();
        String userId = m_profile.getId();
        if (userId.equals("10214856387269005")) {
            result = "admin";
        }


//        if (m_userModel == null) {
//
//            m_userModel = new UserModel();
//            m_profile = Profile.getCurrentProfile();
//            str = m_profile.getId();
//
//        }
//
//        Log.d(Cfg.MYTAG, str);
//
//        // Ddy: temporaryliy made fixed
//
//        if (m_userModel != null) {
//
//            if ( m_userModel.getUserid() == "10212799266162263"  ) {
//                result = "admin";
//            }
//
//        }

        return result;
    }

    public void login(OnUserLoggedInListener listener) {

        int requestCode = Cfg.RC_IM_LOGIN;

        if (listener instanceof OnUserLoggedInListener) {
            mListener = (OnUserLoggedInListener) listener;
        } else {
            throw new RuntimeException(listener.toString()
                    + " must implement OnUserLoggedInListener");
        }

        MainActivity mainActivity = (MainActivity) m_context;
        Intent intent = new Intent(mainActivity, LoginActivity.class);
        intent.putExtra("userClass", User.this);
        mainActivity.startActivityForResult(intent, requestCode);

    }

    public boolean isLoggedIn() {
        return isFacebookLogin();
    }

    public String userId() {
        return m_userModel.getUserid();
    }

    // Ini adalah field utk nama lengkap
    public String name() {
        return m_userModel.getName();
    }

    public String firstName() {
        return m_userModel.getFirstname();
    }

    public String lastName() {
        return m_userModel.getLastname();
    }

    public String email() {
        return m_userModel.getEmail();
    }

    public String gender() {
        return m_userModel.getGender();
    }

    public String birthday() {
        return m_userModel.getBirthday();
    }

    public String businessName() {
        return m_userModel.getBusinessName();
    }

    public String hpNo() {
        return m_userModel.getHpNo();
    }

    public String location() {
        return m_userModel.getLocation();
    }

    public String pictureUrl() {
        return m_userModel.getPictureUrl().toString();
    }

    //priviledgeId
    //currentRoleId

    public String role() {
        return m_userModel.getRole();
    }

    public String enable() {
        return m_userModel.getEnable();
    }

    public String privileges() {
        return m_userModel.getPrivileges();
    }

    public void test() {
        // test only
        AccessToken accessToken =
                AccessToken.getCurrentAccessToken();
        String str =
                accessToken.getUserId();
        showMessage(str);
    }

    private void showMessage(String message) {
        MyApp.getInstance().snackbar(m_context, message);
    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {

        FacebookLoginFragment f = null;
        if (fragment instanceof FacebookLoginFragment) {
            String str = "User::onFragmentInteraction() seem to be ok";
            //Toast.makeText(m_context, str, Toast.LENGTH_SHORT).show(); // m_context sekarang sama dengan null
            //showMessage("User::onFragmentInteraction() seem to be ok");
            Log.d("MYTAG", str);
            f = (FacebookLoginFragment) fragment;
            m_userModel = m_userModel = new UserModel(f.getUserModel());

            //mListener = (OnUserLoggedInListener) m_context;
            // KOK TAK JALAN? pusing!!
            //mListener.onUserLoggedIn(this);

            // Ini jalan
            mListener = MyApp.getInstance().getOnUserLoggedInListener();
            mListener.onUserLoggedIn(this);

        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public interface OnUserLoggedInListener {
        public void onUserLoggedIn(User user);
    }

    private boolean isFacebookLogin() {
        boolean result = false;

        if (!FacebookSdk.isInitialized())
            FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext());

        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if (accessToken == null) {
            // Not logged in
            result = false;
        } else {
            // User login
            result = true;
            String userId = accessToken.getUserId();
            Log.d(Cfg.MYTAG, userId);
        }

        return result;
    }

}
