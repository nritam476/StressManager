package com.example.capstone.stressmanager;

import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Nritam476 on 18-10-2018.
 */

public class MusicService extends Service {
    MediaPlayer mp;
    String key;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        key=intent.getStringExtra("key");

      mp=new MediaPlayer();
      String urii= key;
        Toast.makeText(this, key+"", Toast.LENGTH_SHORT).show();
      mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mp.setDataSource(urii);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp.setLooping(true);

        mp.start();





        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mp.stop();

    }


}
