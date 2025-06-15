package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "garantias", schema = "public")
public class Garantias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('garantias_id_garantia_seq')")
    @Column(name = "id_garantia", nullable = false)
    private Integer id;

    @Column(name = "tipo_garantia", nullable = false, length = 20)
    private String tipoGarantia;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idGarantia")
    private Set<com.banquito.core.prestamos.modelo.GarantiasTiposPrestamos> garantiasTiposPrestamos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(String tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    public Set<com.banquito.core.prestamos.modelo.GarantiasTiposPrestamos> getGarantiasTiposPrestamos() {
        return garantiasTiposPrestamos;
    }

    public void setGarantiasTiposPrestamos(Set<com.banquito.core.prestamos.modelo.GarantiasTiposPrestamos> garantiasTiposPrestamos) {
        this.garantiasTiposPrestamos = garantiasTiposPrestamos;
    }

}