package com.biblioteca.controller;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.dto.NuevoLibroDTO;
import com.biblioteca.model.EstadoLibro;
import com.biblioteca.service.LibroService;

import java.util.List;


public class LibroController {
    private final LibroService service;

    public LibroController(){
        service = new LibroService();
    }

    public List<LibroDTO> getAllBooks() {
        return service.getAllBooks();
    }

    public void registrarLibro(NuevoLibroDTO libro){
        service.createBook(libro);
    }

    public void updateEstadoLibro(int id, EstadoLibro estado){
        service.updateEstadoLibro(id, estado);
    }
}
