package com.example.huntingjobs.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntingjobs.Modelo.Anuncio;
import com.example.huntingjobs.Modelo.Empresa;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;

public class DetalhesAnuncioActivity extends AppCompatActivity {

    private TextView tvTitulo, tvDescricao, tvEmpresa, tvPerfil;
    private Anuncio anuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_anuncio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //associar as variáveis aos componentes da vista
        tvTitulo = findViewById(R.id.etTituloAnuncio);
        tvDescricao = findViewById(R.id.etDescricao);
        tvEmpresa = findViewById(R.id.etEmpresaAnuncio);
        tvPerfil = findViewById(R.id.etPerfil);

        //Atribuir o ID do anuncio
        long id = getIntent().getLongExtra(ListaAnunciosFragment.ANUNCIO, -1);

        //ir buscar o anuncio com o id atribuido na variável anterior
        anuncio = SingletonGestorAnuncios.getInstance(this).getAnuncio((int) id);

        //caso o anuncio exista -> carregar os dados do anuncio nos componentes da vista.
        if (anuncio != null) {
            carregarAnuncio(anuncio);
        }
    }

    private void carregarAnuncio(Anuncio anuncio) {

        Empresa emprea = SingletonGestorAnuncios.getInstance(getApplicationContext()).getEmpresa(anuncio.getId_empresa());

        //atribuir os dados do anuncio aos componentes das views
        tvTitulo.setText(anuncio.getTitulo());
        tvDescricao.setText(Html.fromHtml(anuncio.getDescricao()));
        tvEmpresa.setText("" + emprea.getNomeEmpresa()); //TODO implementar getEmpresaByID na API e no Singleton para ter o nome da empresa neste sitio.
        tvPerfil.setText(Html.fromHtml(anuncio.getPerfil_procurado()));
    }
}