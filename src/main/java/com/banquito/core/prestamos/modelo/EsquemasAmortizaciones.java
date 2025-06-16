package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "esquemas_amortizacion")
public class EsquemasAmortizaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_esquema", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_prestamo", referencedColumnName = "id_tipo_prestamo")
    private com.banquito.core.prestamos.modelo.TiposPrestamos idTipoPrestamo;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "permite_gracia", nullable = false)
    private Boolean permiteGracia = false;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public EsquemasAmortizaciones() {
    }

    public EsquemasAmortizaciones(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.banquito.core.prestamos.modelo.TiposPrestamos getIdTipoPrestamo() {
        return idTipoPrestamo;
    }

    public void setIdTipoPrestamo(com.banquito.core.prestamos.modelo.TiposPrestamos idTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPermiteGracia() {
        return permiteGracia;
    }

    public void setPermiteGracia(Boolean permiteGracia) {
        this.permiteGracia = permiteGracia;
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
        EsquemasAmortizaciones other = (EsquemasAmortizaciones) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EsquemasAmortizaciones [id=" + id + ", idTipoPrestamo=" + idTipoPrestamo + ", nombre=" + nombre
                + ", descripcion=" + descripcion + ", permiteGracia=" + permiteGracia + ", estado=" + estado
                + ", version=" + version + "]";
    }

}