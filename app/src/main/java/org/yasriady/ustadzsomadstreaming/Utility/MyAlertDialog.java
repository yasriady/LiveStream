package org.yasriady.ustadzsomadstreaming.Utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import org.yasriady.ustadzsomadstreaming.R;

/**
 * Created by dedy on 12/7/17.
 * Purpose:
 */

public class MyAlertDialog extends AlertDialog.Builder {

    private Context m_context;
    private AlertDialog m_alertDialog;

    public MyAlertDialog(@NonNull Context context) {
        super(context);
        m_context = context;
    }

    public MyAlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    // https://stackoverflow.com/q/2795300/3626789
    @Override
    public AlertDialog show() {
        //return super.show();


        View view = LayoutInflater.from(m_context).inflate(R.layout.my_alertdialog, null);
        m_alertDialog = super.show();



        setPositiveButton("Update",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //redirectStore(updateUrl);


                    }
                });

        m_alertDialog.setContentView(view);

        return m_alertDialog;
    }
}
