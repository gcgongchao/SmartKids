package com.cmbb.smartkids.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.DisplayMetrics;

import com.cmbb.smartkids.tools.ApkInfoTools;
import com.cmbb.smartkids.tools.logger.Log;
import com.cmbb.smartkids.tools.logger.LogWrapper;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by N.Sun
 */
public abstract class BaseApplication extends Application {

    private final static String TAG = BaseActivity.class.getSimpleName();

    private static String PREF_NAME = "SmartKids.pref";

    static Context sContext;

    private static boolean sIsAtLeastGB;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            //版本大于等于 9
            sIsAtLeastGB = true;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "133---------------");
        sContext = getApplicationContext();
        int pid = android.os.Process.myPid();
        String processAppName = ApkInfoTools.getAppName(context(), pid);
        if (processAppName == null || processAppName.equals("")) return;
        Log.i(TAG, "11---------------");
        initLog();
        init();
    }

    protected abstract void init();

    private void initLog() {
        Log.setLogNode(new LogWrapper());
    }

    public static BaseApplication context() {
        return (BaseApplication) sContext;
    }

    public static void apply(SharedPreferences.Editor editor) {
        if (sIsAtLeastGB) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static SharedPreferences getPreferences() {
        SharedPreferences pre = context().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pre;
    }

    public static int[] getDisplaySize() {
        return new int[]{getPreferences().getInt("screen_width", 480),
                getPreferences().getInt("screen_height", 854)};
    }

    public static void saveDisplaySize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt("screen_width", displayMetrics.widthPixels);
        editor.putInt("screen_height", displayMetrics.widthPixels);
        apply(editor);
    }
}
