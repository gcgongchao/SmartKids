package com.cmbb.smartkids.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.fragment.FragmentToolsCase;
import com.cmbb.smartkids.fragment.FragmentToolsOnline;
import com.cmbb.smartkids.fragment.FragmentToolsOutdoors;
import com.cmbb.smartkids.fragment.FragmentToolsPractical;
import com.cmbb.smartkids.tools.logger.Log;

/**
 * Created by N.Sun
 */
public class SlidingFragmentToolsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = SlidingFragmentToolsPagerAdapter.class.getSimpleName();
    private String[] mTitles;

    private FragmentToolsCase mFragmentToolsCase = new FragmentToolsCase();
    private FragmentToolsOnline mFragmentToolsOnline = new FragmentToolsOnline();
    private FragmentToolsOutdoors mFragmentToolsOutdoors = new FragmentToolsOutdoors();
    private FragmentToolsPractical mFragmentToolsPractical = new FragmentToolsPractical();

    public SlidingFragmentToolsPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return mFragmentToolsOnline;
            case 1:
                return mFragmentToolsCase;
            case 2:
                return mFragmentToolsOutdoors;
            case 3:
                return mFragmentToolsPractical;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

}
