package com.example.huntingjobs.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huntingjobs.Listeners.CandidaturaListener;
import com.example.huntingjobs.Modelo.Anuncio;
import com.example.huntingjobs.Modelo.Empresa;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;

public class CandidaturaActivity extends AppCompatActivity implements CandidaturaListener {

    private TextView nomeempresa, nomeanuncio, mensagem;
    private Button btnCandidatura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidatura);

        nomeanuncio = findViewById(R.id.NomeAnuncioCandidatura);
        nomeempresa = findViewById(R.id.NomeEmpresaCandidatura);
        btnCandidatura = findViewById(R.id.btnEnviarCandidatura);

        mensagem = findViewById(R.id.tvMensagem);

        long id_empresa = getIntent().getLongExtra(DetalhesAnuncioActivity.EMPRESA_CANDIDATURA, -1);
        long id_anuncio = getIntent().getLongExtra(DetalhesAnuncioActivity.ANUNCIO_CANDIDATURA, -1);


        Empresa empresa = SingletonGestorAnuncios.getInstance(this).getEmpresa((int) id_empresa);
        Anuncio anuncio = SingletonGestorAnuncios.getInstance(this).getAnuncio((int) id_anuncio);

        SharedPreferences sharedPreference = getSharedPreferences(SingletonGestorAnuncios.DADOS_USER, Context.MODE_PRIVATE);
        int id = sharedPreference.getInt(SingletonGestorAnuncios.ID,-1);


       carregardados(anuncio.getTitulo(), empresa.getNomeEmpresa());

       btnCandidatura.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (mensagem.getText().toString() != ""){
                   SingletonGestorAnuncios.getInstance(getApplicationContext()).sendCandidatura(getApplicationContext(), mensagem.getText().toString() , anuncio.getId(), id);
               }
           }
       });

        SingletonGestorAnuncios.getInstance(this).setCandidaturaListener(this);

    }

    private void carregardados(String anuncio, String empresa) {
        nomeanuncio.setText("Anuncio: " + anuncio);
        nomeempresa.setText("Empresa: " + empresa);
    }

    @Override
    public void onValidateCandidatura(String mensagem, String id_anuncio, String id_user, Context context) {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }
}