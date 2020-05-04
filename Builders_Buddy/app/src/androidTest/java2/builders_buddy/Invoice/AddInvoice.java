package com.example.builders_buddy.Invoice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class AddInvoice extends AppCompatActivity {
Button add, save;
TextView Total;
ListView List;
EditText JobName, ManHours, materials, qty, price;

InvoiceTemplate template;
addMaterials addMaterials;
    int qt, pr, itotal;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData = db.collection( "Invoice" );
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);

        getSupportActionBar().setTitle("Builders Buddy");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        add = findViewById(R.id.addMaterials);
        save = findViewById(R.id.save);
        Total = findViewById(R.id.total);
        List = findViewById(R.id.list);
        JobName = findViewById(R.id.jobName);
        ManHours = findViewById(R.id.Hours);
        materials = findViewById(R.id.Materials);
        qty = findViewById(R.id.QTY);
        price = findViewById(R.id.Price);
        addMaterials = new addMaterials();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

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
            qty.setError("Must enter quantity ");
        }
        try {
            qt = Integer.parseInt(q);
        } catch(NumberFormatException nfe) {
            // Handle parse error.
        }
        if(TextUtils.isEmpty(p))
        {
            price.setError("Must enter price");
        }
        try {
            pr = Integer.parseInt(p);
        } catch(NumberFormatException nfe) {
            // Handle parse error.
        }
        List.setAdapter(adapter);

        addMaterials.setMaterials(m);
        addMaterials.setQty(qt);
        addMaterials.setPrice(pr);

        itotal = itotal + pr * qt;

        Total.setText( "Total :  £" + itotal);

        arrayList.add( addMaterials.getMaterials() + "      |       " + addMaterials.getQty() + "   |    "+ addMaterials.getPrice());

        adapter.notifyDataSetChanged();
    }

    public void SaveInvoice(View view)
    {
        String Iname = JobName.getText().toString().trim();
        String Ihours = ManHours.getText().toString().trim();
        String total = Total.getText().toString().trim();
        InvoiceTemplate template = new InvoiceTemplate(Iname, Ihours,total,arrayList);
//        List<String> materilss = arrayList;


        newData.add(template);

        Toast.makeText(this, "Invoice Uploaded", Toast.LENGTH_SHORT).show();

    }
}
