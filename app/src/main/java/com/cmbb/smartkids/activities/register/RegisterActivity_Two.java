package com.cmbb.smartkids.activities.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cmbb.smartkids.Application;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activities.HomeActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.broadcast.ToastBroadcast;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.network.ResponseActivityCallback;
import com.cmbb.smartkids.network.model.ResponseModel;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by N.Sun.
 */
public class RegisterActivity_Two extends BaseActivity {
    private static final String TAG = RegisterActivity_Two.class.getSimpleName();

    EditText et_register_check, et_register_set_password, et_register_set_nickname;
    TextView btn_register;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_two;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        et_register_check = (EditText) findViewById(R.id.et_register_check);
        et_register_set_password = (EditText) findViewById(R.id.et_register_set_password);
        et_register_set_nickname = (EditText) findViewById(R.id.et_register_set_nickname);
        btn_register = (TextView) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                attemptRegister();
                break;
        }
        super.onClick(v);
    }

    private void attemptRegister() {
        String check = et_register_check.getText().toString().trim();
        String psw = et_register_set_password.getText().toString().trim();
        String nick = et_register_set_nickname.getText().toString().trim();
        if (TextUtils.isEmpty(check)) {
            showToast("验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(psw)) {
            showToast("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(nick)) {
            showToast("昵称不能为空");
            return;
        }
        if (psw.length() < 6) {
            showToast("密码长于6位");
            return;
        }
        showWaitDialog("正在注册...");
        Map<String, String> body = new HashMap<>();
        body.put("registerPhone", getIntent().getExtras().getString("phone"));
        body.put("registerPassword", psw);
        body.put("security", check);
        body.put("nick", nick);
        OkHttp.asyncPost(Constants.User.REGISTER_URL, body,
                new ResponseActivityCallback<ResponseModel>(RegisterActivity_Two.this,
                        ResponseModel.class) {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        super.onFailure(request, e);

                    }

                    @Override
                    public void onSuccess(ResponseModel data) {
                        super.onSuccess(data);
                        if ("1".equals(data.getCode())) {
                            // showToast
                            Intent intent = new Intent(Constants.INTENT_ACTION_Toast);
                            intent.putExtra(ToastBroadcast.ToastFLAG, ToastBroadcast.SHOW_TOAST_PARAM);
                            intent.putExtra(ToastBroadcast.SHOW_TOAST_Message, "注册成功");
                            sendBroadcast(intent);

                            // 设置全局Token
                            Application.token = data.getContext().getToken();
                            Intent intent1 = new Intent(RegisterActivity_Two.this,
                                    HomeActivity.class);
                            startActivity(intent1);
                        } else {
                            Intent intent = new Intent(Constants.INTENT_ACTION_Toast);
                            intent.putExtra(ToastBroadcast.ToastFLAG, ToastBroadcast.SHOW_TOAST_PARAM);
                            intent.putExtra(ToastBroadcast.SHOW_TOAST_Message, data.getContext().getPresentation());
                            sendBroadcast(intent);
                        }
                    }
                });
    }
}
