package com.example.builders_buddy.TradsCard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.builders_buddy.R;
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

public class CardView extends AppCompatActivity {
    StorageReference storageRef;
    ImageView view;
    String location, doc;
    FirebaseAuth Auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Trade Card View");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_card_view);
        Auth = FirebaseAuth.getInstance();
        location = getIntent().getExtras().getString("Download");
        view = findViewById(R.id.view);
        doc = getIntent().getExtras().getString("Doc");

        if(location == null)
        {
            Toast.makeText(this, "Failed to get Image", Toast.LENGTH_SHORT).show();
        }else {
            getCard();
        }
    }

    public void getCard(){
        storageRef =
                FirebaseStorage.getInstance().getReference();
        storageRef.child(location).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(view);// picasso to load image to image view
                        // Got the download URL for 'users/me/profile.png'
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("CardView ", "Failed to laod from firebase");
                Toast.makeText(CardView.this, "Failed to load image and Information ", Toast.LENGTH_SHORT).show();
            }
        });// Load card to view
    }


    public void Del(View view) {
        storageRef.child(location).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                newData = db.collection(Auth.getUid()+"/TradeCards/ TradeCard Info");
                newData.document(doc).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CardView.this, "Trade Card has been Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), TradeCards.class));
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CardView.this, "Failed to Delete this from the data base", Toast.LENGTH_SHORT).show();
            }
        });// Delete card function
    }
}
