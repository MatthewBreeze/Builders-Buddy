package com.example.builders_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_In extends AppCompatActivity {

    Button Login;
    TextView email,password;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        Login = findViewById(R.id.Sign_in);

    }

    public void Register(View view) {
        startActivity(new Intent(getApplicationContext(), Register.class));

    }

    public void Login(View view)        {
        String semail = email.getText().toString().trim();
        if(TextUtils.isEmpty(semail))
        {
            email.setError("Please enter your email");
            return;
        }

        String sPassword = password.getText().toString().trim();
        if(TextUtils.isEmpty(sPassword))
        {
            password.setError("Please enter your accounts password");
            return;
        }
        // auth user
        Auth.signInWithEmailAndPassword(semail,sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(getApplicationContext(),Home_Page.class));

                }
                else
                {
                    Toast.makeText(Sign_In.this,"Login failed please enter your account details"+task.getException(),Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
