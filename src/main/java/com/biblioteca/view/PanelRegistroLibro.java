package com.biblioteca.view;

import com.biblioteca.controller.LibroController;
import com.biblioteca.dto.NuevoLibroDTO;
import com.biblioteca.model.EstadoLibro;

import javax.swing.*;
import java.awt.*;

public class PanelRegistroLibro extends JPanel {
    private final LibroController libroController;

    public PanelRegistroLibro(LibroController libroController) {
        this.libroController = libroController;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos del formulario
        JTextField txtTitulo = new JTextField(20);
        JTextField txtAutor = new JTextField(20);
        JTextField txtGenero = new JTextField(20);
        JTextField txtAnio = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        add(txtTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        add(txtAutor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Género:"), gbc);
        gbc.gridx = 1;
        add(txtGenero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Año:"), gbc);
        gbc.gridx = 1;
        add(txtAnio, gbc);

        JButton btnRegistrar = new JButton("Registrar Libro");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(btnRegistrar, gbc);

        // Action Listener for registering a book
        btnRegistrar.addActionListener(e -> {
            if (txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos");
                return;
            }

            NuevoLibroDTO libro = new NuevoLibroDTO(
                    txtTitulo.getText(), txtAutor.getText(), txtGenero.getText(), txtAnio.getText(), EstadoLibro.DISPONIBLE
            );

            libroController.registrarLibro(libro);

            // Limpiar campos
            txtTitulo.setText("");
            txtAutor.setText("");
            txtGenero.setText("");
            txtAnio.setText("");

            JOptionPane.showMessageDialog(this, "Libro registrado exitosamente");
        });
    }
}
