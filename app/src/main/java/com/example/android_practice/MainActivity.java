package com.example.android_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
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
    private Toolbar toolbar;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCustomToolbar();
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

    public void initCustomToolbar(){
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Jasper");
        AppBarLayout appbar=findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(Math.abs(i) >= appBarLayout.getTotalScrollRange()/2){
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }else{
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
                }
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_share:
                        Toast.makeText(MainActivity.this,"action_share",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this,"action_settings",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
