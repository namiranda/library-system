package com.biblioteca.model.dao;

import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.model.entity.Prestamo;

import java.sql.Date;
import java.util.List;

public interface PrestamoDAO {
    void insertPrestamo(PrestamoDTO prestamoDTO);

    List<Prestamo> getPrestamosHistorico();

    void updatePrestamo(int prestamoId, Date fechaDevolucion);

}
