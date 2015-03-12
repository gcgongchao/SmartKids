package com.cmbb.smartkids.location;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.cmbb.smartkids.Application;
import com.cmbb.smartkids.tools.logger.Log;

import android.content.Context;

/**
 * @author N.Sun
 * @ClassName: BaiduLocation
 * @Description: TODO(百度定位：1.创建对象 2.调用startLocation)
 * @email niesen918@gmail.com
 * @date 2014-12-10
 */
public class BaiduLocation {
    private final static String TAG = BaiduLocation.class.getSimpleName();


    public LocationClient mLocClient;

    public BaiduLocation(BDLocationListener bdLocationListener) {
        mLocClient = new LocationClient(Application.context());
        mLocClient.registerLocationListener(bdLocationListener);
        initLocation();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(1000 * 60);// 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
        mLocClient.setLocOption(option);
    }

    public void restartLocation() {
        if (mLocClient != null && mLocClient.isStarted()) {
            Log.i(TAG, "locClient is started");
            mLocClient.requestLocation();
        } else
            Log.i(TAG, "locClient is null or not started");
    }

    public void startLocation() {
        mLocClient.start();
    }

    public void stopLocation() {
        mLocClient.stop();
    }

}
