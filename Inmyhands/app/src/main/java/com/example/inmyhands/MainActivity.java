package com.example.inmyhands;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button search, searchbyname;
    EditText regno;
    RadioGroup radioGroup;
    String batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.button);
        searchbyname = findViewById(R.id.button2);
        regno = findViewById(R.id.editTextText);
        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.b19)
                {
                    batch = "Batch19";
                }
                else if(checkedId == R.id.b20)
                {
                    batch = "Batch20";
                }
                else if(checkedId == R.id.b21)
                {
                    batch = "Batch21";
                }
                else if(checkedId == R.id.b22)
                {
                    batch = "Batch22";
                }
                else if(checkedId == R.id.b23)
                {
                    batch = "Batch23";
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg = regno.getText().toString();
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("reg", reg);
                intent.putExtra("batch", batch);
                startActivity(intent);
            }
        });

        searchbyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg = regno.getText().toString();
                Intent intent = new Intent(MainActivity.this, DetailsbynameActivity.class);
                intent.putExtra("reg", reg);
                intent.putExtra("batch", batch);
                startActivity(intent);
            }
        });



    }
}