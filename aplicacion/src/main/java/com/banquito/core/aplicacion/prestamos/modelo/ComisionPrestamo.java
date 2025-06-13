package com.banquito.core.aplicacion.prestamos.modelo;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "ComisionesPrestamos")
public class ComisionPrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComisionPrestamo", nullable = false)
    private Integer id;

    @Column(name = "Nombre", length = 100)
    private String nombre;

    @Column(name = "TipoComision", length = 30)
    private String tipoComision;

    @Column(name = "TipoCalculo", length = 15)
    private String tipoCalculo;

    @Column(name = "Valor", precision = 15, scale = 2)
    private BigDecimal valor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaCreacion", updatable = false)
    private Date fechaCreacion;

    public ComisionPrestamo() {
    }

    public ComisionPrestamo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoComision() {
        return tipoComision;
    }

    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }

    public String getTipoCalculo() {
        return tipoCalculo;
    }

    public void setTipoCalculo(String tipoCalculo) {
        this.tipoCalculo = tipoCalculo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        if (this.tipoCalculo.equals("PORCENTAJE") && valor.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("El porcentaje no puede ser mayor al 100%");
        }
        this.valor = valor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
        ComisionPrestamo other = (ComisionPrestamo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ComisionesPrestamos [id=" + id + ", nombre=" + nombre + ", tipoComision=" + tipoComision
                + ", tipoCalculo=" + tipoCalculo + ", valor=" + valor + ", fechaCreacion=" + fechaCreacion + "]";
    }
} 