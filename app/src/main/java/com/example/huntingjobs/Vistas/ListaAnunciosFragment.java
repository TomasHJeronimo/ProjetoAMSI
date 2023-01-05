package com.example.huntingjobs.Vistas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.huntingjobs.Adaptadores.ListaAnunciosAdapter;
import com.example.huntingjobs.Modelo.Anuncio;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;

import java.util.ArrayList;


public class ListaAnunciosFragment extends Fragment {

    public static final String ANUNCIO = "anuncio";
    private ListView listaAnuncios;
    private ListaAnunciosAdapter adaptador;


    public ListaAnunciosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_lista_anuncios, container, false);
        setHasOptionsMenu(true);

        SingletonGestorAnuncios.getInstance(getContext()).getAllAnuncios(getContext());
        SingletonGestorAnuncios.getInstance(getContext()).getAllEmpresas(getContext());


        listaAnuncios = view.findViewById(R.id.lvAnuncios);
        ArrayList<Anuncio> anuncios = SingletonGestorAnuncios.getInstance(getContext()).getAnunciosDB();
        adaptador = new ListaAnunciosAdapter(getContext(), SingletonGestorAnuncios.getInstance(getContext()).getAnunciosDB());
        listaAnuncios.setAdapter(adaptador);


        listaAnuncios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getContext(), "" + SingletonGestorAnuncios.getInstance(getContext()).getAnuncio((int) l).getId(), Toast.LENGTH_SHORT).show();
                Intent anuncio =new Intent(getContext(),DetalhesAnuncioActivity.class);
                anuncio.putExtra(ANUNCIO, l);
                startActivity(anuncio);
            }
        });

        return view;
    }
}