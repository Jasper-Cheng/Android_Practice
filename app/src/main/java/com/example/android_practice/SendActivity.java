package com.example.android_practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class SendActivity extends AppCompatActivity {
    Button button = null;
    TextView tv = null;
    Disposable subscription = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        button = findViewById(R.id.button2);
        tv = findViewById(R.id.tv2);

        subscription = customRxBus.getInstance().toObservable(MessageEvent.class)
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        MessageEvent messageEvent = (MessageEvent) o;
                        tv.setText(messageEvent.getMessage());
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.dispose();
    }
}
