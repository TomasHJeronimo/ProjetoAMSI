package com.example.huntingjobs.Modelo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.huntingjobs.DBHelpers.EmpresaDBHelper;
import com.example.huntingjobs.Listeners.EmpresasListener;
import com.example.huntingjobs.utils.AnuncioJsonParser;
import com.example.huntingjobs.utils.EmpresasJsonParser;

import org.json.JSONArray;

import java.util.ArrayList;

public class SingletonGestorEmpresas {

    //Links de acesso á API
    private final static String mUrlEmpresas = "http://10.0.2.2/HuntingJobs/backend/web/api/empresas";


    //Instancias
    private static SingletonGestorEmpresas instancia = null;
    private ArrayList<Empresa> empresasLista;
    private static RequestQueue volleyQueue = null;

    //DBHelpers
    private EmpresaDBHelper empresasDB = null;

    //Listeners
    private EmpresasListener empresasListener;

    public SingletonGestorEmpresas(Context context){
        empresasLista = new ArrayList<>();
        empresasDB = new EmpresaDBHelper(context);
    }

    public static synchronized SingletonGestorEmpresas getInstance(Context context) {
        if (instancia == null) {
            instancia = new SingletonGestorEmpresas(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instancia;
    }

    public Empresa getEmpresa(int id) {
        for (Empresa empresa : empresasLista) {
            if (empresa.getId() == id) {
                return empresa;
            }
        }
        return null;
    }

    public void adicionarEmpresaDB(Empresa empresa) {
        empresasDB.adicionarEmpresasBD(empresa); //ADicionar á bd //Método adicionarAnuncios -> AnuncioDBHelper
    }

    public void adicionarEmpresasBD(ArrayList<Empresa> lista) {
        empresasDB.removeAllEmpresasLBD();

        for (Empresa empresa : lista) {
            adicionarEmpresaDB(empresa);
        }
    }

    public ArrayList<Empresa> getEmpresasBD() {
        return empresasLista = empresasDB.getAllEmpresasBD(); //4 empresas
    }

    public void getAllEmpresas(final Context context) {
        if (!AnuncioJsonParser.internetConnection(context)) {
            Toast.makeText(context, "Sem acesso á internet", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mUrlEmpresas,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        empresasLista = EmpresasJsonParser.parserJsonEmpresas(response);
                        adicionarEmpresasBD(empresasLista);

                        //ativar o listener das empresas
                        if (empresasListener != null){
                            empresasListener.onRefreshListaEmpresas(empresasLista);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        volleyQueue.add(jsonArrayRequest);
    }

    public void setEmpresasListener(EmpresasListener empresasListener) {
        this.empresasListener = empresasListener;
    }

}
