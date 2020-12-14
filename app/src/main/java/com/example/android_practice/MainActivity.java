package com.example.android_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.FragmentAdapter;
import fragment.ListFragment;

public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FloatingActionButton floatButton;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab=getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        mViewPager=findViewById(R.id.viewpager);
        mDrawerLayout=findViewById(R.id.main_content);
        floatButton=findViewById(R.id.floatButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mDrawerLayout,"点击成功",Snackbar.LENGTH_SHORT).show();
            }
        });
        initViewPager();
    }

    public void initViewPager(){
        mTabLayout=this.findViewById(R.id.tabs);
        List<String> titles=new ArrayList<>();
        for(int i=0;i<20;i++){
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
