package com.banquito.core.cuentas.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "comisiones_cargos", schema = "public")
public class ComisionesCargos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('comisiones_cargos_id_comision_cargo_seq')")
    @Column(name = "id_comision_cargo", nullable = false)
    private Integer id;

    @Column(name = "tipo_comision", nullable = false, length = 25)
    private String tipoComision;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_servicio", nullable = false)
    private com.banquito.core.cuentas.modelo.ServiciosAsociados idServicio;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "base_calculo", nullable = false, length = 15)
    private String baseCalculo;

    @Column(name = "monto", nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(name = "frecuencia", nullable = false, length = 15)
    private String frecuencia;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idComisionCargo")
    private Set<com.banquito.core.cuentas.modelo.ComisionesCargosCuentas> comisionesCargosCuentas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idComision")
    private Set<com.banquito.core.cuentas.modelo.ExencionesCuentas> exencionesCuentas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoComision() {
        return tipoComision;
    }

    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }

    public com.banquito.core.cuentas.modelo.ServiciosAsociados getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(com.banquito.core.cuentas.modelo.ServiciosAsociados idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(String baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
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

    public Set<com.banquito.core.cuentas.modelo.ComisionesCargosCuentas> getComisionesCargosCuentas() {
        return comisionesCargosCuentas;
    }

    public void setComisionesCargosCuentas(Set<com.banquito.core.cuentas.modelo.ComisionesCargosCuentas> comisionesCargosCuentas) {
        this.comisionesCargosCuentas = comisionesCargosCuentas;
    }

    public Set<com.banquito.core.cuentas.modelo.ExencionesCuentas> getExencionesCuentas() {
        return exencionesCuentas;
    }

    public void setExencionesCuentas(Set<com.banquito.core.cuentas.modelo.ExencionesCuentas> exencionesCuentas) {
        this.exencionesCuentas = exencionesCuentas;
    }

}