package com.example.huntingjobs.Vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.huntingjobs.Adaptadores.ListaAnunciosAdapter;
import com.example.huntingjobs.Adaptadores.ListaCandidaturasAdapter;
import com.example.huntingjobs.Modelo.Candidatura;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.Modelo.SingletonGestorCandidaturas;
import com.example.huntingjobs.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaCandidaturasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCandidaturasFragment extends Fragment {

    public static final String CANDIDATURA = "candidatura";
    private ListView listaCandidaturas;
    private ListaCandidaturasAdapter adaptador;

    public ListaCandidaturasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        SingletonGestorAnuncios.getInstance(getContext()).getAllAnuncios(getContext());
        SingletonGestorCandidaturas.getInstance(getContext()).getAllCandidaturasUser(getContext());

        View view = inflater.inflate(R.layout.fragment_lista_candidaturas, container, false);

        listaCandidaturas = view.findViewById(R.id.lv_Candidaturas);
        ArrayList<Candidatura> candidaturas = SingletonGestorCandidaturas.getInstance(getContext()).getCandidaturasDB();
        adaptador = new ListaCandidaturasAdapter(getContext(), SingletonGestorCandidaturas.getInstance(getContext()).getCandidaturasDB());
        listaCandidaturas.setAdapter(adaptador);

        listaCandidaturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "" + SingletonGestorCandidaturas.getInstance(getContext()).getCandidatura((int) l).getId(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}