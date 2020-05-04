package com.example.builders_buddy.TradsCard;

import android.os.Bundle;

import com.example.builders_buddy.R;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_card);
        getSupportActionBar().setTitle("Builders Buddy");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
}
