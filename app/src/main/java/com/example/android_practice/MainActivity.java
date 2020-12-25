package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import view.InvalidTextView;

public class MainActivity extends AppCompatActivity {
    private InvalidTextView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView=findViewById(R.id.customView);
    }
}
