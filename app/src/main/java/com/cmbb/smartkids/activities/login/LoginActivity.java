package com.cmbb.smartkids.activities.login;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activities.HomeActivity;
import com.cmbb.smartkids.activities.psw.ForgetPswActivityOne;
import com.cmbb.smartkids.activities.register.RegisterActivity_One;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.tools.logger.Log;

/**
 * Created by N.Sun
 */
public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    TextView btn_go_register, btn_forget_password, btn_login;

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
        TextView mTitle = (TextView) getToolbar().findViewById(R.id.toolbar_title);
        mTitle.setText("用户登录");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        btn_go_register = (TextView) findViewById(R.id.btn_go_register);
        btn_forget_password = (TextView) findViewById(R.id.btn_forget_password);
        btn_login = (TextView) findViewById(R.id.btn_login);
        btn_go_register.setOnClickListener(this);
        btn_forget_password.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_register:
                Intent intent = new Intent(this, RegisterActivity_One.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btn_forget_password:
                Intent i = new Intent(this, ForgetPswActivityOne.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.btn_login:
                Intent main = new Intent(this, HomeActivity.class);
                startActivity(main);
                finish();
                break;
        }
    }
}
