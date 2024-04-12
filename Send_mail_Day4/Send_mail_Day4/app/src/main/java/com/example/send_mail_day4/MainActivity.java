package com.example.send_mail_day4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;


public class MainActivity extends AppCompatActivity {

    EditText message,email;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        email = findViewById(R.id.editTextText);
        message = findViewById(R.id.editTextText2);

        send = findViewById(R.id.button3);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String receiver = email.getText().toString();
                String msg = message.getText().toString();

                Python py = Python.getInstance();
                PyObject pyobj = py.getModule("script");
                PyObject obj = pyobj.callAttr("main",receiver,msg);

                Toast.makeText(MainActivity.this, "Check The mail", Toast.LENGTH_SHORT).show();

                email.setText("");
                message.setText("");
            }
        });
    }
}