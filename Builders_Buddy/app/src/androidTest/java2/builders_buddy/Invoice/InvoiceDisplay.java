package com.example.builders_buddy.Invoice;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class InvoiceDisplay extends AppCompatActivity {

    TextView tName, tHours, tTotal, lMaterils;

    String sName, sHours, sMaterials, sTotal;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData = db.collection("Invoice");
    InvoiceTemplate template;
    String invoiceId, currentInvoice;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_display);
        getSupportActionBar().setTitle("Builders Buddy");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        tName = findViewById(R.id.name);
        tHours = findViewById(R.id.manHours);
        tTotal = findViewById(R.id.total);
        lMaterils = findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        currentInvoice = getIntent().getExtras().getString("InvoiceId");
             newData.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                            invoiceId = documentSnapshots.getId();
                            if(invoiceId.contains(currentInvoice))
                           {

                                String materials = "";
                                Toast.makeText(InvoiceDisplay.this, "got id", Toast.LENGTH_LONG).show();
                                InvoiceTemplate template =  documentSnapshots.toObject(InvoiceTemplate.class);
                               tName.setText("Title :"+ template.getJobName());
                               tHours.setText( "Man Hours :" +template.getManHours());
                               tTotal.setText(template.getTotal());


                               for(String mats : template.getMaterails())
                               {
                                   materials += "\n" + mats;
                               }
                               lMaterils.setText(materials);
                           }

                            Toast.makeText(InvoiceDisplay.this, "got one", Toast.LENGTH_SHORT).show();
                        }





               }

                });
    }
}









