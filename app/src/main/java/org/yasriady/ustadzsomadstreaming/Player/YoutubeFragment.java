package org.yasriady.ustadzsomadstreaming.Player;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.yasriady.ustadzsomadstreaming.Category.OnFragmentInteractionListener;
import org.yasriady.ustadzsomadstreaming.R;

// https://console.developers.google.com/apis/dashboard?project=live-stream-186501&duration=PT1H
// https://stackoverflow.com/a/21304639/3626789

// Ddy: note, akan terjadi youtube play sebentar kemudian stop dgn error "UNAUTHRIZED OVERLAY". Hal tsb disebabkan karena pada layout, YoutubePlayerView dioverlay oleh view atau layout lain
// lihat https://stackoverflow.com/a/20458652/3626789


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link YoutubeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YoutubeFragment extends Base {

    private static final String API_KEY = "AIzaSyAknNYUYoLdG5m4OGybmzIYMOpMpmDDb9Q";
    YouTubePlayerSupportFragment mYoutubePlayerFragment;
    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;
    private static final int RECOVERY_REQUEST = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PAGE_NAME = "pageName";
    private static final String VIDEO_ID = "videoId";

    // TODO: Rename and change types of parameters
    private String m_pageName;
    private String m_videoId;

    private OnFragmentInteractionListener mListener;

    public YoutubeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pageName Parameter 1.
     * @param videoId  Parameter 2.
     * @return A new instance of fragment YoutubeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YoutubeFragment newInstance(String pageName, String videoId) {
        YoutubeFragment fragment = new YoutubeFragment();
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
            m_videoId = getArguments().getString(VIDEO_ID);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        //return inflater.inflate(R.layout.fragment_youtube, container, false);
//
//        View fragmentYoutubeView = inflater.inflate(R.layout.fragment_youtube, container, false);
//        mYoutubePlayerFragment = new YouTubePlayerSupportFragment();
//        mYoutubePlayerFragment.initialize( "AIzaSyAknNYUYoLdG5m4OGybmzIYMOpMpmDDb9Q", (YouTubePlayer.OnInitializedListener) this);
//
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_youtube_player, mYoutubePlayerFragment);
//        fragmentTransaction.commit();
//
//        return fragmentYoutubeView;
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_youtube, container, false);
        mYoutubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace /*add*/(R.id.fragment_youtube_player, mYoutubePlayerFragment).commit();

        mYoutubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

                player.setPlayerStateChangeListener(playerStateChangeListener);
                player.setPlaybackEventListener(playbackEventListener);

                if (!wasRestored) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                    //player.loadVideo(m_videoId);
                    //player.play();
                    player.cueVideo(m_videoId);
                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });

        return rootView;

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

//    //@Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//        Toast.makeText(getContext(), "onInitializationSuccess()", Toast.LENGTH_LONG).show();
//
//        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
//        youTubePlayer.setPlaybackEventListener(playbackEventListener);
//
//        if (!b) {
//            youTubePlayer.cueVideo(m_videoId); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
//        }
//    }
//
//    //@Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//        Toast.makeText(getContext(), "onInitializationFailure()", Toast.LENGTH_LONG).show();
//
//        if (youTubeInitializationResult.isUserRecoverableError()) {
//            youTubeInitializationResult.getErrorDialog(getActivity()/*Ddy*/, RECOVERY_REQUEST).show();
//        } else {
//            String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
//            Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
//        }
//    }

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

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onPlaying() {
            // Called when playback starts, either due to user action or call to play().
            //showMessage("Playing");
        }

        @Override
        public void onPaused() {
            // Called when playback is paused, either due to user action or call to pause().
            //showMessage("Paused");
        }

        @Override
        public void onStopped() {
            // Called when playback stops for a reason other than being paused.
            //showMessage("Stopped");
        }

        @Override
        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
            //showMessage("Buffering");
        }

        @Override
        public void onSeekTo(int i) {
            // Called when a jump in playback position occurs, either
            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
            //showMessage("SeekTo");
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
            // Called when the player is loading a video
            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
            //showMessage("Loading");
        }

        @Override
        public void onLoaded(String s) {
            // Called when a video is done loading.
            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
            //showMessage("Loaded");
        }

        @Override
        public void onAdStarted() {
            // Called when playback of an advertisement starts.
            //showMessage("AdStarted");
        }

        @Override
        public void onVideoStarted() {
            // Called when playback of the video starts.
            //showMessage("VideoStarted");
        }

        @Override
        public void onVideoEnded() {
            // Called when the video reaches its end.
            //showMessage("VideoEnded");
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            // Called when an error occurs.
            final String msg = errorReason.toString();
            //showMessage(msg);
        }
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

}
