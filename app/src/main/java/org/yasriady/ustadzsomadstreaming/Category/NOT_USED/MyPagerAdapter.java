package org.yasriady.ustadzsomadstreaming.Category.NOT_USED;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

/**
 * Created by dedy on 6/11/17.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter /*FragmentPagerAdapter*/ {

    private SparseArray<Fragment> m_pages = new SparseArray<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setPages(SparseArray<Fragment> pages) {
        m_pages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        return m_pages.get(position);
    }

    @Override
    public int getCount() {
        return m_pages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title = m_pages.get(position).getArguments().getString("title");
        //CharSequence title = "Not settled yet";//m_pages.get(position).getTitle();
        return title;

    }

}
