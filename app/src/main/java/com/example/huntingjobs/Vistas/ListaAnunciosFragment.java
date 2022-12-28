package com.example.huntingjobs.Vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.huntingjobs.Adaptadores.ListaAnunciosAdapter;
import com.example.huntingjobs.Modelo.Anuncio;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;

import java.util.ArrayList;


public class ListaAnunciosFragment extends Fragment {

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

        listaAnuncios = view.findViewById(R.id.lvAnuncios);
        ArrayList<Anuncio> anuncios = SingletonGestorAnuncios.getInstance(getContext()).getAnunciosDB();
        adaptador = new ListaAnunciosAdapter(getContext(), SingletonGestorAnuncios.getInstance(getContext()).getAnunciosDB());
        listaAnuncios.setAdapter(adaptador);

        return view;
    }
}