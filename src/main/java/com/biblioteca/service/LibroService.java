package com.biblioteca.service;

import com.biblioteca.dto.NuevoLibroDTO;
import com.biblioteca.model.EstadoLibro;
import com.biblioteca.model.dao.LibroDAO;
import com.biblioteca.model.dao.impl.LibroDaoImpl;
import com.biblioteca.dto.LibroDTO;

import java.util.List;

public class LibroService {

    private final LibroDAO libroDAO;

    public LibroService() {
        libroDAO = new LibroDaoImpl();
    }

    public List<LibroDTO> getAllBooks() {
        return libroDAO.getAllBooks();
    }

    public void createBook(NuevoLibroDTO libro) {
        libroDAO.insertBook(libro);
    }

    public void updateEstadoLibro(int id, EstadoLibro estado) {
        libroDAO.updateBook(id, estado);
    }
}
