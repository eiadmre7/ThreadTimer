package com.example.threadtimer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class manualTimerActivity extends AppCompatActivity {
    TextView tvCount;
    int counter=0;
    Thread counterThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_timer);
        tvCount=findViewById(R.id.tvCount);
    }

    public void startCount(View view) {
        counterThread=new Thread(()->{
            try{
                while(true){
                    counter++;
                    tvCount.setText(counter+"");
                    Thread.sleep(1000);
                }
            }
            catch (Exception e){

            }
        });
        counterThread.start();
    }

    public void StopCount(View view) {
        counterThread.interrupt();
    }
}