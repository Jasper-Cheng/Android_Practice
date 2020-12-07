package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.FragmentAdapter;
import fragment.ListFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewPager=findViewById(R.id.viewpager);
        initViewPager();
    }

    public void initViewPager(){
        mTabLayout=this.findViewById(R.id.tabs);
        List<String> titles=new ArrayList<>();
        for(int i=0;i<12;i++){
            titles.add("I'm is "+i);
        }
        for (int i=0;i<titles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }

        List<Fragment> fragments=new ArrayList<>();
        for(int i=0;i<titles.size();i++){
            fragments.add(new ListFragment());
        }

        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),fragments,titles);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
