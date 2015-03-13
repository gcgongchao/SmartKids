package com.cmbb.smartkids.account;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;

import com.cmbb.smartkids.base.Constants;

import java.io.IOException;

/**
 * Created by N.Sun
 */
public class ApiKeyProvider {

    public static void getAuthKey(final Activity activity, final AccountManagerCallback callback) {
        final AccountManagerFuture<Bundle> accountManagerFuture = AccountManager.get(activity)
                .getAuthTokenByFeatures(Constants.Auth.SMARTKIDS_ACCOUNT_TYPE,
                        Constants.Auth.AUTHTOKEN_TYPE, new String[0], activity, null, null,
                        callback, null);

    }
}
