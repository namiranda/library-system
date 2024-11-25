package com.biblioteca;

import com.biblioteca.controller.LibroController;
import com.biblioteca.controller.PrestamoController;
import com.biblioteca.view.BibliotecaGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        LibroController libroController = new LibroController();
        PrestamoController prestamoController = new PrestamoController();

            SwingUtilities.invokeLater(() -> {
                BibliotecaGUI biblioteca = new BibliotecaGUI(libroController, prestamoController);
                biblioteca.setVisible(true);
            });

    }
}