package com.uss.crist.refresh;

/**
 * Created by crist on 06/11/2017.
 */

public class Lista {
    private String titulo;
    private String votos;
    private int idImagen;

    public Lista(String titulo, String votos, int idImagen) {
        this.titulo = titulo;
        this.votos = votos;
        this.idImagen = idImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVotos() {
        return votos;
    }

    public void setVotos(String votos) {
        this.votos = votos;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
}
