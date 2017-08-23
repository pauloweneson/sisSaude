package com.wps.guiamedico;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class esqueciActivity extends AppCompatActivity {

    private EditText tbEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);
    }

    public void btVoltarOnClick(View v) {
        Intent telaLogin = new Intent(this, MainActivity.class);
        startActivity(telaLogin);
    }

    public void btEsqueciEnviar(View v) {
        Funcoes funcoes = new Funcoes();
        tbEmail = (EditText) findViewById(R.id.tb_email);

        if (!tbEmail.getText().toString().equals("")) {

            mAuth = FirebaseAuth.getInstance();
            mAuth
                    .sendPasswordResetEmail(tbEmail.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "E-MAIL ENVIADO COM SUCESSO", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "E-MAIL NÃO ENVIADO COM SUCESSO", Toast.LENGTH_SHORT).show();
        }

    }


}
