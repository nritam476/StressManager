package com.example.capstone.stressmanager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.io.IOUtils;


import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.mapzen.speakerbox.Speakerbox;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import com.roger.catloadinglibrary.CatLoadingView;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;


public class homeScreen extends AppCompatActivity {
    int n;

    DatabaseReference db;
    FirebaseUser user;
    String source;
    int music_progress, book_progress, meditate_progress;

    ImageView music, meditate, books;
    com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar prog;
    TextView text;
    MediaPlayer mp;

    byte[] payload;
    android.support.design.widget.AppBarLayout applayout;

    android.support.design.widget.BottomNavigationView nv;
    String carArr[];
    View vv;
    String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        applayout = findViewById(R.id.app_bar);

        //  text=findViewById(R.id.level);
        // music=findViewById(R.id.music);
        //books=findViewById(R.id.books);
        //meditate=findViewById(R.id.meditate);
        //prog=findViewById(R.id.prog);
        //iimageView=findViewById(R.id.image);
        nv = findViewById(R.id.navigation);

        vv=getLayoutInflater().inflate(R.layout.splash_switcher,null);


        applayout.setBackgroundResource(R.drawable.backgroundchange);
        AnimationDrawable ad = (AnimationDrawable) applayout.getBackground();
        ad.start();


        user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
        int index = email.indexOf('@');

        email = email.substring(0, index);


        db = FirebaseDatabase.getInstance().getReference().child(email);
        //ev.startIntro();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("source").getValue() != null && dataSnapshot.child("music_progress").getValue() != null) {
                    source = dataSnapshot.child("source").getValue().toString();
                    music_progress = Integer.parseInt(dataSnapshot.child("music_progress").getValue().toString());
                    book_progress = Integer.parseInt(dataSnapshot.child("book_progress").getValue().toString());
                    meditate_progress = Integer.parseInt(dataSnapshot.child("meditate_progress").getValue().toString());
                    //Toast.makeText(getApplication(), music_progress+source+"", Toast.LENGTH_SHORT).show();
                    //prog.setProgress((music_progress+book_progress+meditate_progress)/3);
                    //prog.setProgressColor(Color.BLACK);
                    String set = "Progress:" + (music_progress + book_progress + meditate_progress) / 3 + "%";
                    //  text.setText(set);


                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(homeScreen.this, "dasdasd", Toast.LENGTH_SHORT).show();
            }
        });

        // prog.setProgressColor(Color.parseColor("#ed3b27"));
        //prog.setProgressBackgroundColor(Color.parseColor("#808080"));

