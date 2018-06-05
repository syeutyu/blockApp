package com.example.lee.stopapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lee.stopapp.databinding.ItemBinding;

import java.io.Serializable;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Item> list;

    public void setList(List<Item> list) {
        this.list = list;
    }

    public void addData(Item item) {
        list.add(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Item item = list.get(position);
        final String path = item.getRoot();
        holder.itemBinding.appImage.setImageDrawable(item.getImage());
        holder.itemBinding.appName.setText(item.getName());
        holder.itemBinding.appRoot.setText(item.getRoot());
        holder.itemBinding.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(v, path);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void startActivity(View v, String path) {
        Intent intent = new Intent(v.getContext(), InfoActivity.class);
        intent.putExtra("imagePath", path);
        v.getContext().startActivity(intent);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBinding itemBinding;

        ViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }
    }
}
