package com.cmbb.smartkids.activities.register;

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
public class RegisterActivity_One extends BaseActivity {
    private static final String TAG = RegisterActivity_One.class.getSimpleName();

    TextView btn_go_registertwo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_one;
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
        btn_go_registertwo = (TextView) findViewById(R.id.btn_go_registertwo);
        btn_go_registertwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_registertwo:
                Intent intent = new Intent(this, RegisterActivity_Two.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
