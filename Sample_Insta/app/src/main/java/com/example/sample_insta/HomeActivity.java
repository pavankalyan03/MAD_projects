package com.example.sample_insta;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView photoList;
    private DatabaseReference mPhotosDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        photoList = findViewById(R.id.photoList);
        photoList.setHasFixedSize(true);
        photoList.setLayoutManager(new LinearLayoutManager(this));

        mPhotosDatabase = FirebaseDatabase.getInstance().getReference().child("Photos");

        loadDataToUi();
    }

    private void loadDataToUi() {
        mPhotosDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Photos> photosList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Photos photo = snapshot.getValue(Photos.class);
                    if (photo != null) {
                        photosList.add(photo);
                    }
                }
                // Create adapter and set it to RecyclerView
                PhotosAdapter adapter = new PhotosAdapter(photosList, HomeActivity.this);
                photoList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
