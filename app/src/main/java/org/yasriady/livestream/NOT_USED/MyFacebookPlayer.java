package org.yasriady.livestream.NOT_USED;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.ramazeta.fbvideoplayer.FacebookPlayer;

import org.yasriady.livestream.R;

/**
 * Created by dedy on 11/19/17.
 */

public class MyFacebookPlayer extends MyPlayerBase implements FacebookPlayer.FacebookListener {

    private final int LAYOUTID = R.layout.player_facebook;

    private final String APP_ID = "531273377217445";
    private com.ramazeta.fbvideoplayer.FacebookPlayer m_player;
    private String m_pageName, m_videoId, m_videoURL;

    public MyFacebookPlayer(@NonNull Context context) {
        super(context);
        initControl(context, LAYOUTID);
    }

    public MyFacebookPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context, LAYOUTID);
    }

    public MyFacebookPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, LAYOUTID);
    }

    //public FacebookPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    //   super(context, attrs, defStyleAttr, defStyleRes);
    //}

    @Override
    public void initControl(Context context, int layoutId) {
        super.initControl(context, layoutId);
        m_player = findViewById(R.id.player);
        m_player.setAutoPlayerHeight(getContext());
        m_player.setShowText(false);

        //https://web.facebook.com/UstadzAbdulSomad/videos/1971759093036421/
        //https://web.facebook.com/akhyartv/videos/1679019142137840/
        //https://web.facebook.com/QVC/videos/10156054929395955/
        //https://web.facebook.com/CitizenTVKe/videos/10159627801995405/
        //https://web.facebook.com/100015810817095/videos/233685183835174/
        final String pageName = "akhyartv";
        final String videoId = "1679019142137840";
        initVideo(pageName, videoId);
    }

    private void initVideo(final String pageName, final String videoId) {
        this.m_pageName = pageName;
        this.m_videoId = videoId;
        this.m_videoURL = "https://web.facebook.com/" + m_pageName + "/videos/" + m_videoId + "/";
        m_player.initialize(APP_ID, m_videoURL);
    }

    @Override
    public void doPlay() {
        super.doPlay();
        //m_player = findViewById(R.id.player);
        //m_player.setAutoPlayerHeight(getContext());
        //https://web.facebook.com/akhyartv/videos/1679019142137840/
        //m_player.setShowText(false);
        //m_player.initialize(APP_ID, "https://web.facebook.com/UstadzAbdulSomad/videos/1971759093036421/");
        m_player.play();
        m_player.unmute();

    }

    @Override
    public void doPause() {
        m_player.pause();
        super.doPause();
    }

    @Override
    public void doFullscreen() {
        super.doFullscreen();
    }

    @Override
    public void doFullscreenExit() {
        super.doFullscreenExit();
    }

    @Override
    public void doMute() {
        super.doMute();
        m_player.mute();
    }

    @Override
    public void doUnmute() {
        super.doUnmute();
        m_player.unmute();
    }

    @Override
    public void doResume() {
        super.doResume();
        m_player.play();
    }

    @Override
    public void doSetting() {
        super.doSetting();
    }

    // event ---------------------------------------------

    @Override
    public void onStartBuffer() {
        showMessage("onStartBuffer()");
    }

    @Override
    public void onFinishBuffering() {
        showMessage("onFinishBuffering()");
    }

    @Override
    public void onStartPlaying() {
        showMessage("onStartPlaying()");
    }

    @Override
    public void onFinishPlaying() {
        showMessage("onFinishPlaying()");
    }

    @Override
    public void onPaused() {
        showMessage("onPause()");
    }

    @Override
    public void onError() {
        showMessage("onError()");
    }
}
