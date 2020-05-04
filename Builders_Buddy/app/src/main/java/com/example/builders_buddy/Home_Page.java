package com.example.builders_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.builders_buddy.Events.Calender;
import com.example.builders_buddy.Invoice.InvoiceHome;
import com.example.builders_buddy.TradsCard.TradeCards;
import com.example.builders_buddy.tax.TaxPage;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class Home_Page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Home");
        setContentView(R.layout.activity_home__page);

    }
    public void Logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Sign_In.class));

    }

    public void Calender(View view) {
        startActivity(new Intent(getApplicationContext(), Calender.class));

    }

    public void TradeCards(View view) {
        startActivity(new Intent(getApplicationContext(), TradeCards.class));

    }

    public void TaxPage(View view) {
        startActivity(new Intent(getApplicationContext(), TaxPage.class));

    }

    public void JobsList(View view) {
        startActivity(new Intent(getApplicationContext(), InvoiceHome.class));

    }
}
