package com.example.hl.threadapp;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.txtContent);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1 + "");
            }
        };
        final Runnable myworker = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while(progress<=10){
                    Message message = new Message();
                    message.arg1 = progress;
                    handler.sendMessage(message);
                    progress += 1;
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }
        };
        Button start= (Button)findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Thread workThread = new Thread(null,myworker,"workThread");
                workThread.start();
            }
        });
    }
}
