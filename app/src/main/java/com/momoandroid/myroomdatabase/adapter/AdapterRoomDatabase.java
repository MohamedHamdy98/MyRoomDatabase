package com.momoandroid.myroomdatabase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.momoandroid.myroomdatabase.R;
import com.momoandroid.myroomdatabase.databinding.RecyclerViewItemBinding;
import com.momoandroid.myroomdatabase.models.User;

import java.util.ArrayList;
import java.util.List;

public class AdapterRoomDatabase extends RecyclerView.Adapter<AdapterRoomDatabase.ViewHolder> {
    private List<User> modelArrayList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.recycler_view_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = modelArrayList.get(position);
        holder.binding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public void setList(List<User> models) {
        this.modelArrayList = models;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewItemBinding binding;

        public ViewHolder(@NonNull RecyclerViewItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}




