package com.cmbb.smartkids.tools;

import java.io.File;
import java.text.NumberFormat;
import java.util.UUID;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.cmbb.smartkids.Application;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class TDevice {

    public static boolean GTE_HC;
    public static boolean GTE_ICS;
    public static boolean PRE_HC;
    private static Boolean _hasBigScreen = null;
    private static Boolean _hasCamera = null;
    private static Boolean _isTablet = null;
    private static Integer _loadFactor = null;
    private static int _pageSize = -1;
    public static float displayDensity = 0.0F;

    static {
        boolean flag = true;
        boolean flag1;
        boolean flag2;
        if (Build.VERSION.SDK_INT >= 14)
            flag1 = flag;
        else
            flag1 = false;
        GTE_ICS = flag1;
        if (Build.VERSION.SDK_INT >= 11)
            flag2 = flag;
        else
            flag2 = false;
        GTE_HC = flag2;
        if (Build.VERSION.SDK_INT >= 11)
            flag = false;
        PRE_HC = flag;
    }

    public TDevice() {
    }

    public static float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }

    public static int getDefaultLoadFactor() {
        if (_loadFactor == null) {
            Integer integer = Integer.valueOf(0xf & Application.context()
                    .getResources().getConfiguration().screenLayout);
            _loadFactor = integer;
            _loadFactor = Integer.valueOf(Math.max(integer.intValue(), 1));
        }
        return _loadFactor.intValue();
    }

    public static float getDensity() {
        if (displayDensity == 0.0)
            displayDensity = getDisplayMetrics().density;
        return displayDensity;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) Application.context().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

    public static int getPageSize() {
        if (_pageSize == -1)
            if (TDevice.isTablet())
                _pageSize = 50;
            else if (TDevice.hasBigScreen())
                _pageSize = 20;
            else
                _pageSize = 10;
        return _pageSize;
    }

    public static float getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public static float getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static String getUdid() {
        String udid = Application.getPreferences().getString("udid", "");
        if (udid.length() == 0) {
            SharedPreferences.Editor editor = Application.getPreferences()
                    .edit();
            udid = String.format("%s", UUID.randomUUID());
            editor.putString("udid", udid);
            editor.commit();
        }
        return udid;
    }

    public static boolean hasBigScreen() {
        boolean flag = true;
        if (_hasBigScreen == null) {
            boolean flag1;
            Boolean boolean1;
            if ((0xf & Application.context().getResources().getConfiguration().screenLayout) >= 3)
                flag1 = flag;
            else
                flag1 = false;
            boolean1 = Boolean.valueOf(flag1);
            _hasBigScreen = boolean1;
            if (!boolean1.booleanValue()) {
                if (getDensity() <= 1.5F)
                    flag = false;
                _hasBigScreen = Boolean.valueOf(flag);
            }
        }
        return _hasBigScreen.booleanValue();
    }

    public static final boolean hasCamera() {
        if (_hasCamera == null) {
            PackageManager pckMgr = Application.context().getPackageManager();
            boolean flag = pckMgr
                    .hasSystemFeature("android.hardware.camera.front");
            boolean flag1 = pckMgr.hasSystemFeature("android.hardware.camera");
            boolean flag2;
            if (flag || flag1)
                flag2 = true;
            else
                flag2 = false;
            _hasCamera = Boolean.valueOf(flag2);
        }
        return _hasCamera.booleanValue();
    }

    public static boolean hasHardwareMenuKey(Context context) {
        boolean flag = false;
        if (PRE_HC)
            flag = true;
        else if (GTE_ICS) {
            flag = ViewConfiguration.get(context).hasPermanentMenuKey();
        } else
            flag = false;
        return flag;
    }

    public static boolean hasInternet() {
        boolean flag;
        if (((ConnectivityManager) Application.context().getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean gotoGoogleMarket(Activity activity, String pck) {
        try {
            Intent intent = new Intent();
            intent.setPackage("com.android.vending");
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + pck));
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isPackageExist(String pckName) {
        try {
            PackageInfo pckInfo = Application.context().getPackageManager()
                    .getPackageInfo(pckName, 0);
            if (pckInfo != null)
                return true;
        } catch (NameNotFoundException e) {
        }
        return false;
    }

    public static void hideAnimatedView(View view) {
        if (PRE_HC && view != null)
            view.setPadding(view.getWidth(), 0, 0, 0);
    }

    public static void hideSoftKeyboard(View view) {
        if (view == null)
            return;
        ((InputMethodManager) Application.context().getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                view.getWindowToken(), 0);
    }

    public static boolean isLandscape() {
        boolean flag;
        if (Application.context().getResources().getConfiguration().orientation == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isPortrait() {
        boolean flag = true;
        if (Application.context().getResources().getConfiguration().orientation != 1)
            flag = false;
        return flag;
    }

    public static boolean isTablet() {
        if (_isTablet == null) {
            boolean flag;
            if ((0xf & Application.context().getResources().getConfiguration().screenLayout) >= 3)
                flag = true;
            else
                flag = false;
            _isTablet = Boolean.valueOf(flag);
        }
        return _isTablet.booleanValue();
    }

    public static float pixelsToDp(float f) {
        return f / (getDisplayMetrics().densityDpi / 160F);
    }

    public static void showAnimatedView(View view) {
        if (PRE_HC && view != null)
            view.setPadding(0, 0, 0, 0);
    }

    public static void showSoftKeyboard(Dialog dialog) {
        dialog.getWindow().setSoftInputMode(4);
    }

    public static void showSoftKeyboard(View view) {
        ((InputMethodManager) Application.context().getSystemService(
                Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 1);
    }

    public static boolean isSdcardReady() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    public static String getCurCountryLan() {
        return Application.context().getResources().getConfiguration().locale
                .getLanguage()
                + "-"
                + Application.context().getResources().getConfiguration().locale
                .getCountry();
    }

    public static boolean isZhCN() {
        String lang = Application.context().getResources().getConfiguration().locale
                .getCountry();
        if (lang.equalsIgnoreCase("CN")) {
            return true;
        }
        return false;
    }

    public static String percent(double p1, double p2) {
        String str;
        double p3 = p1 / p2;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        str = nf.format(p3);
        return str;
    }

    public static void gotoMarket(Context context, String pck) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + pck));
        context.startActivity(intent);
    }

    public static void openAppInMarket(Context context) {
        if (context != null) {
            String pckName = context.getPackageName();
            try {
                gotoMarket(context, pckName);
            } catch (Exception ex) {
                try {
                    String otherMarketUri = "http://market.android.com/details?id="
                            + pckName;
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(otherMarketUri));
                    context.startActivity(intent);
                } catch (Exception e) {

                }
            }
        }
    }

    public static void setFullScreen(Activity activity) {
        WindowManager.LayoutParams params = activity.getWindow()
                .getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(params);
        activity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void cancelFullScreen(Activity activity) {
        WindowManager.LayoutParams params = activity.getWindow()
                .getAttributes();
        params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().setAttributes(params);
        activity.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static PackageInfo getPackageInfo(String pckName) {
        try {
            return Application.context().getPackageManager()
                    .getPackageInfo(pckName, 0);
        } catch (NameNotFoundException e) {
        }
        return null;
    }

    public static int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = Application.context().getPackageManager()
                    .getPackageInfo(Application.context().getPackageName(), 0).versionCode;
        } catch (NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }

    public static String getVersionName() {
        String name = "";
        try {
            name = Application.context().getPackageManager()
                    .getPackageInfo(Application.context().getPackageName(), 0).versionName;
        } catch (NameNotFoundException ex) {
            name = "";
        }
        return name;
    }

    public static boolean isScreenOn() {
        PowerManager pm = (PowerManager) Application.context()
                .getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }

    public static void installAPK(Context context, File file) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void openDial(Context context, String number) {
        Uri uri = Uri.parse("tel:" + number);
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(it);
    }

    public static void openSMS(Context context, String smsBody, String tel) {
        Uri uri = Uri.parse("smsto:" + tel);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsBody);
        context.startActivity(it);
    }

    //获取总的接收字节（mobile and wifi）
    public static long getTotalRxBytes() {
        return TrafficStats.getUidRxBytes(android.os.Process.myPid()) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats
                .getUidRxBytes(android.os.Process.myPid()) / 1024);
    }

    //获取总的发送字节（mobile and wifi）
    public static long getTotalTxBytes() {
        return TrafficStats.getUidTxBytes(android.os.Process.myPid()) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats
                .getUidTxBytes(android.os.Process.myPid()) / 1024);
    }

}
