package com.example.builders_buddy.Events;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.builders_buddy.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class FindJob extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Location currentLocation;
    LatLng latLng;
    LatLng jobLatLng;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static  final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    private static final float DEFAULT_ZOOM = 15f;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Job Location");
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_find_job);
        // Get the SupportMapFragment and request notification
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();


    }

   private void fetchLastLocation() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    currentLocation = location;
                    latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You Are Here");

                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    supportMapFragment.getMapAsync(FindJob.this);


                }
                else {
                    Toast.makeText(FindJob.this, "null" , Toast.LENGTH_LONG).show();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FindJob.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void geoLocate(){

        String searchString =  getIntent().getExtras().getString("location");

        Geocoder geocoder = new Geocoder(FindJob.this);
        List<Address> list = new ArrayList<>();
        try{
            Toast.makeText(this, "Found", Toast.LENGTH_SHORT).show();
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Toast.makeText(this, "Failed to Find Job location", Toast.LENGTH_SHORT).show();
        }
        if(list.size() >= 1){
            Address address = list.get(0);
            jobLatLng = new LatLng( address.getLatitude(), address.getLongitude());

        }else {
            Toast.makeText(this, "Failed to get Job Location", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveCamera(LatLng latLng, float zoom, String title){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();
    }
    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    // Include the OnCreate() method here too, as described above.
        @Override
        public void onMapReady(GoogleMap googleMap) {
            geoLocate();
            // Add a marker in Sydney, Australia,
            // and move the map's camera to the same location.

            if(jobLatLng != null){
                MarkerOptions jobMarkerOptions = new MarkerOptions().position(jobLatLng).title("Destination");


            if(jobMarkerOptions == null) {

                Toast.makeText(this, "Failed to fond job", Toast.LENGTH_SHORT).show();
            }
            else{
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                googleMap.addMarker(jobMarkerOptions);
            }

            }
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You Are Here");
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
            googleMap.addMarker(markerOptions);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }
}
