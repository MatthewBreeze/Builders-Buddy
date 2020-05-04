package com.example.builders_buddy.TradsCard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.example.builders_buddy.RecView.TradCardExample;
import com.example.builders_buddy.RecView.TradeCardAdapret;
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


public class TradeCards extends AppCompatActivity {


    FirebaseAuth Auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference newData;
    String ImageDownload;
    private String Tag = "TradeCard";
    final ArrayList<TradCardExample> exampleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TradeCardAdapret adapterrecyclerView;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_cards);
        getSupportActionBar().setTitle("Trade Card Home");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Auth = FirebaseAuth.getInstance();
        if(Auth.getUid() != null) {
            newData = db.collection(Auth.getUid()+"/TradeCards/ TradeCard Info");
        }
        else{
            newData = db.collection("Nxvst8dJ8BVKPIMmacfFZ6Fx8Jc2/TradeCards/ TradeCard Info");
        }//testing repo

        if(newData == null){
            Toast.makeText(this, "Failed to get collections", Toast.LENGTH_SHORT).show();
        }else {
            getCards();
        }
    }

    public void getCards(){
        newData.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                    CardUplaod cards = documentSnapshots.toObject(CardUplaod.class);
                    exampleList.add(new TradCardExample(cards.getCompanyName(), cards.getImageDownLoad() ,documentSnapshots.getId()));
                }
                recyclerView = findViewById(R.id.RecView);
                recyclerView.setHasFixedSize(true);
                adapterrecyclerView = new TradeCardAdapret(exampleList);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapterrecyclerView);
            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                adapterrecyclerView.setOnItemClickListener(new TradeCardAdapret.OnItemClickListener() {
                    @Override
                    public void onIteamClick(int position) {
                        exampleList.get(position);
                        Intent viewCardIntend = new Intent(getApplicationContext(), CardView.class);
                        String comapanyname = exampleList.get(position).getTitle();
                        String url = exampleList.get(position).getLocation();
                        String id = exampleList.get(position).getId();
                        viewCardIntend.putExtra("name", comapanyname);
                        viewCardIntend.putExtra("Download", url);
                        viewCardIntend.putExtra("Doc", id);
                        startActivity(viewCardIntend);
                    }
                });// adds day to arrays for cards in rec view
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TradeCards.this, "Failed To Retrieve Data", Toast.LENGTH_SHORT).show();
            }
        });// failer liisitner
         }

    public void AddCard(View view) {
        startActivity(new Intent(getApplicationContext(), AddCard.class));

    }
}
//}


