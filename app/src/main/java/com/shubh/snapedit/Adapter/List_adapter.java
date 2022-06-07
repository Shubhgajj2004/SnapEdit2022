package com.shubh.snapedit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shubh.snapedit.Model.image_list_model;
import com.shubh.snapedit.R;

import java.util.ArrayList;

public class List_adapter extends RecyclerView.Adapter<List_adapter.viewHolder> {
    ArrayList<image_list_model> list;
    Context context;

    public List_adapter(Context context , ArrayList<image_list_model> list)
    {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_image , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getPath()).placeholder(R.drawable.ic_gallary_photo).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class viewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_id);
        }
    }


}
