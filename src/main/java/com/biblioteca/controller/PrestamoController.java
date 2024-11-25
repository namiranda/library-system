package com.biblioteca.controller;

import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.service.PrestamoService;

public class PrestamoController {

    private PrestamoService prestamoService;

    public PrestamoController() {
        this.prestamoService = new PrestamoService();
    }

    public void registrarPrestamo(PrestamoDTO prestamo) {
        prestamoService.registarPrestamo(prestamo);
    }
}
