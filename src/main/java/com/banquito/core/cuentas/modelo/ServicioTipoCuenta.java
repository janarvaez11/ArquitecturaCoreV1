package com.banquito.core.aplicacion.cuentas.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "ServicioTipoCuenta")
public class ServicioTipoCuenta {

    @EmbeddedId
    private ServicioTipoCuentaId id;

    @Temporal (TemporalType.TIMESTAMP)
    @Column(name = "FechaAsignacion", nullable = false)
    private Date fechaAsignacion;

    @ManyToOne()
    @JoinColumn(name = "IdServicio", referencedColumnName = "IdServicio", insertable = false, updatable = false)
    private ServicioAsociado servicioAsociado;

    @ManyToOne()
    @JoinColumn(name = "IdCuenta", referencedColumnName = "IdCuenta", insertable = false, updatable = false)
    private Cuenta cuenta;

    public ServicioTipoCuenta() {
    }

    public ServicioTipoCuenta(ServicioTipoCuentaId id) {
        this.id = id;
    }

    public ServicioTipoCuentaId getId() {
        return id;
    }

    public void setId(ServicioTipoCuentaId id) {
        this.id = id;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public ServicioAsociado getServicioAsociado() {
        return servicioAsociado;
    }

    public void setServicioAsociado(ServicioAsociado servicioAsociado) {
        this.servicioAsociado = servicioAsociado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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
        ServicioTipoCuenta other = (ServicioTipoCuenta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    // metodo toString

    @Override
    public String toString() {
        return "ServicioTipoCuenta [id=" + id + ", fechaAsignacion=" + fechaAsignacion + ", servicioAsociado="
                + servicioAsociado + ", cuenta=" + cuenta + "]";
    }


}
