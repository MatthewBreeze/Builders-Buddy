package com.example.builders_buddy.tax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.builders_buddy.R;
import com.example.builders_buddy.Sign_In;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class TaxPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_page);

    }

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), com.example.builders_buddy.Sign_In.class));
        finish();
    }

    public void January(View view) {
        startActivity(new Intent(getApplicationContext(),January.class));

    }

    public void February(View view) {
        startActivity(new Intent(getApplicationContext(),February.class));
    }

    public void March(View view) {
        startActivity(new Intent(getApplicationContext(),March.class));
    }

    public void April(View view) {
        startActivity(new Intent(getApplicationContext(),April.class));
    }

    public void May(View view) {
        startActivity(new Intent(getApplicationContext(),May.class));
    }

    public void June(View view) {
        startActivity(new Intent(getApplicationContext(),June.class));
    }


    public void July(View view) {
        startActivity(new Intent(getApplicationContext(),July.class));
    }

    public void August(View view) {
        startActivity(new Intent(getApplicationContext(),August.class));
    }


    public void September(View view) {
        startActivity(new Intent(getApplicationContext(),September.class));
    }

    public void November(View view) {
        startActivity(new Intent(getApplicationContext(),November.class));
    }

    public void December(View view) {
        startActivity(new Intent(getApplicationContext(),December.class));
    }
}
