package com.example.capstone.stressmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class profile extends AppCompatActivity {
    DatabaseReference dbb;
    public static final int PICK_IMAGE = 1;
    private StorageReference mStorageRef;
    FirebaseUser user;
    DatabaseReference db;
    android.support.design.widget.AppBarLayout app;
    ImageView ii;
    com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar progg,progg2,prog3;
    String source;
    int music_progress,book_progress,meditate_progress;
    TextView tt,naam,level1,level2,level3,num1,num2,num3,tt2,tt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference().child(user.getUid());
        app=findViewById(R.id.app_bar);
        String email=user.getEmail();
        int index = email.indexOf('@');
        email = email.substring(0,index);
        ii=findViewById(R.id.profilepic);
        progg=findViewById(R.id.progg);
        tt=findViewById(R.id.tt);
        naam=findViewById(R.id.naam);
        naam.setText(user.getEmail());
        level1=findViewById(R.id.videos_level);
        num1=findViewById(R.id.videos_comp);
        level2=findViewById(R.id.stories_level);
        level3=findViewById(R.id.meditate_level);
        num2=findViewById(R.id.stories_comp);
        num3=findViewById(R.id.meditate_comp);
        tt2=findViewById(R.id.tt2);
        tt3=findViewById(R.id.tt3);
        progg2=findViewById(R.id.progg2);
        prog3=findViewById(R.id.progg3);


        db=FirebaseDatabase.getInstance().getReference().child(email).child("profilepic");
        dbb= FirebaseDatabase.getInstance().getReference().child(email);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue()!=null){
                            Glide.with(getApplication()).load(dataSnapshot.getValue()+"").into(ii);

                        }
                        else{
                            Toast.makeText(profile.this, "upload profile pic", Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("source").getValue()!=null && dataSnapshot.child("music_progress").getValue()!=null){
                    source=dataSnapshot.child("source").getValue().toString();
                    music_progress= Integer.parseInt(dataSnapshot.child("music_progress").getValue().toString());
                    book_progress= Integer.parseInt(dataSnapshot.child("book_progress").getValue().toString());
                    meditate_progress= Integer.parseInt(dataSnapshot.child("meditate_progress").getValue().toString());
                    //Toast.makeText(getApplication(), music_progress+source+"", Toast.LENGTH_SHORT).show();
                    progg.setProgress((music_progress));
                    progg2.setProgress(book_progress);
                    prog3.setProgress(meditate_progress);
                    //prog.setProgressColor(Color.BLACK);
                    String set="Progress:"+(music_progress)+"%";
                    tt.setText(set);
                    int settext=(music_progress/25)+1;
                    level1.setText("Level: "+settext);
                    num1.setText((settext-1)+" videos completed");

                    tt2.setText("Progress:"+(book_progress)+"%");
                    int settext2=(book_progress/25)+1;
                    level2.setText("Level: "+settext2);
                    num2.setText((settext2-1)+" stories completed");

                    tt3.setText("Progress:"+(meditate_progress)+"%");
                    int settext22=(meditate_progress/25)+1;
                    level3.setText("Level: "+settext22);
                    num3.setText((settext22-1)+" level completed");




                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profile.this, "dasdasd", Toast.LENGTH_SHORT).show();
            }
        });

        // prog.setProgressColor(Color.parseColor("#ed3b27"));
        //prog.setProgressBackgroundColor(Color.parseColor("#808080"));

        progg.setMax(100.0f);






    }

    public void upload(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            Uri uri=data.getData();
            mStorageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    db.setValue(taskSnapshot.getDownloadUrl()+"");
                    Toast.makeText(profile.this, "upload successfull", Toast.LENGTH_SHORT).show();

                }
            });

        }
        else{
            Toast.makeText(this, "upload image error", Toast.LENGTH_SHORT).show();
        }
    }
}
