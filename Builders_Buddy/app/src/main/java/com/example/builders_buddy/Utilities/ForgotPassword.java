package com.example.builders_buddy.Utilities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {
    FirebaseAuth Auth;

    EditText Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("ForgotPassword");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_forgot_password);
        Email = findViewById(R.id.forgotEmail);
        Auth = FirebaseAuth.getInstance();

    }

    public void sendLink(View view) {
        String sEmail = Email.getText().toString().trim();
        if(TextUtils.isEmpty(sEmail))
        {
            Email.setError("Please Enter your Email");
        }
        else {
            Auth.sendPasswordResetEmail(sEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ForgotPassword.this, "Check Your emails For a Password rest email", Toast.LENGTH_SHORT).show();
                    }
                  else{
                        Toast.makeText(ForgotPassword.this, "Something when wrong please check you email address and try again", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}
