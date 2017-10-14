package com.wps.guiamedico.Interfaces;

import com.wps.guiamedico.Model.Estabelecimento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lemue on 03/09/2017.
 */

public interface InterfaceRetrofit
{
    String ENDPOINT = "http://mobile-aceite.tcu.gov.br/mapa-da-saude/";

    @GET("rest/estabelecimentos")
    Call<List<Estabelecimento>> callListEstabelecimentos();
}
