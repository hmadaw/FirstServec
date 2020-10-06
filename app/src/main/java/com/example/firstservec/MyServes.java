package com.example.firstservec;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyServes extends Service {
MediaPlayer mediaPlayer;
Thread sound ;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "on create" , Toast.LENGTH_LONG).show();
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
