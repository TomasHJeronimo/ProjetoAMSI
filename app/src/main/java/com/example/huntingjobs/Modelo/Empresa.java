package com.example.huntingjobs.Modelo;

import java.io.Serializable;

public class Empresa implements Serializable {

    //Campos necessários para preencher um anuncio
    private int id;
    private int idAdmin;
    private String nomeEmpresa;
    private String descricao;
    private int contacto_telefone;
    private int contacto_telemovel;
    private String morada;
    private String email;

    //Construtor de uma empresa
    public Empresa(int id, int idAdmin, String nome, String descricao, int telefone, int telemovel, String morada, String email) {
        this.setId(id);
        this.setIdAdmin(idAdmin);
        this.setNomeEmpresa(nome);
        this.setDescricao(descricao);
        this.setContacto_telefone(telefone);
        this.setContacto_telemovel(telemovel);
        this.setMorada(morada);
        this.setEmail(email);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getContacto_telefone() {
        return contacto_telefone;
    }

    public void setContacto_telefone(int contacto_telefone) {
        this.contacto_telefone = contacto_telefone;
    }

    public int getContacto_telemovel() {
        return contacto_telemovel;
    }

    public void setContacto_telemovel(int contacto_telemovel) {
        this.contacto_telemovel = contacto_telemovel;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empresa" + this.nomeEmpresa;
    }
}
