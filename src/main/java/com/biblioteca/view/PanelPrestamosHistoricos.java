package com.biblioteca.view;

import com.biblioteca.controller.PrestamoController;
import com.biblioteca.dto.PrestamoDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelPrestamosHistoricos extends JPanel implements Observer {
    private final PrestamoController prestamoController;
    private DefaultTableModel modeloTablaPrestamos;

    public PanelPrestamosHistoricos(PrestamoController prestamoController) {
        this.prestamoController = prestamoController;
        this.prestamoController.addObserver(this);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Tabla para el listado de prestamos
        String[] columnas = {"Id libro", "Estudiante", "Fecha Prestámo", "Fecha Devolución"};
        modeloTablaPrestamos = new DefaultTableModel(columnas, 0);
        JTable tablaPrestamos = new JTable(modeloTablaPrestamos);
        JScrollPane scrollPane = new JScrollPane(tablaPrestamos);
        add(scrollPane, BorderLayout.CENTER);

        actualizarPrestamos();
    }

    public void actualizarPrestamos() {
        List<PrestamoDTO> prestamos = prestamoController.getHistoricoPrestamos();
        modeloTablaPrestamos.setRowCount(0);
        for (PrestamoDTO prestamo : prestamos) {
            modeloTablaPrestamos.addRow(new Object[]{
                    prestamo.getId(),
                    prestamo.getEstudiante(),
                    prestamo.getFechaPrestamo(),
                    prestamo.getFechDevolucion()
            });
        }
    }

    @Override
    public void update() {
        actualizarPrestamos();
    }
}
