package com.cmbb.smartkids.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.tools.logger.Log;

/**
 * Created by N.Sun
 */
public class SlidingTabAdapter extends PagerAdapter {

    private final String TAG = SlidingTabAdapter.class.getSimpleName();

    private String[] titles;
    private Context mContext;

    public SlidingTabAdapter(Context context, String[] titles) {
        super();
        this.titles = titles;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pager_item_sample,
                container, false);
        container.addView(view);
        TextView title = (TextView) view.findViewById(R.id.item_title);
        title.setText(String.valueOf(position + 1));
        Log.i(TAG, "instantiateItem() [position: " + position + "]");
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.i(TAG, "destroyItem() [position: " + position + "]");
    }
}
