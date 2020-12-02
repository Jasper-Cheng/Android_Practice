package com.example.android_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
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
                MainActivityPermissionsDispatcher.callPhoneWithCheck(MainActivity.this);
            }
        });

    }


    @NeedsPermission (Manifest.permission.CALL_PHONE)
    //在需要获取权限的地方注释
    public void callPhone(){
        Intent intent=new Intent(Intent.ACTION_CALL);
        Uri data=Uri.parse("tel:"+"10086");
        intent.setData(data);
        try {
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }
    @OnShowRationale(Manifest.permission.CALL_PHONE)
    //提示用户为何要开此权限
    public void showWhy(final PermissionRequest request){
        AlertDialog dialog=new AlertDialog.Builder(this)
                .setMessage("提示用户为何要开此权限")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();//再次执行权限请求
                    }
                }).show();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    //用户选择拒绝时的提示
    public void showDenied(){
        Toast.makeText(MainActivity.this,"用户选择拒绝时的提示",Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    //用户选择不在询问后的提示
    public void showNotAsk(){
        new AlertDialog.Builder(this)
                .setMessage("该功能需要访问电话的权限，不开启将无法正常工作")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
