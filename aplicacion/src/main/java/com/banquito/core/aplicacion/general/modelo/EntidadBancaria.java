package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "EntidadesBancarias")
public class EntidadBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEntidadBancaria", nullable = false)
    private Integer id;

    @Column(name = "CodigoLocal", length = 6, nullable = false)
    private String codigoLocal;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "CodigoInternacional", length = 20, nullable = false)
    private String codigoInternacional;

    public EntidadBancaria() {
    }

    public EntidadBancaria(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoInternacional() {
        return codigoInternacional;
    }

    public void setCodigoInternacional(String codigoInternacional) {
        this.codigoInternacional = codigoInternacional;
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
        EntidadBancaria other = (EntidadBancaria) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EntidadBancaria{" +
                "id=" + id +
                ", codigoLocal='" + codigoLocal + '\'' +
                ", nombre='" + nombre + '\'' +
                ", codigoInternacional='" + codigoInternacional + '\'' +
                '}';
    }
}


