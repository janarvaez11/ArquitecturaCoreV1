package com.banquito.core.cuentas.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "tasas_saldos", schema = "public")
public class TasasSaldos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('tasas_saldos_id_saldo_seq')")
    @Column(name = "id_saldo", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_tasa_interes", nullable = false)
    private TasasIntereses idTasaInteres;

    @Column(name = "saldo_minimo", nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoMinimo;

    @Column(name = "saldo_maximo", nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoMaximo;

    @Column(name = "tasa", nullable = false, precision = 15, scale = 2)
    private BigDecimal tasa;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TasasIntereses getIdTasaInteres() {
        return idTasaInteres;
    }

    public void setIdTasaInteres(TasasIntereses idTasaInteres) {
        this.idTasaInteres = idTasaInteres;
    }

    public BigDecimal getSaldoMinimo() {
        return saldoMinimo;
    }

    public void setSaldoMinimo(BigDecimal saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
    }

    public BigDecimal getSaldoMaximo() {
        return saldoMaximo;
    }

    public void setSaldoMaximo(BigDecimal saldoMaximo) {
        this.saldoMaximo = saldoMaximo;
    }

    public BigDecimal getTasa() {
        return tasa;
    }

    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
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

}