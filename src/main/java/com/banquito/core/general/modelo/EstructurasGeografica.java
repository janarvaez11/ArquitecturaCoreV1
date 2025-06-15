package com.banquito.core.general.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "estructuras_geograficas", schema = "public")
public class EstructurasGeografica {
    @EmbeddedId
    private EstructurasGeograficaId id;

    @MapsId("idPais")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pais", nullable = false)
    private com.banquito.core.general.modelo.Paises idPais;

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @ColumnDefault("0")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany
    private Set<com.banquito.core.general.modelo.LocacionesGeograficas> locacionesGeograficas = new LinkedHashSet<>();

    public EstructurasGeograficaId getId() {
        return id;
    }

    public void setId(EstructurasGeograficaId id) {
        this.id = id;
    }

    public com.banquito.core.general.modelo.Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(com.banquito.core.general.modelo.Paises idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Set<com.banquito.core.general.modelo.LocacionesGeograficas> getLocacionesGeograficas() {
        return locacionesGeograficas;
    }

    public void setLocacionesGeograficas(Set<com.banquito.core.general.modelo.LocacionesGeograficas> locacionesGeograficas) {
        this.locacionesGeograficas = locacionesGeograficas;
    }

}