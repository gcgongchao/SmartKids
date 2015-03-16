package com.cmbb.smartkids.activities;

import android.os.Bundle;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;

import java.util.Random;

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

    }

    private void initView() {
        iv_splash_bg = (ImageView) findViewById(R.id.iv_splash_bg);
        drawBgs = new int[]{R.drawable.splash_backgroud_one, R.drawable.splash_backgroud_two, R.drawable.splash_backgroud_three,
                R.drawable.splash_backgroud_four, R.drawable.splash_backgroud_five, R.drawable.splash_backgroud_six, R.drawable.splash_backgroud_seven};

        index = new Random().nextInt(drawBgs.length);

        iv_splash_bg.setImageResource(drawBgs[index]);
    }
}