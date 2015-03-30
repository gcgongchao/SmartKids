package com.cmbb.smartkids.activities.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.broadcast.ToastBroadcast;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.network.ResponseActivityCallback;
import com.cmbb.smartkids.network.model.ResponseModel;
import com.cmbb.smartkids.tools.logger.Log;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by N.Sun
 */
public class RegisterActivity_One extends BaseActivity {
    private static final String TAG = RegisterActivity_One.class.getSimpleName();

    TextView btn_go_registertwo;
    EditText et_login_phone;

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
        et_login_phone = (EditText) findViewById(R.id.et_login_phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_registertwo:
                attemptRegister();

                break;
        }
    }

    private void attemptRegister() {
        final String phoneNo = et_login_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNo)) {
            showToast("请输入手机号码");
            return;
        }
        //手机号码正则表达式
        if (!Constants.User.isMobileNo(phoneNo)) {
            showToast("请输入正确的手机号码");
            return;
        }
        showWaitDialog("正在获取验证码..");
        Map<String, String> body = new HashMap<>();
        body.put("registerPhone", phoneNo);
        OkHttp.asyncPost(Constants.User.VALIDPHONE_URL, body,
                new ResponseActivityCallback<ResponseModel>(this, ResponseModel.class) {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        super.onFailure(request, e);
                    }

                    @Override
                    public void onSuccess(ResponseModel data) {
                        super.onSuccess(data);
                        Log.i(TAG, "CODE = " + data.getCode());
                        if ("1".equals(data.getCode().trim())) {
                            // showToast
                            Intent intent = new Intent(Constants.INTENT_ACTION_TOAST);
                            intent.putExtra(ToastBroadcast.ToastFLAG, ToastBroadcast.SHOW_TOAST_PARAM);
                            intent.putExtra(ToastBroadcast.SHOW_TOAST_Message, "请查收验证码");
                            sendBroadcast(intent);

                            Intent intent1 = new Intent(RegisterActivity_One.this,
                                    RegisterActivity_Two.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent1.putExtra("phone", phoneNo);
                            startActivity(intent1);
                        } else {
                            Intent intent = new Intent(Constants.INTENT_ACTION_TOAST);
                            intent.putExtra(ToastBroadcast.ToastFLAG, ToastBroadcast.SHOW_TOAST_PARAM);
                            intent.putExtra(ToastBroadcast.SHOW_TOAST_Message, data.getContext().getPresentation());
                            sendBroadcast(intent);
                        }
                    }
                });

    }
}
