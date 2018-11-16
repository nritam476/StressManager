package com.example.capstone.stressmanager;

import android.content.Context;
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

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * Created by Nritam476 on 19-10-2018.
 */

public class Focus_fragment2 extends Fragment {
    TextView t;
    ImageView i1;
    EditText e1,e2,e3;
    View v1;
    DialogPlus dialog;
    String ans1,ans2,ans3;

    public Focus_fragment2(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int prog=getArguments().getInt("prog");
        v1=inflater.inflate(R.layout.focus_fragment2,null);
        i1=v1.findViewById(R.id.number_image);
        t=v1.findViewById(R.id.textView8);


        if(prog==0) {
            i1.setImageResource(R.drawable.number_image);
            ans1="9320";
            ans2="0142";
            ans3="2506";
        }
        else if(prog==25){
            i1.setImageResource(R.drawable.number_collagetwo);
            ans1="8976";
            ans2="9087";
            ans3="9987";
        }
        else if(prog==50){
            i1.setImageResource(R.drawable.number_collagethree);
            ans1="0963";
            ans2="9282";
            ans3="4523";
        }
        else if(prog==75){
            i1.setImageResource(R.drawable.number_collagefour);
            ans1="1254";
            ans2="6528";
            ans3="8312";
        }
        else if(prog==100){
            i1.setImageResource(R.drawable.number_collagefive);
            ans1="8247";
            ans2="0911";
            ans3="1119";

        }

        new CountDownTimer(45000, 1000) {

            public void onTick(long millisUntilFinished) {
                t.setText(""+ millisUntilFinished / 1000);
            }

            public void onFinish() {
                t.setText("done!");
                i1.setImageAlpha(0);


                View v2=getLayoutInflater().inflate(R.layout.number_quiz,null);
                Button b=v2.findViewById(R.id.button3);
                e1=v2.findViewById(R.id.editText);
                e2=v2.findViewById(R.id.editText2);
                e3=v2.findViewById(R.id.editText3);

                dialog=DialogPlus.newDialog(getActivity()).setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

                    }
                }).setGravity(Gravity.CENTER).setCancelable(false).setContentHolder(new ViewHolder(v2)).create();

                dialog.show();

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String num1,num2,num3;
                        if(e1.getText()!=null && e2.getText()!=null && e3.getText()!=null){
                            num1= String.valueOf(e1.getText());
                            num2= String.valueOf(e2.getText());
                            num3= String.valueOf(e3.getText());

                            if(num1.equals(ans1)&& num2.equals(ans2)&& num3.equals(ans3)){
                                Toast.makeText(getActivity(), "CORRECT ANSWER", Toast.LENGTH_SHORT).show();
                                i1.setImageAlpha(1);
                                dialog.dismiss();
                            }
                            else{

                                Toast.makeText(getActivity(), "WRONG ANSWER", Toast.LENGTH_LONG).show();
                            }


                        }
                        else{
                            Toast.makeText(getActivity().getApplicationContext(), "Enter something", Toast.LENGTH_SHORT).show();
                        }


                    }
                });



            }
        }.start();

        new CountDownTimer(58000, 1000) {

            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                dialog.dismiss();
            }
        }.start();



        return v1;
    }



}
