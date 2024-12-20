package com.biblioteca.view;

import com.biblioteca.controller.LibroController;
import com.biblioteca.controller.PrestamoController;
import com.biblioteca.dto.LibroDTO;
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

public class PanelConsultaLibros extends JPanel implements Observer {
    private final LibroController libroController;
    private final PrestamoController prestamoController;
    private DefaultTableModel modeloTabla;
    private JTable tablaLibros;
    private JComboBox<String> filtroEstado;

    public PanelConsultaLibros(LibroController libroController, PrestamoController prestamoController) {
        this.libroController = libroController;
        this.prestamoController = prestamoController;

        this.libroController.addObserver(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        add(createMainPanel(), BorderLayout.CENTER);

        actualizarTablaLibros();
    }

    private JPanel createMainPanel() {
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

        // Add listeners as before
        tablaLibros.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tablaLibros.getSelectedRow();
            if (selectedRow != -1) {
                String estado = tablaLibros.getValueAt(selectedRow, 5).toString();

                btnSolicitarPrestamo.setEnabled(estado.equals(EstadoLibro.DISPONIBLE.toString()));
                btnGestionarDevolucion.setEnabled(estado.equals(EstadoLibro.PRESTADO.toString()));
            } else {
                btnSolicitarPrestamo.setEnabled(false);
                btnGestionarDevolucion.setEnabled(false);
            }
        });

        filtroEstado.addActionListener(e -> aplicarFiltro());

        btnSolicitarPrestamo.addActionListener(e -> mostrarFormularioPrestamo());
        btnGestionarDevolucion.addActionListener(e -> mostrarFormularioDevolucion());

        return panel;
    }


    private void mostrarFormularioPrestamo() {
        LibroDTO libroSeleccionado = getSelectedBook();
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        // Crear diálogo de préstamo
        JDialog dialogPrestamo = new JDialog(parentFrame, "Solicitar Préstamo", true);
        dialogPrestamo.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        mainPanel.add(new JLabel("Libro:"));
        mainPanel.add(new JLabel(libroSeleccionado.getTitulo()));

        mainPanel.add(new JLabel("Nombre del Solicitante:"));
        JTextField txtNombreSolicitante = new JTextField();
        mainPanel.add(txtNombreSolicitante);

        mainPanel.add(new JLabel("Fecha de Préstamo:"));
        JTextField txtFechaPrestamo = new JTextField(LocalDate.now().toString());
        txtFechaPrestamo.setEditable(false);
        mainPanel.add(txtFechaPrestamo);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnConfirmar);

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
        btnCancelar.addActionListener(evt -> dialogPrestamo.dispose());

        dialogPrestamo.add(mainPanel, BorderLayout.CENTER);
        dialogPrestamo.add(buttonPanel, BorderLayout.SOUTH);

        dialogPrestamo.pack();
        dialogPrestamo.setLocationRelativeTo(parentFrame);
        dialogPrestamo.setVisible(true);
    }

    private void mostrarFormularioDevolucion() {
        LibroDTO libroSeleccionado = getSelectedBook();

        PrestamoDTO prestamo = prestamoController.getPrestamoByLibroId(libroSeleccionado.getId());

        JOptionPane.showMessageDialog(this, "Confirmar la devolucion de " + prestamo.getEstudiante());

        prestamoController.registrarDevolucion(prestamo.getId());
        libroController.updateEstadoLibro(libroSeleccionado.getId(), EstadoLibro.DISPONIBLE);
    }

    private LibroDTO getSelectedBook() {
        int selectedRow = tablaLibros.getSelectedRow();

        if (selectedRow == -1) {
            throw new IllegalStateException("No se seleccionó ningún libro");
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

    public void actualizarTablaLibros() {
        List<LibroDTO> libros = libroController.getAllBooks();
        modeloTabla.setRowCount(0);
        for (LibroDTO libro : libros) {
            modeloTabla.addRow(new Object[]{
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getGenero(),
                    libro.getAnio(),
                    libro.getEstado()
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

    @Override
    public void update() {
        actualizarTablaLibros();
    }
}
