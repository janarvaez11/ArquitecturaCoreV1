package com.banquito.core.aplicacion.cuentas.modelo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "TasasIntereses")
public class TasaInteres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa IDENTITY para campos SERIAL en PostgreSQL
    @Column(name = "IdTasaInteres", nullable = false)
    private Integer idTasaInteres;

    @Column(name = "BaseCalculo", length = 10, nullable = false)
    private String baseCalculo;

    @Column(name = "MetodoCalculo", length = 20, nullable = false)
    private String metodoCalculo;

    @Column(name = "FrecuenciaCapitalizacion", length = 30, nullable = false)
    private String frecuenciaCapitalizacion;

    @Column(name = "Estado", length = 15, nullable = false)
    private String estado;

    @Temporal (TemporalType.TIMESTAMP)
    @Column(name = "FechaInicioVigencia", nullable = false)
    private Date fechaInicioVigencia;

    @Temporal (TemporalType.TIMESTAMP)
    @Column(name = "FechaFinVigencia", nullable = false)
    private Date fechaFinVigencia;

    @OneToMany(mappedBy = "tasaInteres")
    private List<Cuenta> cuentas;

    @OneToMany(mappedBy = "tasaInteres")
    private List<TasaPlazo> tasasPlazos;

    // Constructores
    public TasaInteres() {
    }

    public TasaInteres(Integer idTasaInteres) {
        this.idTasaInteres = idTasaInteres;
    }

    // Getters y Setters
    public Integer getIdTasaInteres() {
        return idTasaInteres;
    }

    public void setIdTasaInteres(Integer idTasaInteres) {
        this.idTasaInteres = idTasaInteres;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<TasaPlazo> getTasasPlazos() {
        return tasasPlazos;
    }

    public void setTasasPlazos(List<TasaPlazo> tasasPlazos) {
        this.tasasPlazos = tasasPlazos;
    }

    // Métodos hashCode y equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTasaInteres == null) ? 0 : idTasaInteres.hashCode());
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
        TasaInteres other = (TasaInteres) obj;
        if (idTasaInteres == null) {
            if (other.idTasaInteres != null)
                return false;
        } else if (!idTasaInteres.equals(other.idTasaInteres))
            return false;
        return true;
    }

    
    // Método toString
    @Override
    public String toString() {
        return "TasaInteres [idTasaInteres=" + idTasaInteres + ", baseCalculo=" + baseCalculo + ", metodoCalculo="
                + metodoCalculo + ", frecuenciaCapitalizacion=" + frecuenciaCapitalizacion + ", estado=" + estado
                + ", fechaInicioVigencia=" + fechaInicioVigencia + ", fechaFinVigencia=" + fechaFinVigencia
                + ", cuentas=" + cuentas + ", tasasPlazos=" + tasasPlazos + "]";
    }
}