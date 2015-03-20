package com.cmbb.smartkids.activities.user;

import android.os.Bundle;

import com.cmbb.smartkids.ListViewFragment;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseNoCompatActivity;
import com.cmbb.smartkids.base.ui.header2.FadingActionBarHelper;

/**
 * Created by N.Sun
 */
public class XXModelActivity extends BaseNoCompatActivity {

    private FadingActionBarHelper mFadingActionBarHelper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xx_model;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ListViewFragment())
                    .commit();
        }

    }

    @Override
    protected void init() {
        mFadingActionBarHelper = new FadingActionBarHelper(getActionBar(),
                getResources().getDrawable(R.color.theme_color));

    }

    public FadingActionBarHelper getFadingActionBarHelper() {
        return mFadingActionBarHelper;
    }
}
