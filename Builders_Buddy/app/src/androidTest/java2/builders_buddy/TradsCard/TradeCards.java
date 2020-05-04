package com.example.builders_buddy.TradsCard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.builders_buddy.R;

import androidx.appcompat.app.AppCompatActivity;

public class TradeCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_cards);
        getSupportActionBar().setTitle("Builders Buddy");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

    }

    public void AddCard(View view)
    {
        startActivity(new Intent(getApplicationContext(), AddCard.class));

    }
}