//        prog.setMax(100.0f);


        nv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_dashboard) {

                    Intent i = new Intent(getApplication(), profile.class);
                    startActivity(i);

                    Toast t=new Toast(getApplicationContext());

                    ImageView iv=vv.findViewById(R.id.imgpo);
                    iv.setImageResource(R.drawable.splash_ten);
                    t.setView(vv);
                    t.setDuration(Toast.LENGTH_LONG);
                    t.setGravity(Gravity.CENTER,0,0);
                    t.show();
                } else if (item.getItemId() == R.id.logout) {
                    FirebaseAuth.getInstance().signOut();
                    Intent oi = new Intent(getApplication(), MainActivity.class);
                    oi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(oi);

                }
                return true;
            }
        });


    }

    public void book_click(View view) {


        String what = source + book_progress + ".pdf";

        Toast.makeText(this, "" + what, Toast.LENGTH_SHORT).show();
        final CatLoadingView mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(), "");


        View v1 = getLayoutInflater().inflate(R.layout.pdff, null);


        final DialogPlus dialog = DialogPlus.newDialog(this).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

            }
        }).setGravity(Gravity.CENTER).setCancelable(true).setContentHolder(new ViewHolder(v1)).create();

        Button b1 = v1.findViewById(R.id.close);
        com.joanzapata.pdfview.PDFView pdf = v1.findViewById(R.id.pdfview);

        pdf.fromAsset(what)
                .showMinimap(false)
                .enableSwipe(true)
                .load();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (book_progress != 100) {
                    db.child("book_progress").setValue(book_progress + 25);
                } else {
                    Toast.makeText(homeScreen.this, "100reached", Toast.LENGTH_SHORT).show();
                }
                finish();
                startActivity(getIntent());

            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mView.dismiss();

                Toast.makeText(homeScreen.this, "Press CLOSE only when you finish reading the story. To read anytime later, press outside the dialog", Toast.LENGTH_LONG).show();

                dialog.show();
            }
        }, 4000);


    }

    public void music_click(View view) {


        final CatLoadingView mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(), "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mView.dismiss();

                Toast t=new Toast(getApplicationContext());
                ImageView iv=vv.findViewById(R.id.imgpo);
                iv.setImageResource(R.drawable.splash_eight);
                t.setView(vv);
                t.setDuration(Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

                Intent i = new Intent(getApplication(), youtube.class);
                i.putExtra("key", music_progress);
                i.putExtra("source", source);
                startActivity(i);

            }
        }, 4000);


    }

    public void med_click(View view) {


    }


    public void cover_click(View view) {

        Intent i = new Intent(getApplication(), WebViewActivity.class);

        Toast t=new Toast(getApplicationContext());
        ImageView iv=vv.findViewById(R.id.imgpo);
        iv.setImageResource(R.drawable.splash_four);
        t.setView(vv);
        t.setDuration(Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
        startActivity(i);


    }


    public void location_click(View view) {

        LocationManager lm= (LocationManager) getSystemService(LOCATION_SERVICE);

        if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Intent i = new Intent(this, MapsActivity.class);
            Toast t=new Toast(getApplicationContext());
            ImageView iv=vv.findViewById(R.id.imgpo);
            iv.setImageResource(R.drawable.splash_six);
            t.setView(vv);
            t.setDuration(Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
            startActivity(i);

        }
        else{

            Toast.makeText(this, "Please enable your location services", Toast.LENGTH_SHORT).show();
        }


    }

    public void moosic_click(View view) {

        Intent i=new Intent(this,Reference.class);

        startActivity(i);


    }

    public void dorandom(View view) {

        Toast t=new Toast(getApplicationContext());
        ImageView iv=vv.findViewById(R.id.imgpo);
        iv.setImageResource(R.drawable.splash_three);
        t.setView(vv);
        t.setDuration(Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();

        carArr = getResources().getStringArray(R.array.quotes);
        Random rand=new Random();
        n=rand.nextInt(carArr.length-1);
        View v1=getLayoutInflater().inflate(R.layout.random_quote,null);
        text=v1.findViewById(R.id.textView9);


        final DialogPlus dialog = DialogPlus.newDialog(this).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

            }
        }).setGravity(Gravity.CENTER).setCancelable(true).setContentHolder(new ViewHolder(v1)).create();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.show();

                Speakerbox sp=new Speakerbox(getApplication());
                sp.play(carArr[n]);

                text.setText(carArr[n]);
            }
        },5000);




    }


    public void musicc_click(View view) {
        Toast t=new Toast(getApplicationContext());
        ImageView iv=vv.findViewById(R.id.imgpo);
        iv.setImageResource(R.drawable.splash_nine);
        t.setView(vv);
        t.setDuration(Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();


        Intent ii=new Intent(this,MusicActivity.class);
        startActivity(ii);

    }

    public void focus_click(View view) {


        Toast t=new Toast(getApplicationContext());
        ImageView iv=vv.findViewById(R.id.imgpo);
        iv.setImageResource(R.drawable.splash_five);
        t.setView(vv);
        t.setDuration(Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplication(),Focus_main.class);
                i.putExtra("level",meditate_progress);
                startActivity(i);
            }
        },4000);


    }

    public void mantra_click(View view) {
        Intent i=new Intent(this,Face_emoji.class);
        i.putExtra("name",email);
        i.putExtra("source",source);
        startActivity(i);

    }
}
