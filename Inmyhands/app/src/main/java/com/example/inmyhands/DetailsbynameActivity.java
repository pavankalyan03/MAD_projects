package com.example.inmyhands;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailsbynameActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<Student> allStudents = new ArrayList<>();
    private StudentAdapter adapter;

    String name, batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsbyname);
        Intent i = getIntent();
        name = i.getStringExtra("reg");
        batch = i.getStringExtra("batch");

        databaseReference = FirebaseDatabase.getInstance().getReference("16xgL7eKtJ_ZtoMKOWulSAxmSaE_QvTCNpUWGPKxD9jw").child(batch+"D");
        adapter = new StudentAdapter(this,new ArrayList<>(),batch);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadStudentNames(name);
    }

    private void loadStudentNames(String query) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                List<String> matchedNames = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("Name").getValue(String.class);
                    if (name != null && name.toLowerCase().contains(query.toLowerCase())) {
                        matchedNames.add(snapshot.getKey());
                    }
                }
                if (matchedNames.isEmpty()) {
                    Toast.makeText(DetailsbynameActivity.this, "No Name Matched with given Name", Toast.LENGTH_SHORT).show();
                }else{
                    retrieveStudentDetails(matchedNames);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void retrieveStudentDetails(List<String> matchedNames) {
        List<Map<String, Object>> studentDetailsList = new ArrayList<>();
        for (String name : matchedNames) {
            databaseReference.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        Map<String, Object> studentDetails = (Map<String, Object>) dataSnapshot.getValue();
                        if (studentDetails != null) {
                            studentDetailsList.add(studentDetails);
                            updateRecyclerView(studentDetailsList); // Update RecyclerView here or outside the loop
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Firebase", "Error: " + databaseError.getMessage());
                }
            });
        }
    }


    private void updateRecyclerView(List<Map<String, Object>> students) {
        adapter.updateData(students);
    }
}
