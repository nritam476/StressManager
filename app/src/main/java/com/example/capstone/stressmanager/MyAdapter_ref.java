package com.example.capstone.stressmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Nritam476 on 20-10-2018.
 */

public class MyAdapter_ref extends FragmentStatePagerAdapter {
    Fragment_ref f;
    public MyAdapter_ref(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position>=0 && position<13){

            f=new Fragment_ref();
            Bundle b=new Bundle();
            b.putInt("pos",position);
            f.setArguments(b);

        }
        return f;
    }

    @Override
    public int getCount() {
        return 12;
    }
}
