package com.example.huntingjobs.Modelo;

import java.io.Serializable;

public class Candidatura implements Serializable {

    private int id;
    private int id_user;
    private int id_anuncio;
    private String mensagem;
    private String data;

    public Candidatura(int id, int id_user, int id_anuncio, String mensagem, String data){
        this.setId(id);
        this.setId_user(id_user);
        this.setId_anuncio(id_anuncio);
        this.setMensagem(mensagem);
        this.setData(data);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_anuncio() {
        return id_anuncio;
    }

    public void setId_anuncio(int id_anuncio) {
        this.id_anuncio = id_anuncio;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toString() {
        return this.data + " : " + this.id;
    }

}
