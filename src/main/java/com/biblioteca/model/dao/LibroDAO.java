package com.biblioteca.model.dao;

import com.biblioteca.dto.LibroDTO;

import java.sql.SQLException;
import java.util.List;

public interface LibroDAO {
    LibroDTO getBookById(int id) throws SQLException;
    List<LibroDTO> getAllBooks();
    void insertBook(LibroDTO book);
    void updateBook(LibroDTO book);
    void deleteBook(int id);
}
