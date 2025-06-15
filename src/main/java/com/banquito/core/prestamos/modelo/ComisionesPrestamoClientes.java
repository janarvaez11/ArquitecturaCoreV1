package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "comisiones_prestamo_cliente", schema = "public")
public class ComisionesPrestamoClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('comisiones_prestamo_cliente_id_comision_cliente_seq')")
    @Column(name = "id_comision_cliente", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_prestamo_cliente", nullable = false)
    private com.banquito.core.prestamos.modelo.PrestamosClientes idPrestamoCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_comision_prestamo", nullable = false)
    private com.banquito.core.prestamos.modelo.ComisionesPrestamos idComisionPrestamo;

    @Column(name = "fecha_aplicacion", nullable = false)
    private LocalDate fechaAplicacion;

    @Column(name = "monto", nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @ColumnDefault("'PENDIENTE'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @ColumnDefault("1")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.banquito.core.prestamos.modelo.PrestamosClientes getIdPrestamoCliente() {
        return idPrestamoCliente;
    }

    public void setIdPrestamoCliente(com.banquito.core.prestamos.modelo.PrestamosClientes idPrestamoCliente) {
        this.idPrestamoCliente = idPrestamoCliente;
    }

    public com.banquito.core.prestamos.modelo.ComisionesPrestamos getIdComisionPrestamo() {
        return idComisionPrestamo;
    }

    public void setIdComisionPrestamo(com.banquito.core.prestamos.modelo.ComisionesPrestamos idComisionPrestamo) {
        this.idComisionPrestamo = idComisionPrestamo;
    }

    public LocalDate getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(LocalDate fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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