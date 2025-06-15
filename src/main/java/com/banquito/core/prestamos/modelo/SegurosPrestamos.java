package com.banquito.core.aplicacion.prestamos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "seguros_prestamos")
public class SegurosPrestamos {

    public enum Estado {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguro_prestamo", nullable = false)
    private Integer idSeguroPrestamo;

    @Column(name = "id_seguro", nullable = false)
    private Integer idSeguro;

    @Column(name = "id_prestamo", nullable = false)
    private Integer idPrestamo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15, nullable = false)
    private Estado estado;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seguro", referencedColumnName = "IdSeguro", insertable = false, updatable = false)
    private Seguro seguro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prestamo", referencedColumnName = "IdPrestamo", insertable = false, updatable = false)
    private Prestamo prestamo;

    public SegurosPrestamos() {
    }

    public SegurosPrestamos(Integer idSeguroPrestamo) {
        this.idSeguroPrestamo = idSeguroPrestamo;
    }

    public Integer getIdSeguroPrestamo() {
        return idSeguroPrestamo;
    }

    public void setIdSeguroPrestamo(Integer idSeguroPrestamo) {
        this.idSeguroPrestamo = idSeguroPrestamo;
    }

    public Integer getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(Integer idSeguro) {
        this.idSeguro = idSeguro;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idSeguroPrestamo == null) ? 0 : idSeguroPrestamo.hashCode());
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
        if (idSeguroPrestamo == null) {
            if (other.idSeguroPrestamo != null)
                return false;
        } else if (!idSeguroPrestamo.equals(other.idSeguroPrestamo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SegurosPrestamos [idSeguroPrestamo=" + idSeguroPrestamo + ", idSeguro=" + idSeguro 
                + ", idPrestamo=" + idPrestamo + ", estado=" + estado + ", version=" + version + "]";
    }
} 