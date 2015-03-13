package com.cmbb.smartkids.activities;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.account.ApiKeyProvider;
import com.cmbb.smartkids.account.LogoutService;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.FeedContract;
import com.cmbb.smartkids.location.BaiduLocation;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.tools.logger.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends BaseActivity implements BDLocationListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    protected BaiduLocation mBaiduLocation;

    protected Button btn_authtoken, btn_waitdialog_with_cancle, btn_exit, btn_toast, btn_toast_normal,
            btn_asyncget, btn_triggerrefresh;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        checkAuth();
        initView();
        initLocation();
    }

    private void checkAuth() {
        ApiKeyProvider.getAuthKey(this, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    Log.i(TAG, future.getResult().getString(AccountManager.KEY_AUTHTOKEN));
                } catch (OperationCanceledException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (AuthenticatorException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initLocation() {
        mBaiduLocation = new BaiduLocation(this);
    }

    private void initView() {
        btn_authtoken = (Button) findViewById(R.id.btn_authtoken);
        btn_authtoken.setOnClickListener(this);
        btn_waitdialog_with_cancle = (Button) findViewById(R.id.btn_waitdialog_with_cancle);
        btn_waitdialog_with_cancle.setOnClickListener(this);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);
        btn_toast = (Button) findViewById(R.id.btn_toast);
        btn_toast.setOnClickListener(this);
        btn_toast_normal = (Button) findViewById(R.id.btn_toast_normal);
        btn_toast_normal.setOnClickListener(this);
        btn_asyncget = (Button) findViewById(R.id.btn_asyncget);
        btn_asyncget.setOnClickListener(this);
        btn_triggerrefresh = (Button) findViewById(R.id.btn_triggerrefresh);
        btn_triggerrefresh.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_authtoken:
                AccountManager accountManager = AccountManager.get(this);
                accountManager.getAuthTokenByFeatures(Constants.Auth.SMARTKIDS_ACCOUNT_TYPE, Constants.Auth.AUTHTOKEN_TYPE, new String[0], this, null, null, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            String result = future.getResult().getString(AccountManager.KEY_AUTHTOKEN);
                            Log.i(TAG, "result = " + result);
                        } catch (OperationCanceledException e) {
                            e.printStackTrace();
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (AuthenticatorException e) {
                            e.printStackTrace();
                        }
                    }
                }, null);
                break;

            case R.id.btn_waitdialog_with_cancle:
                showCancelableWaitDialog(R.string.loading, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.i(TAG, "hide");
                        hideWaitDialog();
                    }
                });
                break;
            case R.id.btn_exit:
                sendBroadcast(new Intent(Constants.INTENT_ACTION_EXIT_APP));
                break;
            case R.id.btn_toast:
                showPinterestToast("MEIZU", R.mipmap.ic_launcher, Gravity.CENTER);
                break;
            case R.id.btn_toast_normal:
                mBaiduLocation.startLocation();
                break;
            case R.id.btn_asyncget:
                showWaitDialog("加载中...");
                try {
                    OkHttp.asyncGet("http://www.baidu.com", new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Log.i(TAG, "request = " + request);
                            Log.i(TAG, "IOException = " + e);
                            hideWaitDialog();
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            hideWaitDialog();
                            Log.i(TAG, "cache response:    " + response.cacheResponse());
                            Log.i(TAG, "network response:  " + response.networkResponse());
                            if (response.isSuccessful()) {
                                Log.i(TAG, "isSuccessful = " + response.isSuccessful());
                                Headers responseHeaders = response.headers();
                                for (int i = 0; i < responseHeaders.size(); i++) {
                                    Log.i(TAG, responseHeaders.name(i) + ": " + responseHeaders
                                            .value(i));
                                }
                            }
                            Log.i(TAG, response.body().string());
                        }
                    });
                } catch (IOException e) {
                    hideWaitDialog();
                    e.printStackTrace();
                }
                break;
            case R.id.btn_triggerrefresh:
                Bundle bundle = new Bundle();
                // Disable sync backoff and ignore sync preferences. In other words...perform sync NOW!
                bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
                bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
                ContentResolver.requestSync(AccountManager.get(this).getAccounts()[0],
                        FeedContract.CONTENT_AUTHORITY, bundle);
                break;
        }
        super.onClick(v);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Log.i(TAG, "Logout = " + LogoutService.logout(this));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null) return;
        showToast("地址：" + bdLocation.getProvince() + bdLocation.getCity(), R.mipmap.ic_launcher);
        mBaiduLocation.stopLocation();
        hideWaitDialog();
    }
}
