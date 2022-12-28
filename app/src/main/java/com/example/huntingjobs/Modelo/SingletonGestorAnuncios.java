package com.example.huntingjobs.Modelo;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.huntingjobs.DBHelpers.AnuncioDBHelper;

import java.sql.Array;
import java.util.ArrayList;

public class SingletonGestorAnuncios {

    private final static String mUrlAnuncios = "http://localhost/HuntingJobs/backend/web/api/anuncios";
    //  private final static String mUrlApiLogin = "http://amsi.dei.estg.ipleiria.pt/api/auth/login";
    private static SingletonGestorAnuncios instancia = null;
    private ArrayList<Anuncio> anunciosLista;
    private static RequestQueue volleyQueue = null;
    //   private LivrosListener livrosListener;

    private AnuncioDBHelper anunciosDB = null;

    public SingletonGestorAnuncios(Context context) {
        anunciosLista = new ArrayList<>();
        anunciosDB = new AnuncioDBHelper(context);

        gerarDadosDinamicos();
    }


    public static synchronized SingletonGestorAnuncios getInstance(Context context) {
        if (instancia == null) {
            instancia = new SingletonGestorAnuncios(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instancia;
    }

    public Anuncio getAnuncio(int id) {

        for (Anuncio anuncio : anunciosLista) {
            if (anuncio.getId() == id) {
                return anuncio;
            }
        }
        return null;

    }

    public void adicionarAnuncioBD(Anuncio anuncio) {
        anunciosDB.adicionarAnunciosDB(anuncio); //ADicionar á bd //Método adicionarAnuncios -> AnuncioDBHelper
    }

    public void adicionarAnunciosDB(ArrayList<Anuncio> lista) {
        anunciosDB.removeAllAnunciosLBD();

        for (Anuncio anuncio : lista) {
            adicionarAnuncioBD(anuncio);
        }
    }

    public ArrayList<Anuncio> getAnunciosDB() {
        return anunciosLista = anunciosDB.getAllAnunciosBD();
    }

    public void setAnuncios(ArrayList<Anuncio> list) {
        this.anunciosLista = list;
    }


    //Testes com dados dinamicos
    private void gerarDadosDinamicos() {
        Anuncio novoAnuncio = new Anuncio(1, 1, "Programador C#", "Programação de aplicações adapatadas aos gostos dos cliente",
                "2 anos de experiencia, Conhecimentos em C#", 1);

        Anuncio novoAnuncio2 = new Anuncio(2, 2, "Programador WEB", "Web Developing adaptado aos gostos do utilizador",
                "2 anos de experiencia, Conhecimentos em HTML, PHP, JAVASCRIPT", 2);

        Anuncio novoAnuncio3 = new Anuncio(3, 2, "Programador UNITY", "Programação de Jogos 3D para crianças ",
                "2 anos de experiencia em Unity, Conhecimentos em C# adapatado ao unity", 2);

        Anuncio novoAnuncio4 = new Anuncio(4, 2, "Programador Java", "Programação de aplicações Android ",
                "2 anos de experiencia, Conhecimentos em Java", 2);

        Anuncio novoAnuncio5 = new Anuncio(5, 2, "Programador Java", "Programação de aplicações Android ",
                "2 anos de experiencia, Conhecimentos em Java", 2);

        Anuncio novoAnuncio6 = new Anuncio(6, 2, "Programador Java", "Programação de aplicações Android ",
                "2 anos de experiencia, Conhecimentos em Java", 2);


        anunciosLista.add(novoAnuncio);
        anunciosLista.add(novoAnuncio2);
        anunciosLista.add(novoAnuncio3);
        anunciosLista.add(novoAnuncio4);
        anunciosLista.add(novoAnuncio5);
        anunciosLista.add(novoAnuncio6);

        adicionarAnunciosDB(anunciosLista);
    }


}
