package com.example.builders_buddy.tax;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.Utilities.ImageRequestHandler;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiptCapture extends AppCompatActivity {
    TextView title;
    EditText toatal, res;
    ImageView selectedImage;
    FirebaseStorage fStorage;
    FirebaseAuth Auth;
    DatabaseReference mDatabase;
    TaxUpload upload;
    private Drawable chosenPic;
    CollectionReference newData;
    String month;
    String repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipet_captue);

            getSupportActionBar().setTitle("    Receipt Capture");
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            Auth = FirebaseAuth.getInstance();
            upload = new TaxUpload();
            toatal = findViewById(R.id.Total);
            res = findViewById(R.id.reason);
            title = findViewById(R.id.title);
            month =  getIntent().getExtras().getString("month") ;

            FirebaseFirestore db = FirebaseFirestore.getInstance();
           //
        if(Auth.getUid() != null)
        {
            newData = db.collection( Auth.getUid().trim() +"/"  + "Tax" + "/"  + month );//  production repo
        }else {

            newData = db.collection("Nxvst8dJ8BVKPIMmacfFZ6Fx8Jc2/" + "Tax" + "/"  + month );//  testing repo
        }
            selectedImage = findViewById(R.id.view);
            fStorage = FirebaseStorage.getInstance();
            title.setText(month + " Receipts");
        }

        public void gal(View view) {
            ImageRequestHandler.getGalleryImage(view);
        }// image select from gallery

        public byte[] imageToByteArray(Drawable image) {
            Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            return stream.toByteArray();
        }// image to bu array method


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            selectedImage = ImageRequestHandler.handleResponse(requestCode, resultCode, data, selectedImage);
            chosenPic = selectedImage.getDrawable();
        }// on activity for camera

        public void save(View view) {

            String sIotal =  toatal.getText().toString();
            String reason  = res.getText().toString().trim();
            if(TextUtils.isEmpty(sIotal))
            {
                toatal.setError("Please enter a total");

            }
            if (TextUtils.isEmpty(reason)){
                res.setError("Please enter the Purchase reason ");
            }// input checks
            if (!TextUtils.isEmpty(reason) && !TextUtils.isEmpty(sIotal)) {

                if(Auth.getUid() != null)
                {
                    repo = Auth.getUid().trim();
                    upload.setDoownloadUrl(Auth.getUid().trim() +"/"  + "Tax" + "/"  + month +" /"  + toatal.getText() + ".jpg");//  production repo
                }else {
                        repo = "Nxvst8dJ8BVKPIMmacfFZ6Fx8Jc2";
                    upload.setDoownloadUrl("Nxvst8dJ8BVKPIMmacfFZ6Fx8Jc2/" + "Tax" + "/"  + month + " /"  + toatal.getText() + ".jpg" );//  testing repo
                }


                upload.setTotal(sIotal);
                upload.setReason(reason);
                newData.add(upload);
                StorageReference sRef = fStorage.getReference()
                        .child( repo +"/"  + "Tax" + "/"  + month +" /"  + toatal.getText() + ".jpg");
                UploadTask uploadTask = sRef.putBytes(imageToByteArray(selectedImage.getDrawable()));

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d("STORAGE SUCCEEDED", taskSnapshot.getMetadata().toString());

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("STORAGE FAILED", e.toString());
                        Toast.makeText(ReceiptCapture.this, "Image has Failed please try again later", Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(this, "Image has been Uploaded", Toast.LENGTH_SHORT).show();
            }
        }// checks pass upload

        public void TakeImage(View view) {
            ImageRequestHandler.getCameraImage(view);
        }// Photo handler

        public void ViewAll(View view) {
            startActivity(new Intent(getApplicationContext(), ReceiptTotal.class).putExtra("Month" , month));
        }// list months rec
    }

