package com.biblioteca.model.dao.impl;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.dto.NuevoPrestamoDTO;
import com.biblioteca.model.dao.DBConexion;
import com.biblioteca.model.dao.PrestamoDAO;
import com.biblioteca.dto.PrestamoDTO;

import java.sql.*;
import java.time.Instant;
import java.util.List;

public class PrestamoDaoImpl implements PrestamoDAO {
    @Override
    public void insert(NuevoPrestamoDTO prestamo) {
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
    public List<PrestamoDTO> getAll() {
        return null;
    }

    @Override
    public void update(int prestamoId) {
        String query = "UPDATE prestamos SET fecha_devolucion = ? WHERE id_prestamo = ?";

        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setTimestamp(1,  Timestamp.from(Instant.now()));
            statement.setInt(2, prestamoId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PrestamoDTO getLastByLibroId(int idLibro) {
        String query = "SELECT * FROM prestamos WHERE id_libro = ? ORDER BY fecha_prestamo DESC LIMIT 1";
        PrestamoDTO prestamo = null;
        try {
            final Connection connection = DBConexion.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idLibro);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                prestamo = new PrestamoDTO(
                        rs.getInt("id_prestamo"),
                        rs.getInt("id_libro"),
                        rs.getString("estudiante"),
                        rs.getTimestamp("fecha_prestamo").toInstant(),
                        null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }
}
