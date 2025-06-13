package com.banquito.core.aplicacion.cuentas.modelo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ComisionesCargos")

public class ComisionCargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa IDENTITY para campos SERIAL en PostgreSQL
    @Column(name = "IdComisionCargo", nullable = false)
    private Integer idComisionCargo;

    @Column(name = "TipoComision", length = 30, nullable = false)
    private String tipoComision;

    @Column(name = "Nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "BaseCalculo", length = 20, nullable = false)
    private String baseCalculo;

    @Column(name = "Monto", precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(name = "Frecuencia", length = 30, nullable = false)
    private String frecuencia;

    @Column(name = "Estado", length = 20, nullable = false)
    private String estado;


    //Relacion a la tabla ServiciosAsociados
    @ManyToOne
    @JoinColumn(name = "IdServicio", referencedColumnName = "IdServicio")
    private ServicioAsociado servicioAsociado;


    //Relacion a la tabla ExencionCuentas y ComisionesCargos
    @OneToMany(mappedBy = "comision")
    private List<ExencionCuenta> exenciones;

    @OneToMany(mappedBy = "comisionCargo")
    private List<CuentaComisionCargo> cuentaComisionCargos;


    //Constructores
    public ComisionCargo() {
    }
    public ComisionCargo(Integer idComisionCargo) {
        this.idComisionCargo = idComisionCargo;
    }

    //Getters y Setters
    public Integer getIdComisionCargo() {
        return idComisionCargo;
    }
    public void setIdComisionCargo(Integer idComisionCargo) {
        this.idComisionCargo = idComisionCargo;
    }
    public String getTipoComision() {
        return tipoComision;
    }
    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
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
    public List<ExencionCuenta> getExenciones() {
        return exenciones;
    }
    public void setExenciones(List<ExencionCuenta> exenciones) {
        this.exenciones = exenciones;
    }
    public List<CuentaComisionCargo> getCuentaComisionCargos() {
        return cuentaComisionCargos;
    }
    public void setCuentaComisionCargos(List<CuentaComisionCargo> cuentaComisionCargos) {
        this.cuentaComisionCargos = cuentaComisionCargos;
    }

    

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public ServicioAsociado getServicioAsociado() {
        return servicioAsociado;
    }
    public void setServicioAsociado(ServicioAsociado servicioAsociado) {
        this.servicioAsociado = servicioAsociado;
    }
    //HashCode y Equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idComisionCargo == null) ? 0 : idComisionCargo.hashCode());
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
        ComisionCargo other = (ComisionCargo) obj;
        if (idComisionCargo == null) {
            if (other.idComisionCargo != null)
                return false;
        } else if (!idComisionCargo.equals(other.idComisionCargo))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "ComisionCargo [idComisionCargo=" + idComisionCargo + ", tipoComision=" + tipoComision + ", nombre="
                + nombre + ", baseCalculo=" + baseCalculo + ", monto=" + monto + ", frecuencia=" + frecuencia
                + ", estado=" + estado + ", servicioAsociado=" + servicioAsociado + ", exenciones=" + exenciones
                + ", cuentaComisionCargos=" + cuentaComisionCargos + "]";
    }

    

}