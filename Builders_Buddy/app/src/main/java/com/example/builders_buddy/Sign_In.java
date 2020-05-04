package com.example.builders_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.builders_buddy.Utilities.ForgotPassword;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.storage.FirebaseStorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_In extends AppCompatActivity {

    Button Login;
    TextView email,password;
    public FirebaseAuth Auth;
    FirebaseStorage fStore;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 234;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("    Sign in");

        Auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        Login = findViewById(R.id.Sign_in);
        if (Auth.getCurrentUser() != null) {
            // User is signed in (getCurrentUser() will be null if not signed in)
            startActivity(new Intent(getApplicationContext(), Home_Page.class));
            finish();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = Auth.getCurrentUser();
    }// auto login
    public void Register(View view) {
        startActivity(new Intent(getApplicationContext(), Register.class));

    }//register

    public void Login(View view)        {
        String semail = email.getText().toString().trim();
        if(TextUtils.isEmpty(semail))
        {
            email.setError("Please enter your email");
            return;
        }//email checkl

        String sPassword = password.getText().toString().trim();
        if(TextUtils.isEmpty(sPassword))
        {
            password.setError("Please enter your accounts password");
            return;
        }// password check
        // auth user
        Auth.signInWithEmailAndPassword(semail,sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(getApplicationContext(),Home_Page.class));

                }// login on success
                else
                {
                    Toast.makeText(Sign_In.this,"Login failed please enter your account details"+task.getException(),Toast.LENGTH_LONG).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Sign_In.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }//on fail
        });


    }//log in

    public void forgotemail(View view) {
        startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
    }// reset password

    public void Google(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }// google log in
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
           credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            Auth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = Auth.getCurrentUser();
                                startActivity(new Intent(getApplicationContext(), Home_Page.class));
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(Sign_In.this, "Logo In Failed", Toast.LENGTH_SHORT).show();

                            }



                        }

                    });
        }// google sign in
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed
            }
        }
    }// google sign in on activity
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }// google sign in
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Sign_in) {
            signIn();
        }

        }
}// google sign in
