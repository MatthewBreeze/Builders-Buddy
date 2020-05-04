package com.example.builders_buddy.Events;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.Utilities.SmsMessaging;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class EventPage extends AppCompatActivity {
    FirebaseAuth Auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData;
    FirebaseAuth auth;
    TextView tDate, tPhone, tLocation,tEvent;
    String date,phoneNumber,event,location, id;
    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        getSupportActionBar().setTitle("Event View");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Auth = FirebaseAuth.getInstance();
        newData = db.collection(Auth.getUid()+"/Event/EventInfo");

        tDate = (TextView) findViewById(R.id.date);
        tPhone = (TextView) findViewById(R.id.phoneNumber);
        tLocation = (TextView) findViewById(R.id.location);
        tEvent = (TextView) findViewById(R.id.event);

        // data passed through
        date = getIntent().getExtras().getString("date");
        phoneNumber = getIntent().getExtras().getString("phone");
        location = getIntent().getExtras().getString("location");
        event = getIntent().getExtras().getString("event");
        id = getIntent().getExtras().getString("id");

        //display checks
        tDate.setText("Date" + " " + date);
        if(phoneNumber == null)
        {
            tPhone.setText("No contact number given" );
        }
        else
        {
            tPhone.setText("Contact Number :" + " " + phoneNumber);
        }

        tLocation.setText("Location" + " " + location);
        tEvent.setText("Event" + " " + event);
    }


    public void find(View view) {
        startActivity(new Intent(getApplicationContext(), FindJob.class)
                .putExtra("location",location));
    }// google maps

    private void makePhoneCall() {
        String number = phoneNumber;
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(EventPage.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EventPage.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(EventPage.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }// phone call function

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }// on request for calling

    public void callJob(View view) {
        makePhoneCall();
    }

    public void textclient(View view) {
        startActivity(new Intent(getApplicationContext(), SmsMessaging.class)
                .putExtra("phone",phoneNumber));
    }// text client

    public void Del(View view) {

        newData.document(id).delete();
        
    }// delete
}
