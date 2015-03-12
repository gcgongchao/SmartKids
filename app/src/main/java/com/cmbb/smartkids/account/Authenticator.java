package com.cmbb.smartkids.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cmbb.smartkids.BuildConfig;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.tools.logger.Log;

/**
 * Created by N.Sun
 */
public class Authenticator extends AbstractAccountAuthenticator {

    private static final String TAG = Authenticator.class.getSimpleName();

    private Context mContext;

    public Authenticator(Context context) {
        super(context);
        this.mContext = context;
    }


    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "addAccount()");
        }

        final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(AuthenticatorActivity.PARAM_AUTHTOKEN_TYPE, authTokenType);
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "AuthenticatorActivity.PARAM_AUTHTOKEN_TYPE = " + authTokenType);
        }
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "getToken");
        }
        final String authToken = AccountManager.get(mContext).peekAuthToken
                (account, authTokenType);
        final Bundle bundle = new Bundle();
        bundle.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
        bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, Constants.Auth.SMARTKIDS_ACCOUNT_TYPE);
        bundle.putString(AccountManager.KEY_AUTHTOKEN, authToken);

        return bundle;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return authTokenType.equals(Constants.Auth.AUTHTOKEN_TYPE) ? authTokenType : null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        final Bundle result = new Bundle();
        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false);
        return result;
    }
}
