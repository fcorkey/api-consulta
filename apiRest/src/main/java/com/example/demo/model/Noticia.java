package com.example.demo.model;



public class Noticia {
    private String fecha;
    private String enlace;
    private String enlace_foto;
    private String titulo;
    private String resumen;

    // Constructor
    public Noticia() {
        this.fecha = fecha;
        this.enlace = enlace;
        this.enlace_foto = enlace_foto;
        this.titulo = titulo;
        this.resumen = resumen;
    }

    // Getters y Setters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getEnlaceFoto() {
        return enlace_foto;
    }

    public void setEnlaceFoto(String enlace_foto) {
        this.enlace_foto = enlace_foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @Override
    public String toString() {
        return "{" + "fecha=" + fecha +", enlace='" + enlace + '\'' + ", enlace_foto='" + enlace_foto + '\'' +", titulo='" + titulo + '\'' + ", resumen='" + resumen + '\'' + '}';
    }
}
