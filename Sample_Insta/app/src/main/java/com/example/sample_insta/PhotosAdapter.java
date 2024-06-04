package com.example.sample_insta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private List<Photos> photosList;
    private Context context;

    public PhotosAdapter(List<Photos> photosList, Context context) {
        this.photosList = photosList;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_photo, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        Photos photo = photosList.get(position);
        holder.tvPost.setText(photo.getPostdetails());
//        Picasso.get().load(photo.getPostimage()).placeholder(R.drawable.photo).into(holder.postImage);
        // Set other views accordingly
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public static class PhotosViewHolder extends RecyclerView.ViewHolder {
        TextView tvPost;
        ImageView postImage;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPost = itemView.findViewById(R.id.tvDescription);
            postImage = itemView.findViewById(R.id.ivPostmage);
            // Initialize other views here
        }
    }
}
