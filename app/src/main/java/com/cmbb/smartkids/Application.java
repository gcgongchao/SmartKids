package com.cmbb.smartkids;

import android.content.SharedPreferences;

import com.cmbb.smartkids.base.BaseApplication;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.update.UmengUpdateAgent;

/**
 * Created by N.Sun
 */
public class Application extends BaseApplication {

    private static final String TAG = Application.class.getSimpleName();

    @Override
    protected void init() {
        initImager(context());
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);
    }

    private void initImager(BaseApplication context) {
        // 图像处理
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    public static long getLastClearImageCache() {
        return getPreferences().getLong("PREF_LAST_CLEAR_IMAGE_CACHE", 0);
    }

    public static void setLastClearImageCache(long time) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong("PREF_LAST_CLEAR_IMAGE_CACHE" + "用户Id", time);
        apply(editor);
    }
}
