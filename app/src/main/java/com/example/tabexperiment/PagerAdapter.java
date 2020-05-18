package com.example.tabexperiment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        mNumOfTabs = numOfTabs;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new frag1();
            case 1: return new frag2();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
