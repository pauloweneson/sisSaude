package com.wps.guiamedico;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOnClick(View v) {
        email = (EditText)findViewById(R.id.tb_email);
        senha = (EditText)findViewById(R.id.tb_senha);
        Button entrar = (Button) findViewById(R.id.bt_entrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().trim().length()>= 1 && senha.getText().toString().trim().length()>= 1){
                    validarLogin();
                }else {
                    Toast.makeText(MainActivity.this,"Digite o usuário e senha!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void linkCadastrarOnClick(View v) {
        Intent telaCadastro = new Intent(MainActivity.this, cadastroActivity.class);
        startActivity(telaCadastro);
    }
    public void esqueciOnClick (View v){
        Intent telaEsqueci = new Intent(this, resetSenhaActivity.class);
        startActivity(telaEsqueci);
    }

    private void validarLogin(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email.getText().toString(),senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent chamaTela = new Intent(MainActivity.this,indexActivity.class);
                /*if(true){
                    Intent chamaTela = new Intent(MainActivity.this,MapsActivity.class);
                    chamaTela.putExtra("numero",1234);*/
                    startActivity(chamaTela);
                    Toast.makeText(MainActivity.this,"Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    ((EditText)findViewById(R.id.tb_senha)).setText("");
                    Toast.makeText(MainActivity.this,"Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
