package com.banquito.core.aplicacion.cuentas.modelo;

import java.util.Date;

import com.banquito.core.aplicacion.general.modelo.Moneda;
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
@Table(name = "TipoCuentas")

public class TipoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa IDENTITY para campos SERIAL en PostgreSQL
    @Column(name = "IdTipoCuenta", nullable = false)
    private Integer idTipoCuenta;

    @Column(name = "Nombre", length = 20, nullable = false)
    private String nombre;

    @Column(name = "Descripcion", length = 50, nullable = false)
    private String descripcion;

    @Column(name = "RequisitosApertura", length = 300, nullable = false)
    private String requisitosApertura;

    @Column(name = "TipoCliente", length = 30, nullable = false)
    private String tipoCliente;

    @Column(name = "CuentasContablesAsociadas", length = 50, nullable = false)
    private String cuentasContablesAsociadas;

    @Column(name = "Estado", length = 20, nullable = false)
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaModificacion")
    private Date fechaModificacion;

    // relacion a la tabla TasaInteres
    @ManyToOne
    @JoinColumn(name = "IdTasaInteresPorDefecto", referencedColumnName = "IdTasaInteres")
    private TasaInteres tasaInteresPorDefecto;

    // relacion a la tabla Monedas
    @ManyToOne
    @JoinColumn(name = "IdMoneda", referencedColumnName = "IdMoneda", nullable = false)
    private Moneda moneda;

    // Constructores
    public TipoCuenta() {
    }

    public TipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    // Getters y Setters

    public Integer getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
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

    public String getRequisitosApertura() {
        return requisitosApertura;
    }

    public void setRequisitosApertura(String requisitosApertura) {
        this.requisitosApertura = requisitosApertura;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCuentasContablesAsociadas() {
        return cuentasContablesAsociadas;
    }

    public void setCuentasContablesAsociadas(String cuentasContablesAsociadas) {
        this.cuentasContablesAsociadas = cuentasContablesAsociadas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public TasaInteres getTasaInteresPorDefecto() {
        return tasaInteresPorDefecto;
    }

    public void setTasaInteresPorDefecto(TasaInteres tasaInteresPorDefecto) {
        this.tasaInteresPorDefecto = tasaInteresPorDefecto;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    // HashCode y Equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTipoCuenta == null) ? 0 : idTipoCuenta.hashCode());
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
        TipoCuenta other = (TipoCuenta) obj;
        if (idTipoCuenta == null) {
            if (other.idTipoCuenta != null)
                return false;
        } else if (!idTipoCuenta.equals(other.idTipoCuenta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TipoCuenta [idTipoCuenta=" + idTipoCuenta + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", requisitosApertura=" + requisitosApertura + ", tipoCliente=" + tipoCliente
                + ", cuentasContablesAsociadas=" + cuentasContablesAsociadas + ", estado=" + estado + ", fechaCreacion="
                + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", tasaInteresPorDefecto="
                + tasaInteresPorDefecto
                + ", moneda=" + moneda + "]";
    }

    // ToString

}
