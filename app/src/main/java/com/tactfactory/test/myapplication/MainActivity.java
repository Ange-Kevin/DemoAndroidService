package com.tactfactory.test.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MyService.class);
//                intent.putExtra(MyService.PARAM_KEY, "Value Toooooootoooooooooo !!!");
//                MainActivity.this.startService(intent);
                String totot = myService.getDisplayValue();
            }
        });

        this.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra(MyService.PARAM_KEY, "No Fake !!!");
                MainActivity.this.startService(intent);
            }
        });

//        Threading threading = new Threading();
//        threading.demoThread(this);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Binding service
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private MyService myService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
        }
    };
    @Override
    protected void onStart() {
        super.onStart();

        // Start Service
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(MyService.PARAM_KEY, "Value 1");
        //this.startService(intent);
        this.bindService(intent, this.connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unbindService(this.connection);
    }
}
