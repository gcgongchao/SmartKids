package com.cmbb.smartkids.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;

import com.cmbb.smartkids.BuildConfig;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.tools.logger.Log;

import java.io.IOException;

/**
 * Created by N.Sun
 */
public class LogoutService {

    private final static String TAG = LogoutService.class.getSimpleName();

    protected final Context mContext;

    public LogoutService(final Context mContext) {
        this.mContext = mContext;
    }

    public Boolean logout() throws AuthenticatorException, OperationCanceledException, IOException {
        final AccountManager accountManagerWithContext = AccountManager.get(mContext);
        if (accountManagerWithContext != null) {
            final Account[] accounts = accountManagerWithContext.getAccountsByType(Constants.Auth.SMARTKIDS_ACCOUNT_TYPE);
            if (accounts.length > 0) {
                final AccountManagerFuture<Boolean> removeAccountFuture =
                        accountManagerWithContext.removeAccount(accounts[0], null, null);
                return removeAccountFuture.getResult();
            }
        } else {
            if (BuildConfig.DEBUG) {
                Log.i(TAG, "accountManagerWithContext is null");
            }
        }
        return false;
    }
}
