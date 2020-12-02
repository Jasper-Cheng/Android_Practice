package com.example.android_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_CALL_PHONE=1;
    private Button bt_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_call=findViewById(R.id.bt_call);
        bt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

    }
    public void call(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},PERMISSIONS_REQUEST_CALL_PHONE);
        }else{
            callPhone();
        }
    }
    public void callPhone(){
        Intent intent=new Intent(Intent.ACTION_CALL);
        Uri data=Uri.parse("tel:"+"10086");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISSIONS_REQUEST_CALL_PHONE){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                callPhone();
            }else{
                Toast.makeText(this,"权限被拒绝",Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
