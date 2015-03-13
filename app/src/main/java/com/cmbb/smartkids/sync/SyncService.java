package com.cmbb.smartkids.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.cmbb.smartkids.tools.logger.Log;

/**
 * Created by N.Sun
 */
public class SyncService extends Service {
    private static final String TAG = SyncService.class.getSimpleName();

    private static final Object sSyncAdapterLock = new Object();
    private static SyncAdapter sSyncAdapter  = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Sync Service Start");
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter != null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Sync Service Destory");
    }
}
