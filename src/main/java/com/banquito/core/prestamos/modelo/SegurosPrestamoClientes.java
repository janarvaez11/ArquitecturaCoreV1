package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "seguros_prestamo_cliente", schema = "public")
public class SegurosPrestamoClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('seguros_prestamo_cliente_id_seguro_prestamo_cliente_seq')")
    @Column(name = "id_seguro_prestamo_cliente", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_prestamo_cliente", nullable = false)
    private PrestamosClientes idPrestamoCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_seguro_prestamo", nullable = false)
    private com.banquito.core.prestamos.modelo.SegurosPrestamos idSeguroPrestamo;

    @Column(name = "monto_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "monto_cuota", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoCuota;

    @ColumnDefault("'ACTIVO'")
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

    public PrestamosClientes getIdPrestamoCliente() {
        return idPrestamoCliente;
    }

    public void setIdPrestamoCliente(PrestamosClientes idPrestamoCliente) {
        this.idPrestamoCliente = idPrestamoCliente;
    }

    public com.banquito.core.prestamos.modelo.SegurosPrestamos getIdSeguroPrestamo() {
        return idSeguroPrestamo;
    }

    public void setIdSeguroPrestamo(com.banquito.core.prestamos.modelo.SegurosPrestamos idSeguroPrestamo) {
        this.idSeguroPrestamo = idSeguroPrestamo;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(BigDecimal montoCuota) {
        this.montoCuota = montoCuota;
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