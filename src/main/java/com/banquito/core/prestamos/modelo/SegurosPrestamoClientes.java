package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "seguros_prestamo_cliente")
public class SegurosPrestamoClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguro_prestamo_cliente", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_prestamo_cliente", nullable = false)
    private PrestamosClientes idPrestamoCliente;

    @ManyToOne
    @JoinColumn(name = "id_seguro_prestamo", nullable = false)
    private com.banquito.core.prestamos.modelo.SegurosPrestamos idSeguroPrestamo;

    @Column(name = "monto_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "monto_cuota", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoCuota;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public SegurosPrestamoClientes() {
    }

    public SegurosPrestamoClientes(Integer id) {
        this.id = id;
    }

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
        SegurosPrestamoClientes other = (SegurosPrestamoClientes) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SegurosPrestamoClientes [id=" + id + ", idPrestamoCliente=" + idPrestamoCliente + ", idSeguroPrestamo="
                + idSeguroPrestamo + ", montoTotal=" + montoTotal + ", montoCuota=" + montoCuota + ", estado=" + estado
                + ", version=" + version + "]";
    }

}