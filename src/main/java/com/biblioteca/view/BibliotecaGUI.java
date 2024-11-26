package com.biblioteca.view;

import com.biblioteca.controller.LibroController;
import com.biblioteca.controller.PrestamoController;
import com.biblioteca.dto.LibroDTO;
import com.biblioteca.dto.NuevoLibroDTO;
import com.biblioteca.dto.NuevoPrestamoDTO;
import com.biblioteca.dto.PrestamoDTO;
import com.biblioteca.model.EstadoLibro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class BibliotecaGUI extends JFrame {
    private final LibroController libroController;
    private final PrestamoController prestamoController;
    private DefaultTableModel modeloTabla;
    private JTable tablaLibros;
    private JComboBox<String> filtroEstado;

    public BibliotecaGUI(LibroController libroController, PrestamoController prestamoController) {
        this.libroController = libroController;
        this.prestamoController = prestamoController;
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
        tabbedPane.addTab("Consultar Libros", crearPanelConsulta());
        tabbedPane.addTab("Préstamos Históricos", crearPanelPrestamos());

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
                    txtTitulo.getText(), txtAutor.getText(), txtGenero.getText(), txtAnio.getText(), EstadoLibro.DISPONIBLE
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

        return panel;
    }

    private JPanel crearPanelConsulta() {
        JPanel panel = new JPanel(new BorderLayout());

        // Crear tabla de libros disponibles
        String[] columnas = {"ID", "Título", "Autor", "Género", "Año", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaLibros = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel superior para filtros
        JPanel panelFiltros = new JPanel();
        filtroEstado = new JComboBox<>(new String[]{"Todos", "Disponible", "Prestado"});
        panelFiltros.add(new JLabel("Filtrar por estado:"));
        panelFiltros.add(filtroEstado);

        panel.add(panelFiltros, BorderLayout.NORTH);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnSolicitarPrestamo = new JButton("Solicitar Préstamo");
        JButton btnGestionarDevolucion = new JButton("Gestionar Devolución");

        // Inicialmente deshabilitar los botones
        btnSolicitarPrestamo.setEnabled(false);
        btnGestionarDevolucion.setEnabled(false);

        panelBotones.add(btnSolicitarPrestamo);
        panelBotones.add(btnGestionarDevolucion);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Listener para habilitar/deshabilitar botones cuando se selecciona una fila
        tablaLibros.getSelectionModel().addListSelectionListener(e -> {
            boolean filaSeleccionada = tablaLibros.getSelectedRow() != -1;
            btnSolicitarPrestamo.setEnabled(filaSeleccionada);
            btnGestionarDevolucion.setEnabled(filaSeleccionada);
        });

        // Listener para el filtro de estado
        filtroEstado.addActionListener(e -> aplicarFiltro());

        // Listener para solicitar préstamo
        btnSolicitarPrestamo.addActionListener(e -> mostrarFormularioPrestamo());

        // Listener para gestionar devolución
        btnGestionarDevolucion.addActionListener(e -> mostrarFormularioDevolucion());

        return panel;
    }

    private void mostrarFormularioPrestamo() {
        LibroDTO libroSeleccionado = getSelectedBook();

        // Crear diálogo de préstamo
        JDialog dialogPrestamo = new JDialog(this, "Solicitar Préstamo", true);
        dialogPrestamo.setLayout(new GridLayout(0, 2, 10, 10));
        dialogPrestamo.setSize(300, 250);
        dialogPrestamo.setLocationRelativeTo(this);

        // Campos para el préstamo
        dialogPrestamo.add(new JLabel("Libro:"));
        dialogPrestamo.add(new JLabel(libroSeleccionado.getTitulo()));

        dialogPrestamo.add(new JLabel("Nombre del Solicitante:"));
        JTextField txtNombreSolicitante = new JTextField();
        dialogPrestamo.add(txtNombreSolicitante);

        dialogPrestamo.add(new JLabel("Fecha de Préstamo:"));
        JTextField txtFechaPrestamo = new JTextField(LocalDate.now().toString());
        txtFechaPrestamo.setEditable(false);
        dialogPrestamo.add(txtFechaPrestamo);

        // Botón de confirmar
        JButton btnConfirmar = new JButton("Confirmar Préstamo");
        btnConfirmar.addActionListener(evt -> {
            // Aquí implementarías la lógica para guardar el préstamo
            String nombreSolicitante = txtNombreSolicitante.getText();

            if (nombreSolicitante.isEmpty()) {
                JOptionPane.showMessageDialog(dialogPrestamo,
                        "Por favor complete todos los campos",
                        "Campos Incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            NuevoPrestamoDTO prestamo = new NuevoPrestamoDTO(
                    libroSeleccionado.getId(),
                    nombreSolicitante,
                    Instant.now(),
                    null
            );

            prestamoController.registrarPrestamo(prestamo);
            libroController.updateEstadoLibro(libroSeleccionado.getId(), EstadoLibro.PRESTADO);

            dialogPrestamo.dispose();
        });
        dialogPrestamo.add(btnConfirmar);

        // Botón de cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(evt -> dialogPrestamo.dispose());
        dialogPrestamo.add(btnCancelar);

        dialogPrestamo.setVisible(true);
    }

    private void mostrarFormularioDevolucion() {
        LibroDTO libroSeleccionado = getSelectedBook();

        PrestamoDTO prestamo = prestamoController.getPrestamoByLibroId(libroSeleccionado.getId());

        JOptionPane.showMessageDialog(this, "Confirmar la devolucion de " + prestamo.getEstudiante());

        prestamoController.registrarDevolucion(prestamo.getId());
        libroController.updateEstadoLibro(libroSeleccionado.getId(), EstadoLibro.DISPONIBLE);
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

    private void aplicarFiltro() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
        tablaLibros.setRowSorter(sorter);

        String filtro = (String) filtroEstado.getSelectedItem();
        if (!"Todos".equals(filtro)) {
            sorter.setRowFilter(RowFilter.regexFilter(filtro, 5));
        } else {
            sorter.setRowFilter(null);
        }
    }

    private LibroDTO getSelectedBook() {
        int selectedRow = tablaLibros.getSelectedRow();

        if (selectedRow == -1) {
            throw new IllegalStateException("No row selected");
        }

        int modelRow = tablaLibros.convertRowIndexToModel(selectedRow);

        String id = tablaLibros.getModel().getValueAt(modelRow, 0).toString();
        String titulo = tablaLibros.getModel().getValueAt(modelRow, 1).toString();
        String autor = tablaLibros.getModel().getValueAt(modelRow, 2).toString();
        String genero = tablaLibros.getModel().getValueAt(modelRow, 3).toString();
        String anio = tablaLibros.getModel().getValueAt(modelRow, 4).toString();
        String estado = tablaLibros.getModel().getValueAt(modelRow, 5).toString();
        return new LibroDTO(
                Integer.parseInt(id), titulo, autor, genero, anio, estado
        );
    }


}