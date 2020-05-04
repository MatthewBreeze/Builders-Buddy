package com.example.builders_buddy.Invoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.builders_buddy.R;

import androidx.appcompat.app.AppCompatActivity;

public class InvoiceHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_home);
        getSupportActionBar().setTitle("Invoice Home");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    public void CreateInvoice(View view) {
        startActivity(new Intent(getApplicationContext(),AddInvoice.class));
    }// create invoice

    public void ViewInvoice(View view) {
        startActivity(new Intent(getApplicationContext(), ViewInvocieAll.class));

    }// navigation intent to view Invoice

}
