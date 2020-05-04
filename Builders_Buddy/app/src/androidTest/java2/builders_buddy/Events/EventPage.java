package com.example.builders_buddy.Events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.builders_buddy.R;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class EventPage extends AppCompatActivity {

    FirebaseAuth auth;
    TextView tDate, tTime, tLocation,tEvent;
    String date,time,event,location;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        getSupportActionBar().setTitle("Builders Buddy");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        tDate = (TextView) findViewById(R.id.date);
        tTime = (TextView) findViewById(R.id.time);
        tLocation = (TextView) findViewById(R.id.location);
        tEvent = (TextView) findViewById(R.id.event);


        date = getIntent().getExtras().getString("date");
        time = getIntent().getExtras().getString("time");
        location = getIntent().getExtras().getString("location");
        event = getIntent().getExtras().getString("event");

        tDate.setText("Date" + " " + date);
        if(time == null)
        {
            tTime.setText("Time" + " " + "No time Specified");
        }
        else
        {
            tTime.setText("Time" + " " + time);
        }

        tLocation.setText("Location" + " " + location);
        tEvent.setText("Event" + " " + event);

    }

    public void find(View view) {

        startActivity(new Intent(getApplicationContext(), FindJob.class)
                .putExtra("location",location));



    }
}
