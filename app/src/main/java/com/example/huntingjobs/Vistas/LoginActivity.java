package com.example.huntingjobs.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huntingjobs.Listeners.LoginListener;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;
import com.example.huntingjobs.utils.LoginJsonParser;

public class LoginActivity extends AppCompatActivity implements LoginListener {


    public static final String MAIL = "amsi.dei.estg.ipleiria.books.mail";
    private static final String USERNAME = "username";
    private EditText etPass;
    private EditText etUsername;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsernameInsert);
        etPass = findViewById(R.id.etPassword);

        etUsername.setText("Monteiro");
        // etPass.setText("Password");

        //Listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarLogin();
            }
        });


        SingletonGestorAnuncios.getInstance(this).setLoginListener(this);
    }


    private void validarLogin() {
        //Dados dos campos Login
        String username = etUsername.getText().toString();
        String pass = etPass.getText().toString();

        if (!isUsernameValido(username)){
            etUsername.setError(getString(R.string.erro));
            return;
        }

        if (!isPassValida(pass)){
            etPass.setError(getString(R.string.erroPass));
        }

        SingletonGestorAnuncios.getInstance(this).loginAPI(username,pass,this);
    }

    private boolean isPassValida(String pass) {
        if (pass == null)
            return false;

        return pass.length() >= 4;

    }

    private boolean isUsernameValido(String username) {

        //Se a caixa de texto estiver nula ou vazia.
        if (username == null || username.isEmpty()){
            return false;
        }


        //Padrões para testar o e-mail inserido


        //Devolve
        return true;
    }

    @Override
    public void onValidateLogin(String username, String password, Context context) {
        if (isUsernameValido(username) && isPassValida(password)){
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.putExtra(USERNAME, username);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(context, "Login Inválido, Tente novamente", Toast.LENGTH_SHORT).show();
        }
    }
}