package org.yasriady.livestream.Category.NOT_USED;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

//https://stackoverflow.com/a/28504193/3626789
public class CustomViewPager extends ViewPager {

    private boolean enabled;

    public CustomViewPager(Context context) {
        super(context);
        this.enabled = true;
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //return super.onTouchEvent(ev);
        return enabled ? super.onTouchEvent(ev) : false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return enabled ? super.onInterceptTouchEvent(event) : false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isPagingEnabled() {
        return enabled;
    }


}
