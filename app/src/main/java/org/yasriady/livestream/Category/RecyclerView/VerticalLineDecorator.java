package org.yasriady.livestream.Category.RecyclerView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dedy on 11/26/17.
 */

public class VerticalLineDecorator extends RecyclerView.ItemDecoration {
    private int space=0;

    public VerticalLineDecorator(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildAdapterPosition(view) == 0)
            outRect.top = space;

        outRect.bottom = space;
    }
}