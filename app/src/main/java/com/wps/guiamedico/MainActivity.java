package com.wps.guiamedico;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView linkCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOnClick(View v) {
        Intent telaIndex = new Intent(this,indexActivity.class);
        startActivity(telaIndex);
    }

    public void linkCadastrarOnClick(View v) {
        Intent telaCadastro = new Intent(MainActivity.this, cadastroActivity.class);
        startActivity(telaCadastro);
    }
}
