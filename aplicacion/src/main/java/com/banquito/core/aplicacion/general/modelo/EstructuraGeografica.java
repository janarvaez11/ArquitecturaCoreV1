package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;
import java.util.List;



@Entity
@Table(name = "EstructuraGeografica")
public class EstructuraGeografica {

    @EmbeddedId
    private EstructuraGeograficaId id;

    @Column(name = "Nombre", length = 25, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "IdPais", referencedColumnName = "IdPais", insertable = false, updatable = false)
    private Pais pais;

    @OneToMany(mappedBy = "estructuraGeografica")
    private List<LocacionGeografica> locacionesGeograficas;


    public EstructuraGeografica() {
    }

    public EstructuraGeografica(EstructuraGeograficaId id) {
        this.id = id;
    }

    public EstructuraGeograficaId getId() {
        return id;
    }

    public void setId(EstructuraGeograficaId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<LocacionGeografica> getLocacionesGeograficas() {
        return locacionesGeograficas;
    }

    public void setLocacionesGeograficas(List<LocacionGeografica> locacionesGeograficas) {
        this.locacionesGeograficas = locacionesGeograficas;
    }

    
}