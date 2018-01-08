package com.tactfactory.test.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {

    public static final String PARAM_KEY = "Toto";

    private volatile String displayValue = "";

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                MyService.this.displayValue = MyService.this.displayValue;
                int i = 0;
                i++;
                //Toast.makeText(MyService.this, MyService.this.displayValue, Toast.LENGTH_SHORT).show();
            }
        }
    });

    @Override
    public void onCreate() {
        super.onCreate();
        this.thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int result = super.onStartCommand(intent, flags, startId);

        this.displayValue = intent.getStringExtra(PARAM_KEY);

        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Binding service
    ///////////////////////////////////////////////////////////////////////////////////////////////


    public class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    private IBinder binder = new LocalBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        this.displayValue = intent.getStringExtra(PARAM_KEY);

        return this.binder;
    }

    public String getDisplayValue() {
        return this.displayValue;
    }

}
