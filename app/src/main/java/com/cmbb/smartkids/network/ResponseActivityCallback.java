package com.cmbb.smartkids.network;

import android.content.Intent;

import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.broadcast.ToastBroadcast;
import com.cmbb.smartkids.network.model.ResponseModel;
import com.cmbb.smartkids.tools.logger.Log;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by N.Sun
 */
public class ResponseActivityCallback<T> implements Callback {

    private static final String TAG = ResponseActivityCallback.class.getSimpleName();
    private BaseActivity mContext;
    private Class cls;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseActivityCallback(BaseActivity context, Class cls) {
        this.mContext = context;
        this.cls = cls;
    }

    @Override
    public void onFailure(Request request, IOException e) {
        mContext.hideWaitDialog();
        Intent intent = new Intent(Constants.INTENT_ACTION_TOAST);
        intent.putExtra(ToastBroadcast.ToastFLAG, ToastBroadcast.SHOW_TOAST_PARAM);
        intent.putExtra(ToastBroadcast.SHOW_TOAST_Message, "请检测网络");
        mContext.sendBroadcast(intent);
    }

    @Override
    public void onResponse(Response response) throws IOException {
        mContext.hideWaitDialog();
        if (response.isSuccessful()) {
            Log.i(TAG, "network = " + response.networkResponse());
            Log.i(TAG, "cache = " + response.cacheResponse());
            Object data = objectMapper.readValues(new JsonFactory().createParser(response.body()
                    .charStream()), cls).next();
            Log.i(TAG, "response = " + data.toString());
            onSuccess((T) data);
        }
    }

    public void onSuccess(T data) {

    }
}
