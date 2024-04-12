package com.example.expandable_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class nestedAdapter extends RecyclerView.Adapter<nestedAdapter.nestedViewHolder> {

    private final List<String> mlist;

    public nestedAdapter(List<String> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @NotNull
    @Override
    public nestedViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_item, parent, false);
        return new nestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull nestedViewHolder holder, int position) {
        if (mlist != null && position < mlist.size()) {
            holder.mtv.setText(mlist.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mlist != null ? mlist.size() : 0;
    }

    public static class nestedViewHolder extends RecyclerView.ViewHolder {

        private final TextView mtv;
        public nestedViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            mtv = itemView.findViewById(R.id.nestedItemTv);
        }
    }

}
