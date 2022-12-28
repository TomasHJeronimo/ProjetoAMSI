package com.example.huntingjobs.Modelo;

import java.io.Serializable;

public class Anuncio implements Serializable {


    //Campos necessários para preencher um anuncio
    private int id;
    private int id_empresa;
    private String titulo;
    private String descricao;
    private String perfil_procurado;
    private int categoria;

    //Construtor de um anuncio
    public Anuncio(int id, int id_empresa, String titulo, String descricao, String perfil_procurado, int categoria) {
        this.setId(id);
        this.setId_empresa(id_empresa);
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setPerfil_procurado(perfil_procurado);
        this.setCategoria(categoria);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPerfil_procurado() {
        return perfil_procurado;
    }

    public void setPerfil_procurado(String perfil_procurado) {
        this.perfil_procurado = perfil_procurado;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    //Para testes no Toast.
    public String toString() {
        return this.titulo + " : " + this.id;
    }
}
