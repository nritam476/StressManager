package com.example.capstone.stressmanager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mapzen.speakerbox.Speakerbox;

import java.util.Random;

public class Face_emoji extends AppCompatActivity {

    String[] carArr;
    int n;
    String source,email;
    Speakerbox sp;
    MediaPlayer mp;
    Animation anim;
    Button bb,bb2;
    VideoView vdv;
    Uri urii;
    StorageReference sf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_emoji);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // iv=findViewById(R.id.imageView7);
        //iv1=findViewById(R.id.imageView80);
        //iv2=findViewById(R.id.imageView90);
        bb=findViewById(R.id.button21);
        bb2=findViewById(R.id.button2);
        vdv=findViewById(R.id.vdv);
        sf= FirebaseStorage.getInstance().getReference().child("videos/video1.mp4");


        sf.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                urii=uri;
            }
        });


        mp=MediaPlayer.create(getApplicationContext(),R.raw.medi_music);
        mp.setLooping(true);
        mp.setVolume(0.6f,0.6f);





        source=getIntent().getStringExtra("source");


        if (source.equals("financial")) {
            carArr = getResources().getStringArray(R.array.financial_quotes);
           // Toast.makeText(this, "financial", Toast.LENGTH_SHORT).show();
        } else if (source.equals("student")) {
            carArr = getResources().getStringArray(R.array.student_quotes);
            //Toast.makeText(this, "student", Toast.LENGTH_SHORT).show();

        } else {
            carArr = getResources().getStringArray(R.array.love_quotes);
            //Toast.makeText(this, "love", Toast.LENGTH_SHORT).show();
        }


        email=getIntent().getStringExtra("name");
        //Toast.makeText(this, email+"", Toast.LENGTH_SHORT).show();


        sp=new Speakerbox(getApplication());

        sp.play("Hello"+email+"                                                Its nice to see you here.                                                                                                                        For best results use headphones and close your eyes.");



            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    bb2.setVisibility(View.VISIBLE);
                }
            },5000);







    }

    public void dothis(View view) {






        bb2.setVisibility(View.INVISIBLE);
        bb.setVisibility(View.VISIBLE);
        //iv.startAnimation(anim);iv1.startAnimation(anim);iv2.startAnimation(anim);

        vdv.setVideoURI(urii);

        vdv.start();







        Random rand=new Random();
        n=rand.nextInt(carArr.length);




                mp.start();

       // Toast.makeText(this, n+"", Toast.LENGTH_SHORT).show();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sp.play(carArr[n]);
                Toast.makeText(Face_emoji.this, "playing", Toast.LENGTH_SHORT).show();
            }
        },4000);





    }

    public void dothiss(View view) {
    //    bb.setVisibility(View.INVISIBLE);
       // bb2.setVisibility(View.VISIBLE);
        mp.stop();
        sp.stop();
        vdv.stopPlayback();

        Intent i=new Intent(this,homeScreen.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

            mp.stop();
            sp.stop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();

        sp.stop();
    }


}
