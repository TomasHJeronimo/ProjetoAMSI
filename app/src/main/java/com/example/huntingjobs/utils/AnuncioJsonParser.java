package com.example.huntingjobs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.huntingjobs.Modelo.Anuncio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnuncioJsonParser {

    private ArrayList<Anuncio> anuncios;


    //json de uma lista de anuncios - GET ANUNCIOS
    public static ArrayList<Anuncio> parserJsonAnuncios(JSONArray resposta) {
        ArrayList<Anuncio> listaAnuncios = new ArrayList<>();

        try {
            for (int i = 0; i <= resposta.length(); i++) {
                JSONObject jsonAnuncio = (JSONObject) resposta.get(i);
                int id = jsonAnuncio.getInt("id");
                int id_empresa = jsonAnuncio.getInt("id_Empresa");
                String titulo = jsonAnuncio.getString("titulo");
                String descricao = jsonAnuncio.getString("descricao");
                String perfil_procurado = jsonAnuncio.getString("perfil_procurado");
                int categoria = jsonAnuncio.getInt("categoria");

                Anuncio anuncioAPI = new Anuncio(id, id_empresa, titulo, descricao, perfil_procurado, categoria);
                listaAnuncios.add(anuncioAPI);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return listaAnuncios;
    }

    //Json de um unico anuncio - GET anuncio/id
    public static Anuncio parserjsonAnuncio(String resposta) {
        Anuncio anuncioUnico = null;
        try {
            JSONObject jsonAnuncioUnico = new JSONObject(resposta);
            int id = jsonAnuncioUnico.getInt("id");
            int id_empresa = jsonAnuncioUnico.getInt("id_Empresa");
            String titulo = jsonAnuncioUnico.getString("titulo");
            String descricao = jsonAnuncioUnico.getString("descricao");
            String perfil_procurado = jsonAnuncioUnico.getString("perfil_procurado");
            int categoria = jsonAnuncioUnico.getInt("categoria");

            anuncioUnico = new Anuncio(id, id_empresa, titulo, descricao, perfil_procurado, categoria);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return anuncioUnico;
    }

    //verificar se o dispositivo tem conexão á internet
    public static Boolean internetConnection(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }


}
