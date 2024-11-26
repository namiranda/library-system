package com.biblioteca.controller;

import com.biblioteca.dto.NuevoPrestamoDTO;
import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.service.PrestamoService;

import java.util.List;

public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController() {
        this.prestamoService = new PrestamoService();
    }

    public void registrarPrestamo(NuevoPrestamoDTO prestamo) {
        prestamoService.registarPrestamo(prestamo);
    }

    public PrestamoDTO getPrestamoByLibroId(int idLibro) {
        return prestamoService.getPrestamoById(idLibro);
    }

    public void registrarDevolucion(int idPrestamo){
        prestamoService.registrarDevolucion(idPrestamo);
    }

    public List<PrestamoDTO> getHistoricoPrestamos(){
        return prestamoService.getHistoricoPrestamos();
    }
}
