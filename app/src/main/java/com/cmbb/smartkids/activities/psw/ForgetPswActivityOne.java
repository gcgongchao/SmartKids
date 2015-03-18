package com.cmbb.smartkids.activities.psw;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;

/**
 * Created by Administrator on 2015/3/17.
 */
public class ForgetPswActivityOne extends BaseActivity {
    private static final String TAG = ForgetPswActivityOne.class.getSimpleName();
    TextView btn_forget_next;
    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_one;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        initView();

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

    private void initView() {
        btn_forget_next = (TextView) findViewById(R.id.btn_forget_next);
        btn_forget_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_forget_next:
                Intent intent = new Intent(this, ForgetPswActivityTwo.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
