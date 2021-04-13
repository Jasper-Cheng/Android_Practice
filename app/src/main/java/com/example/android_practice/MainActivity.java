package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import bean.TestBean;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import services.RequestService;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button button = null;
    TextView tv = null;
    Disposable subscription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        tv = findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequestMethod("https://wanandroid.com");
            }
        });

    }

    private void getRequestMethod(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        RequestService requestService = retrofit.create(RequestService.class);
        requestService.getTestPost("wxarticle")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("Jasper", "onSubscribe");
                        tv.setText("onSubscribe");
                        subscription = d;
                    }

                    @Override
                    public void onNext(@NonNull TestBean testBean) {
                        Log.e("Jasper", "onNext");
                        tv.setText(testBean.getData().getSize() + "");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Jasper", "onError");
                        Log.e("Jasper", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("Jasper", "onComplete");
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        subscription.dispose();
    }
}
