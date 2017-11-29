package org.yasriady.livestream.NOT_USED;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.yasriady.livestream.R;

/**
 * Created by dedy on 11/19/17.
 */

public abstract class MyPlayerBase extends RelativeLayout {

    private ImageButton m_btnPlay, m_btnPause, m_btnFullscreen, m_btnFullscreenExit, m_btnMute, m_btnUnmute, m_btnSetting;
    protected boolean m_isPlaying, m_isFullscreen, m_isMute;

    public MyPlayerBase(@NonNull Context context) {
        super(context);
    }

    public MyPlayerBase(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPlayerBase(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //public MyPlayerBase (@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    //   super(context, attrs, defStyleAttr, defStyleRes);
    //}

    public void doPlay() {
        m_isPlaying = true;
        setButton();
    }

    public void doPause() {
        m_isPlaying = false;
        setButton();
    }

    public void doFullscreen() {
        m_isFullscreen = true;
        setButton();
    }

    public void doFullscreenExit() {
        m_isFullscreen = false;
        setButton();
    }

    public void doMute() {
        m_isMute = true;
        setButton();
    }

    public void doUnmute() {
        m_isMute = false;
        setButton();
    }

    public void doResume() {
        m_isPlaying = true;
        setButton();
    }

    public void doSetting() {

    }


    public void initControl(Context context, final int layoutId) {

        m_isPlaying = false;
        m_isFullscreen = false;
        m_isMute = false;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(layoutId, this);
        }

        m_btnPlay = findViewById(R.id.btnPlay);
        m_btnPause = findViewById(R.id.btnPause);
        m_btnFullscreen = findViewById(R.id.btnFullscreen);
        m_btnFullscreenExit = findViewById(R.id.btnFullscreenExit);
        m_btnMute = findViewById(R.id.btnMute);
        m_btnUnmute = findViewById(R.id.btnUnmute);
        m_btnSetting = findViewById(R.id.btnSetting);

        ButtonHandler buttonHandler = new ButtonHandler();
        m_btnPlay.setOnClickListener(buttonHandler);
        m_btnPause.setOnClickListener(buttonHandler);
        m_btnFullscreen.setOnClickListener(buttonHandler);
        m_btnFullscreenExit.setOnClickListener(buttonHandler);
        m_btnMute.setOnClickListener(buttonHandler);
        m_btnUnmute.setOnClickListener(buttonHandler);
        m_btnSetting.setOnClickListener(buttonHandler);

    }

    class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btnPlay:
                    doPlay();
                    break;
                case R.id.btnPause:
                    doPause();
                    break;
                case R.id.btnFullscreen:
                    doFullscreen();
                    break;
                case R.id.btnFullscreenExit:
                    doFullscreenExit();
                    break;
                case R.id.btnMute:
                    doMute();
                    break;
                case R.id.btnUnmute:
                    doUnmute();
                    break;
                case R.id.btnSetting:
                    doSetting();
                    break;
            }
        }
    }

    private int bool2Vis(boolean value) {
        int result = value ? View.VISIBLE : View.INVISIBLE;
        return result;
    }

    private void setButton() {

        m_btnPlay.setVisibility(bool2Vis(!m_isPlaying));
        m_btnPause.setVisibility(bool2Vis(m_isPlaying));
        m_btnFullscreen.setVisibility(bool2Vis(!m_isFullscreen));
        m_btnFullscreenExit.setVisibility(bool2Vis(m_isFullscreen));
        m_btnMute.setVisibility(bool2Vis(!m_isMute));
        m_btnUnmute.setVisibility(bool2Vis(m_isMute));
        m_btnSetting.setVisibility(bool2Vis(true));

    }

    protected void showMessage(final String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

}
