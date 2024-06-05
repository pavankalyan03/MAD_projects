package com.example.inmyhands;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private keyvalueAdapter studentAdapter;
    private DatabaseReference studentRef;

    TextView Nametxt;
    String batch,reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        reg = intent.getStringExtra("reg");
        batch = intent.getStringExtra("batch");

        Nametxt = findViewById(R.id.nametxt);
        recyclerView = findViewById(R.id.recyclerView); // Get your RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new keyvalueAdapter(new ArrayList<>());
        recyclerView.setAdapter(studentAdapter);

        studentRef = FirebaseDatabase.getInstance().getReference("16xgL7eKtJ_ZtoMKOWulSAxmSaE_QvTCNpUWGPKxD9jw/"+batch);

        fetchStudentData(reg);

    }

    private void fetchStudentData(String registrationNumber) {
        studentRef.child(registrationNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<keyvalue> keyValueItems = new ArrayList<>();
                    for (DataSnapshot child : snapshot.getChildren()) {
                        String key = child.getKey();
                        Object valueObject = child.getValue();
                        if (key.equals("Name")) {
                            Nametxt.setText(valueObject.toString());
                        } else {
                            String value;
                            if (valueObject instanceof Long) {
                                value = String.valueOf(valueObject); // Convert Long to String
                            } else {
                                assert valueObject != null;
                                value = valueObject.toString(); // Assume it's already a String or can be converted
                            }

                            keyValueItems.add(new keyvalue(key, value));
                        }
                    }
                    studentAdapter.updateItems(keyValueItems);
                } else {
                    Toast.makeText(DetailsActivity.this, "Student Details Dosen't Existt.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }
}