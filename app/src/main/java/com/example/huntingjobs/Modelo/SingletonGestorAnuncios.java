package com.example.huntingjobs.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huntingjobs.DBHelpers.AnuncioDBHelper;
import com.example.huntingjobs.Listeners.AnunciosListener;
import com.example.huntingjobs.Listeners.LoginListener;
import com.example.huntingjobs.Listeners.RegistoListenener;
import com.example.huntingjobs.R;
import com.example.huntingjobs.utils.AnuncioJsonParser;
import com.example.huntingjobs.utils.LoginJsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingletonGestorAnuncios {

    private final static String mUrlAnuncios = "http://10.0.2.2/HuntingJobs/backend/web/api/anuncios";
    private final static String mUrlApiLogin = "http://10.0.2.2/HuntingJobs/backend/web/api/auth/login";
    private final static String mUrlRegisto = "http://10.0.2.2/HuntingJobs/backend/web/api/auth/novo";
    public final static String ID = "id";
    public final static String MAIL = "email";
    public final static String USERNAME = "username";
    public final static String PASSWORD = "password";
    public static final String DADOS_USER = "DADOS_USER";
    private static SingletonGestorAnuncios instancia = null;
    private ArrayList<Anuncio> anunciosLista;
    private static RequestQueue volleyQueue = null;
    //   private LivrosListener livrosListener;

    private AnuncioDBHelper anunciosDB = null;
    private LoginListener loginListener;
    private RegistoListenener registoListenener;


    //Listeners
    private AnunciosListener anunciosListener;

    public SingletonGestorAnuncios(Context context) {
        anunciosLista = new ArrayList<>();
        anunciosDB = new AnuncioDBHelper(context);
    }


    public static synchronized SingletonGestorAnuncios getInstance(Context context) {
        if (instancia == null) {
            instancia = new SingletonGestorAnuncios(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instancia;
    }

    public Anuncio getAnuncio(int id) {

        for (Anuncio anuncio : anunciosLista) {
            if (anuncio.getId() == id) {
                return anuncio;
            }
        }
        return null;

    }

    public void adicionarAnuncioBD(Anuncio anuncio) {
        anunciosDB.adicionarAnunciosDB(anuncio); //ADicionar á bd //Método adicionarAnuncios -> AnuncioDBHelper
    }

    public void adicionarAnunciosDB(ArrayList<Anuncio> lista) {
        anunciosDB.removeAllAnunciosLBD();

        for (Anuncio anuncio : lista) {
            adicionarAnuncioBD(anuncio);
        }
    }

    public ArrayList<Anuncio> getAnunciosDB() {
        return anunciosLista = anunciosDB.getAllAnunciosBD();
    }

    public void setAnuncios(ArrayList<Anuncio> list) {
        this.anunciosLista = list;
    }


    public void getAllAnuncios(final Context context) {
        if (!AnuncioJsonParser.internetConnection(context)) {
            Toast.makeText(context, "Sem acesso á internet", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mUrlAnuncios,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        anunciosLista = AnuncioJsonParser.parserJsonAnuncios(response);
                        adicionarAnunciosDB(anunciosLista);


                        //ativar o listener dos anuncios
                        if (anunciosListener != null) {
                            anunciosListener.onRefreshListaAnuncios(anunciosLista);
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


    public void loginAPI(final String username, final String password, Context context) {
        if (!AnuncioJsonParser.internetConnection(context)) {
            Toast.makeText(context, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                mUrlApiLogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (!response.equals("null")) {
                            User loginUser = LoginJsonParser.parserJsonLogin(response);
                            SharedPreferences sharedPreferencesUser = context.getSharedPreferences(DADOS_USER, Context.MODE_PRIVATE);
                            SharedPreferences.Editor sharedEditor = sharedPreferencesUser.edit();
                            sharedEditor.putInt(ID, loginUser.getId());
                            sharedEditor.putString(USERNAME, loginUser.getUsername());
                            sharedEditor.putString(MAIL, loginUser.getEmail());
                            sharedEditor.putString(PASSWORD, loginUser.getPassword());
                            sharedEditor.apply();
                            //ativar o listener
                            if (loginListener != null) {
                                loginListener.onValidateLogin(username, password, context);
                            }

                            Toast.makeText(context, "Resulta", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Login Inválido", Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Erro na conexão", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        volleyQueue.add(stringRequest);
    }

    public void registoAPI(final String username, final String email, final String password, Context context) {
        if (!AnuncioJsonParser.internetConnection(context)) {
            Toast.makeText(context, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                mUrlRegisto,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.equals("null")) {
                            Toast.makeText(context, "Novo Utilizador Criado", Toast.LENGTH_SHORT).show();
                            if (registoListenener != null) {
                                registoListenener.onValidateRegisto(username, email, password, context);
                            }
                        } else {
                            Toast.makeText(context, "Registo Inválido", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Erro na conexão", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                return params;
            }

        };

        volleyQueue.add(stringRequest);


    }


    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void setRegistoListenener(RegistoListenener registoListenener) {
        this.registoListenener = registoListenener;
    }
}
