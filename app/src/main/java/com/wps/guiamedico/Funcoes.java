package com.wps.guiamedico;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by Apollo on 16/08/2017.
 */

public class Funcoes
{

    public Funcoes() {}

    public void alerta(Context objeto, String titulo, String texto) {

        AlertDialog.Builder alert = new AlertDialog.Builder(objeto);

        alert.setTitle(titulo);
        alert.setMessage(texto);
        alert.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alert.show();
    }

    public void addItemSpinner(indexActivity classe, Spinner spinner, List<String> item) {
        spinner.setAdapter(new ArrayAdapter<String>(classe, android.R.layout.simple_spinner_item,item));
    }
}
