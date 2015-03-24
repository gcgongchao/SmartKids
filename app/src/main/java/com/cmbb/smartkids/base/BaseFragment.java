package com.cmbb.smartkids.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.ui.DialogControl;
import com.cmbb.smartkids.base.ui.ToastControl;
import com.cmbb.smartkids.base.ui.WaitDialog;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Sen on 2015/3/6.
 */
public class BaseFragment extends Fragment implements View.OnClickListener{

    protected static String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
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
