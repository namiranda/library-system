package com.biblioteca.service;

import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.model.dao.PrestamoDAO;
import com.biblioteca.model.dao.impl.PrestamoDaoImpl;

public class PrestamoService {

    private PrestamoDAO prestamoDAO;

    public PrestamoService() {
        this.prestamoDAO = new PrestamoDaoImpl();
    }

    public void registarPrestamo(PrestamoDTO prestamoDTO) {
        prestamoDAO.insertPrestamo(prestamoDTO);
    }
}
