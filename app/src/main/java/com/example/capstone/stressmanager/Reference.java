package com.example.capstone.stressmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Reference extends AppCompatActivity {

    android.support.v4.view.ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        vp=findViewById(R.id.vpp);
        MyAdapter_ref mad=new MyAdapter_ref(getSupportFragmentManager());
        vp.setAdapter(mad);






    }
}
