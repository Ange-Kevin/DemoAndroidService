package com.tactfactory.test.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TabHost;
import android.widget.Toast;

public class Threading {

    public class MyRunnable implements Runnable {

        private final Context ctx;

        public MyRunnable(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            // Simple Mode
            // Calc.......

            // Loop mode
            while (true) {

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String threadId = String.valueOf(Thread.currentThread().getId());
                Toast.makeText(ctx, threadId, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private final Context ctx;

        public MyAsyncTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String threadId = String.valueOf(Thread.currentThread().getId());
            Toast.makeText(ctx, threadId, Toast.LENGTH_SHORT).show();

            return null;
        }
    }

    public void demoThread(Context ctx) {
        String threadId = String.valueOf(Thread.currentThread().getId());
        Toast.makeText(ctx, threadId, Toast.LENGTH_SHORT).show();

        Thread myThread = new Thread(new MyRunnable(ctx));
        myThread.start();
    }

    public void demoTask(Context ctx) {
        MyAsyncTask task = new MyAsyncTask(ctx);
        task.execute();
    }

}
