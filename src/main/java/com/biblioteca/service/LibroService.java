package com.biblioteca.service;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.model.dao.LibroDAO;
import com.biblioteca.model.dao.impl.LibroDaoImpl;
import com.biblioteca.model.entity.Libro;

import java.util.Collections;
import java.util.List;

public class LibroService {

    private LibroDAO libroDAO;

    public LibroService() {
        libroDAO = new LibroDaoImpl();
    }

    public List<LibroDTO> getAllBooks() {
        List<Libro> libros = libroDAO.getAllBooks();
        if (libros == null) {
            return Collections.emptyList();
        }
        return libros.stream()
                .map(Libro::toDTO)
                .toList();
    }

    public void createBook(LibroDTO libro) {
        libroDAO.insertBook(libro);
    }
}
