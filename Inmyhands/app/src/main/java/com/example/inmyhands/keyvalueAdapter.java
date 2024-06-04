package com.example.inmyhands;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class keyvalueAdapter extends RecyclerView.Adapter<keyvalueAdapter.ViewHolder> {
    private List<keyvalue> keyvalueList;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView key;
        public TextView value;

        public ViewHolder(View view)
        {
            super(view);
            key = (TextView) view.findViewById(R.id.key);
            value = (TextView) view.findViewById(R.id.value);
        }
    }

    public keyvalueAdapter(List<keyvalue> keyvalueList)
    {
        this.keyvalueList = keyvalueList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyvalue_list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        keyvalue keyvalue = keyvalueList.get(position);
        holder.key.setText(keyvalue.getKey());
        holder.value.setText(keyvalue.getValue());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateItems(List<keyvalue> newItems) {
        this.keyvalueList = newItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return keyvalueList.size();
    }


}
