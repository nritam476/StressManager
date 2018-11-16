package com.example.capstone.stressmanager;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nritam476 on 19-10-2018.
 */

public class Focus_fragment1 extends android.support.v4.app.Fragment {
    TextView timer;

    public Focus_fragment1(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v1=getLayoutInflater().inflate(R.layout.focus_fragment1,null);

        timer=v1.findViewById(R.id.textView7);



        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(""+ millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
            }
        }.start();

        return v1;
    }
}
