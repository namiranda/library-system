package com.biblioteca.dto;

public class LibroDTO {
    private final int id;
    private final String titulo;
    private final String autor;
    private final String genero;
    private final String anio;
    private String estado;

    public LibroDTO(int id, String titulo, String autor, String genero, String anio, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anio = anio;
        this.estado = estado;
    }

    public int getId() {
        return id;
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
}
