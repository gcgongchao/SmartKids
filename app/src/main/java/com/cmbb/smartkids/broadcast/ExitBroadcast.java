package com.cmbb.smartkids.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.cmbb.smartkids.tools.logger.Log;

public class ExitBroadcast extends BroadcastReceiver {
    private static final String TAG = ExitBroadcast.class.getSimpleName();
    FragmentActivity activity = null;

    public ExitBroadcast(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "程序退出");
        if (null != activity) {
            activity.finish();
        }
    }
}
