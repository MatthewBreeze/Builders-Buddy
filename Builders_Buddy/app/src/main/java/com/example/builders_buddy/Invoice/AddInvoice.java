package com.example.builders_buddy.Invoice;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.RecView.InvoiceItemAdapter;
import com.example.builders_buddy.RecView.InvoiceItemRecView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddInvoice extends AppCompatActivity {
    final ArrayList<InvoiceItemRecView> exampleList = new ArrayList<>();
    Button add, save;
    TextView Total;
    EditText JobName, ManHours, materials, qty, price, hourlyRate;
    addMaterials addMaterials;
    double pr, itotal, dHourlyRate ;
    int qt;
    FirebaseAuth Auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData;
    private RecyclerView recyclerView;
    private InvoiceItemAdapter adapterrecyclerView;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);
        Auth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("New Invoice");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        add = findViewById(R.id.addMaterials);
        save = findViewById(R.id.save);
        Total = findViewById(R.id.total);
        JobName = findViewById(R.id.jobName);
        ManHours = findViewById(R.id.Hours);
        materials = findViewById(R.id.Materials);
        hourlyRate = findViewById(R.id.pricePerHour);


        qty = findViewById(R.id.QTY);
        price = findViewById(R.id.Price);
        addMaterials = new addMaterials();
        newData = db.collection( Auth.getUid() + "/Jobs/Invoices" );

    }

    public void addMaterials(View view) {

        String m = materials.getText().toString().trim();
        String q = qty.getText().toString().trim();
        String p = price.getText().toString().trim();


        if(TextUtils.isEmpty(m))
        {
            materials.setError("Must enter materials");
        }
        if(TextUtils.isEmpty(q))
        {
            qty.setError("Must enter quantity");
        }
            try {
                qt = Integer.parseInt(q);
            } catch (NumberFormatException nfe) {
                // Handle parse error.
            }
        if(TextUtils.isEmpty(p))
        {
            price.setError("Must enter price");
        }
            try {
                pr = Double.parseDouble(p);
            } catch (NumberFormatException nfe) {
                // Handle parse error.
            }

        if(exampleList == null){

            Toast.makeText(this, "Failed to add item please ensure all Sections are filled ", Toast.LENGTH_SHORT).show();
        }
        else{
            itotal = itotal + pr * qt;

            Total.setText( "Total :  £" + itotal);

            exampleList.add(new InvoiceItemRecView(m, q , p));

            recyclerView = findViewById(R.id.RecView);
            recyclerView.setHasFixedSize(true);
            adapterrecyclerView = new InvoiceItemAdapter(exampleList);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapterrecyclerView);
        }
    }

    public void SaveInvoice(View view)
    {
        String Iname = JobName.getText().toString().trim();
        String Ihours = ManHours.getText().toString().trim();
        String total = "";
        double dMandHours;
        try {
            dHourlyRate = Double.parseDouble(hourlyRate.getText().toString().trim());
            dMandHours = Double.parseDouble(ManHours.getText().toString().trim());

            itotal = itotal + dMandHours * dHourlyRate;

            Total.setText( "Total :  £" + itotal);


        } catch(NumberFormatException nfe) {
            Log.d("AddInvoice","Failed to convert hourly rate to double");
        }//data checks

        if(TextUtils.isEmpty(Iname)){
            JobName.setError("Please set a Job name");
        }//data checks
        if(TextUtils.isEmpty(Ihours)){
            ManHours.setError("Please Enter Man Hours");
        }//data checks
        total = Total.getText().toString().trim();
        InvoiceTemplate template = new InvoiceTemplate(Iname, Ihours,total,exampleList,dHourlyRate);// mapping data
        if(Auth == null || TextUtils.isEmpty(Iname)||TextUtils.isEmpty(Ihours))
        {
            Toast.makeText(this, "Invoice Failed upload", Toast.LENGTH_SHORT).show();
        }// checks
        else{

            newData.add(template).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(AddInvoice.this, "Invoice Uploaded", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddInvoice.this, "Failed to Up Load Invoice", Toast.LENGTH_SHORT).show();
                }
            });
        }// push data to db



    }
    public void authCheck(boolean Testing) {
        if (Testing) {
            newData = db.collection( "Testing/AddInvoice/Invoice");
        }// testing
    }
}

