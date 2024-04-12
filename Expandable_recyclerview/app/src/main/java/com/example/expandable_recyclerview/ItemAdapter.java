package com.example.expandable_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import android.widget.TextView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<Datamodel> mlist;
    private List<String> list;

    public ItemAdapter(List<Datamodel> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemViewHolder holder, int position) {
        Datamodel model = mlist.get(position);
        holder.mtextView.setText(model.getItemText());

        boolean isExpandable = model.isExpandable();
        holder.relativeLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if (isExpandable) {
            holder.mArrowImage.setImageResource(R.drawable.arrow_up);
        }else {
            holder.mArrowImage.setImageResource(R.drawable.arrow_down);
        }

        nestedAdapter adapter = new nestedAdapter(list);
        holder.nestedrecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.nestedrecyclerView.setHasFixedSize(true);
        holder.nestedrecyclerView.setAdapter(adapter);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setExpandable(!model.isExpandable());
                list = model.getNestedList();
                notifyItemChanged(holder.getAdapterPosition());

            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private RelativeLayout relativeLayout;
        private TextView mtextView;
        private ImageView mArrowImage;
        private RecyclerView nestedrecyclerView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
            relativeLayout = itemView.findViewById(R.id.expandable_layout);
            mtextView = itemView.findViewById(R.id.itemTv);
            mArrowImage = itemView.findViewById(R.id.arrow_imageview);
            nestedrecyclerView = itemView.findViewById(R.id.child_rv);
        }
    }
}
