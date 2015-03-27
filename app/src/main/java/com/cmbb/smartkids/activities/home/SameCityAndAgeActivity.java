package com.cmbb.smartkids.activities.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.SlidingFragmentCityAgeAdapter;
import com.cmbb.smartkids.adapter.SlidingFragmentToolsPagerAdapter;
import com.cmbb.smartkids.adapter.SlidingTabAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.ui.slidingtab.SlidingTabLayout;

/**
 * Created by N.Sun
 */
public class SameCityAndAgeActivity extends BaseActivity {

    private static final String TAG = SameCityAndAgeActivity.class.getSimpleName();

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private SlidingFragmentCityAgeAdapter mSlidingFragmentCityAgeAdapter;
    private String[] mTitles = new String[]{"同 城", "同 龄"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_cityandage;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        initTabTop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabTop() {
        mSlidingFragmentCityAgeAdapter = new SlidingFragmentCityAgeAdapter(getSupportFragmentManager(),
                mTitles);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSlidingFragmentCityAgeAdapter);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.sliding_tabview_item, R.id.sliding_tabs_item);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }

            @Override
            public int getDividerColor(int position) {
                return getResources().getColor(android.R.color.transparent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
