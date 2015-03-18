package com.cmbb.smartkids.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activities.login.LoginActivity;
import com.cmbb.smartkids.base.BaseActivity;

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
        initLogin();
    }



    private void initLogin() {
    }

    private void initTask() {
        final Intent intent = new Intent(this, LoginActivity.class);
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
