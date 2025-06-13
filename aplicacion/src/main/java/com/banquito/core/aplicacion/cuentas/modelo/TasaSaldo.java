package com.banquito.core.aplicacion.cuentas.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "TasaSaldos")
public class TasaSaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa IDENTITY para campos SERIAL en PostgreSQL
    @Column(name = "IdSaldo", nullable = false)
    private Integer idSaldo;

    @Column(name = "Nombre", length = 20, nullable = false)
    private String nombre;

    @Column(name = "SaldoMinimo", precision = 15, scale = 2)
    private BigDecimal saldoMinimo;

    @Column(name = "SaldoMaximo", precision = 15, scale = 2)
    private BigDecimal saldoMaximo;

    @Column(name = "Tasa", precision = 5, scale = 2)
    private BigDecimal tasa;

    @Version
    @Column(name = "Version")
    private Long version;

    // Relacion a la tabla TasaInteres
    @ManyToOne
    @JoinColumn(name = "IdTasaInteres", referencedColumnName = "IdTasaInteres", nullable = false)
    private TasaInteres tasaInteres;

    // Constructores
    public TasaSaldo() {
    }

    public TasaSaldo(Integer idSaldo) {
        this.idSaldo = idSaldo;
    }

    // Getters y Setters

    public Integer getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(Integer idSaldo) {
        this.idSaldo = idSaldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public TasaInteres getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(TasaInteres tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idSaldo == null) ? 0 : idSaldo.hashCode());
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
        TasaSaldo other = (TasaSaldo) obj;
        if (idSaldo == null) {
            if (other.idSaldo != null)
                return false;
        } else if (!idSaldo.equals(other.idSaldo))
            return false;
        return true;
    }

    // Método ToString
    @Override
    public String toString() {
        return "TasaSaldo [idSaldo=" + idSaldo + ", nombre=" + nombre + ", saldoMinimo=" + saldoMinimo
                + ", saldoMaximo=" + saldoMaximo + ", tasa=" + tasa + ", tasaInteres=" + tasaInteres + "]";
    }

}