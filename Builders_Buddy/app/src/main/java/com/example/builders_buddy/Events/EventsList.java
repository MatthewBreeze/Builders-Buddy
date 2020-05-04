package com.example.builders_buddy.Events;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.RecView.EventAdapter;
import com.example.builders_buddy.RecView.EventRecView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EventsList extends AppCompatActivity {


    FirebaseAuth Auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData;
    Events events;
    private ArrayAdapter<String> adapter;
    private RecyclerView recyclerView;
    private EventAdapter adapterrecyclerView;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        getSupportActionBar().setTitle("Events List");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        events = new Events();
        Auth = FirebaseAuth.getInstance();
        path = Auth.getUid() + "/Event/EventInfo";
        testing(false);
        newData = db.collection(path);//production
        CreateList();




    }
public String testing(boolean testing){
        if(testing)
        {
            path = "Nxvst8dJ8BVKPIMmacfFZ6Fx8Jc2/Event/EventInfo";
        }
        if(!testing){

            if(Auth.getUid() == null){
                path = "Nxvst8dJ8BVKPIMmacfFZ6Fx8Jc2/Event/EventInfo";
            }else {
                path = Auth.getUid().trim() + "/Event/EventInfo";
            }
        }
    return path;// testing;
}

void CreateList(){
    final ArrayList<EventRecView> exampleList = new ArrayList<>();
    newData.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
        @Override
        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
            for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                Events events = documentSnapshots.toObject(Events.class);
                events.setId(documentSnapshots.getId());
                exampleList.add(new EventRecView(events.getEvent(), events.getLocation(), events.getPhoneNumber(), events.getDate(), events.getId()));
            }
            recyclerView = findViewById(R.id.RecView);
            recyclerView.setHasFixedSize(true);
            adapterrecyclerView = new EventAdapter(exampleList);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapterrecyclerView);
        }
    }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            adapterrecyclerView.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
                @Override
                public void onIteamClick(int position) {
                    exampleList.get(position);
                    Intent jobView = new Intent(getApplicationContext(), EventPage.class);
                    String event = exampleList.get(position).getTitle();
                    String date = exampleList.get(position).getDate();
                    String contactNumber = exampleList.get(position).getPhoneNumber();
                    String location = exampleList.get(position).getLocation();
                    String id = exampleList.get(position).getId();
                    jobView.putExtra("event", event);
                    jobView.putExtra("date", date);
                    jobView.putExtra("phone", contactNumber);
                    jobView.putExtra("location", location);
                    jobView.putExtra("id", id);
                    startActivity(jobView);

                }
            });
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(EventsList.this, "Failed to retrieve Data", Toast.LENGTH_SHORT).show();
        }
    });
}
}


