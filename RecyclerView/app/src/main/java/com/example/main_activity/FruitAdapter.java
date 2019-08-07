package com.example.main_activity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    @NonNull
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitView.setImageResource(fruit.getImageId());
        holder.fruitText.setText(fruit.getName());
    }

    public FruitAdapter(List<Fruit> mFruitList) {
        this.mFruitList = mFruitList;
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitView;
        TextView fruitText;

        public ViewHolder(@NonNull View view) {
            super(view);
            fruitText = view.findViewById(R.id.fruit_name);
            fruitView = view.findViewById(R.id.fruit_image);
        }
    }
}
