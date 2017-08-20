package com.wps.guiamedico;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class cadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void btVoltarOnClick(View v) {
        Intent telaLogin = new Intent(this,MainActivity.class);
        startActivity(telaLogin);
    }

    public void btCadastrarOnClick(View v) {
        Intent telaPrincipal = new Intent(this, indexActivity.class);
        Funcoes funcoes = new Funcoes();
        TextView tbNome = (TextView) findViewById(R.id.tb_nome);
        TextView tbEmail = (TextView) findViewById(R.id.tb_email);
        TextView tbSenha = (TextView) findViewById(R.id.tb_senha);
        TextView tbSenhaConf = (TextView) findViewById(R.id.tb_confsenha);

        if(!tbNome.getText().toString().equals("") && !tbEmail.getText().toString().equals("") && !tbSenha.getText().toString().equals("") && !tbSenhaConf.getText().toString().equals("")) {
            if(tbSenha.getText().toString().equals(tbSenhaConf.getText().toString())) {
                funcoes.alerta(this,"Alerta","Usuario cadastrado com sucesso.");
                startActivity(telaPrincipal);
            }
            else{
                funcoes.alerta(this,"Alerta","Senha incompativel da senha de confirmação.");
                tbSenha.setText("");
                tbSenhaConf.setText("");
            }
        }
        else
            funcoes.alerta(this,"Alerta","Preencha todos os campos.");
    }
}
