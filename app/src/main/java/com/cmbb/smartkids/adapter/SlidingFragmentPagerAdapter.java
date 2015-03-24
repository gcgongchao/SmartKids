package com.cmbb.smartkids.adapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cmbb.smartkids.activities.home.HomeFragmentTest;
import com.cmbb.smartkids.db.SmartKidContract;
import com.cmbb.smartkids.tools.logger.Log;

/**
 * Created by N.Sun
 */
public class SlidingFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = SlidingFragmentPagerAdapter.class.getSimpleName();

    private Cursor mCursor;

    public SlidingFragmentPagerAdapter(FragmentManager fm, Cursor c) {
        super(fm);
        this.mCursor = c;
    }

    @Override
    public Fragment getItem(int position) {

//       Log.i(TAG, "getItem" + mCursor.getCount());
//        if (mCursor.moveToPosition(position)) {
//            Bundle arguments = new Bundle();
//            arguments.putString(HomeFragmentTest.ARG_ITEM_ID, mCursor.getString(mCursor
//                    .getColumnIndex(SmartKidContract.UserAccount.COLUMN_USERNAME)));
//            HomeFragmentTest test = new HomeFragmentTest();
//            test.setArguments(arguments);
//            return test;
//        }
        HomeFragmentTest test = new HomeFragmentTest();

        return test;
    }

    @Override
    public int getCount() {
//        if (mCursor != null) {
//            return mCursor.getCount();
//        }
        return 5;
    }

//    public void swapCursor(Cursor cursor) {
//        mCursor = cursor;
//        notifyDataSetChanged();
//    }
}
