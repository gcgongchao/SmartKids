package com.cmbb.smartkids.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by N.Sun
 */
public class SafeAsyncService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SafeAsyncService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

}
