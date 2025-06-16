package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "comisiones_prestamo_cliente")
public class ComisionesPrestamoClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comision_cliente", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_prestamo_cliente", referencedColumnName = "id_prestamo_cliente", nullable = false)
    private com.banquito.core.prestamos.modelo.PrestamosClientes idPrestamoCliente;

    @ManyToOne
    @JoinColumn(name = "id_comision_prestamo", referencedColumnName = "id_comision_prestamo", nullable = false)
    private com.banquito.core.prestamos.modelo.ComisionesPrestamos idComisionPrestamo;

    @Column(name = "fecha_aplicacion", nullable = false)
    private LocalDate fechaAplicacion;

    @Column(name = "monto", nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public ComisionesPrestamoClientes() {
    }

    public ComisionesPrestamoClientes(Integer id) {
        this.id = id;
    }

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
        ComisionesPrestamoClientes other = (ComisionesPrestamoClientes) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ComisionesPrestamoClientes [id=" + id + ", idPrestamoCliente=" + idPrestamoCliente
                + ", idComisionPrestamo=" + idComisionPrestamo + ", fechaAplicacion=" + fechaAplicacion + ", monto="
                + monto + ", estado=" + estado + ", version=" + version + "]";
    }

}