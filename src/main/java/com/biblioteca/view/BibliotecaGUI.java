package com.biblioteca.view;

import com.biblioteca.controller.LibroController;
import com.biblioteca.dto.LibroDTO;
import com.biblioteca.dto.NuevoLibroDTO;
import com.biblioteca.dto.PrestamoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.List;

public class BibliotecaGUI extends JFrame {
    private final LibroController libroController;
    private DefaultTableModel modeloTabla;
    private JTable tablaLibros;
    private JComboBox<String> filtroEstado;

    public BibliotecaGUI(LibroController libroController) {
        this.libroController = libroController;
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
        tabbedPane.addTab("Registrar Libro", crearPanelRegistro());
        tabbedPane.addTab("Gestionar Préstamos", crearPanelPrestamos());
        tabbedPane.addTab("Consultar Libros", crearPanelConsulta());
        add(tabbedPane, BorderLayout.CENTER);
        this.actualizarTablaLibros();
    }

    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos del formulario
        JTextField txtTitulo = new JTextField(20);
        JTextField txtAutor = new JTextField(20);
        JTextField txtGenero = new JTextField(20);
        JTextField txtAnio = new JTextField(20);

        // Añadir componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        panel.add(txtTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        panel.add(txtAutor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Genero:"), gbc);
        gbc.gridx = 1;
        panel.add(txtGenero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Año"), gbc);
        gbc.gridx = 1;
        panel.add(txtAnio, gbc);

        JButton btnRegistrar = new JButton("Registrar Libro");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> {
            if (txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos");
                return;
            }

            NuevoLibroDTO libro = new NuevoLibroDTO(
                    txtTitulo.getText(), txtAutor.getText(), txtGenero.getText(), txtAnio.getText(), "DISPONIBLE"
            );

            libroController.registrarLibro(libro);
            actualizarTablaLibros();

            // Limpiar campos
            txtTitulo.setText("");
            txtAutor.setText("");
            txtGenero.setText("");
            txtAnio.setText("");


            JOptionPane.showMessageDialog(this, "Libro registrado exitosamente");
        });

        return panel;
    }

    private JPanel crearPanelPrestamos() {
        JPanel panel = new JPanel(new BorderLayout());

        // Crear tabla de libros disponibles
        String[] columnas = {"ID", "Título", "Autor", "Género", "Año", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaLibros = new JTable(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnPrestar = new JButton("Registrar Préstamo");
        JButton btnDevolver = new JButton("Registrar Devolución");

        btnPrestar.addActionListener(e -> registrarPrestamo());
        btnDevolver.addActionListener(e -> registrarDevolucion());

        panelBotones.add(btnPrestar);
        panelBotones.add(btnDevolver);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelConsulta() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel superior para filtros
        JPanel panelFiltros = new JPanel();
        filtroEstado = new JComboBox<>(new String[]{"Todos", "Disponible", "Prestado"});
        panelFiltros.add(new JLabel("Filtrar por estado:"));
        panelFiltros.add(filtroEstado);

        panel.add(panelFiltros, BorderLayout.NORTH);

        // Tabla de libros
        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        panel.add(scrollPane, BorderLayout.CENTER);

        filtroEstado.addActionListener(e -> aplicarFiltro());
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Double click
                    int idLibroSeleccionado = getIdLibro();
                    PrestamoDTO prestamo = new PrestamoDTO(idLibroSeleccionado, "Juan", Instant.now(), null);
                }
            }
        });
        return panel;
    }


    private void actualizarTablaLibros() {
        List<LibroDTO> libros = libroController.getAllBooks();
        System.out.println("libros:" + libros.toString());

        modeloTabla.setRowCount(0);
        for (LibroDTO libro : libros) {
            modeloTabla.addRow(new Object[]{
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getGenero(),
                    libro.getAnio(),
                    libro.isDisponible() ? "Disponible" : "Prestado"
            });
        }
    }

    private void registrarPrestamo() {
        int filaSeleccionada = tablaLibros.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro");
            return;
        }

        // if (libro != null && libro.isDisponible()) {
        //     libro.setDisponible(false);
        //      actualizarTablaLibros();
        //     JOptionPane.showMessageDialog(this, "Préstamo registrado exitosamente");
        //  } else {
        //      JOptionPane.showMessageDialog(this, "El libro no está disponible para préstamo");
        //  }
    }

    private void registrarDevolucion() {
        int filaSeleccionada = tablaLibros.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro");
            return;
        }

        //  if (libro != null && !libro.isDisponible()) {
        //     libro.setDisponible(true);
        //    actualizarTablaLibros();
        //    JOptionPane.showMessageDialog(this, "Devolución registrada exitosamente");
        // } else {
        //      JOptionPane.showMessageDialog(this, "El libro ya está disponible");
        //  }
    }


    private void aplicarFiltro() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
        tablaLibros.setRowSorter(sorter);

        String filtro = (String) filtroEstado.getSelectedItem();
        if (!"Todos".equals(filtro)) {
            sorter.setRowFilter(RowFilter.regexFilter(filtro, 3));
        } else {
            sorter.setRowFilter(null);
        }
    }

    private int getIdLibro() {
        int selectedRow = tablaLibros.getSelectedRow();

        if (selectedRow == -1) {
            throw new IllegalStateException("No row selected");
        }

        int modelRow = tablaLibros.convertRowIndexToModel(selectedRow);

        String id = tablaLibros.getModel().getValueAt(modelRow, 0).toString();
        return Integer.parseInt(id);
    }


}