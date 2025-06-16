package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "garantias_tipos_prestamos")
public class GarantiasTiposPrestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_garantia_tipo_prestamo", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_garantia", referencedColumnName = "id_garantia", nullable = false)
    private Garantias idGarantia;

    @ManyToOne
    @JoinColumn(name = "id_tipo_prestamo", referencedColumnName = "id_tipo_prestamo", nullable = false)
    private com.banquito.core.prestamos.modelo.TiposPrestamos idTipoPrestamo;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idGarantiaTipoPrestamo")
    private Set<com.banquito.core.prestamos.modelo.GarantiasTiposPrestamosClientes> garantiasTiposPrestamosClientes = new LinkedHashSet<>();

    public GarantiasTiposPrestamos() {
    }

    public GarantiasTiposPrestamos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Garantias getIdGarantia() {
        return idGarantia;
    }

    public void setIdGarantia(Garantias idGarantia) {
        this.idGarantia = idGarantia;
    }

    public com.banquito.core.prestamos.modelo.TiposPrestamos getIdTipoPrestamo() {
        return idTipoPrestamo;
    }

    public void setIdTipoPrestamo(com.banquito.core.prestamos.modelo.TiposPrestamos idTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
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

    public Set<com.banquito.core.prestamos.modelo.GarantiasTiposPrestamosClientes> getGarantiasTiposPrestamosClientes() {
        return garantiasTiposPrestamosClientes;
    }

    public void setGarantiasTiposPrestamosClientes(
            Set<com.banquito.core.prestamos.modelo.GarantiasTiposPrestamosClientes> garantiasTiposPrestamosClientes) {
        this.garantiasTiposPrestamosClientes = garantiasTiposPrestamosClientes;
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
        GarantiasTiposPrestamos other = (GarantiasTiposPrestamos) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GarantiasTiposPrestamos [id=" + id + ", idGarantia=" + idGarantia + ", idTipoPrestamo=" + idTipoPrestamo
                + ", estado=" + estado + ", version=" + version + ", garantiasTiposPrestamosClientes="
                + garantiasTiposPrestamosClientes + "]";
    }

}