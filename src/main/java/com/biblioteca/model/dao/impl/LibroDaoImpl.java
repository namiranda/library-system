package com.biblioteca.model.dao.impl;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.model.dao.DBConexion;
import com.biblioteca.model.dao.LibroDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class LibroDaoImpl implements LibroDAO {

    public static final String QUERY = "SELECT * FROM libro WHERE id = ?";

    @Override
    public LibroDTO getBookById(int id) {
        LibroDTO libroDTO = null;
        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                libroDTO = new LibroDTO(
                        rs.getInt("id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libroDTO;
    }

    @Override
    public List<LibroDTO> getAllBooks() {
        return Collections.emptyList();
    }

    @Override
    public void insertBook(LibroDTO book) {

    }

    @Override
    public void updateBook(LibroDTO book) {

    }

    @Override
    public void deleteBook(int id) {

    }
}
