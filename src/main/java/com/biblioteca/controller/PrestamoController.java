package com.biblioteca.controller;

import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.service.PrestamoService;

public class PrestamoController {

    private PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    public void registrarPrestamo(PrestamoDTO prestamo) {

    }
}
