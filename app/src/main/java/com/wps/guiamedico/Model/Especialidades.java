package com.wps.guiamedico.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apollo on 08/11/2017.
 */

public class Especialidades {

    public List<String> listEspecialidades;
    public Especialidades() {
        listEspecialidades = new ArrayList<>();

        listEspecialidades.add("Selecione");
        listEspecialidades.add("Atendimento Urgência");
        listEspecialidades.add("Atendimento Ambulatorial");
        listEspecialidades.add("Centro Cirúrgico");
        listEspecialidades.add("Obstetrícia");
        listEspecialidades.add("NeoNatal");
        listEspecialidades.add("Diálise");
    }
}
