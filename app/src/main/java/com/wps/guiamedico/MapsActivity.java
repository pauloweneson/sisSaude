package com.wps.guiamedico;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;
    private Float latitude;
    private Float longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        /*====================================================================================*/
        TextView textView;
        Intent it = getIntent();

        textView = (TextView)findViewById(R.id.tx_local);
        textView.setText((String)it.getStringExtra("nome"));

        textView = (TextView)findViewById(R.id.tx_telefone);
        textView.setText((String)it.getStringExtra("telefone"));

        textView = (TextView)findViewById(R.id.tx_endereco);
        textView.setText((String)it.getStringExtra("endereco"));

        latitude = Float.parseFloat((String)it.getStringExtra("latitude"));
        longitude = Float.parseFloat((String)it.getStringExtra("longitude"));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-15.834786, -47.912461);
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Mapa"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17.0f));
    }
}
