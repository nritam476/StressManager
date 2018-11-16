package com.example.capstone.stressmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Nritam476 on 19-10-2018.
 */

public class MyAdapter extends FragmentStatePagerAdapter {
    int prog;
    public MyAdapter(FragmentManager fm,int pro) {
        super(fm);
        prog=pro;
    }

    @Override
    public Fragment getItem(int position) {

        if(position==0) {
            Fragment one = new Focus_fragment1();

            return one;

        }
        else if(position==1) {

            Fragment two = new Focus_fragment2();
            Bundle b=new Bundle();
            b.putInt("prog",prog);
            two.setArguments(b);
            return two;
        }
        else {
            Fragment three=new Focus_fragment3();
            Bundle b=new Bundle();
            b.putInt("prog",prog);
            three.setArguments(b);
            return three;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
