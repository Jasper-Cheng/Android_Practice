package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Button snackbarButton;
    private Button loginButton;
    private EditText et_username;
    private EditText et_password;
    private TextInputLayout tl_username;
    private TextInputLayout tl_password;
    private RelativeLayout activity_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity_main=findViewById(R.id.activity_main);
        snackbarButton=findViewById(R.id.snackbarButton);
        loginButton=findViewById(R.id.loginButton);
        et_password=findViewById(R.id.et_password);
        et_username=findViewById(R.id.et_username);
        tl_username=findViewById(R.id.tl_username);
        tl_password=findViewById(R.id.tl_password);
        snackbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tl_password.setErrorEnabled(false);
                tl_username.setErrorEnabled(false);
                if(!validatePassword(et_password.getText().toString())){
                    tl_password.setErrorEnabled(true);
                    tl_password.setError("请输入正确的密码");
                }else if(!validateUsername(et_username.getText().toString())){
                    tl_username.setErrorEnabled(true);
                    tl_username.setError("请输入正确的用户名");
                }else{
                    Toast.makeText(MainActivity.this,"OK,YOU ARE VERY GOOD!",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public void showSnackbar(){
        Snackbar.make(activity_main,"snackbar",Snackbar.LENGTH_LONG)
                .setAction("点击事件", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Toast",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }
    public boolean validatePassword(String password){
        if (password.length()>4){
            return true;
        }
        return false;
    }
    public boolean validateUsername(String username){
        if (username.length()>4){
            return true;
        }
        return false;
    }

}
