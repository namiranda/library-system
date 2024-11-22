package com.biblioteca.model.dao;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.model.entity.Libro;

import java.sql.SQLException;
import java.util.List;

public interface LibroDAO {
    Libro getBookById(int id) throws SQLException;
    List<Libro> getAllBooks();
    void insertBook(LibroDTO book);
    void updateBook(LibroDTO book);
    void deleteBook(int id);
}
