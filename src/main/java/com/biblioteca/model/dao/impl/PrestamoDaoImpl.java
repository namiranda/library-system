package com.biblioteca.model.dao.impl;

import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.model.dao.DBConexion;
import com.biblioteca.model.dao.PrestamoDAO;
import com.biblioteca.model.entity.Prestamo;

import java.sql.*;
import java.util.List;

public class PrestamoDaoImpl implements PrestamoDAO {
    @Override
    public void insertPrestamo(PrestamoDTO prestamo) {
        String query = "INSERT INTO prestamos (id_libro, estudiante, fecha_prestamo) values (?,?,?)";

        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, prestamo.getIdLibro());
            statement.setString(2, prestamo.getEstudiante());
            statement.setTimestamp(3, Timestamp.from(prestamo.getFechaPrestamo()));

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Prestamo> getPrestamosHistorico() {
        return null;
    }

    @Override
    public void updatePrestamo(int prestamoId, Date fechaDevolucion) {
        String query = "UPDATE prestamos SET fecha_devolucion = ? WHERE id_prestamo = ?";

        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, fechaDevolucion);
            statement.setInt(2, prestamoId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
