package com.biblioteca.controller;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.dto.NuevoLibroDTO;
import com.biblioteca.model.EstadoLibro;
import com.biblioteca.service.LibroService;
import com.biblioteca.view.Observer;

import java.util.ArrayList;
import java.util.List;


public class LibroController {
    private final List<Observer> observers = new ArrayList<>();
    private final LibroService service;

    public LibroController(){
        service = new LibroService();
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

        public List<LibroDTO> getAllBooks() {
        return service.getAllBooks();
    }

    public void registrarLibro(NuevoLibroDTO libro){
        service.createBook(libro);
        notifyObservers();
    }

    public void updateEstadoLibro(int id, EstadoLibro estado){
        service.updateEstadoLibro(id, estado);
        notifyObservers();
    }
}
