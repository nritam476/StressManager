package com.example.capstone.stressmanager;

import android.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class MapsActivity extends AppCompatActivity{

    private GoogleMap mMap;
    String prefix;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient fc;
    double lat,lon;
    boolean isPermisssionGranted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        fc= LocationServices.getFusedLocationProviderClient(getApplication());

        checkLocationPermission();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        prefix="Hospital";
        autocompleteFragment.setText(prefix);

        





        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {





                mMap.clear();

                LatLng newlatlng = place.getLatLng();

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newlatlng, 12.0f));

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(newlatlng);
                markerOptions.title(""+place.getName());
                mMap.addMarker(markerOptions);

            }

            @Override
            public void onError(Status status) {
            }
        });

    }

    private void checkLocationPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            isPermisssionGranted = true;
            fc.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    Log.d("Tag","in on sucess ");
                    if(location != null)
                    {

                        lat = location.getLatitude();
                        lon = location.getLongitude();

                        initMap();
                    }
                    else
                    {
                        Toast.makeText(MapsActivity.this, "location is null", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},3);
        }


    }

    private void initMap() {

        Toast.makeText(this, "Initialize Map", Toast.LENGTH_SHORT).show();

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                if(isPermisssionGranted)
                {
                    LatLng mylatlng = new LatLng(lat,lon);
                   // Log.d("Tag","latitude "+latitude);
                    //Log.d("Tag","longitude "+longitude);

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylatlng, 12.0f));


                    mMap.setMyLocationEnabled(true);

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(mylatlng);
                    markerOptions.title("You are here");



                    mMap.addMarker(markerOptions);

                }

            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 2:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    checkLocationPermission();
                else
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
                break;

            case 3:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    checkLocationPermission();
                else
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

}
