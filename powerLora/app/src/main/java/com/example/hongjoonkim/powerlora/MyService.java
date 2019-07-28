package com.example.hongjoonkim.powerlora;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;


public class MyService extends Service {

    NotificationManager Notifi_M;
    ServiceThread thread;
    Notification Notifi;
    double set_lati,live_lati;
    double set_longti,live_longti;
    double distance = 0;
    int boundary = 0;
    WhereAlaram w1; // 설정 범위 값
    WhereAlaram2 w2; // 현 위치 값

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notifi_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업

    public void onDestroy() {
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class myServiceHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {

            try {
                w1 = new WhereAlaram();
                w1.getResponse(w1.page);
                w2 = new WhereAlaram2();
                w2.getResponse(w2.page);

                set_lati = Double.parseDouble(w1.latitute);
                set_longti = Double.parseDouble(w1.longtitute);
                live_lati = Double.parseDouble(w2.latitute);
                live_longti = Double.parseDouble(w2.longtitute);

                Location locationA = new Location("point A");
                Location locationB = new Location("point B");
                locationA.setLatitude(set_lati);
                locationA.setLongitude(set_longti);
                locationB.setLatitude(live_lati);
                locationB.setLongitude(live_longti);
                distance = locationA.distanceTo(locationB);
                boundary = Integer.parseInt(w1.boundary);
                Intent intent = new Intent(MyService.this, MainActivity.class);
                if(distance > boundary)
                {
                    show();
                }

            }catch (RuntimeException e)
            {
                e.printStackTrace();
            }




        }
    };
    public void show()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("치매 노인 안전 관리 시스템");
        builder.setContentText("해당 고객님이 안전 반경을 벗어 나셨습니다.");

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        builder.setLargeIcon(largeIcon);
        builder.setColor(Color.RED);
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);

        long[] vibrate = {0,100,200,300};
        builder.setVibrate(vibrate);
        builder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("default","기본 채널",NotificationManager.IMPORTANCE_DEFAULT));
        }
        manager.notify(1,builder.build());
    }
}
