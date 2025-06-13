package com.banquito.core.aplicacion.cuentas.modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ServicioTipoCuentaId implements Serializable {

    @Column(name = "IdServicio", nullable = false)
    private Integer IdServicio;

    @Column(name = "IdCuenta", nullable = false)
    private Integer IdCuenta;

    public ServicioTipoCuentaId() {
    }

    public ServicioTipoCuentaId(Integer idServicio, Integer idCuenta) {
        IdServicio = idServicio;
        IdCuenta = idCuenta;
    }

    public Integer getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(Integer idServicio) {
        IdServicio = idServicio;
    }

    public Integer getIdCuenta() {
        return IdCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        IdCuenta = idCuenta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((IdServicio == null) ? 0 : IdServicio.hashCode());
        result = prime * result + ((IdCuenta == null) ? 0 : IdCuenta.hashCode());
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
        ServicioTipoCuentaId other = (ServicioTipoCuentaId) obj;
        if (IdServicio == null) {
            if (other.IdServicio != null)
                return false;
        } else if (!IdServicio.equals(other.IdServicio))
            return false;
        if (IdCuenta == null) {
            if (other.IdCuenta != null)
                return false;
        } else if (!IdCuenta.equals(other.IdCuenta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ServicioTipoCuentaId [IdServicio=" + IdServicio + ", IdCuenta=" + IdCuenta + "]";
    }

}
