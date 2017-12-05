package org.yasriady.ustadzsomadstreaming.NOT_USED;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.ramazeta.fbvideoplayer.FacebookPlayer;

import org.yasriady.ustadzsomadstreaming.R;


//https://web.facebook.com/plugins/video.php?href=https%3A%2F%2Fweb.facebook.com%2Fhotpicks1%2Fvideos%2F2039070229658618%2F&show_text=0&width=560
//https://www.youtube.com/embed/MCOFyxUbtis
//http://www.androidbegin.com/tutorial/AndroidCommercial.3gp

// Makkah Live HD - WwafGRDNzas
//erfCaD_DihM   قناة القران الكريم‬‎ Live Stream

// Akhyar.tv
// https://web.facebook.com/akhyartv/videos/1679019142137840/

// https://www.sitepoint.com/using-the-youtube-api-to-embed-video-in-an-android-app/
//public class MainActivity_1 extends /*AppCompatActivity*/ YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, OnPreparedListener
public class MainActivity_1 extends AppCompatActivity implements OnPreparedListener
{

//    private static final int RECOVERY_REQUEST = 1;
//    private YouTubePlayerView youTubeView;
//
//    private MyPlayerStateChangeListener playerStateChangeListener;
//    private MyPlaybackEventListener playbackEventListener;


    VideoView m_videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        //startYouTubePlayer()
        //startVideoView();
        startFBPlayer();


    }

//    private void startYouTubePlayer() {
//
//        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
//        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
//        playerStateChangeListener = new MyPlayerStateChangeListener();
//        playbackEventListener = new MyPlaybackEventListener();
//
//    }
//
//    private void onBtnClick() {
//    }
//
//
//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//
//        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
//        youTubePlayer.setPlaybackEventListener(playbackEventListener);
//
//
//        if (!b) {
//            youTubePlayer.cueVideo("WwafGRDNzas"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
//        }
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//        if (youTubeInitializationResult.isUserRecoverableError()) {
//            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
//        } else {
//            String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
//            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        //super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RECOVERY_REQUEST) {
//            // Retry initialization if user performed a recovery action
//            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
//        }
//
//    }
//
//    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
//        return youTubeView;
//    }
//
//    private void showMessage(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//    }
//
//    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {
//
//        @Override
//        public void onPlaying() {
//            // Called when playback starts, either due to user action or call to play().
//            showMessage("Playing");
//        }
//
//        @Override
//        public void onPaused() {
//            // Called when playback is paused, either due to user action or call to pause().
//            showMessage("Paused");
//        }
//
//        @Override
//        public void onStopped() {
//            // Called when playback stops for a reason other than being paused.
//            showMessage("Stopped");
//        }
//
//        @Override
//        public void onBuffering(boolean b) {
//            // Called when buffering starts or ends.
//        }
//
//        @Override
//        public void onSeekTo(int i) {
//            // Called when a jump in playback position occurs, either
//            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
//        }
//    }
//
//    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {
//
//        @Override
//        public void onLoading() {
//            // Called when the player is loading a video
//            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
//        }
//
//        @Override
//        public void onLoaded(String s) {
//            // Called when a video is done loading.
//            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
//        }
//
//        @Override
//        public void onAdStarted() {
//            // Called when playback of an advertisement starts.
//        }
//
//        @Override
//        public void onVideoStarted() {
//            // Called when playback of the video starts.
//        }
//
//        @Override
//        public void onVideoEnded() {
//            // Called when the video reaches its end.
//        }
//
//        @Override
//        public void onError(YouTubePlayer.ErrorReason errorReason) {
//            // Called when an error occurs.
//        }
//    }


    private void startVideoView() {

//        m_videoView = findViewById(R.id.video_view);
//        m_videoView.setOnPreparedListener(this);
//
//        //For now we just picked an arbitrary item to play
//        //m_videoView.setVideoURI(Uri.parse("https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4"));
//        m_videoView.setVideoURI(Uri.parse("https://web.facebook.com/akhyartv/videos/1679019142137840/"));

    }

    @Override
    public void onPrepared() {
        //Starts the video playback as soon as it is ready
        m_videoView.start();

    }

    private void startFBPlayer() {

        // get id from XML
        FacebookPlayer fbPlayerView = (FacebookPlayer) findViewById(R.id.fbPlayerView);

        // auto heights
        fbPlayerView.setAutoPlayerHeight(this);
        // init with app_id and video_url
        fbPlayerView.initialize("531273377217445", "https://web.facebook.com/akhyartv/videos/1679019142137840/");

        //play video
        fbPlayerView.play();

//        //pause video
//        fbPlayerView.pause();
//
//        //mute video
//        fbPlayerView.mute();
//
//        //unmute video
//        fbPlayerView.unmute();

    }


}


