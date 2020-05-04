package com.example.builders_buddy.tax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.builders_buddy.R;

import androidx.appcompat.app.AppCompatActivity;

public class TaxPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_page);
    }

    public void October(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","October"));
    }

    public void January(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","January"));
    }

    public void February(View view) {
        startActivity(new Intent(getApplicationContext(), ReceiptCapture.class).putExtra("month","February"));
    }

    public void March(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","March"));
    }

    public void April(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","April"));
    }

    public void May(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","May"));
    }

    public void June(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","June"));
    }


    public void July(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","July"));
    }

    public void August(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","August"));
    }


    public void September(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","September"));
    }

    public void November(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","November"));
    }

    public void December(View view) {
        startActivity(new Intent(getApplicationContext(),ReceiptCapture.class).putExtra("month","December"));
    }
}// month selection for tax
