package org.yasriady.ustadzsomadstreaming.Player;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.yasriady.ustadzsomadstreaming.Category.OnFragmentInteractionListener;
import org.yasriady.ustadzsomadstreaming.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FacebookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FacebookFragment extends Base {

    private final String APP_ID = "531273377217445";
    private com.ramazeta.fbvideoplayer.FacebookPlayer m_player;
    private String m_videoURL;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PAGE_NAME = "pageName";
    private static final String VIDEO_ID = "videoId";

    // TODO: Rename and change types of parameters
    private String m_pageName;
    private String m_videoId;

    private OnFragmentInteractionListener mListener;

    public FacebookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pageName Parameter 1.
     * @param videoId Parameter 2.
     * @return A new instance of fragment FacebookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FacebookFragment newInstance(String pageName, String videoId) {
        FacebookFragment fragment = new FacebookFragment();
        Bundle args = new Bundle();
        args.putString(PAGE_NAME, pageName);
        args.putString(VIDEO_ID, videoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            m_pageName = getArguments().getString(PAGE_NAME);
            m_videoId= getArguments().getString(VIDEO_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facebook, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initPlayer( m_pageName, m_videoId );
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

    private void initPlayer(final String pageName, final String videoId) {

        m_player = getView(). findViewById(R.id.player);
        m_player.setAutoPlayerHeight(getContext());
        m_player.setShowText(false);
        m_player.setShowCaptions(false);

        //https://web.facebook.com/UstadzAbdulSomad/videos/1971759093036421/
        //https://web.facebook.com/akhyartv/videos/1679019142137840/
        //https://web.facebook.com/QVC/videos/10156054929395955/
        //https://web.facebook.com/CitizenTVKe/videos/10159627801995405/
        //https://web.facebook.com/100015810817095/videos/233685183835174/
        initVideo(pageName, videoId);

    }

    private void initVideo(final String pageName, final String videoId) {
        this.m_pageName = pageName;
        this.m_videoId = videoId;
        this.m_videoURL = "https://web.facebook.com/" + m_pageName + "/videos/" + m_videoId + "/";
        m_player.initialize(APP_ID, m_videoURL);

    }

}
