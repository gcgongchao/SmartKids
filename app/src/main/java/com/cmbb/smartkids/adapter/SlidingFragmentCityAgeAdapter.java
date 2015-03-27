package com.cmbb.smartkids.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cmbb.smartkids.fragment.home.FragmentHomeSameAge;
import com.cmbb.smartkids.fragment.home.FragmentHomeSameCity;

/**
 * Created by N.Sun
 */
public class SlidingFragmentCityAgeAdapter extends FragmentPagerAdapter {
    private static final String TAG = SlidingFragmentCityAgeAdapter.class.getSimpleName();
    private String[] mTitles;

    private FragmentHomeSameAge fragmentHomeSameAge = new FragmentHomeSameAge();
    private FragmentHomeSameCity fragmentHomeSameCity = new FragmentHomeSameCity();

    public SlidingFragmentCityAgeAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragmentHomeSameCity;
            case 1:
                return fragmentHomeSameAge;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
