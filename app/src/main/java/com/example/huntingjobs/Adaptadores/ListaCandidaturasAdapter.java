package com.example.huntingjobs.Adaptadores;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huntingjobs.Modelo.Anuncio;
import com.example.huntingjobs.Modelo.Candidatura;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;
import com.example.huntingjobs.Vistas.ListaCandidaturasFragment;

import java.util.ArrayList;

public class ListaCandidaturasAdapter extends BaseAdapter {

    private ArrayList<Candidatura> listaCandidaturas;
    private Context context;
    private LayoutInflater layoutInflater;

    public ListaCandidaturasAdapter(Context context, ArrayList<Candidatura> candidaturas){
        this.context = context;
        this.listaCandidaturas = candidaturas;
    }


    @Override
    public int getCount() {
        return listaCandidaturas.size();
    }

    @Override
    public Object getItem(int i) {
        return listaCandidaturas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaCandidaturas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null){
            view = layoutInflater.inflate(R.layout.item_lista_candidatura,null);
        }

        //preenchimento do view
        ListaCandidaturasAdapter.ViewHolderLista viewHL = (ListaCandidaturasAdapter.ViewHolderLista) view.getTag();
        if (viewHL == null){
            viewHL = new ViewHolderLista(view);
            view.setTag(viewHL);
        }

        viewHL.update(listaCandidaturas.get(i));

        return view;
    }


    private class ViewHolderLista{
        private TextView tvNomeAnuncio,tvMensagem;

        public ViewHolderLista(View view){
            tvNomeAnuncio = view.findViewById(R.id.tvAnuncio);
            tvMensagem = view.findViewById(R.id.tvMensagem);
        }

        public void update(Candidatura candidatura){

            int id_anuncio = candidatura.getId_anuncio();
            String titulo = SingletonGestorAnuncios.getInstance(context).getAnuncio(id_anuncio).getTitulo();

            tvNomeAnuncio.setText(titulo);
            tvMensagem.setText(candidatura.getMensagem());
        }

    }

}
