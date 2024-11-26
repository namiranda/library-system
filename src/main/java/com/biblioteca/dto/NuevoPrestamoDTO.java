package com.biblioteca.dto;

import java.time.Instant;

public class NuevoPrestamoDTO {
    private int idLibro;
    private String estudiante;
    private Instant fechaPrestamo;
    private Instant fechDevolucion;

    public NuevoPrestamoDTO(int idLibro, String estudiante, Instant fechaPrestamo, Instant fechDevolucion) {
        this.idLibro = idLibro;
        this.estudiante = estudiante;
        this.fechaPrestamo = fechaPrestamo;
        this.fechDevolucion = fechDevolucion;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public Instant getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Instant getFechDevolucion() {
        return fechDevolucion;
    }
}
