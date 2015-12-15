package com.di.skeletonapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.di.skeletonapp.R;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<String> items = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter() {
    }

    public void addItem(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userName.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName;

        public ViewHolder(View v) {
            super(v);

            userName = (TextView) v.findViewById(R.id.list_username);
        }
    }

}
