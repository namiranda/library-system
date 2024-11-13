package com.biblioteca;

import com.biblioteca.controller.LibroController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        LibroController libroController = new LibroController();
        libroController.getAllBooks();
    }
}