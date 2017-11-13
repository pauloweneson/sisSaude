package com.wps.guiamedico.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Comentario {
    private String comentario;
    private String email;
    private String codCnes;

    public void mostraComentario(String comentario, String email, String codCnes){
        comentario = getComentario();
        email = getEmail();
        codCnes = getCodCnes();
    }

    public String getCodCnes() {
        return codCnes;
    }

    public void setCodCnes(String codCnes) {
        this.codCnes = codCnes;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


