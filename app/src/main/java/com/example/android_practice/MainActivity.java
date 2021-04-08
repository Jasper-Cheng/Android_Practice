package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.ThreadMode;

import Bean.MessageEvent;
import BusPackage.OttoBus;

public class MainActivity extends AppCompatActivity {
    Button skipButton = null;
    Button subscribeButton = null;
    TextView messageShow = null;
    Bus bus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skipButton = findViewById(R.id.skip);
        subscribeButton = findViewById(R.id.subscribe);
        messageShow = findViewById(R.id.messageShow);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NextActivity.class));
            }
        });

        bus = OttoBus.getInstance();
        bus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Subscribe
    public void onMoonEvent(MessageEvent messageEvent) {
        messageShow.setText(messageEvent.getName());
    }
}
