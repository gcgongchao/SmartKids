package com.cmbb.smartkids.fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activities.user.XXModelActivity;
import com.cmbb.smartkids.adapter.OnClubClickListener;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.ui.autoscroll.ADViewPagerAdapter;
import com.cmbb.smartkids.base.ui.autoscroll.AutoScrollViewPager;
import com.cmbb.smartkids.db.SmartKidContract;
import com.cmbb.smartkids.tools.logger.Log;
import com.cmbb.smartkids.vo.TestModel;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public class FragmentHome_Only extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private static final String TAG = FragmentHome_Only.class.getSimpleName();

    private ADViewPagerAdapter adViewPagerAdapter;

    private ListView lv_hot;
    private SimpleCursorAdapter simpleCursorAdapter;

    private List<TestModel> testData = new ArrayList<TestModel>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        for (int i = 0; i < 5; i++) {
            TestModel testModel = new TestModel();
            testModel.setName("SmartKids " + i);
            testModel.setTag("SmartKids");
            testModel.setUrl(Constants.Test.testUrl[i]);
            testData.add(testModel);
        }

        for (int j = 0; j < 10; j++) {
            ContentValues cv = new ContentValues();
            cv.put(SmartKidContract.UserAccount.COLUMN_USERNAME, "MEIZU " + j);
            getActivity().getContentResolver().insert(SmartKidContract.UserAccount
                    .TABLE_CONTENT_URI, cv);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.test_listview_only, container, false);
        AutoScrollViewPager mViewPager = (AutoScrollViewPager) rootView.findViewById(R.id.scroll_pager);
        adViewPagerAdapter = new ADViewPagerAdapter();
        adViewPagerAdapter.setOnADListener(new OnClubClickListener() {
            @Override
            public void onClick(View v, int position, Object item) {
                showToast("SmartKids");
            }
        });
        adViewPagerAdapter.setData(testData);
        Log.i(TAG, "mViewPager = " + mViewPager + "adViewPagerAdapter = " + adViewPagerAdapter);
        mViewPager.setAdapter(adViewPagerAdapter);
        //mViewPager.startAutoScroll();
        CirclePageIndicator mIndicator = (CirclePageIndicator) rootView.findViewById(R.id.tab_indicator);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
                Log.i(TAG, "onPageScrolled = " + arg0 + arg1 + arg2);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.frgment_home_hot_list_item_test, null, new String[]{SmartKidContract
                .UserAccount.COLUMN_USERNAME},
                new int[]{R.id.tv_sub_title},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv_hot = (ListView) rootView.findViewById(R.id.lv_hot);
        lv_hot.setAdapter(simpleCursorAdapter);
        lv_hot.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), SmartKidContract.UserAccount.TABLE_CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i(TAG, "Cursor count = " + data.getCount());
        simpleCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), XXModelActivity.class);
        getActivity().startActivity(intent);

    }
}
