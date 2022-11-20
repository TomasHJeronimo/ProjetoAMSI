package com.example.huntingjobs.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huntingjobs.R;

public class LoginActivity extends AppCompatActivity {


    public static final String MAIL = "amsi.dei.estg.ipleiria.books.mail";
    private EditText etPass;
    private EditText etEmail;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etMailInsert);
        etPass = findViewById(R.id.etPassword);

        etEmail.setText("pedromonteiro@outlook.pt");
        etPass.setText("12345");

        //Listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarLogin();
            }
        });


    }

    private void validarLogin() {
        //Dados dos campos Login
        String mail = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if (!isMailValido(mail)){
            etEmail.setError(getString(R.string.erro));
            return;
        }

        if (!isPassValida(pass)){
            etPass.setError(getString(R.string.erroPass));
        }

        Intent intentMail = new Intent(this, HomePageActivity.class);
        intentMail.putExtra(MAIL, mail);
        startActivity(intentMail);
    }

    private boolean isPassValida(String pass) {
        if (pass == null)
            return false;

        return pass.length() >= 4;

    }

    private boolean isMailValido(String mail) {

        //Se a caixa de texto estiver nula ou vazia.
        if (mail == null || mail.isEmpty()){
            return false;
        }


        //Padrões para testar o e-mail inserido


        //Devolve
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }


}