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
    private TextView linkCadastrar;
    private EditText email;
    private EditText senha;
    private FirebaseAuth mAuth;
    private TextView linkEsqueci;
    private Button entrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void buttonOnClick(View v) {
        email = (EditText)findViewById(R.id.tb_email);
        senha = (EditText)findViewById(R.id.tb_senha);
        entrar = (Button)findViewById(R.id.bt_entrar);
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
    public void esqueci() {
        linkEsqueci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEsqueci = new Intent(MainActivity.this, esqueciActivity.class);
                startActivity(telaEsqueci);
            }
        });

    }
    private void validarLogin(){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email.getText().toString(),senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(true){
                        Toast.makeText(MainActivity.this,"Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent chamaTela = new Intent(MainActivity.this,indexActivity.class);
                        startActivity(chamaTela);
                    }else{
                        Intent chamaTela2 = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(chamaTela2);
                        ((EditText)findViewById(R.id.tb_senha)).setText("");
                        Toast.makeText(MainActivity.this,"Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
