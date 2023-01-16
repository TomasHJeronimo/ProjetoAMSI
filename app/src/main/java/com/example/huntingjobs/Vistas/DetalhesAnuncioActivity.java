package com.example.huntingjobs.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huntingjobs.Modelo.Anuncio;
import com.example.huntingjobs.Modelo.Empresa;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;

public class DetalhesAnuncioActivity extends AppCompatActivity {

    public static final String EMPRESA = "empresa";
    public static final String EMPRESA_CANDIDATURA = "empresa_candidatura";
    public static final String ANUNCIO_CANDIDATURA = "anuncio_candidatura";
    private TextView tvTitulo, tvDescricao, tvEmpresa, tvPerfil;
    private Anuncio anuncio;
    private Button search,candidatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_anuncio);




        //associar as variáveis aos componentes da vista
        tvTitulo = findViewById(R.id.etTituloAnuncio);
        tvDescricao = findViewById(R.id.etDescricao);
        tvEmpresa = findViewById(R.id.etEmpresaAnuncio);
        tvPerfil = findViewById(R.id.etPerfil);
        search = findViewById(R.id.btnSearchEmpresa);
        candidatura = findViewById(R.id.btnCandidatura);

        //Atribuir o ID do anuncio
        long id = getIntent().getLongExtra(ListaAnunciosFragment.ANUNCIO, -1);

        //ir buscar o anuncio com o id atribuido na variável anterior
        anuncio = SingletonGestorAnuncios.getInstance(this).getAnuncio((int) id);

        setTitle("" + anuncio.getTitulo());

        //Com o anuncio atribuido , ir buscar a empresa atraves do campo id_empresa
        Empresa empresa = SingletonGestorAnuncios.getInstance(getApplicationContext()).getEmpresa(anuncio.getId_empresa());

        //caso o anuncio exista -> carregar os dados do anuncio nos componentes da vista.
        if (anuncio != null) {
            carregarAnuncio(anuncio);
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "" + SingletonGestorAnuncios.getInstance(getApplicationContext()).getEmpresa(empresa.getId()).getId(), Toast.LENGTH_SHORT).show();
                long id = SingletonGestorAnuncios.getInstance(getApplicationContext()).getEmpresa(empresa.getId()).getId();

                Intent anuncio =new Intent(getApplicationContext(),DetalhesEmpresaActivity.class);
                anuncio.putExtra(EMPRESA, id);
                startActivity(anuncio);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_down_up);
            }
        });


        candidatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long id_empresa = SingletonGestorAnuncios.getInstance(getApplicationContext()).getEmpresa(empresa.getId()).getId();
                long id_anuncio = anuncio.getId();

                Toast.makeText(DetalhesAnuncioActivity.this, "" + anuncio.getId(), Toast.LENGTH_SHORT).show();

                Intent candidatura = new Intent(getApplicationContext(), CandidaturaActivity.class);
                candidatura.putExtra(EMPRESA_CANDIDATURA, id_empresa);
                candidatura.putExtra(ANUNCIO_CANDIDATURA, id_anuncio);
                startActivity(candidatura);
                overridePendingTransition(R.anim.slide_left, R.anim.slide_out_right);
                finish();
            }
        });
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