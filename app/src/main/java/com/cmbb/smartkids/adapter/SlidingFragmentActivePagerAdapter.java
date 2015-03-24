package com.cmbb.smartkids.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cmbb.smartkids.fragment.FragmentActiveActive;
import com.cmbb.smartkids.fragment.FragmentActiveMessage;

/**
 * Created by N.Sun
 */
public class SlidingFragmentActivePagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = SlidingFragmentActivePagerAdapter.class.getSimpleName();
    private String[] mTitles;

    // 创建Fragment
    private FragmentActiveActive fragmentActiveActive = new FragmentActiveActive();
    private FragmentActiveMessage fragmentActiveMessage = new FragmentActiveMessage();

    public SlidingFragmentActivePagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return fragmentActiveActive;
            case 1:
                return fragmentActiveMessage;
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
