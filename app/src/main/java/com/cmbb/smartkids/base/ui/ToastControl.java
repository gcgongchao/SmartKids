package com.cmbb.smartkids.base.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmbb.smartkids.Application;
import com.cmbb.smartkids.R;

/**
 * Created by N.Sun
 */
public interface ToastControl {

    public abstract void showToast(int message);

    public abstract void showToast(String message);

    public abstract void showToast(int message, int icon);

    public abstract void showToast(String message, int icon);

    public abstract void showToastShort(int message);

    public abstract void showToastShort(String message);

    public abstract void showToastShort(int message, Object... args);

    public abstract void showToast(int message, int duration, int icon);

    public abstract void showToast(int message, int duration, int icon,
                                 int gravity);

    public abstract void showToast(int message, int duration, int icon,
                                 int gravity, Object... args);

    public abstract void showToast(String message, int duration, int icon,
                                 int gravity);

    public abstract void showPinterestToast(int msgResid, int icon, int gravity);

    public abstract void showPinterestToast(String message, int icon, int gravity);

}
