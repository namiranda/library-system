package com.biblioteca;

import com.biblioteca.controller.LibroController;
import com.biblioteca.dto.LibroDTO;
import com.biblioteca.view.BibliotecaGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        LibroController libroController = new LibroController();
        libroController.getAllBooks();

            SwingUtilities.invokeLater(() -> {
                BibliotecaGUI biblioteca = new BibliotecaGUI();
                biblioteca.setVisible(true);
            });
        //
//        LibroDTO libroDTO = new LibroDTO(
//                1,
//                "Test",
//                "Test",
//                "no ficcion",
//                "1999",
//                "disponible"
//        );
//
//        libroController.createBook(libroDTO);
    }
}