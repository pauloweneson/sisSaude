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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class cadastroActivity extends AppCompatActivity {

    private EditText tbNome;
    private EditText tbEmail;
    private EditText tbSenha;
    private EditText tbSenhaConf;
    private FirebaseAuth mAuth;

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
        tbNome = (EditText) findViewById(R.id.tb_nome);
        tbEmail = (EditText) findViewById(R.id.tb_email);
        tbSenha = (EditText) findViewById(R.id.tb_senha);
        tbSenhaConf = (EditText) findViewById(R.id.tb_confsenha);

        if(!tbNome.getText().toString().equals("") && !tbEmail.getText().toString().equals("") && !tbSenha.getText().toString().equals("") && !tbSenhaConf.getText().toString().equals("")) {
            if(tbSenha.getText().toString().equals(tbSenhaConf.getText().toString())) {
                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(tbEmail.getText().toString(),tbSenha.getText().toString());
                Toast.makeText(getApplicationContext(),"Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                //finish();
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
