package com.example.huntingjobs.Adaptadores;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.huntingjobs.Modelo.Anuncio;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;

import java.util.ArrayList;

public class ListaAnunciosAdapter extends BaseAdapter {


    private ArrayList<Anuncio> listaAnuncios;
    private Context contexto;
    private LayoutInflater layInflater;


    public ListaAnunciosAdapter(Context context, ArrayList<Anuncio> anuncios) {
        this.contexto = context;
        this.listaAnuncios = anuncios;
    }

    @Override
    public int getCount() {
        return listaAnuncios.size();
    }

    @Override
    public Object getItem(int i) {
        return listaAnuncios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaAnuncios.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layInflater == null){
            layInflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null){
            view = layInflater.inflate(R.layout.item_lista_anuncio,null);
        }

        //preenchimento do view
        ViewHolderLista viewHL = (ViewHolderLista) view.getTag();
        if (viewHL == null){
            viewHL = new ViewHolderLista(view);
            view.setTag(viewHL);
        }

        viewHL.update(listaAnuncios.get(i));

        return view;
    }

    private class ViewHolderLista{
        private TextView tvTituloLista,tvDescricaoLista, tvNomeEmpresaLista;

        public ViewHolderLista(View view){
            tvTituloLista = view.findViewById(R.id.tvTituloAnuncio);
            tvDescricaoLista = view.findViewById(R.id.tvDesricaoAnuncio);
        }

        public void update(Anuncio anuncio){
            tvTituloLista.setText(anuncio.getTitulo());
            tvDescricaoLista.setText(Html.fromHtml(anuncio.getDescricao()));
        }

    }

}
