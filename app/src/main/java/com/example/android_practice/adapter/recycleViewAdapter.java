package com.example.android_practice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_practice.R;

import java.util.List;


public class recycleViewAdapter extends RecyclerView.Adapter<recycleViewAdapter.ViewHolder> {
    private List<String> fruitlist=null;
    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.fruitImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,holder.getAdapterPosition());
//                Toast.makeText(view.getContext(),"you clicked view"+fruitlist.get(holder.getAdapterPosition()),Toast.LENGTH_LONG).show();
            }
        });
        holder.fruitImages.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onItemLongClick(v,holder.getAdapterPosition());
                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fruitImages.setImageResource(R.mipmap.ic_launcher);
        holder.fruitName.setText(fruitlist.get(position));
    }

    @Override
    public int getItemCount() {
        return fruitlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitImages;
        TextView fruitName;
        public ViewHolder(View view){
            super(view);
            fruitImages=view.findViewById(R.id.fruit_image);
            fruitName=view.findViewById(R.id.fruitname);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public recycleViewAdapter(List<String> fruitlist){
        this.fruitlist=fruitlist;
    }
}
