package com.cmbb.smartkids.network;

import com.cmbb.smartkids.base.BaseActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by N.Sun
 */
public class ResponseActivityCallback<T> implements Callback {

    private BaseActivity mContext;
    private Class cls;
    private final Gson gson = new Gson();

    public ResponseActivityCallback(BaseActivity context, Class cls) {
        this.mContext = context;
        this.cls = cls;
    }

    @Override
    public void onFailure(Request request, IOException e) {
        mContext.hideWaitDialog();

    }

    @Override
    public void onResponse(Response response) throws IOException {
        mContext.hideWaitDialog();
        if (response.isSuccessful()) {
            Object data = gson.fromJson(response.body().charStream(), cls);
            onSuccess((T) data);
        }
    }

    public void onSuccess(T data) {

    }
}
