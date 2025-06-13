package com.banquito.core.aplicacion.cuentas.modelo;

import java.math.BigDecimal;
import java.util.Date;

import com.banquito.core.aplicacion.clientes.modelo.Cliente;

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
@Table(name = "CuentasClientes")

public class CuentaCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCuentaCliente", nullable = false)
    private Integer idCuentaCliente;

    @Column(name = "NumeroCuenta", length = 10, nullable = false)
    private String numeroCuenta;

    @Column(name = "Estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "SaldoDisponible", precision = 100, scale = 2)
    private BigDecimal saldoDisponible;

    @Column(name = "SaldoContable", precision = 100, scale = 2)
    private BigDecimal saldoContable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaApertura", nullable = false)
    private Date fechaApertura;

    // Relacion a la tabla Cuentas
    @ManyToOne
    @JoinColumn(name = "IdCuenta", referencedColumnName = "IdCuenta", nullable = false)
    private Cuenta cuenta;

    // Relacion a la tabla Clientes
    @ManyToOne
    @JoinColumn(name = "IdCliente", referencedColumnName = "id_cliente", nullable = false)
    private Cliente cliente;

    // Constructores

    public CuentaCliente() {
    }

    // Getters y Setters

    public CuentaCliente(Integer idCuentaCliente) {
        this.idCuentaCliente = idCuentaCliente;
    }

    public Integer getIdCuentaCliente() {
        return idCuentaCliente;
    }

    public void setIdCuentaCliente(Integer idCuentaCliente) {
        this.idCuentaCliente = idCuentaCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(BigDecimal saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public BigDecimal getSaldoContable() {
        return saldoContable;
    }

    public void setSaldoContable(BigDecimal saldoContable) {
        this.saldoContable = saldoContable;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // HashCode y Equals

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCuentaCliente == null) ? 0 : idCuentaCliente.hashCode());
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
        CuentaCliente other = (CuentaCliente) obj;
        if (idCuentaCliente == null) {
            if (other.idCuentaCliente != null)
                return false;
        } else if (!idCuentaCliente.equals(other.idCuentaCliente))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CuentaCliente [idCuentaCliente=" + idCuentaCliente + ", numeroCuenta=" + numeroCuenta + ", estado="
                + estado + ", saldoDisponible=" + saldoDisponible + ", saldoContable=" + saldoContable
                + ", fechaApertura=" + fechaApertura + ", cuenta=" + cuenta + ", cliente=" + cliente + "]";
    }

    

}