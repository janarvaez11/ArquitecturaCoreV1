package com.banquito.core.aplicacion.cuentas.modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CuentaComisionCargoId implements Serializable {

    @Column(name = "IdCuenta", nullable = false)
    private Integer idCuenta;

    @Column(name = "IdComisionCargo", nullable = false)
    private Integer idComisionCargo;

    public CuentaComisionCargoId() {
    }

    public CuentaComisionCargoId(Integer idCuenta, Integer idComisionCargo) {
        this.idCuenta = idCuenta;
        this.idComisionCargo = idComisionCargo;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Integer getIdComisionCargo() {
        return idComisionCargo;
    }

    public void setIdComisionCargo(Integer idComisionCargo) {
        this.idComisionCargo = idComisionCargo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCuenta == null) ? 0 : idCuenta.hashCode());
        result = prime * result + ((idComisionCargo == null) ? 0 : idComisionCargo.hashCode());
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
        CuentaComisionCargoId other = (CuentaComisionCargoId) obj;
        if (idCuenta == null) {
            if (other.idCuenta != null)
                return false;
        } else if (!idCuenta.equals(other.idCuenta))
            return false;
        if (idComisionCargo == null) {
            if (other.idComisionCargo != null)
                return false;
        } else if (!idComisionCargo.equals(other.idComisionCargo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CuentaComisionCargoId [idCuenta=" + idCuenta + ", idComisionCargo=" + idComisionCargo + "]";
    }
}
