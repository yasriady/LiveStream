package org.yasriady.livestream.Utility;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import org.yasriady.livestream.R;

/**
 * Created by dedy on 12/2/17.
 */

public class ItemDialog extends Dialog {

    public ItemDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog2);
        begin();
    }

    private void begin() {

        findViewById(R.id.btnShare).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        findViewById(R.id.btnReport).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        findViewById(R.id.btnDownload).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
            }

        });

    }

}


