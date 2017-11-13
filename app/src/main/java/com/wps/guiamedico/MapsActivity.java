package com.wps.guiamedico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wps.guiamedico.Model.Comentario;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;
    private Float latitude;
    private Float longitude;
    private String codCnes;
    private FirebaseUser user;
    //private FirebaseAuth mAuth;
    EditText texMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        texMessage = (EditText)findViewById(R.id.tx_message);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        TextView txTitle = (TextView)findViewById(R.id.txTitle);
        txTitle.setText("Informações");
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

        codCnes = it.getStringExtra("CodCnes");
        //Toast.makeText(getApplicationContext(),codCnes, Toast.LENGTH_LONG).show();
    }


    public void btnHome (View v){
        this.finish();
    }

    public void buttonOnClick(View v) {
        Comentario comentario = new Comentario();
        comentario.setComentario(texMessage.getText().toString());
        comentario.setEmail(user.getEmail().toString());
        comentario.setCodCnes(codCnes);
        if(texMessage != null) {
            FirebaseDatabase.getInstance().getReference().push().setValue(comentario);
            Toast.makeText(MapsActivity.this,"Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MapsActivity.this,"Deu ruim!", Toast.LENGTH_SHORT).show();
            texMessage.setText("");
        }

    }

    /*

    NÃO PRESTA ESSA MERDA WTF KKKK -------------------
    public void mostraComentario (){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("https://sissaude-f866d.firebaseio.com/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Comentario comentario = dataSnapshot.getValue(Comentario.class);
                System.out.println(comentario);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
    */
    public void btnLogout (View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
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