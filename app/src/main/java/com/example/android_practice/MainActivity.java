package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {
    private Button mImageLoader = null;
    private ImageView content = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageLoader = findViewById(R.id.ImageLoader);
        content = findViewById(R.id.content);
        mImageLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
                ImageLoader imageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(content, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get("https://uploads-ssl.webflow.com/5b651f8b5fc94c6f5f470a7a/5cc68f1cd9da337009847c17_Volley%20-%20Open%20Graph%20Image.png", listener);
            }
        });
    }
}
