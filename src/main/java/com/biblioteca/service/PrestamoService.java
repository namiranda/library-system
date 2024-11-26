package com.biblioteca.service;

import com.biblioteca.dto.NuevoPrestamoDTO;
import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.model.dao.PrestamoDAO;
import com.biblioteca.model.dao.impl.PrestamoDaoImpl;

public class PrestamoService {

    private final PrestamoDAO prestamoDAO;

    public PrestamoService() {
        this.prestamoDAO = new PrestamoDaoImpl();
    }

    public void registarPrestamo(NuevoPrestamoDTO nuevoPrestamoDTO) {
        prestamoDAO.insert(nuevoPrestamoDTO);
    }

    public PrestamoDTO getPrestamoById(int idLibro) {
        return prestamoDAO.getLastByLibroId(idLibro);
    }

    public void registrarDevolucion(int idPrestamo) {
        prestamoDAO.update(idPrestamo);
    }
}
