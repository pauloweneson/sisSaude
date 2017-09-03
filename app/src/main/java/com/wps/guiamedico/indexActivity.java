package com.wps.guiamedico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class indexActivity extends AppCompatActivity {
    List<String> listEspecialidades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Funcoes funcoes = new Funcoes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        /*Para adicionar item no Spinner*/
        listEspecialidades = new ArrayList();
        listEspecialidades.add("Ginecologia");
        listEspecialidades.add("Dermatologia");
        listEspecialidades.add("Psiquiatria");

        funcoes.addItemSpinner(this, (Spinner) findViewById(R.id.spinner_especialidade), listEspecialidades);
        /*Fim adicionar item no Spinner*/
    }
}
