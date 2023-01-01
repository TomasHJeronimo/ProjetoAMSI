package com.example.huntingjobs.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.example.huntingjobs.Modelo.User;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginJsonParser {

    public static User parserJsonLogin(String resposta) {


        User userLogin = null;

        try {
            JSONObject jsonLogin = new JSONObject(resposta);
                int id = jsonLogin.getInt("id");
                String username = jsonLogin.getString("username");
                String password = jsonLogin.getString("password_hash");
                String email = jsonLogin.getString("email");
                userLogin = new User(id, username, password, email);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userLogin;
    }


}
