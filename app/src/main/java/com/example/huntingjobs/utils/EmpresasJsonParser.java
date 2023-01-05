package com.example.huntingjobs.utils;

import com.example.huntingjobs.Modelo.Anuncio;
import com.example.huntingjobs.Modelo.Empresa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EmpresasJsonParser {

    private ArrayList<Empresa> empresas;


    //json de uma lista de empresas - GET Empresas
    public static ArrayList<Empresa> parserJsonEmpresas(JSONArray resposta) {
        ArrayList<Empresa> listaEmpresas = new ArrayList<>();

        try {
            for (int i = 0; i <= resposta.length(); i++) {
                JSONObject jsonEmpresa = (JSONObject) resposta.get(i);

                int id = jsonEmpresa.getInt("id");
                int idAdmin = jsonEmpresa.getInt("idAdmin");
                String Nome = jsonEmpresa.getString("Nome");
                String descricao = jsonEmpresa.getString("descricao");
                int telefone = jsonEmpresa.getInt("contactoTelefone");
                int telemovel = jsonEmpresa.getInt("contactoTelemovel");
                String morada = jsonEmpresa.getString("morada");
                String email = jsonEmpresa.getString("email");

                Empresa empresaAPI = new Empresa(id, idAdmin, Nome, descricao, telefone, telemovel, morada, email);
                listaEmpresas.add(empresaAPI);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaEmpresas;
    }


    public static Empresa parserJsonEmpresa(String resposta) {
        Empresa empresaUnica = null;

        try {
            JSONObject jsonEmpresaUnica = new JSONObject(resposta);

            int id = jsonEmpresaUnica.getInt("id");
            int idAdmin = jsonEmpresaUnica.getInt("idAdmin");
            String Nome = jsonEmpresaUnica.getString("Nome");
            String descricao = jsonEmpresaUnica.getString("descricao");
            int telefone = jsonEmpresaUnica.getInt("contacto_telefone");
            int telemovel = jsonEmpresaUnica.getInt("contacto_telemovel");
            String morada = jsonEmpresaUnica.getString("morada");
            String email = jsonEmpresaUnica.getString("email");

            empresaUnica = new Empresa(id,idAdmin,Nome, descricao, telefone, telemovel, morada, email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return empresaUnica;
    }
}
