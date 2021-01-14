package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button HttpURLClientGetButton = null;
    private TextView content = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpURLClientGetButton = findViewById(R.id.HttpURLClientGetButton);
        content = findViewById(R.id.content);
        HttpURLClientGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, String>() {

                    @Override
                    protected String doInBackground(String... strings) {
                        return useHttpUrlConnectionPost(strings[0]);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        content.setText(s);
                    }
                }.execute("https://www.baidu.com/");
            }
        });
    }

    public static HttpURLConnection getHttpURLConnection(String url) {
        HttpURLConnection mHttpURLConnection = null;
        try {
            URL mUrl = new URL(url);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setConnectTimeout(15000);
            mHttpURLConnection.setReadTimeout(15000);
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mHttpURLConnection;
    }

    public static void postParams(OutputStream outputStream, List<NameValuePair> paramsList) throws IOException {
        StringBuilder mStringBuilder = new StringBuilder();
        for (NameValuePair pair : paramsList) {
            if (!TextUtils.isEmpty(mStringBuilder)) {
                mStringBuilder.append("&");
            }
            mStringBuilder.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            mStringBuilder.append("=");
            mStringBuilder.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(mStringBuilder.toString());
        bufferedWriter.flush();
        bufferedWriter.close();

    }

    private String useHttpUrlConnectionPost(String url) {
        InputStream mInputStream = null;
        HttpURLConnection mHttpURLConnection = getHttpURLConnection(url);
        try {
            Log.e("Jasper", url);
            List<NameValuePair> postParams = new ArrayList<>();
//            postParams.add(new BasicNameValuePair("ip","59.108.54.137"));
//            postParams(mHttpURLConnection.getOutputStream(),postParams);
            mHttpURLConnection.connect();
            mInputStream = mHttpURLConnection.getInputStream();
            int code = mHttpURLConnection.getResponseCode();
            Log.e("Jasper", "请求码" + code);
            String response = convertStreamToString(mInputStream);
            mInputStream.close();
            return response;
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


}
