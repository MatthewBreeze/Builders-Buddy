package com.example.builders_buddy.tax;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.RecView.TaxAdapter;
import com.example.builders_buddy.RecView.TaxRecView;
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

public class ReceiptTotal extends AppCompatActivity {
    TextView header;
    FirebaseAuth Auth;
    double monthTotal;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData;
    private RecyclerView recyclerView;
    private TaxAdapter adapterrecyclerView;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_total);
        getSupportActionBar().setTitle("Receipt List");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        header = findViewById(R.id.totalValue);


        Auth = FirebaseAuth.getInstance();
        if (Auth.getUid() != null) {
            newData = db.collection(Auth.getUid().trim() + "/" + "Tax" + "/" + getIntent().getExtras().getString("Month"));//  db repo
        } else {
            newData = db.collection("Nxvst8dJ8BVKPIMmacfFZ6Fx8Jc2/" + "Tax" + "/" + getIntent().getExtras().getString("Month"));//  testing repo
        }
        if (newData == null) {
            Toast.makeText(this, "Failed to get data", Toast.LENGTH_SHORT).show();
        } else {

            getCollection();
        }
    }

    void getCollection(){

        final ArrayList<TaxRecView> exampleList = new ArrayList<>();
        newData.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {

                    TaxUpload upload = documentSnapshots.toObject(TaxUpload.class);// cast to data model
                    try {
                        monthTotal = monthTotal + Double.parseDouble(upload.getTotal());
                    } catch (NumberFormatException nfe) {
                        // Handle parse error.
                    }
                    exampleList.add(new TaxRecView(upload.getReason(), upload.getTotal(), upload.getDoownloadUrl(),documentSnapshots.getId()));
                }

                recyclerView = findViewById(R.id.RecView);
                recyclerView.setHasFixedSize(true);
                adapterrecyclerView = new TaxAdapter(exampleList);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapterrecyclerView);
            }// got data from db
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                header.setText("                    " + getIntent().getExtras().getString("Month") + "            " + "Total : £" + monthTotal);
                adapterrecyclerView.setOnItemClickListener(new TaxAdapter.OnItemClickListener() {
                    @Override
                    public void onIteamClick(int position) {
                        exampleList.get(position);
                        Intent ViewRex = new Intent(getApplicationContext(), Receipt_Display.class);
                        String id = exampleList.get(position).getId();
                        String url = exampleList.get(position).getDownloadUrl();
                        ViewRex.putExtra("Download", url);
                        ViewRex.putExtra("Doc",id);
                        ViewRex.putExtra("Month",getIntent().getExtras().getString("Month"));
                        startActivity(ViewRex);

                    }


                });// array for rec view
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ReceiptTotal.this, "Failed to get data please try again", Toast.LENGTH_LONG).show();
            }
        });// db fail check

    }

}






