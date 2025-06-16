package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "seguros_prestamos")
public class SegurosPrestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguro_prestamo", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_seguro", referencedColumnName = "id_seguro", nullable = false)
    private Seguros idSeguro;

    @ManyToOne
    @JoinColumn(name = "id_prestamo", referencedColumnName = "id_prestamo", nullable = false)
    private Prestamos idPrestamo;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idSeguroPrestamo")
    private Set<SegurosPrestamoClientes> segurosPrestamoClientes = new LinkedHashSet<>();

    public SegurosPrestamos() {
    }

    public SegurosPrestamos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Seguros getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(Seguros idSeguro) {
        this.idSeguro = idSeguro;
    }

    public Prestamos getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Prestamos idPrestamo) {
        this.idPrestamo = idPrestamo;
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

    public Set<SegurosPrestamoClientes> getSegurosPrestamoClientes() {
        return segurosPrestamoClientes;
    }

    public void setSegurosPrestamoClientes(Set<SegurosPrestamoClientes> segurosPrestamoClientes) {
        this.segurosPrestamoClientes = segurosPrestamoClientes;
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
        SegurosPrestamos other = (SegurosPrestamos) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SegurosPrestamos [id=" + id + ", idSeguro=" + idSeguro + ", idPrestamo=" + idPrestamo + ", estado="
                + estado + ", version=" + version + ", segurosPrestamoClientes=" + segurosPrestamoClientes + "]";
    }

}