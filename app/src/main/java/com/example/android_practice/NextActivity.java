package com.example.android_practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import Bean.MessageEvent;

public class NextActivity extends AppCompatActivity {
    Button postButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        postButton = findViewById(R.id.post);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setName("Jasper");
                messageEvent.setAge(26);
                EventBus.getDefault().post(messageEvent);
                finish();
            }
        });
    }
}
