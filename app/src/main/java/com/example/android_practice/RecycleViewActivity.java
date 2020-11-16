package com.example.android_practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_practice.adapter.recycleViewAdapter;
import com.example.android_practice.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {
    RecyclerView recycleView;
//    LinearLayoutManager layoutManager;
    GridLayoutManager gridLayoutManager;
    recycleViewAdapter recycleViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recycleView=findViewById(R.id.recycle_view);
//        layoutManager=new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recycleView.setLayoutManager(layoutManager);
        gridLayoutManager=new GridLayoutManager(this,5);
        recycleView.setLayoutManager(gridLayoutManager);
        recycleViewAdapter=new recycleViewAdapter(fullFruitData());
        recycleViewAdapter.setOnItemClickListener(new recycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this,"click this "+position,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this,"longClick this "+position,Toast.LENGTH_LONG).show();
            }
        });
        recycleView.addItemDecoration(new DividerItemDecoration(this, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL));
        recycleView.setAdapter(recycleViewAdapter);
    }

    public List<String> fullFruitData(){
        ArrayList<String> arrayList=new ArrayList<String>();
        for(int i=0;i<15;i++){
            arrayList.add("My name is "+i+" fruit");
        }
        return arrayList;
    }
}
