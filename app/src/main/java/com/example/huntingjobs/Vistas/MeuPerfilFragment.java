package com.example.huntingjobs.Vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huntingjobs.R;

public class MeuPerfilFragment extends Fragment {

    private TextView tvNome;


    public MeuPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_meu_perfil, container, false);

        tvNome = view.findViewById(R.id.id_fullName_TextView);

        String mail = getArguments().getString(LoginActivity.MAIL);

        tvNome.setText(mail);

        return view;
    }
}