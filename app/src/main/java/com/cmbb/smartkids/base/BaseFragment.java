package com.cmbb.smartkids.base;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.cmbb.smartkids.Application;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.ui.DialogControl;
import com.cmbb.smartkids.base.ui.ToastControl;
import com.cmbb.smartkids.base.ui.WaitDialog;
import com.cmbb.smartkids.tools.logger.Log;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;

/**
 * Created by N.Sun
 */
public class BaseFragment extends Fragment implements View.OnClickListener {

    protected static String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
    }

    protected String getToken() {
        Log.i(TAG, TAG + " Token = " + Application.token);
        if (TextUtils.isEmpty(Application.token)) {
            AccountManager accountManager = AccountManager.get(getActivity());
            if (accountManager.getAccountsByType(Constants.Auth.SMARTKIDS_ACCOUNT_TYPE).length > 0) {
                accountManager.getAuthTokenByFeatures(Constants.Auth.SMARTKIDS_ACCOUNT_TYPE,
                        Constants.Auth.AUTHTOKEN_TYPE, new String[0], getActivity(), null, null,
                        new AccountManagerCallback<Bundle>() {
                            @Override
                            public void run(AccountManagerFuture<Bundle> future) {
                                try {
                                    String token = future.getResult().getString(AccountManager.KEY_AUTHTOKEN);
                                    if (!TextUtils.isEmpty(token)) {
                                        Application.token = token;
                                        onTokeReceived(token);
                                    }
                                } catch (OperationCanceledException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (AuthenticatorException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, null);
            }
        }

        return Application.token;
    }

    protected void onTokeReceived(String result) {
        // Load 数据
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

    public void showToast(int message) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToast(message);
        }
    }

    public void showToast(String message) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToast(message);
        }
    }

    public void showToast(int message, int icon) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToast(message, icon);
        }
    }

    public void showToast(String message, int icon) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToast(message, icon);
        }
    }

    public void showToastShort(int message) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToastShort(message);
        }
    }

    public void showToastShort(String message) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToastShort(message);
        }
    }

    public void showToastShort(int message, Object... args) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToastShort(message, args);
        }
    }

    public void showToast(int message, int duration, int icon) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToast(message, duration, icon);
        }
    }

    public void showToast(int message, int duration, int icon,
                          int gravity) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToast(message, duration, icon, gravity);
        }
    }

    public void showToast(int message, int duration, int icon,
                          int gravity, Object... args) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToast(message, duration, icon, gravity, args);
        }
    }

    public void showToast(String message, int duration, int icon,
                          int gravity) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showToast(message, duration, icon, gravity);
        }
    }

    public void showPinterestToast(int msgResid, int icon, int gravity) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showPinterestToast(msgResid, icon, gravity);
        }
    }

    public void showPinterestToast(String message, int icon, int gravity) {
        Activity activity = getActivity();
        if (activity instanceof ToastControl) {
            ((ToastControl) activity).showPinterestToast(message, icon, gravity);
        }
    }

    protected void hideWaitDialog() {
        Activity activity = getActivity();
        if (activity instanceof DialogControl) {
            ((DialogControl) activity).hideWaitDialog();
        }
    }

    protected WaitDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    protected WaitDialog showWaitDialog(int resid) {
        Activity activity = getActivity();
        if (activity instanceof DialogControl) {
            return ((DialogControl) activity).showWaitDialog(resid);
        }
        return null;
    }

    protected WaitDialog showWaitDialog(String messge) {
        Activity activity = getActivity();
        if (activity instanceof DialogControl) {
            return ((DialogControl) activity).showWaitDialog(messge);
        }
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
