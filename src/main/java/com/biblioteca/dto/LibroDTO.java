package com.biblioteca.dto;

public class LibroDTO {
    private int id;

    public LibroDTO(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LibroDTO{" +
                "id=" + id +
                '}';
    }
}
