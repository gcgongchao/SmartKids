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
    private AccountManager accountManager;

    public ApiKeyProvider(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public String getAuthKey(final Activity activity) throws
            AuthenticatorException, OperationCanceledException, IOException {
        final AccountManagerFuture<Bundle> accountManagerFuture = accountManager
                .getAuthTokenByFeatures(Constants.Auth.SMARTKIDS_ACCOUNT_TYPE,
                        Constants.Auth.AUTHTOKEN_TYPE, new String[0], activity, null, null,
                        null, null);
        return accountManagerFuture.getResult().getString(AccountManager.KEY_AUTHTOKEN);
    }
}
