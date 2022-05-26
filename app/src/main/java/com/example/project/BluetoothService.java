package com.example.project;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class BluetoothService extends Service {

    private BluetoothAdapter bluetooth;
    private Handler handler;

    private boolean autoUpdate;

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler();
        bluetooth = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        

        return START_REDELIVER_INTENT;
    }

    public void setScanningPeriod(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }


}