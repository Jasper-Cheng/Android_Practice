package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import view.CustomView_layout;

public class MainActivity extends AppCompatActivity {
    private CustomView_layout customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView=findViewById(R.id.customView);
        customView.smoothScrollTo(-600,-600);
    }
}
