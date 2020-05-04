package com.example.builders_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Register extends AppCompatActivity {

    EditText rEmail, rPassword, rPasswordcheck;
    Button rRegister;
    FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        rEmail = findViewById(R.id.Email);//text
        rPassword = findViewById(R.id.Password);//text
        //rRegister = findViewById(R.id.registerNewClient);// Button
        rPasswordcheck = findViewById(R.id.ReEnterPassword);
        Auth = FirebaseAuth.getInstance();
    }

    public void register(View view) {
        String email = rEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            rEmail.setError("Email is required");
        }// email check
        String password = rPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            rPassword.setError("Password is required");
        }// password check
        if (password.length() < 6) {
            rPassword.setError("password must be atleast 6 characters");
        }// password check
        String passworcheck = rPasswordcheck.getText().toString().trim();
        if (TextUtils.isEmpty(passworcheck) && !TextUtils.equals(password, passworcheck)) {
            rPasswordcheck.setError("must match the first password");
        }// check password check
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && password.length() > 6 && !TextUtils.isEmpty(passworcheck) && TextUtils.equals(password, passworcheck) )
        {
        Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Your account has been created Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Home_Page.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "Something went wrong Please check your details or your internet and try again" , Toast.LENGTH_LONG).show();
            }
        });// regisitering user
        }
    }
}