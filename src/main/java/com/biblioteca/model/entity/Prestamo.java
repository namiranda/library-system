package com.biblioteca.model.entity;

import java.time.Instant;
import java.util.Date;

public class Prestamo {
    private int id;
    private int idLibro;
    private String estudiante;
    private Instant fechaPrestamo;
    private Instant fechDevolucion;

    public Prestamo(int id, int idLibro, String estudiante, Instant fechaPrestamo, Instant fechDevolucion) {
        this.id = id;
        this.idLibro = idLibro;
        this.estudiante = estudiante;
        this.fechaPrestamo = fechaPrestamo;
        this.fechDevolucion = fechDevolucion;
    }

    public int getId() {
        return id;
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
