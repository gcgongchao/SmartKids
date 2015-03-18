package com.cmbb.smartkids.activities.register;

import android.os.Bundle;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;

/**
 * Created by N.Sun.
 */
public class RegisterActivity_Two extends BaseActivity {
    private static final String TAG = RegisterActivity_Two.class.getSimpleName();

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_two;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {

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
}
