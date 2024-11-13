package com.biblioteca.model.dao.impl;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.model.dao.DBConexion;
import com.biblioteca.model.dao.LibroDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDaoImpl implements LibroDAO {

    @Override
    public LibroDTO getBookById(int id) {
        String query =  "SELECT * FROM libros WHERE id = ?";
        LibroDTO libroDTO = null;
        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
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
        String query = "SELECT * FROM libros";
        List<LibroDTO> bookList = new ArrayList<>();

        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                bookList.add(new LibroDTO(rs.getInt("id_libro")));
            }
            System.out.println(bookList.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
