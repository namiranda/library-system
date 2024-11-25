package com.biblioteca.service;

import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.model.dao.PrestamoDAO;

public class PrestamoService {

    private PrestamoDAO prestamoDAO;

    public PrestamoService(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
    }

    public void registarPrestamo(PrestamoDTO prestamoDTO) {
        prestamoDAO.insertPrestamo(prestamoDTO);
    }
}
