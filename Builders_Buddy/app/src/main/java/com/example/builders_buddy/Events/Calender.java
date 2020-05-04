package com.example.builders_buddy.Events;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Calender<TAG> extends AppCompatActivity {

    FirebaseAuth Auth;

    // ...
    DatabaseReference mDatabase;
    CollectionReference newData;
    CalendarView calendarView;
    EditText Event, location, phoneNumber;
    FirebaseFirestore db;
    Button saveevent;
    Events events;
    Button save;
    String filePath, eLocation, event, sPhoneNumber, date;
    private static final String TAG = "Calender";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mmm-yyyy", Locale.ENGLISH);

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        getSupportActionBar().setTitle("Calendar");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        newData = db.collection( Auth.getUid()+"/Event/EventInfo");

        calendarView = findViewById(R.id.calendar);
        Event = findViewById(R.id.event);
        location = findViewById(R.id.location);
        phoneNumber = findViewById(R.id.phnenumber);
        saveevent = findViewById(R.id.save);
        events = new Events();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "/" + (i1 + 1) + "/" + i;
                Toast.makeText(Calender.this, "you have set a event for " + date, Toast.LENGTH_LONG).show();

            }
        });
    }


    public void save(View view) {
        event = Event.getText().toString().trim();
        //String jobTime = time.getText().toString().trim();

        if (TextUtils.isEmpty(event)) {
            Event.setError("you must enter a event");
        }

        eLocation = location.getText().toString().trim();

        if (TextUtils.isEmpty(eLocation)) {
            location.setError("you must set a location");
        }
        sPhoneNumber = phoneNumber.getText().toString().trim();

        if (TextUtils.isEmpty(sPhoneNumber)) {
            phoneNumber.setError("Please Enter a Phone number");
        } else if (android.util.Patterns.PHONE.matcher(sPhoneNumber).matches()) {
            events.setPhoneNumber(sPhoneNumber);
        } else {
            phoneNumber.setError("This number is not a valid phone number");
        }
        if (filePath != null) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child(filePath + "/Events/");
        } else if (Auth.getUid() == null) {
            Toast.makeText(this, "Unable to find user id Using test File path", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Failed to find User");
        } else {

            mDatabase = FirebaseDatabase.getInstance().getReference().child(Auth.getUid() + "/Events/");
            calendarView.getDate();
            events.setEvent(event);
            events.setLocation(eLocation);
            events.setDate(date);

            if(events.getDate() == null || events.getEvent() == null|| events.getLocation() == null || events.getPhoneNumber() == null)
            {
                Toast.makeText(this, "Failed to upload", Toast.LENGTH_SHORT).show();
            }
            else{
                newData.add(events).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Calender.this, "Event Has been saved", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Calender.this, "Failed Uploaded", Toast.LENGTH_LONG).show();
                    }
                });

            }
            }

    }

    public void View(View view) {
        startActivity(new Intent(getApplicationContext(), EventsList.class));
    }

    public void authCheck(boolean Testing) {
        if (Testing) {
            newData = db.collection( "Testing/Event/EventInfo");
        }
    }
}

