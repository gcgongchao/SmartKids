package com.cmbb.smartkids.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cmbb.smartkids.BuildConfig;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.OnClubClickListener;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.ui.autoscroll.ADViewPagerAdapter;
import com.cmbb.smartkids.base.ui.autoscroll.AutoScrollViewPager;
import com.cmbb.smartkids.base.ui.header2.HeaderCompatFragment;
import com.cmbb.smartkids.vo.TestModel;
import com.viewpagerindicator.CirclePageIndicator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public class FragmentHome3_NoSliding extends HeaderCompatFragment {
    private static final String TAG = FragmentHome3_NoSliding.class.getSimpleName();

    private ListView mListView;
    private String[] mListViewTitles;
    private boolean mLoaded;

    private FrameLayout mContentOverlay;
    // autoscroll indicator
    private ADViewPagerAdapter adViewPagerAdapter;
    private List<TestModel> testData = new ArrayList<TestModel>();

    // content List
    private AsyncLoadSomething mAsyncLoadSomething;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setHeaderBackgroundScrollMode(HEADER_BACKGROUND_SCROLL_PARALLAX);
        setOnHeaderScrollChangedListener(new HeaderCompatFragment.OnHeaderScrollChangedListener() {
            @Override
            public void onHeaderScrollChanged(float progress, int height, int scroll) {
//                height -= getActivity().getActionBar().getHeight();
//
//                progress = (float) scroll / height;
//                if (progress > 1f) progress = 1f;
//                progress = (1 - (float) Math.cos(progress * Math.PI)) * 0.5f;

//                ((BaseNoCompatActivity) getActivity())
//                        .getFadingActionBarHelper()
//                        .setActionBarAlpha((int) (255 * progress));
            }
        });
        for (int i = 0; i < 5; i++) {
            TestModel testModel = new TestModel();
            testModel.setName("SmartKids " + i);
            testModel.setTag("SmartKids");
            testModel.setUrl(Constants.Test.testUrl[i]);
            testData.add(testModel);
        }
        cancelAsyncTask(mAsyncLoadSomething);
        mAsyncLoadSomething = new AsyncLoadSomething(this);
        mAsyncLoadSomething.execute();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDetach() {
        cancelAsyncTask(mAsyncLoadSomething);

        super.onDetach();
    }

    @Override
    public View onCreateHeaderView(LayoutInflater inflater, ViewGroup container) {
        View rootHeader = inflater.inflate(R.layout.fragment_home_compat_header, container, false);
        AutoScrollViewPager mViewPager = (AutoScrollViewPager) rootHeader.findViewById(R.id.scroll_pager);
        adViewPagerAdapter = new ADViewPagerAdapter();
        adViewPagerAdapter.setOnADListener(new OnClubClickListener() {
            @Override
            public void onClick(View v, int position, Object item) {
                //showToast("SmartKids");
            }
        });
        adViewPagerAdapter.setData(testData);
        mViewPager.setAdapter(adViewPagerAdapter);
        mViewPager.startAutoScroll();
        CirclePageIndicator mIndicator = (CirclePageIndicator) rootHeader.findViewById(R.id.tab_indicator);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
                com.cmbb.smartkids.tools.logger.Log.i(TAG, "onPageScrolled = " + arg0 + arg1 + arg2);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        return rootHeader;

    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        mListView = (ListView) inflater.inflate(R.layout.fragment_listview, container, false);
        if (mLoaded) setListViewTitles(mListViewTitles);
        return mListView;
    }

    @Override
    public View onCreateContentOverlayView(LayoutInflater inflater, ViewGroup container) {
        ProgressBar progressBar = new ProgressBar(getActivity());
        mContentOverlay = new FrameLayout(getActivity());
        mContentOverlay.addView(progressBar, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        if (mLoaded) mContentOverlay.setVisibility(View.GONE);
        return mContentOverlay;
    }

    private void setListViewTitles(String[] titles) {
        mLoaded = true;
        mListViewTitles = titles;
        if (mListView == null) return;

        mListView.setVisibility(View.VISIBLE);
        setListViewAdapter(mListView, new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                mListViewTitles));
    }

    private void cancelAsyncTask(AsyncTask task) {
        if (task != null) task.cancel(false);
    }

    // //////////////////////////////////////////
    // ///////////// -- LOADER -- ///////////////
    // //////////////////////////////////////////

    private static class AsyncLoadSomething extends AsyncTask<Void, Void, String[]> {

        private static final String TAG = "AsyncLoadSomething";

        final WeakReference<FragmentHome3_NoSliding> weakFragment;

        public AsyncLoadSomething(FragmentHome3_NoSliding fragment) {
            this.weakFragment = new WeakReference<FragmentHome3_NoSliding>(fragment);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            final FragmentHome3_NoSliding audioFragment = weakFragment.get();
            if (audioFragment.mListView != null)
                audioFragment.mListView.setVisibility(View.INVISIBLE);
            if (audioFragment.mContentOverlay != null)
                audioFragment.mContentOverlay.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(Void... voids) {

            try {
                // Emulate long downloading
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return new String[]{"Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder"};
        }

        @Override
        protected void onPostExecute(String[] titles) {
            super.onPostExecute(titles);
            final FragmentHome3_NoSliding audioFragment = weakFragment.get();
            if (audioFragment == null) {
                if (BuildConfig.DEBUG)
                    Log.d(TAG, "Skipping.., because there is no fragment anymore.");
                return;
            }

            if (audioFragment.mContentOverlay != null)
                audioFragment.mContentOverlay.setVisibility(View.GONE);
            audioFragment.setListViewTitles(titles);
        }
    }

}
