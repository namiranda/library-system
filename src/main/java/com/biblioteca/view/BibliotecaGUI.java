package com.biblioteca.view;

import com.biblioteca.controller.LibroController;
import com.biblioteca.controller.PrestamoController;
import javax.swing.*;
import java.awt.*;

public class BibliotecaGUI extends JFrame {
    private final PanelRegistroLibro panelRegistroLibro;
    private final PanelConsultaLibros panelConsultaLibros;
    private final PanelPrestamosHistoricos panelPrestamosHistoricos;

    public BibliotecaGUI(LibroController libroController, PrestamoController prestamoController) {

        panelRegistroLibro = new PanelRegistroLibro(libroController);
        panelConsultaLibros = new PanelConsultaLibros(libroController, prestamoController);
        panelPrestamosHistoricos = new PanelPrestamosHistoricos(prestamoController);

        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Registrar Libro", panelRegistroLibro);
        tabbedPane.addTab("Consultar Libros", panelConsultaLibros);
        tabbedPane.addTab("Préstamos Históricos", panelPrestamosHistoricos);

        add(tabbedPane, BorderLayout.CENTER);
    }
}