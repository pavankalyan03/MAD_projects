package com.example.inmyhands;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button search, searchbyname;
    EditText regno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.button);
        searchbyname = findViewById(R.id.button2);
        regno = findViewById(R.id.editTextText);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg = regno.getText().toString();
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("reg", reg);
                startActivity(intent);
            }
        });

        searchbyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg = regno.getText().toString();
                Intent intent = new Intent(MainActivity.this, DetailsbynameActivity.class);
                intent.putExtra("reg", reg);
                startActivity(intent);
            }
        });



    }
}