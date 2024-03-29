package com.example.huntingjobs.Vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.Modelo.SingletonGestorCandidaturas;
import com.example.huntingjobs.R;
import com.google.android.material.navigation.NavigationView;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView nV;
    private DrawerLayout dL;
    private String mail;
    private FragmentManager fragmentManager;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dL = findViewById(R.id.drawerLayout);
        nV = findViewById(R.id.navView);

        fragmentManager = getSupportFragmentManager();

        SingletonGestorCandidaturas.getInstance(getApplicationContext()).getAllCandidaturasUser(getApplicationContext());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dL, toolbar, R.string.ndOpen,R.string.ndClose);
        toggle.syncState();

        dL.addDrawerListener(toggle);

        nV.setNavigationItemSelectedListener(this);

        carregarCabecalho();

        carregarFragmentoInicial();
    }


    private boolean carregarFragmentoInicial() {
        Menu menu = nV.getMenu();
        MenuItem item = menu.getItem(1);
        item.setChecked(true);
        return onNavigationItemSelected(item);
    }

    private void carregarCabecalho() {

        SharedPreferences sharedPreference = getSharedPreferences(SingletonGestorAnuncios.DADOS_USER, Context.MODE_PRIVATE);
        mail = sharedPreference.getString(SingletonGestorAnuncios.MAIL,null);

        View hview = nV.getHeaderView(0);
        TextView tvmail = hview.findViewById(R.id.tvEmailheader);
        tvmail.setText(mail);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int opcao = item.getItemId();
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putString(LoginActivity.MAIL, mail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        switch (opcao){
            case R.id.listaAnuncio:
                fragment = new ListaAnunciosFragment();
                fragment.setArguments(bundle);
                toolbar.setTitle("Lista de Anuncios");
                break;
            case R.id.navPerfil:
                fragment = new MeuPerfilFragment();
                fragment.setArguments(bundle);
                toolbar.setTitle("Meu Perfil");
                break;
            case R.id.sobre_nos:
                fragment = new SobreNosFragment();
                toolbar.setTitle("Sobre Nós");
                break;
            case R.id.logout:
                prefs = getSharedPreferences(SingletonGestorAnuncios.DADOS_USER, MODE_PRIVATE);
                prefs.edit().clear().apply();
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
                finish();
                break;
            case R.id.listaCandidaturas:
                fragment = new ListaCandidaturasFragment();
                toolbar.setTitle("Lista de Candidaturas");
                break;

        }

        if (fragment != null){
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        }

        dL.closeDrawer(GravityCompat.START);
        return false;
    }
}