package com.example.huntingjobs.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huntingjobs.DBHelpers.AnuncioDBHelper;
import com.example.huntingjobs.DBHelpers.CandidaturaDBHelper;
import com.example.huntingjobs.Listeners.CandidaturaListener;
import com.example.huntingjobs.Listeners.CandidaturasListener;
import com.example.huntingjobs.utils.AnuncioJsonParser;
import com.example.huntingjobs.utils.CandidaturaJsonParser;

import org.json.JSONArray;

import java.util.ArrayList;

public class SingletonGestorCandidaturas {


    //URL API
    private final static String mUrlCandidaturasUser = "http://10.0.2.2/HuntingJobs/backend/web/api/candidaturas/user/";
    private final static String mUrlCandidaturas = "http://10.0.2.2/HuntingJobs/backend/web/api/candidaturas/";

    //Instancia
    private static SingletonGestorCandidaturas instancia = null;

    //Array Lists
    private ArrayList<Candidatura> candidaturasLista;

    //Database Helper
    private CandidaturaDBHelper candidaturasDBHelper = null;

    //Volley Queue
    private static RequestQueue volleyQueue = null;

    //Listeners
    private CandidaturasListener candidaturasListener;
    private CandidaturaListener candidaturaListener;


    public SingletonGestorCandidaturas (Context context){
        candidaturasLista = new ArrayList<>();
        candidaturasDBHelper = new CandidaturaDBHelper(context);
    }

    public static synchronized SingletonGestorCandidaturas getInstance(Context context){
        if (instancia == null) {
            instancia = new SingletonGestorCandidaturas(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instancia;
    }


    public Candidatura getCandidatura(int id) {

        for (Candidatura candidatura : candidaturasLista) {
            if (candidatura.getId() == id) {
                return candidatura;
            }
        }
        return null;
    }

    public void adicionarCandidaturaBD(Candidatura candidatura) {
        candidaturasDBHelper.adicionarCandidaturasDB(candidatura); //ADicionar á bd //Método adicionarAnuncios -> AnuncioDBHelper
    }

    public void adicionarCandidaturasDB(ArrayList<Candidatura> candidaturasLista) {
        candidaturasDBHelper.removeAllCandidaturasLBD();
        for (Candidatura candidatura : candidaturasLista) {
            adicionarCandidaturaBD(candidatura);
        }
    }

    public ArrayList<Candidatura> getCandidaturasDB() {
        return candidaturasLista = candidaturasDBHelper.getAllCandidaturasBD();  //4
    }


    public void getAllCandidaturasUser(final Context context) {
        if (!AnuncioJsonParser.internetConnection(context)) {
            Toast.makeText(context, "Sem acesso á internet", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreference = context.getSharedPreferences(SingletonGestorAnuncios.DADOS_USER, Context.MODE_PRIVATE);
        int id = sharedPreference.getInt(SingletonGestorAnuncios.ID,-1);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mUrlCandidaturasUser + id,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        candidaturasLista = CandidaturaJsonParser.parserJsonCandidaturas(response); //6
                        adicionarCandidaturasDB(candidaturasLista);  //7


                        //ativar o listener dos anuncios
                        if (candidaturasListener != null) {
                            candidaturasListener.onRefreshListaCandidaturas(candidaturasLista); //8
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        volleyQueue.add(jsonArrayRequest);
    }

    public void removerCandidaturaBD(long id) {

        Candidatura candidatura = getCandidatura((int)id);
        if (candidatura != null) {
            candidaturasDBHelper.removerCandidatura(candidatura.getId());
            /*
            if (livrosBD.removerLivro(livro.getId())) {
                livros.remove(livro);
            }*/
        }
    }

    public void removerCandidaturaAPI(final Context context, final Candidatura candidatura){
        if (!AnuncioJsonParser.internetConnection(context)) {
            Toast.makeText(context, "Sem acesso á internet", Toast.LENGTH_SHORT).show();
            return;
        }


        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE,
                mUrlCandidaturas + candidatura.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        removerCandidaturaBD(candidatura.getId());
                        Toast.makeText(context, "Candidatura Removida com sucesso", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        volleyQueue.add(stringRequest);
    }

}
