package com.example.firstservec;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.lang.reflect.Field;

public class MyServes extends Service {
    ;
    private static final String CHANNEL_ID = "channel_id_1";
    MediaPlayer mediaPlayer;
Thread sound ;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();

        Intent notificationIntent =  new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("music")
                .setContentText(getResources().getResourceEntryName(R.raw.abo))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();


        startForeground(1,notification);
        Toast.makeText(getApplicationContext(), "on create" , Toast.LENGTH_LONG).show();

        createNotificationChannel();
    }
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID, "music channel",NotificationManager.IMPORTANCE_DEFAULT);
            serviceChannel.setDescription("channel for music");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(serviceChannel);

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "on Start Command" , Toast.LENGTH_LONG).show();
        mediaPlayer = MediaPlayer.create(this ,R.raw.abo);
        sound = new Thread(){
            public void run(){
                mediaPlayer.start();

        }
        };
        sound.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "on Destroy" , Toast.LENGTH_LONG).show();
        mediaPlayer.release();
    }
}
