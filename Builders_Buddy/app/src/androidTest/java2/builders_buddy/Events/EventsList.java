package com.example.builders_buddy.Events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.builders_buddy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EventsList extends AppCompatActivity {


    FirebaseAuth Auth;
    DatabaseReference mDatabase;
    ListView ListView;
    Events events;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        getSupportActionBar().setTitle("Builders Buddy");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        events = new Events();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        ListView = (ListView) findViewById(R.id.ListView);
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                startActivity(new Intent(getApplicationContext(), EventPage.class)
                        .putExtra("date",events.getDate())
                        .putExtra("location",events.getLocation())
                        .putExtra("time", events.getTime())
                        .putExtra("event",events.getEvent()));

            }
        });


        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {

                    events = ds.getValue(Events.class);
                    //if(events.getId() == Auth.getUid())
                   // {
                        arrayList.add(events.getDate() + "     |     "+ events.getLocation()+"     |      "+events.getEvent());
                  //  }
                }
                ListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    public void Back(View view)
    {
        startActivity(new Intent(getApplicationContext(), Calender.class));

    }

}