package com.example.builders_buddy.Invoice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.RecView.InvoiceAdapter;
import com.example.builders_buddy.RecView.InvoiceRecView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewInvocieAll extends AppCompatActivity {
    FirebaseAuth Auth;
    FirebaseFirestore db;
    CollectionReference newData;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private RecyclerView recyclerView;
    private InvoiceAdapter adapterrecyclerView;
    String collectionPath;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_invocie);
        getSupportActionBar().setTitle("Invoice List");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        if(Auth.getUid()== null)
        {
            collectionPath ="Nxvst8dJ8BVKPIMmacfFZ6Fx8Jc2/Jobs/Invoices";
        }// testing collection path
        else{
            collectionPath = Auth.getUid() + "/Jobs/Invoices";
        }// prodction db
       
        if(collectionPath == null)
        {
            Toast.makeText(this, "Faeild to get collection path", Toast.LENGTH_SHORT).show();
        }else {
            getCollection();
        }
            }
            public void getCollection(){
                newData = db.collection(collectionPath);
                final ArrayList<InvoiceRecView> exampleList = new ArrayList<>();
                newData.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                            String docId = documentSnapshots.getId();
                            InvoiceTemplate template =  documentSnapshots.toObject(InvoiceTemplate.class);
                            exampleList.add(new InvoiceRecView(template.getJobName(), template.getTotal(), "Man Hours: "+ template.getManHours(),docId));
                        }// gets data from db
                        recyclerView = findViewById(R.id.RecView);
                        recyclerView.setHasFixedSize(true);
                        adapterrecyclerView = new InvoiceAdapter(exampleList);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapterrecyclerView);
                    }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        adapterrecyclerView.setOnItemClickListener(new InvoiceAdapter.OnItemClickListener() {
                            @Override
                            public void onIteamClick(int position) {
                                exampleList.get(position);
                                Intent jobView = new Intent(getApplicationContext(), InvoiceDisplay.class);
                                String docId = exampleList.get(position).getDocId();
                                jobView.putExtra("path",collectionPath);
                                jobView.putExtra("InvoiceId", docId);
                                startActivity(jobView);

                            }
                        });// puts data in array from db to rec view
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewInvocieAll.this, "Failed to get invoices please try again", Toast.LENGTH_SHORT).show();
                    }
                });// failed db

            }
    }

    



