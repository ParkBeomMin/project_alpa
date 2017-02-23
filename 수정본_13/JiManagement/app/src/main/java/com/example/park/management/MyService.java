package com.example.park.management;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    NotificationManager Notif_M;
    ServiceThread thread;
    Notification Noti;
    public MyService() {
        Log.d("Runnig","Service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Notif_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
        super.onStart(intent, startId);
        Log.d("servising","runing");


    }

    @Override
    public void onDestroy() {
        Log.d("slog", "onDestroy()");
        thread.stopForever();
        thread = null;
    }
    class myServiceHandler extends Handler{
        @Override
        public void handleMessage(android.os.Message msg) {
            Intent intent = new Intent(MyService.this,StartActivity.class);
            PendingIntent pendingIntent= PendingIntent.getActivity(MyService.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            Noti = new Notification.Builder(getApplicationContext()).setContentText("title").setContentText("text").setSmallIcon(R.drawable.alpalogo)
                    .setTicker("dㅏㄹ림").setContentIntent(pendingIntent).build();
            SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

            int badgeCount = auto.getInt("Badge", 0);
            Intent bagintent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            bagintent.putExtra("badge_count", badgeCount);
            //앱의  패키지 명
            bagintent.putExtra("badge_count_package_name","com.example.park.management");
            // AndroidManifest.xml에 정의된 메인 activity 명
            bagintent.putExtra("badge_count_class_name", "com.example.park.management.StartActivity");
            sendBroadcast(bagintent);
            Log.d("servising","runing");

        }
    }
}
