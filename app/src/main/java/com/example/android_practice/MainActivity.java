package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button normal_button;
    Button fold_button;
    Button hang_button;
    NotificationManager notificationManager;
    Notification notification = null;
    String id = "my_channel_01";
    String name = "我是渠道名字";
    PendingIntent pendingIntent = null;
    Intent mIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        normal_button = findViewById(R.id.normal_button);
        fold_button = findViewById(R.id.fold_button);
        hang_button = findViewById(R.id.hang_button);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);

        normal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //普通通知显示，判断是否在android8.0以上
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
                    notificationManager.createNotificationChannel(mChannel);
                    notification = new Notification.Builder(MainActivity.this)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setChannelId(id)
                            .setContentTitle("普通通知")
                            .setContentText("My name is Jasper!")
                            .setSmallIcon(R.mipmap.ic_launcher).build();
                } else {
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setContentTitle("普通通知")
                            .setContentText("My name is Jasper!")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setOngoing(true);
                    notification = notificationBuilder.build();
                }
                notificationManager.notify(111123, notification);
            }
        });
    }
}
