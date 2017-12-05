package org.yasriady.ustadzsomadstreaming.Login.Facebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.yasriady.ustadzsomadstreaming.Category.OnFragmentInteractionListener;
import org.yasriady.ustadzsomadstreaming.Login.Base;
import org.yasriady.ustadzsomadstreaming.R;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FacebookLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FacebookLoginFragment extends Base {

    LoginButton m_loginButton;
    CallbackManager m_callbackManager;
    private AccessToken m_accessToken;
    private Profile m_profile;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FacebookLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FacebookLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FacebookLoginFragment newInstance(String param1, String param2) {
        FacebookLoginFragment fragment = new FacebookLoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_facebook_login, container, false);
        View view = inflater.inflate(R.layout.fragment_facebook_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        begin(view);
    }

    private void begin(View view) {

        if (!FacebookSdk.isInitialized()) {
            FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext());
        }
        m_callbackManager = CallbackManager.Factory.create();

        m_loginButton = (LoginButton) view.findViewById(R.id.login_button);
        // https://developers.facebook.com/docs/facebook-login/permissions/
        m_loginButton.setReadPermissions(Arrays.asList("public_profile", "user_friends", "user_status", "email"/*, "publish_actions"*/));
        // https://stackoverflow.com/a/29379794/3626789

        // If using in a fragment
        m_loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        m_loginButton.registerCallback(m_callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                //showMessage("onSuccess(LoginResult loginResult)");

                // App code, lakukan inisialisasi user disini
                //initUser(loginResult); x_

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());
                                // Application code
                                initUser(object, response);
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync(); // diganti menjadi synch, baris berikut ini
                //request.executeAndWait();  TAK BISA, crash karena sedang ada asynctask lain sedang running
            }

            @Override
            public void onCancel() {
                // App code
                m_loginButton.setVisibility(View.VISIBLE);
                showMessage("onCancel()");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                m_loginButton.setVisibility(View.VISIBLE);
                showMessage("onError(FacebookException exception)");
            }
        });

        m_loginButton.setOnClickListener(new LoginButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_loginButton.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void initUser(JSONObject object, GraphResponse response) {

        String
                id = "",
                appId = "",
                userId = "",
                password = "",
                provider = "",
                name = "",
                firstname = "",
                lastname = "",
                email = "",
                gender = "",
                birthday = "",
                businessName = "",
                hpNo = "",
                location = "",
                pictureUrl = "",
                privilegeId = "",
                currentRoleId = "",
                role = "",
                enable = "",
                privileges = "",
                ts = "";

        try {

            if (!object.isNull("id")) {
                userId = object.getString("id");
                m_userModel.setUserid(userId);
            }

            if (!object.isNull("name")) {
                name = object.getString("name");
                m_userModel.setName(name);
            }

            //firstName = object.getString("firstname");
            //lastName = object.getString("lastname");

            if (!object.isNull("email")) {
                email = object.getString("email");
                m_userModel.setEmail(email);
            }
            if (!object.isNull("gender")) {
                gender = object.getString("gender");
                m_userModel.setGender(gender);
            }
            // ...

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mListener.onFragmentInteraction(this);

    }

    private void initUser(LoginResult loginResult) {
        //AccessToken.getCurrentAccessToken() and Profile.getCurrentProfile().
        m_accessToken = AccessToken.getCurrentAccessToken();
        m_profile = Profile.getCurrentProfile();

        Log.d("MYTAG", "test");
        //final String name = m
        //m_userModel.setName(name);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(this);
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //showMessage( "onActivityResult()");
        m_callbackManager.onActivityResult(requestCode, resultCode, data);
    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }


}
