package com.cmbb.smartkids.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;

import com.cmbb.smartkids.BuildConfig;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.tools.logger.Log;

public class AuthenticationService extends Service {

    private static final String TAG = AuthenticationService.class.getSimpleName();
    private static final String ACCOUNT_NAME = "SmartKids";
    private Authenticator mAuthenticator;

    public AuthenticationService() {
    }

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "AuthenticationService created");
        }
        mAuthenticator = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {

        if (intent != null && AccountManager.ACTION_AUTHENTICATOR_INTENT.equals(intent.getAction())) {
            return getAuthenticator().getIBinder();
        }

        return mAuthenticator.getIBinder();
    }

    @Override
    public void onDestroy() {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "AuthenticationService destory");
        }
        super.onDestroy();
    }

    private Authenticator getAuthenticator() {
        if (mAuthenticator == null) {
            mAuthenticator = new Authenticator(this);
        }
        return mAuthenticator;
    }

}
