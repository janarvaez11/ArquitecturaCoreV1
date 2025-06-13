package com.banquito.core.aplicacion.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.banquito.core.aplicacion.clientes.modelo.Cliente;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "PrestamosClientes")
public class PrestamosClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PrestamosClientes_id_gen")
    @Column(name = "IdPrestamoCliente", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "IdCliente", referencedColumnName = "id_cliente")
    private Cliente idCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "IdPrestamo", referencedColumnName = "IdPrestamo")
    private Prestamo idPrestamo;

    @Column(name = "Estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "FechaAprobacion")
    private Instant fechaAprobacion;

    @Column(name = "FechaDesembolso")
    private Instant fechaDesembolso;

    @Column(name = "FechaVencimiento")
    private Instant fechaVencimiento;

    public PrestamosClientes() {
    }

    public PrestamosClientes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Prestamo getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Prestamo idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Instant getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Instant fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Instant getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(Instant fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public Instant getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Instant fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        PrestamosClientes that = (PrestamosClientes) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PrestamosClientes [id=" + id + ", idCliente=" + idCliente + ", idPrestamo=" + idPrestamo + ", estado="
                + estado + ", fechaAprobacion=" + fechaAprobacion + ", fechaDesembolso=" + fechaDesembolso
                + ", fechaVencimiento=" + fechaVencimiento + "]";
    }

}