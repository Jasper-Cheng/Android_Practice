package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button button = null;
    TextView tv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        tv = findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAsynHttp("https://www.baidu.com");

            }
        });

    }

    private Observable<String> getObservable(final String url) {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter emitter) throws Throwable {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .method("GET", null)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        emitter.onError(new Exception("error"));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        emitter.onNext(response.body().string());
                        emitter.onComplete();
                    }
                });
            }
        });
        return observable;
    }

    private void postAsynHttp(String url) {
        getObservable(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("Jasper", "onSubscribe");
                        tv.setText("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.e("Jasper", "onNext");
                        tv.setText(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Jasper", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("Jasper", "onComplete");
                    }
                });
    }
}
