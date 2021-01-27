package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button JsonRequest = null;
    private TextView content = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonRequest = findViewById(R.id.JsonRequest);
        content = findViewById(R.id.content);
        JsonRequest.setOnClickListener(new View.OnClickListener() {
            RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());

            @Override
            public void onClick(View v) {
                JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest("https://www.wanandroid.com/article/list/0/json", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        content.setText(response.toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        content.setText(error.getMessage());
                    }
                });
                mRequestQueue.add(mJsonObjectRequest);
            }
        });
    }
}
