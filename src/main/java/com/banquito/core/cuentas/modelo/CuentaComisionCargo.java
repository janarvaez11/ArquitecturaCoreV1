package com.banquito.core.aplicacion.cuentas.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "CuentasComisionesCargos")
public class CuentaComisionCargo {
    
    @EmbeddedId
    private CuentaComisionCargoId id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaAsignacion", nullable = false)
    private Date FechaAsignacion;

    @ManyToOne
    @JoinColumn(name = "IdCuenta", insertable = false, updatable = false)
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "IdComisionCargo", insertable = false, updatable = false)
    private ComisionCargo comisionCargo;

    // Constructores
    public CuentaComisionCargo() {
        this.id = new CuentaComisionCargoId();
    }

    // Getters y Setters
    public CuentaComisionCargoId getId() {
        return id;
    }

    public void setId(CuentaComisionCargoId id) {
        this.id = id;
    }

    public Date getFechaAsignacion() {
        return FechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        FechaAsignacion = fechaAsignacion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        if (this.id == null) {
            this.id = new CuentaComisionCargoId();
        }
        this.id.setIdCuenta(cuenta.getIdCuenta());
    }

    public ComisionCargo getComisionCargo() {
        return comisionCargo;
    }

    public void setComisionCargo(ComisionCargo comisionCargo) {
        this.comisionCargo = comisionCargo;
        if (this.id == null) {
            this.id = new CuentaComisionCargoId();
        }
        this.id.setIdComisionCargo(comisionCargo.getIdComisionCargo());
    }

    // HashCode y Equals
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
        CuentaComisionCargo other = (CuentaComisionCargo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CuentaComisionCargo [id=" + id + ", FechaAsignacion=" + FechaAsignacion + "]";
    }
}
