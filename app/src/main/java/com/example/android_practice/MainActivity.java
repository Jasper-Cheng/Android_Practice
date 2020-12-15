package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {
    private View customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView=findViewById(R.id.customView);
//        customView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));
//        ObjectAnimator.ofFloat(customView,"translationX",0,300).setDuration(1000).start();
    }
}
