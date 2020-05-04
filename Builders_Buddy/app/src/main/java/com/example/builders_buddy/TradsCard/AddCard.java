package com.example.builders_buddy.TradsCard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.Utilities.ImageRequestHandler;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class AddCard extends AppCompatActivity {
    ImageView selectedImage;
    CardUplaod cardUplaod;
    public EditText company;
    FirebaseStorage fStorage;
    FirebaseAuth Auth;
    DatabaseReference mDatabase;
    CollectionReference newData;
    private Drawable chosenPic;
    private static final String TAG = "Add TradeCard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        getSupportActionBar().setTitle("Add Trade Card");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Button add = findViewById(R.id.addcard);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                      Manifest.permission.CAMERA
                    },
                    100);
        }
        Auth = FirebaseAuth.getInstance();
        selectedImage = findViewById(R.id.view);
        company = findViewById(R.id.company);

        if(Auth.getUid() == null) {

            Log.d(TAG, "Firebase Failed to get user id");
        }
        else {
            fStorage = FirebaseStorage.getInstance();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            newData = db.collection( Auth.getUid().trim() +"/"  + "TradeCards" + "/"  + " TradeCard Info" );
            cardUplaod = new CardUplaod();
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageRequestHandler.getCameraImage(v);
            }
        });
    }


    public void gal(View view) {
        ImageRequestHandler.getGalleryImage(view);
    }

    public byte[] imageToByteArray(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectedImage = ImageRequestHandler.handleResponse(requestCode, resultCode, data, selectedImage);
        chosenPic = selectedImage.getDrawable();
    }

    public void save(View view) {

        String comapnyName = company.getText().toString().trim();
        if(TextUtils.isEmpty(comapnyName))
        {
            company.setError("Please enter The company");
        }
        else {
            cardUplaod.setCompanyName(comapnyName);
            cardUplaod.setImageDownLoad(Auth.getUid().trim() +"/"  + "Trade Cards" + "/"  + comapnyName + ".jpg");// upload data

            StorageReference sRef = fStorage.getReference()
                    .child( Auth.getUid().trim() +"/"  + "Trade Cards" + "/"  + comapnyName + ".jpg");
            UploadTask uploadTask = sRef.putBytes(imageToByteArray(selectedImage.getDrawable()));

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    newData.add(cardUplaod).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddCard.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                        }// image and data was upload correctly
                    });
                    Log.d("STORAGE SUCCEEDED", taskSnapshot.getMetadata().toString());
                    startActivity(new Intent(getApplicationContext(), TradeCards.class));
                }// only image worked
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddCard.this, "Failed to upload failed card please try again", Toast.LENGTH_LONG).show();
                    Log.d("STORAGE FAILED", e.toString());
                }
            });// Image failed

        }

    }
}











