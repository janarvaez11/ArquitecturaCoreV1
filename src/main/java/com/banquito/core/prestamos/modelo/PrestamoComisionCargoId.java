package com.banquito.core.aplicacion.prestamos.modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PrestamoComisionCargoId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Column(name = "IdPrestamo", nullable = false)
    private Integer idPrestamo;

    @Column(name = "IdComisionPrestamo", nullable = false)
    private Integer idComisionPrestamo;

    public PrestamoComisionCargoId() {
    }

    public PrestamoComisionCargoId(Integer idPrestamo, Integer idComisionPrestamo) {
        this.idPrestamo = idPrestamo;
        this.idComisionPrestamo = idComisionPrestamo;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdComisionPrestamo() {
        return idComisionPrestamo;
    }

    public void setIdComisionPrestamo(Integer idComisionPrestamo) {
        this.idComisionPrestamo = idComisionPrestamo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPrestamo == null) ? 0 : idPrestamo.hashCode());
        result = prime * result + ((idComisionPrestamo == null) ? 0 : idComisionPrestamo.hashCode());
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
        PrestamoComisionCargoId other = (PrestamoComisionCargoId) obj;
        if (idPrestamo == null) {
            if (other.idPrestamo != null)
                return false;
        } else if (!idPrestamo.equals(other.idPrestamo))
            return false;
        if (idComisionPrestamo == null) {
            if (other.idComisionPrestamo != null)
                return false;
        } else if (!idComisionPrestamo.equals(other.idComisionPrestamo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PrestamoComisionCargoId [idPrestamo=" + idPrestamo + ", idComisionPrestamo=" + idComisionPrestamo + "]";
    }
} 