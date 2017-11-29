package org.yasriady.livestream.Content;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.yasriady.recyclertablayout.RecyclerTabLayout;

/**
 * Created by dedy on 11/21/17.
 */

public class x_MyPagerAdapter extends RecyclerTabLayout.Adapter<x_MyPagerAdapter.ViewHolder> {

    public x_MyPagerAdapter(ViewPager viewPager) {
        super(viewPager);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate view
        View view = null;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position == getCurrentIndicatorPosition()) {
            // Highlight view
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
