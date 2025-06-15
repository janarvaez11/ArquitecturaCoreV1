package com.banquito.core.cuentas.modelo;

import com.banquito.core.clientes.modelo.Clientes;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "cuentas_clientes", schema = "public")
public class CuentasClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('cuentas_clientes_id_cuenta_cliente_seq')")
    @Column(name = "id_cuenta_cliente", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_cuenta", nullable = false)
    private Cuentas idCuenta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes idCliente;

    @Column(name = "numero_cuenta", nullable = false, length = 10)
    private String numeroCuenta;

    @Column(name = "saldo_disponible", nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoDisponible;

    @Column(name = "saldo_contable", nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoContable;

    @Column(name = "fecha_apertura", nullable = false)
    private Instant fechaApertura;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cuentas getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuentas idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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

    public Instant getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Instant fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

}