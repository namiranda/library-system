package com.biblioteca.service;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.model.dao.LibroDAO;
import com.biblioteca.model.dao.impl.LibroDaoImpl;

import java.util.List;

public class LibroService {

    private LibroDAO libroDAO;

    public LibroService() {
        libroDAO = new LibroDaoImpl();
    }

    public List<LibroDTO> getAllBooks() {
        return libroDAO.getAllBooks();
    }

    public void createBook(LibroDTO libro) {
        libroDAO.insertBook(libro);
    }
}
