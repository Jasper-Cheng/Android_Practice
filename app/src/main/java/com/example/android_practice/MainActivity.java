package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button HttpClientGetButton = null;
    private Button HttpClientPostButton = null;
    private TextView content = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpClientGetButton = findViewById(R.id.HttpClientGetButton);
        HttpClientPostButton = findViewById(R.id.HttpClientPostButton);
        content = findViewById(R.id.content);

        HttpClientGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        return useHttpClientGet(strings[0]);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        content.setText(s);
                    }
                }.execute("https://www.baidu.com/");
            }
        });
        HttpClientPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        return useHttpClientPost(strings[0]);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        content.setText(s);
                    }
                }.execute("https://www.baidu.com/");
            }
        });
    }

    private HttpClient createHttpClient() {
        HttpParams mHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(mHttpParams, 15000);

        HttpConnectionParams.setSoTimeout(mHttpParams, 15000);
        HttpConnectionParams.setTcpNoDelay(mHttpParams, true);
        HttpProtocolParams.setVersion(mHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(mHttpParams, HTTP.UTF_8);

        HttpProtocolParams.setUseExpectContinue(mHttpParams, true);
        HttpClient mHttpClient = new DefaultHttpClient(mHttpParams);
        return mHttpClient;
    }

    private String useHttpClientGet(String url) {
        HttpGet mHttpGet = new HttpGet(url);
        mHttpGet.addHeader("Connection", "Keep-Alive");
        HttpClient mHttpClient = createHttpClient();
        try {
            Log.e("Jasper", url);
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpGet);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            Log.e("Jasper", "GET请求状态码:" + code);
            if (null != mHttpEntity) {
                InputStream mInputStream = mHttpEntity.getContent();
                String response = convertStreamToString(mInputStream);
                mInputStream.close();
                return response;
            } else {
                return "null";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        }
    }

    private String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }

    private String useHttpClientPost(String url) {
        HttpPost mHttpPost = new HttpPost(url);
        mHttpPost.addHeader("Connection", "Keep-Alive");
        try {
            Log.e("Jasper", url);
            HttpClient mHttpClient = createHttpClient();
//            List<NameValuePair> postParams=new ArrayList<>();
//            postParams.add(new BasicNameValuePair("ip","59.108.54.37"));
//            postParams.add(new BasicNameValuePair("accessKey","public"));
//            mHttpPost.setEntity(new UrlEncodedFormEntity(postParams));
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            Log.e("Jasper", "POST请求状态码:" + code);
            if (null != mHttpEntity) {
                InputStream mInputStream = mHttpEntity.getContent();
                String response = convertStreamToString(mInputStream);
                mInputStream.close();
                return response;
            } else {
                return "null";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        }
    }

}
