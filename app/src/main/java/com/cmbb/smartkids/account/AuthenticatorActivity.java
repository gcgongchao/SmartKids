package com.cmbb.smartkids.account;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.Button;

import com.cmbb.smartkids.BuildConfig;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.FeedContract;
import com.cmbb.smartkids.tools.logger.Log;

/**
 * Created by N.Sun
 */
public class AuthenticatorActivity extends Activity {

    private static final String TAG = AuthenticatorActivity.class.getSimpleName();

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
    private String email;
    private String password;
    private String token;

    // View
    private Button btn_login;

    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        mAccountAuthenticatorResponse =
                getIntent().getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);

        if (mAccountAuthenticatorResponse != null) {
            mAccountAuthenticatorResponse.onRequestContinued();
        }
        accountManager = AccountManager.get(this);
        //获取参数
        final Intent intent = getIntent();
        email = intent.getStringExtra(PARAM_USERNAME);
        authTokenType = intent.getStringExtra(PARAM_AUTHTOKEN_TYPE);
        confirmCredentials = intent.getBooleanExtra(PARAM_CONFIRM_CREDENTIALS, false);
        requestNewAccount = email == null;
        // 设置界面
        setContentView(R.layout.activity_authenticator);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录
                handleLogin();
            }
        });
    }

    //处理登录
    private void handleLogin() {
        // 请求服务器获取User对象
        User user = new User();
        user.setAvatarUrl("http://www.meizu.com");
        user.setPhone("15201921714");
        user.setUsername("N.Sun");
        user.setToken("fwheif13sd289190ehfwihfuh");
        email = user.getUsername();
        password = "niesen12345";
        token = user.getToken();

        onAuthenticationResult();
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "格式化 = " + String.format("%s=%s&%s=%s", PARAM_USERNAME, email, PARAM_PASSWORD,
                    password));
        }
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
        final Account account = new Account(email, Constants.Auth.SMARTKIDS_ACCOUNT_TYPE);
        accountManager.setPassword(account, password);
        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_BOOLEAN_RESULT, result);
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void finishLogin() {
        authToken = token;
        final Account account = new Account(email, Constants.Auth.SMARTKIDS_ACCOUNT_TYPE);
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
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, email);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, Constants.Auth.SMARTKIDS_ACCOUNT_NAME);
        if (authTokenType != null && authTokenType.equals(Constants.Auth.AUTHTOKEN_TYPE)) {
            intent.putExtra(AccountManager.KEY_AUTHTOKEN, authToken);
        }
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
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
