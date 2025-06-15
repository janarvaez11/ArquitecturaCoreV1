package com.banquito.core.cuentas.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tasas_intereses", schema = "public")
public class TasasIntereses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('tasas_intereses_id_tasa_interes_seq')")
    @Column(name = "id_tasa_interes", nullable = false)
    private Integer id;

    @Column(name = "base_calculo", nullable = false, length = 15)
    private String baseCalculo;

    @Column(name = "metodo_calculo", nullable = false, length = 20)
    private String metodoCalculo;

    @Column(name = "frecuencia_capitalizacion", nullable = false, length = 15)
    private String frecuenciaCapitalizacion;

    @Column(name = "fecha_inicio_vigencia", nullable = false)
    private LocalDate fechaInicioVigencia;

    @Column(name = "fecha_fin_vigencia", nullable = false)
    private LocalDate fechaFinVigencia;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idTasaInteres")
    private Set<Cuentas> cuentas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTasaInteres")
    private Set<com.banquito.core.cuentas.modelo.TasasPlazos> tasasPlazos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTasaInteres")
    private Set<com.banquito.core.cuentas.modelo.TasasSaldos> tasasSaldos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTasaInteresPorDefecto")
    private Set<com.banquito.core.cuentas.modelo.TiposCuentas> tiposCuentas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(String baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public String getMetodoCalculo() {
        return metodoCalculo;
    }

    public void setMetodoCalculo(String metodoCalculo) {
        this.metodoCalculo = metodoCalculo;
    }

    public String getFrecuenciaCapitalizacion() {
        return frecuenciaCapitalizacion;
    }

    public void setFrecuenciaCapitalizacion(String frecuenciaCapitalizacion) {
        this.frecuenciaCapitalizacion = frecuenciaCapitalizacion;
    }

    public LocalDate getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(LocalDate fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public LocalDate getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(LocalDate fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
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

    public Set<Cuentas> getCuentas() {
        return cuentas;
    }

    public void setCuentas(Set<Cuentas> cuentas) {
        this.cuentas = cuentas;
    }

    public Set<com.banquito.core.cuentas.modelo.TasasPlazos> getTasasPlazos() {
        return tasasPlazos;
    }

    public void setTasasPlazos(Set<com.banquito.core.cuentas.modelo.TasasPlazos> tasasPlazos) {
        this.tasasPlazos = tasasPlazos;
    }

    public Set<com.banquito.core.cuentas.modelo.TasasSaldos> getTasasSaldos() {
        return tasasSaldos;
    }

    public void setTasasSaldos(Set<com.banquito.core.cuentas.modelo.TasasSaldos> tasasSaldos) {
        this.tasasSaldos = tasasSaldos;
    }

    public Set<com.banquito.core.cuentas.modelo.TiposCuentas> getTiposCuentas() {
        return tiposCuentas;
    }

    public void setTiposCuentas(Set<com.banquito.core.cuentas.modelo.TiposCuentas> tiposCuentas) {
        this.tiposCuentas = tiposCuentas;
    }

}