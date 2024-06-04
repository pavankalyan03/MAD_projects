package com.example.sample_insta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

public class ProfileActivity extends AppCompatActivity {
    private EditText etUsername,etPhone;
    private Button btnUpdate;
    private ImageView profImage;
    private Uri imageUri=null;
    private DatabaseReference mPhotoHubUsers;
    private FirebaseAuth mAuth;
    private String userId="";
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        etUsername=findViewById(R.id.etUsername);
        etPhone=findViewById(R.id.etPhone);
        btnUpdate=findViewById(R.id.btnUpdate);
        profImage=findViewById(R.id.profImage);
        pd=new ProgressDialog(this);
        pd.setTitle("Just a moment");
        pd.setMessage("Updating profile...");
        mAuth=FirebaseAuth.getInstance();
        userId=mAuth.getUid();


        mPhotoHubUsers= FirebaseDatabase.getInstance().getReference().child("PhotoHub/Users");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username=etUsername.getText().toString().trim();
                final String phone=etPhone.getText().toString().trim();

                if (TextUtils.isEmpty(username) || username.length()<4)
                {
                    setError(etUsername,"Invalid username");
                }else {
                    if (TextUtils.isEmpty(phone) || phone.length()<10)
                    {
                        setError(etPhone,"Invalid phone number");

                    }else {

                        pd.show();
                        if (mAuth.getCurrentUser()==null)
                        {

                        }else {


                            DatabaseReference newUser=mPhotoHubUsers.child(userId);
                            String email = mAuth.getCurrentUser().getEmail();
                            newUser.child("name").setValue(username);
                            newUser.child("email").setValue(email);
                            newUser.child("phone").setValue(phone).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                                    }
                                }
                            });


                        }

                    }
                }


            }
        });

    }

    private void setError(EditText etPhone, String s) {
        etPhone.setError(s);
        etPhone.requestFocus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPhotoHubUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("name"))
                {
                    String name,phone;
                    name=dataSnapshot.child("name").getValue().toString();
                    phone=dataSnapshot.child("phone").getValue().toString();

                    etPhone.setText(phone);
                    etUsername.setText(name);





                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}