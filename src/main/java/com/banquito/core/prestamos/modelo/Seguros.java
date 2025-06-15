package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "seguros", schema = "public")
public class Seguros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('seguros_id_seguro_seq')")
    @Column(name = "id_seguro", nullable = false)
    private Integer id;

    @Column(name = "tipo_seguro", nullable = false, length = 30)
    private String tipoSeguro;

    @Column(name = "compania", nullable = false, length = 100)
    private String compania;

    @Column(name = "monto_asegurado", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoAsegurado;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idSeguro")
    private Set<com.banquito.core.prestamos.modelo.SegurosPrestamos> segurosPrestamos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public BigDecimal getMontoAsegurado() {
        return montoAsegurado;
    }

    public void setMontoAsegurado(BigDecimal montoAsegurado) {
        this.montoAsegurado = montoAsegurado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
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

    public Set<com.banquito.core.prestamos.modelo.SegurosPrestamos> getSegurosPrestamos() {
        return segurosPrestamos;
    }

    public void setSegurosPrestamos(Set<com.banquito.core.prestamos.modelo.SegurosPrestamos> segurosPrestamos) {
        this.segurosPrestamos = segurosPrestamos;
    }

}