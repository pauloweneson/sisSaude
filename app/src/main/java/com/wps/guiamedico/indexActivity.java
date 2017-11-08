package com.wps.guiamedico;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wps.guiamedico.Adapter.AdapterIndex;
import com.wps.guiamedico.Interfaces.InterfaceRetrofit;
import com.wps.guiamedico.Model.Especialidades;
import com.wps.guiamedico.Model.Estabelecimento;
import com.wps.guiamedico.Model.UF;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class indexActivity extends AppCompatActivity
{
    List<String> listEspecialidades;
    private static final String TAG = indexActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManeger;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    public Especialidades especialidades;
    public InterfaceRetrofit interfaceRetrofit;
    public UF uf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Funcoes funcoes = new Funcoes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        TextView txTitle = (TextView)findViewById(R.id.txTitle);
        txTitle.setText("SisSaúde");

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

        interfaceRetrofit = retrofit.create(InterfaceRetrofit.class);
        Call<List<Estabelecimento>> callListEstabelecimentos = interfaceRetrofit.callListEstabelecimentos("df");

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
        Spinner spinner_especialidade = (Spinner) findViewById(R.id.spinner_especialidade);
        Spinner spinner_uf = (Spinner) findViewById(R.id.spinner_uf);

        /*Para adicionar item no Spinner*/
        especialidades = new Especialidades();
        funcoes.addItemSpinner(this, spinner_especialidade, especialidades.listEspecialidades);
        uf = new UF();
        funcoes.addItemSpinner(this, spinner_uf, uf.listUF);
        /*Fim adicionar item no Spinner*/

        spinner_especialidade.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(!uf.listUF.get(position).toString().equals("Selecione"))
                            Toast.makeText(getApplicationContext(),especialidades.listEspecialidades.get(position).toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                    //add some code here
                }
        );
        spinner_uf.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(!uf.listUF.get(position).toString().equals("Selecione")) {
                            //Toast.makeText(getApplicationContext(), uf.listUF.get(position).toString(), Toast.LENGTH_LONG).show();
                            listarEstabelecimentos(uf.listUF.get(position).toString(),"");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                    //add some code here
                }
        );
    }
    public void btnLogout (View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }
    public void btnHome (View v){
        Toast.makeText(getApplicationContext(), "Calma calma papai.", Toast.LENGTH_LONG).show();
    }

    private void listarEstabelecimentos(String uf , String especialidade) {
        Call<List<Estabelecimento>> callListEstabelecimentos = interfaceRetrofit.callListEstabelecimentos(uf);

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
    }

}
