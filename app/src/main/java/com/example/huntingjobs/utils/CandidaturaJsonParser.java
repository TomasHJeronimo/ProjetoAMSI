package com.example.huntingjobs.utils;

import com.example.huntingjobs.Modelo.Candidatura;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CandidaturaJsonParser {

    private ArrayList<Candidatura> candidaturas;


    //json de uma lista de Candidaturas - GET candidaturas

    public static ArrayList<Candidatura> parserJsonCandidaturas(JSONArray resposta){
        ArrayList<Candidatura> listaCandidaturas =  new ArrayList<>();

        try{
            for (int i = 0; i<=resposta.length(); i++){
                JSONObject jsonCandidatura = (JSONObject) resposta.get(i);
                int id = jsonCandidatura.getInt("id");
                int id_user = jsonCandidatura.getInt("id_user");
                int id_anuncio = jsonCandidatura.getInt("id_anuncio");
                String mensagem = jsonCandidatura.getString("mensagem");
                String data = jsonCandidatura.getString("data_candidatura");

                Candidatura candidaturaAPI =  new Candidatura(id,id_user,id_anuncio,mensagem,data);
                listaCandidaturas.add(candidaturaAPI);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return listaCandidaturas;
    }

    public static Candidatura parserJsonCandidatura(String resposta){
        Candidatura candidaturaUnica = null;

        try{
            JSONObject jsonCategoriaUnica = new JSONObject(resposta);
            int id = jsonCategoriaUnica.getInt("id");
            int id_user = jsonCategoriaUnica.getInt("id_user");
            int id_anuncio = jsonCategoriaUnica.getInt("id_anuncio");
            String mensagem = jsonCategoriaUnica.getString("mensagem");
            String data = jsonCategoriaUnica.getString("data_candidatura");

            Candidatura candidaturaUnicaAPI =  new Candidatura(id,id_user,id_anuncio,mensagem,data);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return candidaturaUnica;
    }


}
