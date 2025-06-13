package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "Paises")
public class Pais {

    @Id
    @Column(name = "IdPais", length = 2, nullable = false)
    private String id;

    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "CodigoTelefono", length = 4)
    private String codigoTelefono;

    @OneToMany(mappedBy = "pais")
    private List<EstructuraGeografica> estructurasGeograficas;

    @OneToMany(mappedBy = "pais")
    private List<LocacionGeografica> locacionesGeograficas;

    public Pais() {
    }

    public Pais(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoTelefono() {
        return codigoTelefono;
    }

    public void setCodigoTelefono(String codigoTelefono) {
        this.codigoTelefono = codigoTelefono;
    }

    public List<EstructuraGeografica> getEstructurasGeograficas() {
        return estructurasGeograficas;
    }

    public void setEstructurasGeograficas(List<EstructuraGeografica> estructurasGeograficas) {
        this.estructurasGeograficas = estructurasGeograficas;
    }

    public List<LocacionGeografica> getLocacionesGeograficas() {
        return locacionesGeograficas;
    }

    public void setLocacionesGeograficas(List<LocacionGeografica> locacionesGeograficas) {
        this.locacionesGeograficas = locacionesGeograficas;
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
        Pais other = (Pais) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pais [id=" + id + ", nombre=" + nombre + ", codigoTelefono=" + codigoTelefono
                + ", estructurasGeograficas=" + estructurasGeograficas + ", locacionesGeograficas="
                + locacionesGeograficas + "]";
    }

    
}