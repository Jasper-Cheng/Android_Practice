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
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.FragmentAdapter;
import fragment.ListFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private CoordinatorLayout mCoordinatorLayout;
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
        mCoordinatorLayout=findViewById(R.id.coordinator);
        mDrawerLayout=findViewById(R.id.dl_main_drawer);
        NavigationView navigationView=findViewById(R.id.nv_main_navigation);
        if(navigationView!=null){
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    menuItem.setChecked(true);
                    String title=menuItem.getTitle().toString();
                    Toast.makeText(MainActivity.this,title,Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
        }
        floatButton=findViewById(R.id.floatButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mCoordinatorLayout,"点击成功",Snackbar.LENGTH_SHORT).show();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
