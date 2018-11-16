package com.example.capstone.stressmanager;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Nritam476 on 20-10-2018.
 */

public class Fragment_ref extends android.support.v4.app.Fragment{

    public Fragment_ref(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v1=inflater.inflate(R.layout.splash_switcher,null);
        ImageView im=v1.findViewById(R.id.imgpo);
        ImageView imm=v1.findViewById(R.id.fng);
        imm.setVisibility(View.VISIBLE);
        Animation an= AnimationUtils.loadAnimation(getActivity(),R.anim.rotatemove);
        int pos=getArguments().getInt("pos");
        if(pos!=11){

        imm.startAnimation(an);
        }


        if(pos==0){
            im.setImageResource(R.drawable.splash_one);
        }
        else if(pos==1){
                im.setImageResource(R.drawable.splash_two);
        }
        else if(pos==2){
            im.setImageResource(R.drawable.splash_eleven);
        }
        else if(pos==3){
            im.setImageResource(R.drawable.splash_twelve);
        }
        else if(pos==4){
            im.setImageResource(R.drawable.splash_ten);
        }
        else if(pos==5){
            im.setImageResource(R.drawable.splash_three);
        }
        else if(pos==6){
            im.setImageResource(R.drawable.splash_four);
        }
        else if(pos==7){
            im.setImageResource(R.drawable.splash_five);
        }
        else if(pos==8){
            im.setImageResource(R.drawable.splash_six);
        }
        else if(pos==9){
            im.setImageResource(R.drawable.splash_seven);
        }
        else if(pos==10){
            im.setImageResource(R.drawable.splash_eight);
        }
        else if(pos==11){
            imm.setVisibility(View.INVISIBLE);
            im.setImageResource(R.drawable.splash_nine);
            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getActivity(),homeScreen.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            });
        }
        return v1;
    }
}
