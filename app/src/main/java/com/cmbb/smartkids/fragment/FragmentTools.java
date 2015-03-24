package com.cmbb.smartkids.fragment;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.SlidingFragmentToolsPagerAdapter;
import com.cmbb.smartkids.adapter.SlidingTabAdapter;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.ui.slidingtab.SlidingTabLayout;
import com.cmbb.smartkids.tools.logger.Log;

/**
 * Created by N.Sun
 */
public class FragmentTools extends BaseFragment implements LoaderManager.LoaderCallbacks {

    private static final String TAG = FragmentTools.class.getSimpleName();

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private SlidingFragmentToolsPagerAdapter mSlidingFragmentToolsPagerAdapter;
    private String[] mTitles = {"在线服务", "案例直通", "户外帮助", "实用工具"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSlidingFragmentToolsPagerAdapter = new SlidingFragmentToolsPagerAdapter
                (getChildFragmentManager(), mTitles);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tools, container, false);
        initTabTop(rootView);
        return rootView;
    }

    private void initTabTop(View rootView) {
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSlidingFragmentToolsPagerAdapter);
        mSlidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.sliding_tabs);
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
    public Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

}
