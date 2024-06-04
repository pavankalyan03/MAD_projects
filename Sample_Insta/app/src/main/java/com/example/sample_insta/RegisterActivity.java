package com.example.sample_insta;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail,etPassword,etPassword1;
    private Button btnRegister;
    private ProgressDialog pd;
    private FirebaseAuth mAuth;
    private LinearLayout parentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        mAuth=FirebaseAuth.getInstance();
        pd=new ProgressDialog(this);
        pd.setTitle("Just a moment");
        pd.setMessage("Creating account...");
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        etPassword1=findViewById(R.id.etPassword1);
        btnRegister=findViewById(R.id.btnSignUp);
        parentLayout=findViewById(R.id.parentLayout);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etEmail.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                String password1=etPassword1.getText().toString().trim();

                if (!email.contains("@") && TextUtils.isEmpty(email) && email.length()<7)
                {

                    givemesometoast(etEmail,"Invalid email.. ");
                }else if (!TextUtils.isEmpty(password) && password.length()>7 || !TextUtils.isEmpty(password1) && password1.length()>7 )
                {
                    //Do your stuffs here.
                    if (password.equals(password1) && password.length()>=7) {

                        pd.show();
                        mAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    pd.dismiss();
                                    finish();

                                    Pair[] pairs=new Pair[1];
                                    pairs[0]=new Pair<View,String>(etEmail,"etTransition");

                                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this,pairs);

                                    startActivity(new Intent(RegisterActivity.this,ProfileActivity.class),options.toBundle());

                                }else {
                                    pd.dismiss();
                                    Toast.makeText(RegisterActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        //  Toast.makeText(RegisterActivity.this, "Correct credentials", Toast.LENGTH_SHORT).show();

                        //givemesometoast("Your credentials input are correct...");
                    }else {
                        pd.dismiss();
                        givemesometoast(etPassword,"Passwords dont match...");

                    }

                }else{
                    givemesometoast(etPassword,"Invalid passwords ");
                }



            }
        });


    }

    private void givemesometoast(EditText eText,String s) {
        eText.setError(s);
        eText.requestFocus();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loginme(View view) {
        Pair[] pairs=new Pair[1];
        pairs[0]=new Pair<View,String>(etEmail,"etTransition");

        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this,pairs);

        startActivity(new Intent(RegisterActivity.this,MainActivity.class),options.toBundle());
        finish();
    }
}