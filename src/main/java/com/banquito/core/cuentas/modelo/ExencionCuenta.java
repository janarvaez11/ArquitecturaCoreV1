package com.banquito.core.aplicacion.cuentas.modelo;

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
@Table(name = "ExencionesCuentas")
public class ExencionCuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdExencion", nullable = false)
    private Integer idExencion;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Descripcion", length = 100, nullable = false)
    private String descripcion;

    //Relación con la tabla Comisiones
    @ManyToOne
    @JoinColumn(name = "IdComision", referencedColumnName = "IdComisionCargo", nullable = false)
    private ComisionCargo comision;

    //Constructores
    public ExencionCuenta() {
    }
    public ExencionCuenta(Integer idExencion) {
        this.idExencion = idExencion;
    }

    //Getters y Setters
    public Integer getIdExencion() {
        return idExencion;
    }
    public void setIdExencion(Integer idExencion) {
        this.idExencion = idExencion;
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
    public ComisionCargo getComision() {
        return comision;
    }
    public void setComision(ComisionCargo comision) {
        this.comision = comision;
    }

    //HashCode y Equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idExencion == null) ? 0 : idExencion.hashCode());
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
        ExencionCuenta other = (ExencionCuenta) obj;
        if (idExencion == null) {
            if (other.idExencion != null)
                return false;
        } else if (!idExencion.equals(other.idExencion))
            return false;
        return true;
    }

    //Método toString
    @Override
    public String toString() {
        return "ExencionCuenta [idExencion=" + idExencion + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", comision=" + comision + "]";
    }

}