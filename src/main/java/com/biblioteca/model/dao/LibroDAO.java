package com.biblioteca.model.dao;

import com.biblioteca.dto.NuevoLibroDTO;
import com.biblioteca.dto.LibroDTO;
import com.biblioteca.model.EstadoLibro;

import java.sql.SQLException;
import java.util.List;

public interface LibroDAO {
    LibroDTO getBookById(int id) throws SQLException;
    List<LibroDTO> getAllBooks();
    void insertBook(NuevoLibroDTO book);
    void updateBook(int id, EstadoLibro estado);
    void deleteBook(int id);
}
