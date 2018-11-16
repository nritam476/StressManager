package com.example.capstone.stressmanager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * Created by Nritam476 on 19-10-2018.
 */

public class Focus_fragment3 extends Fragment {
    DatabaseReference db;
    FirebaseUser user;
    View v1;
    ImageView i1;
    TextView t;
    EditText e1,e2,e3,e4;
    ImageView i10;
    String nam1,nam2,nam3,nam4;
    int alldone;

    public  Focus_fragment3(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final int prog=getArguments().getInt("prog");
        user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        int index = email.indexOf('@');

        email = email.substring(0, index);


        db = FirebaseDatabase.getInstance().getReference().child(email).child("meditate_progress");





        v1=inflater.inflate(R.layout.focus_fragment3,null);
        i1=v1.findViewById(R.id.photo_image);
        t=v1.findViewById(R.id.textView11);



        if(prog==0) {
            i1.setImageResource(R.drawable.photo_collage);
        }
        else if(prog==25){
            i1.setImageResource(R.drawable.collagetwo);
        }
        else if(prog==50){
            i1.setImageResource(R.drawable.collagethree);
        }
        else if(prog==75){
            i1.setImageResource(R.drawable.collagefour);
        }
        else if(prog==100){
            i1.setImageResource(R.drawable.collagefive);

        }

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                t.setText(""+ millisUntilFinished / 1000);
            }

            public void onFinish() {
                t.setText("done!");
                i1.setImageAlpha(0);


                View v2=getLayoutInflater().inflate(R.layout.photo_guess,null);
                Button b=v2.findViewById(R.id.but_result);
                i10=v2.findViewById(R.id.imageView10);
                e1=v2.findViewById(R.id.onee);
                e2=v2.findViewById(R.id.twoo);
                e3=v2.findViewById(R.id.three);
                e4=v2.findViewById(R.id.fourr);

                final DialogPlus dialog=DialogPlus.newDialog(getActivity()).setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

                    }
                }).setGravity(Gravity.CENTER).setCancelable(false).setContentHolder(new ViewHolder(v2)).create();


                if(prog==0) {
                    i10.setImageResource(R.drawable.photo_quiz);
                    nam1="rosamund clarke";
                    nam2="bertand cruise";
                    nam3="rakesh chopra";
                    nam4="isabella lopez";
                }
                else if(prog==25){
                    i10.setImageResource(R.drawable.collagetw);
                    nam1="robert downy";
                    nam2="ben john";
                    nam3="darshan javed";
                    nam4="penelope cruz";
                }
                else if(prog==50){
                    i10.setImageResource(R.drawable.collagethr);
                    nam1="ben afflek";
                    nam2="john lee";
                    nam3="nina jones";
                    nam4="daisy dan";
                }
                else if(prog==75){
                    i10.setImageResource(R.drawable.collagefou);
                    nam1="robert";
                    nam2="jimmy";
                    nam3="kristen";
                    nam4="raju";
                }
                else if(prog==100){
                    i10.setImageResource(R.drawable.collagefiv);
                    nam1="prem";
                    nam2="rohini";
                    nam3="ram";
                    nam4="naina";

                }

                dialog.show();
                alldone=0;
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String num1,num2,num3,num4;

                        if(e1.getText()!=null && e2.getText()!=null && e3.getText()!=null && e4.getText()!=null){
                            num1=e1.getText().toString();
                            num2=e2.getText().toString();
                            num3=e3.getText().toString();
                            num4=e4.getText().toString();

                            if(num1.equalsIgnoreCase(nam1)){
                                e1.setText("Correct");
                                alldone=alldone+1;
                            }
                            if(num2.equalsIgnoreCase(nam2)){
                                e2.setText("Correct");
                                alldone=alldone+1;
                            }
                            if(num3.equalsIgnoreCase(nam3)){
                                e3.setText("Correct");
                                alldone=alldone+1;
                            }
                            if(num4.equalsIgnoreCase(nam4)){
                                e4.setText("Correct");
                                alldone=alldone+1;
                            }
                            if(alldone==4){
                                Toast.makeText(getActivity(), "ALL CORRECT", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                if(prog!=100) {
                                    db.setValue(prog + 25);
                                }
                                else{
                                    Toast.makeText(getActivity(), "100 reached", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getActivity(), "NOT CORRECT", Toast.LENGTH_SHORT).show();

                            }


                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Complete all", Toast.LENGTH_SHORT).show();
                        }


                    }
                });



            }
        }.start();



        return v1;
    }
}
