package com.biblioteca;

import com.biblioteca.controller.LibroController;
import com.biblioteca.view.BibliotecaGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        LibroController libroController = new LibroController();

            SwingUtilities.invokeLater(() -> {
                BibliotecaGUI biblioteca = new BibliotecaGUI(libroController);
                biblioteca.setVisible(true);
            });

    }
}