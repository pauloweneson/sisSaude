package com.wps.guiamedico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class indexActivity extends AppCompatActivity {
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Funcoes funcoes = new Funcoes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        /*Para adicionar item no Spinner*/
        list = new ArrayList();
        list.add("Ginecologia");
        list.add("Dermatologia");
        list.add("Psiquiatria");

        funcoes.addItemSpinner(this, (Spinner) findViewById(R.id.spinner),list);
        /*Fim adicionar item no Spinner*/
    }
}
