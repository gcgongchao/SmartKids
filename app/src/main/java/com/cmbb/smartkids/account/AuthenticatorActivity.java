package com.cmbb.smartkids.account;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cmbb.smartkids.Application;
import com.cmbb.smartkids.BuildConfig;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activities.HomeActivity;
import com.cmbb.smartkids.activities.psw.ForgetPswActivityOne;
import com.cmbb.smartkids.activities.register.RegisterActivity_One;
import com.cmbb.smartkids.activities.register.RegisterActivity_Two;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.broadcast.ToastBroadcast;
import com.cmbb.smartkids.db.FeedContract;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.network.ResponseActivityCallback;
import com.cmbb.smartkids.network.model.ResponseModel;
import com.cmbb.smartkids.tools.logger.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by N.Sun
 */
public class AuthenticatorActivity extends BaseActivity {

    private static final String TAG = AuthenticatorActivity.class.getSimpleName();

    TextView btn_go_register, btn_forget_password, btn_login;
    EditText et_login_phone, et_login_psw;

    private static final long SYNC_FREQUENCY = 60 * 60;  // 1 hour (in seconds)

    //param_confirm_credentials
    public static final String PARAM_CONFIRM_CREDENTIALS = "confirmCredentials";
    //param_password
    public static final String PARAM_PASSWORD = "password";
    //param_username
    public static final String PARAM_USERNAME = "username";
    //param_authtoken_type
    public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";

    private AccountManager accountManager;

    private AccountAuthenticatorResponse mAccountAuthenticatorResponse = null;
    private Bundle mResultBundle = null;

    // 登录参数

    private boolean confirmCredentials = false;
    protected boolean requestNewAccount = false;
    private String authToken;
    private String authTokenType;

    private String username;
    private String password;
    private String token;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authenticator;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        initAuth();
        initView();
    }

    private void initAuth() {
        mAccountAuthenticatorResponse =
                getIntent().getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);

        if (mAccountAuthenticatorResponse != null) {
            mAccountAuthenticatorResponse.onRequestContinued();
        }
        accountManager = AccountManager.get(this);
        //获取参数
        final Intent intent = getIntent();
        //username = intent.getStringExtra(PARAM_USERNAME);
        authTokenType = intent.getStringExtra(PARAM_AUTHTOKEN_TYPE);
        confirmCredentials = intent.getBooleanExtra(PARAM_CONFIRM_CREDENTIALS, false);
        requestNewAccount = username == null;
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
        et_login_phone = (EditText) findViewById(R.id.et_login_phone);
        et_login_psw = (EditText) findViewById(R.id.et_login_psw);
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
                handleLogin();
                break;
        }

    }

    //处理登录
    private void handleLogin() {
        username = et_login_phone.getText().toString().trim();
        password = et_login_psw.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            showToast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("请输入密码");
            return;
        }
        if (!Constants.User.isMobileNo(username)) {
            showToast("请输入正确的手机号码");
            return;
        }
        // 请求服务器获取User对象
        showWaitDialog("正在登录...");
        Map<String, String> body = new HashMap<>();
        body.put("registerPhone", username);
        body.put("registerPassword", password);
        OkHttp.asyncPost(Constants.User.LOGINS_URL, body,
                new ResponseActivityCallback<ResponseModel>(this, ResponseModel.class) {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        super.onFailure(request, e);
                    }

                    @Override
                    public void onSuccess(ResponseModel data) {
                        super.onSuccess(data);
                        if ("1".equals(data.getCode().trim())) {
                            // showToast
                            Intent intent = new Intent(Constants.INTENT_ACTION_Toast);
                            intent.putExtra(ToastBroadcast.ToastFLAG, ToastBroadcast.SHOW_TOAST_PARAM);
                            intent.putExtra(ToastBroadcast.SHOW_TOAST_Message, data.getContext().getPresentation());
                            sendBroadcast(intent);
                            // 注册系统 获取token
                            token = data.getContext().getToken();
                            // token 全局
                            Application.token = data.getContext().getToken();
                            onAuthenticationResult();

                        } else {
                            // 发送Toast
                            Intent intent = new Intent(Constants.INTENT_ACTION_Toast);
                            intent.putExtra(ToastBroadcast.ToastFLAG, ToastBroadcast.SHOW_TOAST_PARAM);
                            intent.putExtra(ToastBroadcast.SHOW_TOAST_Message, data.getContext().getPresentation());
                            sendBroadcast(intent);
                        }
                    }
                });

