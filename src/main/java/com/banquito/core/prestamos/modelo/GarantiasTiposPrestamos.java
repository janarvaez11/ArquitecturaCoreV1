package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "garantias_tipos_prestamos", schema = "public")
public class GarantiasTiposPrestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('garantias_tipos_prestamos_id_garantia_tipo_prestamo_seq')")
    @Column(name = "id_garantia_tipo_prestamo", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_garantia", nullable = false)
    private Garantias idGarantia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_tipo_prestamo", nullable = false)
    private com.banquito.core.prestamos.modelo.TiposPrestamos idTipoPrestamo;

    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @ColumnDefault("0")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idGarantiaTipoPrestamo")
    private Set<com.banquito.core.prestamos.modelo.GarantiasTiposPrestamosClientes> garantiasTiposPrestamosClientes = new LinkedHashSet<>();

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

    public void setGarantiasTiposPrestamosClientes(Set<com.banquito.core.prestamos.modelo.GarantiasTiposPrestamosClientes> garantiasTiposPrestamosClientes) {
        this.garantiasTiposPrestamosClientes = garantiasTiposPrestamosClientes;
    }

}