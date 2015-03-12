package com.cmbb.smartkids.network;

import com.cmbb.smartkids.Application;
import com.cmbb.smartkids.tools.logger.Log;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by N.Sun
 */
public class OkHttp {
    private static final String TAG = "OkHttp";
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB

    // timeout
    static {
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        try {
            mOkHttpClient.setCache(new Cache(Application.context().getExternalCacheDir(), cacheSize));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 不使用异步线程。
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback) {
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    /**
     * 同步Get(一般不使用)
     *
     * @param url
     * @return String
     */
    public static String syncGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = execute(request);
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            Log.i(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        Log.i(TAG, "cache response:    " + response.cacheResponse());
        Log.i(TAG, "network response:  " + response.networkResponse());

        return response.body().string();
    }

    /**
     * 异步get
     *
     * @param url
     * @param callback
     * @return
     */
    public static void asyncGet(String url, Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        enqueue(request, callback);
    }

}
