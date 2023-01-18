package com.example.huntingjobs.Vistas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;

public class MeuPerfilFragment extends Fragment {

    private TextView tvNome, tvMail;


    public MeuPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        SharedPreferences sharedPreference = getContext().getSharedPreferences(SingletonGestorAnuncios.DADOS_USER, Context.MODE_PRIVATE);
        String mailUser = sharedPreference.getString(SingletonGestorAnuncios.MAIL,"Sem email: Algum erro ocorreu");
        String username = sharedPreference.getString(SingletonGestorAnuncios.USERNAME,"Sem email: Algum erro ocorreu");

        View view = inflater.inflate(R.layout.fragment_meu_perfil, container, false);

        tvNome = view.findViewById(R.id.id_fullName_TextView);
        tvMail = view.findViewById(R.id.tvMailuser);

        tvNome.setText(username);
        tvMail.setText(mailUser);


        return view;
    }
}