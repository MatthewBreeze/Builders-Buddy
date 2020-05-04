package com.example.builders_buddy.Invoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.RecView.InvoiceItemRecView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class InvoiceDisplay extends AppCompatActivity {

    TextView tName, tHours, tTotal, lMaterils,qty,price;

    String sName, sHours, sMaterials, sTotal;
    FirebaseAuth Auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData;
    InvoiceTemplate template;
    String invoiceId, currentInvoice,collectionPath;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_display);
        getSupportActionBar().setTitle("Invoice Display");
        collectionPath = getIntent().getExtras().getString("path");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Auth = FirebaseAuth.getInstance();
        newData = db.collection(collectionPath);



        Auth = FirebaseAuth.getInstance();
        tName = findViewById(R.id.name);
        tHours = findViewById(R.id.manHours);
        tTotal = findViewById(R.id.total);
        lMaterils = findViewById(R.id.materialslist);
        qty = findViewById(R.id.qtylis);
        price = findViewById(R.id.Pricelist);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        currentInvoice = getIntent().getExtras().getString("InvoiceId");
        if(collectionPath == null){
            Toast.makeText(this, "Failed to get File path", Toast.LENGTH_SHORT).show();
        }
        else {
            getInvoice();
        }


    }// pulling data
 public void getInvoice(){
     newData.get()
             .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                 @Override
                 public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                     for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                         invoiceId = documentSnapshots.getId();
                         if(invoiceId.contains(currentInvoice))
                         {
                             String materials = "";
                             String qtylis = "";
                             String pricelis = "";
                             InvoiceTemplate template =  documentSnapshots.toObject(InvoiceTemplate.class);
                             tName.setText(" " + template.getJobName());
                             tHours.setText( " Man Hours :" +template.getManHours());
                             tTotal.setText(" " + template.getTotal());

                             for(InvoiceItemRecView mats : template.getMaterails())
                             {
                                 materials += " " + mats.getMaterial()+ "\n";
                                 qtylis += " " + mats.getQty()+ "\n";
                                 pricelis +=  " " +  mats.getPrice() + "\n";
                             }// materials display
                             lMaterils.setText(materials);
                             qty.setText(qtylis);
                             price.setText(pricelis);
                         }
                     }
                 }// on getting data
             }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             Toast.makeText(InvoiceDisplay.this, "Failed to get Invoice", Toast.LENGTH_SHORT).show();
         }
     });// fail checks
 }
    public void Del(View view) {
        newData.document(invoiceId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(InvoiceDisplay.this, "Invoice deleted", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InvoiceDisplay.this, "Failed to delete please try again", Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(getApplicationContext(), ViewInvocieAll.class));
    }// del invoice
}









