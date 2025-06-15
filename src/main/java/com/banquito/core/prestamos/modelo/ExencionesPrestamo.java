package com.banquito.core.aplicacion.prestamos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ExencionesPrestamos")
public class ExencionesPrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdExencion", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdComisionPrestamo", referencedColumnName = "idComisionPrestamo")
    private ComisionPrestamo idComisionPrestamo;

    @Column(name = "TipoExencion", length = 30, nullable = false)
    private String tipoExencion;

    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "Descripcion", length = 100, nullable = false)
    private String descripcion;

    public ExencionesPrestamo() {
    }

    public ExencionesPrestamo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ComisionPrestamo getIdComisionPrestamo() {
        return idComisionPrestamo;
    }

    public void setIdComisionPrestamo(ComisionPrestamo idComisionPrestamo) {
        this.idComisionPrestamo = idComisionPrestamo;
    }

    public String getTipoExencion() {
        return tipoExencion;
    }

    public void setTipoExencion(String tipoExencion) {
        this.tipoExencion = tipoExencion;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        ExencionesPrestamo other = (ExencionesPrestamo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ExencionesPrestamo [id=" + id + ", idComisionPrestamo=" + idComisionPrestamo + ", tipoExencion="
                + tipoExencion + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }

}
