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

public class resetSenhaActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);
    }

    public void btnVoltarOnClick(View v) {
        Intent telaPrincipal = new Intent(this, MainActivity.class);
        startActivity(telaPrincipal);
        finish();
    }

    public void btnRecuperarOnClick(View v) {
        EditText emailRecuperar = (EditText) findViewById(R.id.emailRecuperar);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (!emailRecuperar.getText().toString().equals("")) {
            mAuth
                    .sendPasswordResetEmail(emailRecuperar.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "E-mail recuperado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(resetSenhaActivity.this, "E-mail não enviado, por favor tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {

            Toast.makeText(resetSenhaActivity.this, "E-mail não enviado, por favor tente novamente", Toast.LENGTH_SHORT).show();
        }
    }
}