//        User user = new User();
//        user.setAvatarUrl("http://www.meizu.com");
//        user.setPhone("15201921714");
//        user.setUsername("N.Sun");
//        user.setToken("fwheif13sd289190ehfwihfuh");
//
//        email = user.getUsername();
//        password = "niesen12345";
//        token = user.getToken();
//
//        onAuthenticationResult();
//        if (BuildConfig.DEBUG) {
//            Log.i(TAG, "格式化 = " + String.format("%s=%s&%s=%s", PARAM_USERNAME, email, PARAM_PASSWORD,
//                    password));
//        }
    }

    private void onAuthenticationResult() {
        if (!confirmCredentials) {
            finishLogin();
            Log.i(TAG, "finishLogin");
        } else {
            finishConfirmCredentials(true);
            Log.i(TAG, "finishConfirmCredentials");
        }

    }

    private void finishConfirmCredentials(boolean result) {
        final Account account = new Account(username, Constants.Auth.SMARTKIDS_ACCOUNT_TYPE);
        accountManager.setPassword(account, password);
        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_BOOLEAN_RESULT, result);
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        Intent intent1 = new Intent(AuthenticatorActivity.this,
                HomeActivity.class);
        startActivity(intent1);
        finish();
    }

    private void finishLogin() {
        authToken = token;
        final Account account = new Account(username, Constants.Auth.SMARTKIDS_ACCOUNT_TYPE);
        if (requestNewAccount) {
            accountManager.addAccountExplicitly(account, password, null);
            accountManager.setAuthToken(account, Constants.Auth.AUTHTOKEN_TYPE, authToken);
            //设置自动同步(目前关闭)
            // Inform the system that this account supports sync
            //ContentResolver.setIsSyncable(account, FeedContract.CONTENT_AUTHORITY, 1);
            // Inform the system that this account is eligible for auto sync when the network is up
            //ContentResolver.setSyncAutomatically(account, FeedContract.CONTENT_AUTHORITY, true);
            // Recommend a schedule for automatic synchronization. The system may modify this based
            // on other scheduled syncs and network utilization.
            //ContentResolver.addPeriodicSync(account, FeedContract.CONTENT_AUTHORITY,new Bundle(), SYNC_FREQUENCY);
            // 设置Sharepreference flag

        } else {
            accountManager.setPassword(account, password);
        }
        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, username);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, Constants.Auth.SMARTKIDS_ACCOUNT_NAME);
        if (authTokenType != null && authTokenType.equals(Constants.Auth.AUTHTOKEN_TYPE)) {
            intent.putExtra(AccountManager.KEY_AUTHTOKEN, authToken);
        }
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        // 返回主页
        Intent intent1 = new Intent(AuthenticatorActivity.this,
                HomeActivity.class);
        startActivity(intent1);
        finish();
    }

    public final void setAccountAuthenticatorResult(Bundle result) {
        mResultBundle = result;
    }

    @Override
    public void finish() {
        if (mAccountAuthenticatorResponse != null) {
            // send the result bundle back if set, otherwise send an error.
            if (mResultBundle != null) {
                mAccountAuthenticatorResponse.onResult(mResultBundle);
            } else {
                mAccountAuthenticatorResponse.onError(AccountManager.ERROR_CODE_CANCELED,
                        "canceled");
            }
            mAccountAuthenticatorResponse = null;
        }
        super.finish();
    }

}
