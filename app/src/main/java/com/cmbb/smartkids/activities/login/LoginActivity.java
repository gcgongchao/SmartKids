package com.cmbb.smartkids.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activities.MainActivity2;
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
    protected void initActionBar() {
        ActionBar bar = getSupportActionBar();
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View v = getLayoutInflater().inflate(R.layout.custom_actionbar, null);
        TextView back = (TextView) v.findViewById(R.id.iv_bar_back);
        TextView title = (TextView) v.findViewById(R.id.tv_bar_title);
        title.setText("用户登录");
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBackPressed();
            }
        });
        bar.setCustomView(v);
        bar.show();
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
                Intent main = new Intent(this, MainActivity2.class);
                startActivity(main);
                finish();
                break;
        }
    }
}
