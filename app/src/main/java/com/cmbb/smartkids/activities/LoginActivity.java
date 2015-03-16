package com.cmbb.smartkids.activities;

import android.os.Bundle;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.tools.logger.Log;

/**
 * Created by N.Sun
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        initView();

    }

    private void initView() {
        Log.i(TAG, "GAODU"+getSupportActionBar());
    }
}
