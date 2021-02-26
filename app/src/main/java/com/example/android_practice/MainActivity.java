package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button OkHttp_GET = null;
    Button OkHttp_POST = null;
    Button OkHttp_FILEUPLOAD = null;
    Button OkHttp_FILEDOWNLOAD = null;
    Button OkHttp_MultipartFile = null;
    TextView OkHttp_content = null;
    Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttp_GET = findViewById(R.id.OkHttp_GET);
        OkHttp_POST = findViewById(R.id.OkHttp_POST);
        OkHttp_FILEUPLOAD = findViewById(R.id.OkHttp_FILEUPLOAD);
        OkHttp_FILEDOWNLOAD = findViewById(R.id.OkHttp_FILEDOWNLOAD);
        OkHttp_MultipartFile = findViewById(R.id.OkHttp_MultipartFile);
        OkHttp_content = findViewById(R.id.OkHttp_content);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.cache(new Cache(getExternalCacheDir().getAbsoluteFile(), 10 * 1024 * 1024));
        final OkHttpClient okHttpClient = builder.build();

        OkHttp_GET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Request request = new Request.Builder().url("https://www.baidu.com").build();
                Call call = okHttpClient.newCall(request);
                //异步请求用enqueue,同步用execute
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                OkHttp_content.setText(e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String s = response.body().string();
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                OkHttp_content.setText(s);
                            }
                        });
                    }
                });
            }
        });


        OkHttp_POST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody requestBody = new FormBody.Builder().add("ip", "110.110.110.110").build();
                Request request = new Request.Builder().url("https://www.baidu.com").post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                OkHttp_content.setText(e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String s = response.body().string();
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                OkHttp_content.setText(s);
                            }
                        });
                    }
                });
            }
        });

        OkHttp_FILEUPLOAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");
                String filepath = "";
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
                } else {
                    return;
                }
                File file = new File(filepath, "jasper.txt");
                Request request = new Request.Builder().url("https://api.github.com/markdown/raw")
                        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                OkHttp_content.setText("error" + e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String s = response.body().string();
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                OkHttp_content.setText("jasper:" + s);
                            }
                        });
                    }
                });
            }
        });

        OkHttp_FILEDOWNLOAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request.Builder().url("https://mymodernmet.com/wp/wp-content/uploads/2019/05/Big-Picture-Natural-World-Photo-Contest-thumbnail.jpg").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                OkHttp_content.setText("error" + e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final InputStream inputStream = response.body().byteStream();
                        FileOutputStream fileOutputStream = null;
                        String filepath = getFilesDir().getAbsolutePath();
                        final File file = new File(filepath, "jasper.jpg");
                        if (file != null) {
                            fileOutputStream = new FileOutputStream(file);
                            byte[] buffer = new byte[2048];
                            int len = 0;
                            while ((len = inputStream.read(buffer)) != -1) {
                                fileOutputStream.write(buffer, 0, len);
                            }
                            fileOutputStream.flush();
                        }

                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                OkHttp_content.setText("jasper:" + file.length());
                            }
                        });
                    }
                });
            }
        });
    }
}
