package com.example.capstone.stressmanager;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Focus_main extends AppCompatActivity {


    android.support.v4.view.ViewPager vp;
    int pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_main);
        pro=getIntent().getIntExtra("level",0);


        vp=findViewById(R.id.vp);

        MyAdapter mad=new MyAdapter(getSupportFragmentManager(),pro);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                vp.setCurrentItem(1);
            }
        }.start();

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                vp.setCurrentItem(2);
            }
        }.start();

        new CountDownTimer(150000, 1000) {

            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                Intent ii=new Intent(getApplicationContext(),homeScreen.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ii);
            }
        }.start();




        vp.setAdapter(mad);








    }
}
