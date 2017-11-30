package org.yasriady.livestream.Utility;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import org.yasriady.livestream.Cfg;
import org.yasriady.livestream.MyApp;
import org.yasriady.livestream.MyPref2;
import org.yasriady.livestream.R;

/**
 * Created by dedy on 11/29/17.
 */

public class PrefDialog extends Dialog {

    private Switch m_switchDevelopmentMode;
    private EditText m_edServerIndex;
    private Button m_btnSave, m_btnClose;

    private MyPref2 m_pref;
    //private MyConverter c = new MyConverter();

    private boolean m_devMode;
    private int m_serverIdx;

    private DismissHandler m_dismissHandlder;

    public PrefDialog(@NonNull Context context) {
        super(context);
    }

    public PrefDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected PrefDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        m_switchDevelopmentMode = findViewById(R.id.swDevelopmentMode);
        m_edServerIndex = findViewById(R.id.edServerIndex);
        m_btnSave = findViewById(R.id.btnSave);
        m_btnClose= findViewById(R.id.btnClose);

        m_btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSave();
            }
        });
        m_btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        // Load presaved data
        m_devMode = m_pref.get(Cfg.DEVELOPMENT_MODE, true);
        m_serverIdx = (int) m_pref.get(Cfg.SERVER_INDEX, 0);

        m_switchDevelopmentMode.setChecked(m_devMode);
        m_edServerIndex.setText(String.valueOf(m_serverIdx));

    }

//    private boolean getDevelopmentMode() {
//        boolean developmentMode = m_app.getSharedPrefBoolean(Cfg.DEVELOPMENT_MODE, true);
//        return developmentMode;
//    }
//
//    private int getServerIndex() {
//        int serverIndex = m_app.getSharedPrefInt(Cfg.SERVER_INDEX, 0);
//        return serverIndex;
//    }
//
//
//    static class MyConverter {
//
//        public MyConverter() {
//        }
//
//        public static int v(final String string) {
//            int result = Integer.parseInt(string);
//            return result;
//        }
//
//    }

    public void showPref(MyPref2 pref, DismissHandler dismissHandler) {
        m_pref = pref;
        m_dismissHandlder = dismissHandler;
        super.show();
    }

    private void onSave() {

        // get from control
        m_devMode = m_switchDevelopmentMode.isChecked();
        m_serverIdx = Integer.parseInt(m_edServerIndex.getText().toString());
        // save to db
        m_pref.set(Cfg.DEVELOPMENT_MODE, m_devMode);
        m_pref.set(Cfg.SERVER_INDEX, (double) m_serverIdx);

        m_dismissHandlder.onDialogDismissed();

    }

    public interface DismissHandler {
        public void onDialogDismissed();
    }

}
