package com.banquito.core.aplicacion.prestamos.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PrestamoComisionCargo")
public class PrestamoComisionCargo {

    @EmbeddedId
    private PrestamoComisionCargoId id;

    @Column(name = "FechaAsignacion")
    private LocalDateTime fechaAsignacion;

    @ManyToOne
    @JoinColumn(name = "IdPrestamo", referencedColumnName = "IdPrestamo", insertable = false, updatable = false)
    private Prestamo prestamo;

    @ManyToOne
    @JoinColumn(name = "IdComisionPrestamo", referencedColumnName = "idComisionPrestamo", insertable = false, updatable = false)
    private ComisionPrestamo comisionPrestamo;

    public PrestamoComisionCargo() {
        this.id = new PrestamoComisionCargoId();
    }

    public PrestamoComisionCargoId getId() {
        return id;
    }

    public void setId(PrestamoComisionCargoId id) {
        this.id = id;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
        if (this.id == null) {
            this.id = new PrestamoComisionCargoId();
        }
        this.id.setIdPrestamo(prestamo.getId());
    }

    public ComisionPrestamo getComisionPrestamo() {
        return comisionPrestamo;
    }

    public void setComisionPrestamo(ComisionPrestamo comisionPrestamo) {
        this.comisionPrestamo = comisionPrestamo;
        if (this.id == null) {
            this.id = new PrestamoComisionCargoId();
        }
        this.id.setIdComisionPrestamo(comisionPrestamo.getId());
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
        PrestamoComisionCargo other = (PrestamoComisionCargo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PrestamoComisionCargo [id=" + id + ", fechaAsignacion=" + fechaAsignacion + "]";
    }
} 