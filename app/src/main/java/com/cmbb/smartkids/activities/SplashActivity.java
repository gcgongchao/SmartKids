package com.cmbb.smartkids.activities;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.cmbb.smartkids.Application;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.tools.logger.Log;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by N.Sun
 */
public class SplashActivity extends BaseActivity {

    private final static String TAG = SplashActivity.class.getSimpleName();
    private ImageView iv_splash_bg;

    private int[] drawBgs;
    private int index;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        initView();
        initTask();
        getToken();
    }

    @Override
    protected void onTokeReceived(String result) {
        super.onTokeReceived(result);
    }

    private void initTask() {
        final Intent intent = new Intent(this, HomeActivity.class);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(timerTask, 3000);
    }

    private void initView() {
        iv_splash_bg = (ImageView) findViewById(R.id.iv_splash_bg);
        drawBgs = new int[]{R.drawable.splash_backgroud_one, R.drawable.splash_backgroud_two, R.drawable.splash_backgroud_three,
                R.drawable.splash_backgroud_four, R.drawable.splash_backgroud_five, R.drawable.splash_backgroud_six, R.drawable.splash_backgroud_seven};

        index = new Random().nextInt(drawBgs.length);

        iv_splash_bg.setImageResource(drawBgs[index]);
    }
}
