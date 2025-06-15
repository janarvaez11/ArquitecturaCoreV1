package com.banquito.core.aplicacion.prestamos.modelo;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Seguros")
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSeguro", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdPrestamo", referencedColumnName = "IdPrestamo")
    private Prestamo prestamo;

    @Column(name = "TipoSeguro", length = 50, nullable = false)
    private String tipoSeguro;

    @Column(name = "Compania", length = 100, nullable = false)
    private String compania;

    @Column(name = "MontoAsegurado", precision = 15, scale = 2, nullable = false)
    private BigDecimal montoAsegurado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaInicio", nullable = false)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaFin", nullable = false)
    private Date fechaFin;

    @Column(name = "Estado", length = 20, nullable = false)
    private String estado;

    public Seguro() {
    }

    public Seguro(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public BigDecimal getMontoAsegurado() {
        return montoAsegurado;
    }

    public void setMontoAsegurado(BigDecimal montoAsegurado) {
        this.montoAsegurado = montoAsegurado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        Seguro other = (Seguro) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Seguro [id=" + id + ", prestamo=" + prestamo + ", tipoSeguro=" + tipoSeguro + ", compania=" + compania
                + ", montoAsegurado=" + montoAsegurado + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
                + ", estado=" + estado + "]";
    }

}
