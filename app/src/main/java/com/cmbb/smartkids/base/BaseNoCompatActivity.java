package com.cmbb.smartkids.base;

import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmbb.smartkids.Application;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.ui.DialogControl;
import com.cmbb.smartkids.base.ui.DialogHelper;
import com.cmbb.smartkids.base.ui.PinterestToast;
import com.cmbb.smartkids.base.ui.ToastControl;
import com.cmbb.smartkids.base.ui.WaitDialog;
import com.cmbb.smartkids.base.ui.header2.FadingActionBarHelper;
import com.cmbb.smartkids.broadcast.ExitBroadcast;
import com.cmbb.smartkids.tools.logger.Log;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by N.Sun
 */
public abstract class BaseNoCompatActivity extends Activity implements View.OnClickListener,
        DialogControl, ToastControl {

    private static final String TAG = BaseActivity.class.getSimpleName();

    protected FadingActionBarHelper mFadingActionBarHelper;
    // Toast
    private static String lastToast = "";
    private static long lastToastTime;

    protected boolean _isVisible;
    private WaitDialog _waitDialog;
    // EXIT
    protected BroadcastReceiver existReceiver;

    protected ActionBar mActionBar;

    // ContentView
    public abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        BaseApplication.saveDisplaySize(this);
        initActionbar();
        init();
        init(savedInstanceState);
        initExit();
        initShare();
    }

    protected void initActionbar() {
        try {
            mActionBar = (ActionBar) getActionBar();
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(false);
        } catch (NullPointerException e) {

        }

        mFadingActionBarHelper = new FadingActionBarHelper(getActionBar(),
                getResources().getDrawable(R.color.theme_color));
    }

    private void initExit() {
        existReceiver = new ExitBroadcast(this);
        IntentFilter filter = new IntentFilter(Constants.INTENT_ACTION_EXIT_APP);
        registerReceiver(existReceiver, filter);
    }

    public FadingActionBarHelper getFadingActionBarHelper() {
        return mFadingActionBarHelper;
    }

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void init();

    // umeng share
    private void initShare() {
        // UMSocialService umSocialService = UMServiceFactory.getUmS
    }

    @Override
    protected void onResume() {
        _isVisible = true;
        if (System.currentTimeMillis() - Application.getLastClearImageCache() > 24 * 3600 * 1000l) {
            ImageLoader.getInstance().clearDiskCache();
            ImageLoader.getInstance().clearMemoryCache();
            Application.setLastClearImageCache(System.currentTimeMillis());
        }
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        _isVisible = false;
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(existReceiver);
        ImageLoader.getInstance().clearMemoryCache();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    public boolean isVisible() {
        return _isVisible;
    }

    @Override
    public void showToast(int message) {
        showToast(message, Toast.LENGTH_LONG, 0);
    }

    @Override
    public void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, 17);
    }

    @Override
    public void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon);
    }

    @Override
    public void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, 17);
    }

    @Override
    public void showToastShort(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    @Override
    public void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, 17);
    }

    @Override
    public void showToastShort(int message, Object... args) {
        showToast(message, Toast.LENGTH_SHORT, 0, 17, args);
    }

    @Override
    public void showToast(int message, int duration, int icon) {
        showToast(message, duration, icon, 17);
    }

    @Override
    public void showToast(int message, int duration, int icon,
                          int gravity) {
        showToast(Application.context().getString(message), duration, icon, gravity);
    }

    @Override
    public void showToast(int message, int duration, int icon,
                          int gravity, Object... args) {
        showToast(Application.context().getString(message, args), duration, icon, gravity);
    }

    @Override
    public void showToast(String message, int duration, int icon,
                          int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(Application.context()).inflate(
                        R.layout.toast_view, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setImageResource(icon);
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(Application.context());
                toast.setView(view);
                toast.setGravity(gravity, 0, 0);
                // toast.setGravity(Gravity.TOP|Gravity.LEFT,0 ,0);
                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void showPinterestToast(int msgResid, int icon, int gravity) {
        showPinterestToast(getString(msgResid), icon, gravity);
    }

    @Override
    public void showPinterestToast(String message, int icon, int gravity) {
        PinterestToast toast = new PinterestToast(this);
        toast.setMessage(message);
        toast.setMessageIc(icon);
        toast.setLayoutGravity(gravity);
        toast.show();
    }

    @Override
    public WaitDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    @Override
    public WaitDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public WaitDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelper.getWaitDialog(this, message);
            } else
                _waitDialog.setMessage(message);
            _waitDialog.show();
            return _waitDialog;
        }
        return null;
    }

    @Override
    public WaitDialog showCancelableWaitDialog(int resid,
                                               DialogInterface.OnCancelListener listener) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelper.getCancelableWaitDialog(this,
                        getString(resid));
            } else
                _waitDialog.setMessage(getString(resid));
            _waitDialog.setOnCancelListener(listener);
            _waitDialog.show();
            return _waitDialog;
        }
        return null;
    }

    @Override
    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void recycleBitmap(ImageView view) {
        if (view == null)
            return;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) view.getDrawable();
        if (bitmapDrawable != null) {
            view.setImageBitmap(null);
            // 如果图片还未回收，先强制回收该图片
            if (bitmapDrawable.getBitmap() != null
                    && !bitmapDrawable.getBitmap().isRecycled()) {
                Log.i(TAG, "图片回收");
                bitmapDrawable.getBitmap().recycle();
            }
        }
    }
}


