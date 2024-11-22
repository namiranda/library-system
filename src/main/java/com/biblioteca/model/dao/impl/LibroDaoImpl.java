package com.biblioteca.model.dao.impl;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.model.dao.DBConexion;
import com.biblioteca.model.dao.LibroDAO;
import com.biblioteca.model.entity.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDaoImpl implements LibroDAO {

    @Override
    public Libro getBookById(int id) {
        String query =  "SELECT * FROM libros WHERE id_libro = ?";
        Libro libro = null;
        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                libro = new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getString("año"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public List<Libro> getAllBooks() {
        String query = "SELECT * FROM libros";
        List<Libro> bookList = new ArrayList<>();

        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                bookList.add(new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getString("año"),
                        rs.getString("estado")
                ));
            }
            System.out.println(bookList.toString()); //TODO: borrar

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertBook(LibroDTO book) {
        String query = "INSERT INTO libros (titulo, autor, genero, año, estado) values (?,?,?,?,?)";

        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1 , book.getTitulo());
            statement.setString(2, book.getAutor());
            statement.setString(3, book.getGenero());
            statement.setString(4, book.getAnio());
            statement.setString(5, book.getEstado());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateBook(LibroDTO book) {

    }

    @Override
    public void deleteBook(int id) {
        String query = "DELETE FROM libros where id = ?";
    }
}
