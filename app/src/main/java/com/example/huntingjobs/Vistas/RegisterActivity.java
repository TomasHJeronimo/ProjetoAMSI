package com.example.huntingjobs.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huntingjobs.Listeners.RegistoListenener;
import com.example.huntingjobs.Modelo.SingletonGestorAnuncios;
import com.example.huntingjobs.R;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity implements RegistoListenener {

    Button btnRegisto;
    EditText username, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegisto = findViewById(R.id.btnRegisto);
        username = findViewById(R.id.etUsernameInsertRegisto);
        email = findViewById(R.id.etEmailRegisto);
        password = findViewById(R.id.etPasswordRegisto);

        username.setText("MonteiroAPI");
        email.setText("Monteiro@api.teste");
        password.setText("passwordteste");

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (username.getText().toString().equals("Inserir Username")){
                    username.setText("");
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (email.getText().toString().equals("Inserir Email")){
                    email.setText("");
                }
            }
        });

        btnRegisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarRegisto();
            }
        });


        SingletonGestorAnuncios.getInstance(this).setRegistoListenener(this);
    }

    private void validarRegisto() {

        String textUsername = username.getText().toString();
        String textEmail = email.getText().toString();
        String textPassword = password.getText().toString();

        if (textUsername.isEmpty() || textUsername == null) {
            username.setError(getString(R.string.usernameinvalido));
            return;
        }

        if (!isEmailValido(textEmail)) {
            email.setError(getString(R.string.emailinvalido));
            return;
        }

        if (!isPassValida(textPassword)) {
            password.setError(getString(R.string.erroPass));
            return;
        }

        SingletonGestorAnuncios.getInstance(getApplicationContext()).registoAPI(textUsername,textEmail,textPassword,getApplicationContext());
    }

    private boolean isPassValida(String password) {
        if (password == null)
            return false;

        return password.length() >= 4;
    }

    private boolean isEmailValido(String mail) {
        if (mail == null || mail.isEmpty()) {
            return false;
        }

        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    @Override
    public void onValidateRegisto(String username, String email, String password, Context context) {
        if (username != null || email != null || password != null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(context, "Dados Inválidos", Toast.LENGTH_SHORT).show();
        }
    }
}