package com.banquito.core.aplicacion.cuentas.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ServiciosAsociados")
public class ServicioAsociado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa IDENTITY para campos SERIAL en PostgreSQL
    @Column(name = "IdServicio", nullable = false)
    private Integer idServicio;

    @Column(name = "Nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "Descripcion", length = 150, nullable = false)
    private String descripcion;

    @Column(name = "Estado", length = 30, nullable = false)
    private String estado;

    // Constructores
    public ServicioAsociado() {
    }

    public ServicioAsociado(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idServicio == null) ? 0 : idServicio.hashCode());
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
        ServicioAsociado other = (ServicioAsociado) obj;
        if (idServicio == null) {
            if (other.idServicio != null)
                return false;
        } else if (!idServicio.equals(other.idServicio))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ServicioAsociado [idServicio=" + idServicio + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", estado=" + estado + "]";
    }

    


    
    

    






}
