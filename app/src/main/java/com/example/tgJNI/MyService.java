package com.example.tgJNI;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;
import java.util.HashMap;

public class MyService extends Service {

    private static final String TAG = "tgJNI";
    MessageStorage messageStorage = new MessageStorage();

    public MyService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "tgJNI MyService onCreate");
        //服务创建时候调用
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = null;
            channel = new NotificationChannel("tg111", getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(this, "tg111").build();
            startForeground(1, notification); //id随便写
            SystemClock.sleep(3000);
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "tgJNI onStartCommand");
        //服务每次启动的时候调用
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "Start Open tgJNI");
                    messageStorage.ReadHistoryData();


                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }

            }
        }).start();

        int interval = 5 * 1000; //1s一条进行计算
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long triggerTime = SystemClock.elapsedRealtime() + interval; //系统开机到现在的毫秒数+延迟执行时间
        Intent i = new Intent(this, MyService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pi);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyService---->onDestroy，前台service被杀死");
        stopForeground(true);
    }
}
