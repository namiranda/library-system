package com.biblioteca.dto;

public class NuevoLibroDTO {
    private String titulo;
    private String autor;
    private String genero;
    private String anio;
    private String estado;

    public NuevoLibroDTO(String titulo, String autor, String genero, String anio, String estado) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anio = anio;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getAnio() {
        return anio;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "LibroDTO{" +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", anio='" + anio + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }


}
