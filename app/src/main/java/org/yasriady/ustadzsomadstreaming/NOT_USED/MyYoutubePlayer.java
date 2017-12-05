package org.yasriady.ustadzsomadstreaming.NOT_USED;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by dedy on 11/20/17.
 */

public class MyYoutubePlayer extends MyPlayerBase {

    private final String API_KEY = "AIzaSyAknNYUYoLdG5m4OGybmzIYMOpMpmDDb9Q";

    private YouTubePlayerView m_player;
    private static final int RECOVERY_REQUEST = 1;

    public MyYoutubePlayer(@NonNull Context context) {
        super(context);
    }

    public MyYoutubePlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyYoutubePlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void doPlay() {

    }

    @Override
    public void doPause() {

    }

    @Override
    public void doFullscreen() {

    }

    @Override
    public void doFullscreenExit() {

    }

    @Override
    public void doMute() {

    }

    @Override
    public void doUnmute() {

    }

    @Override
    public void doResume() {

    }

    @Override
    public void doSetting() {

    }


}
