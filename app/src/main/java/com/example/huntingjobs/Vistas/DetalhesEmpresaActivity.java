package com.example.huntingjobs.Vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huntingjobs.Modelo.Empresa;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetalhesEmpresaActivity extends AppCompatActivity {

    private TextView nomeEmpresa, emailEmpresa;
    private EditText descricaoEmpresa, contactoEmpresa;
    private FloatingActionButton btnFavorito;
    private Empresa empresa;
    private int fabestado = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_empresa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        nomeEmpresa = findViewById(R.id.tvNomeEmpresaDetalhes);
        emailEmpresa = findViewById(R.id.tvEmailEmpresaDetalhes);
        descricaoEmpresa = findViewById(R.id.etDescricaoEmpresaDetalhes);
        contactoEmpresa = findViewById(R.id.etContactoEmpresaDetalhes);
        btnFavorito = findViewById(R.id.fab);

        //Atribuir o ID do anuncio
        long id = getIntent().getLongExtra(DetalhesAnuncioActivity.EMPRESA, -1);
        empresa = SingletonGestorAnuncios.getInstance(getApplicationContext()).getEmpresa((int)id);

        setTitle(empresa.getNomeEmpresa());

        nomeEmpresa.setText(empresa.getNomeEmpresa());
        emailEmpresa.setText(empresa.getEmail());
        descricaoEmpresa.setText(Html.fromHtml(empresa.getDescricao()));
        contactoEmpresa.setText(
                "Morada: " + empresa.getMorada() + "\n"
                + "Telefone: " + empresa.getContacto_telefone() + "\n"
                + "Telemovel: " + empresa.getContacto_telemovel()
        );

        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabestado == 0){
                    btnFavorito.setImageResource(R.drawable.ic_star_empty);
                    fabestado = 1;
                }else{
                    btnFavorito.setImageResource(R.drawable.ic_star);
                    fabestado = 0;
                }
            }
        });

    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left, R.anim.slide_out_right);
    }
}