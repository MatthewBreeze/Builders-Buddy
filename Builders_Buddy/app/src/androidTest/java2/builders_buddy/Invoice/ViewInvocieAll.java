package com.example.builders_buddy.Invoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ViewInvocieAll extends AppCompatActivity {
    ListView ListView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData = db.collection("Invoice");
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    InvoiceTemplate template;
    String invoiceId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_invocie);
        getSupportActionBar().setTitle("Builders Buddy");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        ListView = findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), InvoiceDisplay.class)
                        .putExtra("InvoiceId", invoiceId));

            }
        });
                newData.get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                               for ( QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                                    InvoiceTemplate template =  documentSnapshots.toObject(InvoiceTemplate.class);
                                   Toast.makeText(ViewInvocieAll.this, "got this far", Toast.LENGTH_SHORT).show();
                                  invoiceId = documentSnapshots.getId();
                                    arrayList.add(template.getJobName() + "     |     " + template.getTotal());
                                }
                                ListView.setAdapter(adapter);
                            }


                        });
            }
    }

    



