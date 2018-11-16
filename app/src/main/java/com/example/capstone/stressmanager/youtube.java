package com.example.capstone.stressmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.roger.catloadinglibrary.CatLoadingView;

public class youtube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    int music_progress;
    String source;
    WebView wv;

    DatabaseReference db,dbb;
    FirebaseUser user;
    public static final String API_KEY="AIzaSyArE5Dsotfv1duaiUdhmrX-n5jeFPrwkRM";
    String videoId;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        com.google.android.youtube.player.YouTubePlayerView yv=findViewById(R.id.youtube_player);
        yv.initialize(API_KEY,this);

        user= FirebaseAuth.getInstance().getCurrentUser();
        String email=user.getEmail();
        int index = email.indexOf('@');

        email = email.substring(0,index);




        music_progress=getIntent().getIntExtra("key",0);
        source=getIntent().getStringExtra("source");
        Toast.makeText(this, music_progress+source+"", Toast.LENGTH_SHORT).show();
        String key=source+music_progress;

        db= FirebaseDatabase.getInstance().getReference().child("youtube").child(key);
        dbb= FirebaseDatabase.getInstance().getReference().child(email).child("music_progress");


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){

                    videoId= dataSnapshot.getValue().toString();
                    Toast.makeText(youtube.this, videoId+"", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(youtube.this, "null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
        if(!b){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    youTubePlayer.cueVideo(videoId);
                }
            },3000);



        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "youtube fail", Toast.LENGTH_SHORT).show();
    }

    public void close(View view) {


        View v1=getLayoutInflater().inflate(R.layout.music_feedback,null);
        i=1;
        Button b=v1.findViewById(R.id.agreeyes);
        Button bb=v1.findViewById(R.id.nonever);
        wv=v1.findViewById(R.id.WebView1);

        final DialogPlus dialog=DialogPlus.newDialog(this).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

            }
        }).setGravity(Gravity.CENTER).setCancelable(false).setContentHolder(new ViewHolder(v1)).create();


        dialog.show();

        Toast.makeText(this, "Press YES when you feel satisfied else keep pressing NO for new videos", Toast.LENGTH_LONG).show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music_progress!=100){
                    dbb.setValue(music_progress+25);}
                else{
                    Toast.makeText(getApplication(), "100reached", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();

                Intent ii=new Intent(getApplication(),homeScreen.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ii);
            }
        });

       bb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               wv.loadUrl("https://tinyurl.com/randomstuffz"+i);
               i=i+1;
               if(i==4)
               {
                   i=1;
               }
           }
       });










    }

    public void play(View view) {




    }
}
