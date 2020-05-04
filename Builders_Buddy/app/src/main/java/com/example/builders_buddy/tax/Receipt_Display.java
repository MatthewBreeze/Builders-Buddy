package com.example.builders_buddy.tax;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.TradsCard.TradeCards;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Receipt_Display extends AppCompatActivity {
    StorageReference storageRef;
    FirebaseAuth Auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData;
    ImageView view;
    TextView tTotal;
    String doc, location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("    Receipt Display");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_receipt__display);
        Auth = FirebaseAuth.getInstance();
        location = getIntent().getExtras().getString("Download");
        doc = getIntent().getExtras().getString("Doc");
        view = findViewById(R.id.view);

        if(location == null)
        {
            Toast.makeText(this, "Failed to get images", Toast.LENGTH_SHORT).show();
        }else{


         storageRef =
                FirebaseStorage.getInstance().getReference();
        storageRef.child(location).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(view);
                        // Got the download URL for 'users/me/profile.png'
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("CardView ", "Failed to laod from firebase");
                Toast.makeText(Receipt_Display.this, "Failed to load image and Information ", Toast.LENGTH_SHORT).show();
            }
        });
        }
    }// get and load in voice from Firebase

    public void Del(View view) {
        storageRef.child(location).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                newData = db.collection(Auth.getUid().trim() + "/" + "Tax" + "/" + getIntent().getExtras().getString("Month"));
                newData.document(doc).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Receipt_Display.this, "Receipt has been Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), TradeCards.class));
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Receipt_Display.this, "Failed to Delete from the data base", Toast.LENGTH_SHORT).show();
            }
        });// delete invoice
    }

}