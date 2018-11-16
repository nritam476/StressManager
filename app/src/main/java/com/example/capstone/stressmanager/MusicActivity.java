package com.example.capstone.stressmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class MusicActivity extends AppCompatActivity implements View.OnClickListener{

    pl.droidsonroids.gif.GifImageView im;
    TextView t1,t2,t3,t4,t5,t6;
   RelativeLayout rel1,rel2,rel3,rel4,rel5,rel6;
   String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        im=findViewById(R.id.image_back);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView1);
        t3=findViewById(R.id.textView2);
        t4=findViewById(R.id.textView3);
        t5=findViewById(R.id.textView4);
        t6=findViewById(R.id.textView5);

        rel1=findViewById(R.id.rel1);
        rel2=findViewById(R.id.rel2);
        rel3=findViewById(R.id.rel3);
        rel4=findViewById(R.id.rel4);
        rel5=findViewById(R.id.rel5);
        rel6=findViewById(R.id.rel6);


        rel1.setOnClickListener(this);
        rel2.setOnClickListener(this);
        rel3.setOnClickListener(this);
        rel4.setOnClickListener(this);
        rel5.setOnClickListener(this);
        rel6.setOnClickListener(this);






        t1.setText("Baby");
        t2.setText("Piano");
        t3.setText("Ambient");
        t4.setText("Water");
        t5.setText("Chill");
        t6.setText("Meditation");









    }

    @Override
    public void onClick(View view) {


        stopService(new Intent(this,MusicService.class));



        if(view.getId()==R.id.rel1){
            key="http://soundbible.com/mp3/baby-music-box_daniel-simion.mp3";
           // Toast.makeText(this, "lalalla", Toast.LENGTH_SHORT).show();
            Intent k=new Intent(this,MusicService.class);
            k.putExtra("key",key);

            startService(k);
            im.setImageResource(R.drawable.music_baby);


        }
        else if(view.getId()==R.id.rel2){

            key="http://soundbible.com/mp3/Cowboy_Theme-Pavak-1711860633.mp3";
           // Toast.makeText(this, "lalalla", Toast.LENGTH_SHORT).show();
            Intent k=new Intent(this,MusicService.class);
            k.putExtra("key",key);

            startService(k);


            im.setImageResource(R.drawable.music_piano);
        }
        else if(view.getId()==R.id.rel3){

            key="http://soundbible.com/mp3/Bird_in_Rain-Mike_Koenig-441535833.mp3";
            Intent k=new Intent(this,MusicService.class);
            k.putExtra("key",key);

            startService(k);
            im.setImageResource(R.drawable.music_ambient);
        }
        else if(view.getId()==R.id.rel4){
            key="http://soundbible.com/grab.php?id=1144&type=mp3";
            Intent k=new Intent(this,MusicService.class);
            k.putExtra("key",key);

            startService(k);
            im.setImageResource(R.drawable.music_water);
        }
        else if(view.getId()==R.id.rel5){
            key="http://soundbible.com/mp3/Ambiance-SoundBible.com-1535680949.mp3";
            Intent k=new Intent(this,MusicService.class);
            k.putExtra("key",key);

            startService(k);
            im.setImageResource(R.drawable.music_chill);
        }
        else if(view.getId()==R.id.rel6){
            key="http://soundbible.com/grab.php?id=1263&type=mp3";
            Intent k=new Intent(this,MusicService.class);
            k.putExtra("key",key);

            startService(k);
            im.setImageResource(R.drawable.music_meditate);

        }






    }

    public void dothis(View view) {

        stopService(new Intent(this,MusicService.class));
    }


}
