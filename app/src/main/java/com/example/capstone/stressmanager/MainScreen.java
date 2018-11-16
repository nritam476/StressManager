package com.example.capstone.stressmanager;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

public class MainScreen extends AppCompatActivity {
    //RadioGroup rg;
    DatabaseReference dr;
    FirebaseUser user;
    String temp;
    RadioGroup rgg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main_screen);
       // rg=findViewById(R.id.rg);
        user= FirebaseAuth.getInstance().getCurrentUser();
        String email=user.getEmail();
        int index = email.indexOf('@');

        email = email.substring(0,index);

        dr= FirebaseDatabase.getInstance().getReference().child(email);



        temp="new";
        View v1=getLayoutInflater().inflate(R.layout.depression_menu,null);


        DialogPlus dialog=DialogPlus.newDialog(this).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

            }
        }).setGravity(Gravity.TOP).setCancelable(false).setContentHolder(new ViewHolder(v1)).create();


        rgg=v1.findViewById(R.id.rg2);
        Button go=v1.findViewById(R.id.submit);


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((rgg.getCheckedRadioButtonId()==R.id.relationship)||(rgg.getCheckedRadioButtonId()==R.id.financial)||(rgg.getCheckedRadioButtonId()==R.id.student)) {


                    if (rgg.getCheckedRadioButtonId() == R.id.relationship) {

                        temp = "relationship";
                    } else if (rgg.getCheckedRadioButtonId() == R.id.financial) {
                        temp = "financial";
                    } else if (rgg.getCheckedRadioButtonId() == R.id.student) {
                        temp = "student";
                    }


                    dr.child("source").setValue(temp);
                    dr.child("music_progress").setValue(0);
                    dr.child("book_progress").setValue(0);
                    dr.child("meditate_progress").setValue(0);

                    Intent ii = new Intent(getApplication(), Reference.class);
                    ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(ii);

                }
                else{

                    Toast.makeText(MainScreen.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }



                    /*else{
                        Toast.makeText(MainScreen.this, "Choose something", Toast.LENGTH_SHORT).show();
                    }*/
            }
        });


        dialog.show();





    }


}
