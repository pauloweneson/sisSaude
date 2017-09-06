package com.wps.guiamedico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class cadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void btVoltarOnClick(View v) {
        Intent telaLogin = new Intent(this,MainActivity.class);
        startActivity(telaLogin);
        finish();
    }

    public void btCadastrarOnClick(View v) {
        Intent telaPrincipal = new Intent(this, indexActivity.class);
        Funcoes funcoes = new Funcoes();
        EditText tbNome = (EditText) findViewById(R.id.tb_nome);
        EditText tbEmail = (EditText) findViewById(R.id.tb_email);
        EditText tbSenha = (EditText) findViewById(R.id.tb_senha);
        EditText tbSenhaConf = (EditText) findViewById(R.id.tb_confsenha);

        if(!tbNome.getText().toString().equals("") && !tbEmail.getText().toString().equals("") && !tbSenha.getText().toString().equals("") && !tbSenhaConf.getText().toString().equals("")) {
            if(tbSenha.getText().toString().equals(tbSenhaConf.getText().toString())) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(tbEmail.getText().toString(), tbSenha.getText().toString());
                Toast.makeText(getApplicationContext(),"Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
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
