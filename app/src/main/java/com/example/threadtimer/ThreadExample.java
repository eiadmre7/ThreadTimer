package com.example.threadtimer;

import android.util.Log;

public class ThreadExample implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("THREAD EXAMPLE", "start Long Work " + i );//+ "Thread id= " + this.getId());
        }
    }
}
