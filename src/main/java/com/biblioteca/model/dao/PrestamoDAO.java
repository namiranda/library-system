package com.biblioteca.model.dao;

import com.biblioteca.dto.NuevoPrestamoDTO;
import com.biblioteca.dto.PrestamoDTO;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

public interface PrestamoDAO {
    void insert(NuevoPrestamoDTO nuevoPrestamoDTO);

    List<PrestamoDTO> getAll();

    void update(int prestamoId);

    PrestamoDTO getLastByLibroId(int idLibro);
}
