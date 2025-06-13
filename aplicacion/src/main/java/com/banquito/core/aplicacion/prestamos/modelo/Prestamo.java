package com.banquito.core.aplicacion.prestamos.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPrestamo", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdTipoPrestamo", referencedColumnName = "IdTipoPrestamo")
    private TipoPrestamo tipoPrestamo;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Descripcion", length = 200, nullable = false)
    private String descripcion;

    @Column(name = "Estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "FechaModificacion")
    private LocalDate fechaModificacion;

    @Column(name = "BaseCalculo", length = 30, nullable = false)
    private String baseCalculo;

    @Column(name = "TasaMoratoria", precision = 5, scale = 2, nullable = false)
    private BigDecimal tasaMonetaria;

    public Prestamo() {
    }

    public Prestamo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoPrestamo getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(TipoPrestamo tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(String baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public BigDecimal getTasaMonetaria() {
        return tasaMonetaria;
    }

    public void setTasaMonetaria(BigDecimal tasaMonetaria) {
        this.tasaMonetaria = tasaMonetaria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Prestamo other = (Prestamo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Prestamo [id=" + id + ", tipoPrestamo=" + tipoPrestamo + ", nombre=" + nombre + ", descripcion="
                + descripcion + ", estado=" + estado + ", fechaModificacion=" + fechaModificacion + ", baseCalculo="
                + baseCalculo + ", tasaMonetaria=" + tasaMonetaria + "]";
    }

}
