package com.cmbb.smartkids.fragment;

import android.content.ContentValues;
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
import android.widget.ListView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.SlidingFragmentPagerAdapter;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.db.SmartKidContract;
import com.cmbb.smartkids.tools.logger.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by N.Sun
 */
public class FragmentHome2 extends BaseFragment {

    private static final String TAG = FragmentHome2.class.getSimpleName();

    //protected ListView listView;
    protected ViewPager pager;
    protected SlidingFragmentPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SlidingFragmentPagerAdapter(getChildFragmentManager(), null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_listview, container, false);
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        return view;
    }
}
