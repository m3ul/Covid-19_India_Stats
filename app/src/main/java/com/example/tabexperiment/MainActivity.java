package com.example.tabexperiment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabLayout);

//        tabLayout.addTab(tabLayout.newTab().setText("Home"));
//        tabLayout.addTab(tabLayout.newTab().setText("National Stats"));
//
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager=(ViewPager)findViewById(R.id.viewPager);
        final PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
            tv.setTypeface(Typeface.SANS_SERIF);
            tabLayout.getTabAt(i).setCustomView(tv);
        }

    }
}
