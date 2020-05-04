package com.example.builders_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Register extends AppCompatActivity {

    EditText rFirstNmame, rLastName,rEmail, rPassword,rPasswordcheck, rPhoneNumber;
    Button rRegister;
    FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Builders Buddy");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        rFirstNmame = findViewById(R.id.FirstName);//text
        rEmail = findViewById(R.id.Email);//text
        rPassword =findViewById(R.id.Password);//text
        rPhoneNumber = findViewById(R.id.PhoneNumber);//text
        rRegister = findViewById(R.id.Register);// Button
        rLastName = findViewById(R.id.Lastname);
        rPasswordcheck = findViewById(R.id.ReEnterPassword);
        Auth = FirebaseAuth.getInstance();

//        if(Auth.getCurrentUser()!= null)
//        {
//            startActivity(new Intent(getApplicationContext(),Home_Page.class));
//
//        }


        rRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = rEmail.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    rEmail.setError("Email is required");
                    return;
                }
                String  password = rPassword.getText().toString().trim();
                if(TextUtils.isEmpty(password))
                {
                    rPassword.setError("Password is required");
                    return;
                }
                if(password.length()< 6)
                {
                    rPassword.setError("password must be atleast 6 characters");
                    return;
                }
                String passworcheck = rPasswordcheck.getText().toString().trim();
                if(TextUtils.isEmpty(passworcheck) && !TextUtils.equals(password,passworcheck))
                {
                    rPasswordcheck.setError("password must be atleast 6 characters and match the first password");
                }

                String phonenumber = rPhoneNumber.getText().toString().trim();
                if(TextUtils.isEmpty(phonenumber))
                {
                    rPhoneNumber.setError("Phone number is required");
                    return;
                }
                String name = rFirstNmame.getText().toString().trim();
                if(TextUtils.isEmpty(name))
                {
                    rFirstNmame.setError("name is required");
                    return;
                }

                // regisitering user

                Auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this, "Your account has been created Successfully",Toast.LENGTH_LONG ).show();
                            startActivity(new Intent(getApplicationContext(),Home_Page.class));
                        }

                        else {
                            Toast.makeText(Register.this, "Something went worng" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
