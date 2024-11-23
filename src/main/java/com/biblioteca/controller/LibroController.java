package com.biblioteca.controller;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.service.LibroService;

import java.util.List;


public class LibroController {
    private LibroService service;

    public LibroController(){
        service = new LibroService();
    }

    public List<LibroDTO> getAllBooks() {
        return service.getAllBooks();
    }

    public void registrarLibro(LibroDTO libro){
        service.createBook(libro);
    }
}
