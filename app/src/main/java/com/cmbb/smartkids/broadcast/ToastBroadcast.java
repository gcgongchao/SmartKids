package com.cmbb.smartkids.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cmbb.smartkids.base.BaseActivity;

/**
 * Created by N.Sun
 */
public class ToastBroadcast extends BroadcastReceiver {
    private static final String TAG = ToastBroadcast.class.getSimpleName();
    public static final String ToastFLAG = "toast_flag";

    public static final String SHOW_TOAST_Message = "show_toast_message";

    public static final String SHOW_TOAST_PIC_RES = "show_toast_pic_res";

    public static final int SHOW_TOAST_PIC_PARAM = 2;

    public static final int SHOW_TOAST_PARAM = 1;

    BaseActivity activity = null;

    public ToastBroadcast(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != activity) {
            switch (intent.getExtras().getInt(ToastFLAG)) {
                case SHOW_TOAST_PIC_PARAM:
                    activity.showToast(intent.getExtras().getString(SHOW_TOAST_Message), intent.getExtras().getInt(SHOW_TOAST_PIC_RES));
                    break;
                case SHOW_TOAST_PARAM:
                    activity.showToast(intent.getExtras().getString(SHOW_TOAST_Message));
                    break;

            }

        }

    }
}
