package org.yasriady.livestream.Login;

import android.content.Context;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.Profile;

import org.yasriady.livestream.Login.Utils.UserModel;
import org.yasriady.livestream.MainActivity;
import org.yasriady.livestream.MyApp;

/**
 * Created by dedy on 12/1/17.
 */

public class User {

    private UserModel m_userModel;

    private static final int IM_LOGIN = 97; // my arbitrary number
    Context m_context;


    public User(Context context){
        m_context = context;
    }

    public void login() {
        int requestCode = IM_LOGIN;
        MainActivity mainActivity = (MainActivity) m_context;
        Intent intent = new Intent(mainActivity, LoginActivity.class);
        //mainActivity.startActivity(intent);
        mainActivity.startActivityForResult(intent, requestCode);
    }

    // ?
    public final String getRole() {
        //return m_userModel.getPrivileges();
        // Ddy: temporaryliy made fixed
        //if(  )
        return "admin";
    }

    public boolean isLoggedIn() {
        return true;
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

    public String businessName(){
        return m_userModel.getBusinessName();
    }

    public String hpNo() {
        return m_userModel.getHpNo();
    }

    public String location() {
        return m_userModel.getLocation();
    }

    public String pictureUrl(){
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

        String str=
        accessToken.getUserId();

        showMessage(str);
    }

    private void showMessage(String message) {
        MyApp.getInstance().snackbar(m_context, message);
    }

}
