package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button Retrofit_GET = null;
    Button Retrofit_POST = null;
    Button Retrofit_FILEPLOAD = null;
    TextView Retrofit_content = null;
    Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit_GET = findViewById(R.id.Retrofit_GET);
        Retrofit_POST = findViewById(R.id.Retrofit_POST);
        Retrofit_FILEPLOAD = findViewById(R.id.Retrofit_FILEPLOAD);
        Retrofit_content = findViewById(R.id.Retrofit_content);

        final Handler handler = new Handler();

        Retrofit_GET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.baidu.com";
                Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
                Call<ResponseBody> call = retrofit.create(Service.class).getTestGet("wxarticle", "Java");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Retrofit_content.setText("success");
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, final Throwable t) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Retrofit_content.setText(t.getMessage());
                            }
                        });
                    }
                });
            }
        });

        Retrofit_POST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baseUrl = "https://wanandroid.com";
                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
                Call<ResponseBody> call = retrofit.create(Service.class).getTestPost("wxarticle", "Java");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Retrofit_content.setText("success");
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, final Throwable t) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Retrofit_content.setText(t.getMessage());
                            }
                        });
                    }
                });
            }
        });

    }
}
