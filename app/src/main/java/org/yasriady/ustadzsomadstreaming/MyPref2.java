package org.yasriady.ustadzsomadstreaming;

import android.content.Context;

import org.yasriady.ustadzsomadstreaming.BaseClasses.PrefBase;
import org.yasriady.ustadzsomadstreaming.Utility.PrefDialog;

/**
 * Created by dedy on 11/30/17.
 */

public class MyPref2 extends PrefBase {

    public MyPref2(Context context, MyDB db) {
        super(context, db);
    }

    public void show(PrefDialog.DismissHandler dismissHandler) {
        PrefDialog dlg = new PrefDialog(m_context);
        dlg.showPref(this, dismissHandler);
    }


}
