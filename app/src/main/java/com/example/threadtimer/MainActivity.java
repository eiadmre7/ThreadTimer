package com.example.threadtimer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    private Button btnPauseResume,btnReset,btnStart;
    private TextView timer;
    private Handler handler;
    private String sTimeer;
    private int Min=0,sec=0;
    private boolean flag;
    private boolean isPaused=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        createHandler();
    }
    private void createHandler() {
        handler = new Handler(Looper.getMainLooper())
        {
            // UI THREAD
            @Override
            public void handleMessage(@NonNull Message msg) {
                //super.handleMes
                int what = msg.what;
                Log.d(TAG,"message what received " + what);
                updateTimer();
            }
        };

    }

    private void updateTimer() {
        //if the timer isn't paused
        if (!isPaused)
        {
            if (sec < 59)
                sec++;
            else {
                sec = 0;
                Min++;
            }
            displayTime();
        }
    }

    private void displayTime() {
        String mm,ss;
        if(sec<10)
            ss="0"+sec;
        else
            ss=""+sec;
        if(Min<10)
            mm="0"+Min;
        else
            mm=""+Min;
        sTimeer = mm + ":" + ss;
        timer.setText(sTimeer);
    }

    private void initViews() {
        timer=findViewById(R.id.textView);
        btnPauseResume=findViewById(R.id.btnstopresume);
        btnStart=findViewById(R.id.btnStart);
        btnReset=findViewById(R.id.btnReset);
    }

    public void startLongOperation()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("THREAD EXAMPLE", "start Long Work ");//+ "Thread id= " + this.getId());

                    //bitmap = BitmapFactory.decodeResource(R.drawable.dog);
                    Message m = Message.obtain();
                    m.what = 1;
                    handler.sendMessage(m);

                }  // finished download the DOG image
            }
        }).start();

    }

    public void StopResume(View view) {
        if(isPaused) {
            isPaused = false;
            btnPauseResume.setText("Pause");
        }
        else
        {
            isPaused=true;
            btnPauseResume.setText("Resume");
        }
    }

    public void ResetTimer(View view) {
        if(isPaused) {
            btnReset.setVisibility(View.GONE);
            btnStart.setVisibility(View.VISIBLE);
            flag = false;
            sec = 0;
            Min = 0;
            displayTime();
        }
        else
            Toast.makeText(this, "Plese pause first", Toast.LENGTH_SHORT).show();
    }

    public void startTimer(View view) {
        btnStart.setVisibility(View.GONE);
        btnReset.setVisibility(View.VISIBLE);
        flag=true;
        isPaused=false;
        btnPauseResume.setText("pause");
        startLongOperation();
    }
}