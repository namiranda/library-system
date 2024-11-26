package com.biblioteca.controller;

import com.biblioteca.dto.NuevoPrestamoDTO;
import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.service.PrestamoService;
import com.biblioteca.view.Observer;

import java.util.ArrayList;
import java.util.List;

public class PrestamoController {
    private final List<Observer> observers = new ArrayList<>();
    private final PrestamoService prestamoService;

    public PrestamoController() {
        this.prestamoService = new PrestamoService();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();  // Notify all observers
        }
    }

    public void registrarPrestamo(NuevoPrestamoDTO prestamo) {
        prestamoService.registarPrestamo(prestamo);
        notifyObservers();
    }

    public PrestamoDTO getPrestamoByLibroId(int idLibro) {
        return prestamoService.getPrestamoById(idLibro);
    }

    public void registrarDevolucion(int idPrestamo){
        prestamoService.registrarDevolucion(idPrestamo);
        notifyObservers();
    }

    public List<PrestamoDTO> getHistoricoPrestamos(){
        return prestamoService.getHistoricoPrestamos();
    }
}
