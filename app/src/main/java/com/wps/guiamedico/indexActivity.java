package com.wps.guiamedico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wps.guiamedico.Adapter.AdapterIndex;
import com.wps.guiamedico.Interfaces.InterfaceRetrofit;
import com.wps.guiamedico.Model.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class indexActivity extends AppCompatActivity
{
    List<String> listEspecialidades;
    private static final String TAG = indexActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManeger;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Funcoes funcoes = new Funcoes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManeger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManeger);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InterfaceRetrofit.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceRetrofit interfaceRetrofit = retrofit.create(InterfaceRetrofit.class);

        Call<List<Estabelecimento>> callListEstabelecimentos = interfaceRetrofit.callListEstabelecimentos();

        callListEstabelecimentos.enqueue(new retrofit2.Callback<List<Estabelecimento>>(){
            @Override
            public void onResponse(Call<List<Estabelecimento>> call, Response<List<Estabelecimento>> response) {
                if(response.isSuccessful()){
                    List<Estabelecimento> le = new ArrayList<Estabelecimento>();
                    for(Estabelecimento estabelecimento : response.body())
                        le.add(estabelecimento);

                    recyclerView.setAdapter(new AdapterIndex(le,indexActivity.this));

                    Log.e("RESPOSTA", "Sucesso. Boa muleke!");
                }
                else{
                    Log.e("RESPOSTA", "Response is faild");
                }
            }

            @Override
            public void onFailure(Call<List<Estabelecimento>> call, Throwable t) {
                Log.e("RESPOSTA", "On Failure - dooorga");
            }
        });

        /*Para adicionar item no Spinner*/
        listEspecialidades = new ArrayList();
        listEspecialidades.add("Ginecologia");
        listEspecialidades.add("Dermatologia");
        listEspecialidades.add("Psiquiatria");

        funcoes.addItemSpinner(this, (Spinner) findViewById(R.id.spinner_especialidade), listEspecialidades);
        /*Fim adicionar item no Spinner*/
    }
    public void btnLogout (View v){

        Log.d(TAG, "logout:");
        mAuth.getInstance().signOut();
        FirebaseAuth.getInstance().signOut();
        finish();
    }

}
