package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "estructuras_geograficas")
public class EstructurasGeograficas {
    @EmbeddedId
    private EstructurasGeograficasId id;

    @MapsId("idPais")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pais", nullable = false)
    private com.banquito.core.aplicacion.general.modelo.Paises idPais;

    @Size(max = 25)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @Size(max = 15)
    @NotNull
    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "estructurasGeograficas")
    private Set<com.banquito.core.aplicacion.general.modelo.LocacionesGeograficas> locacionesGeograficas = new LinkedHashSet<>();

    public EstructurasGeograficasId getId() {
        return id;
    }

    public void setId(EstructurasGeograficasId id) {
        this.id = id;
    }

    public com.banquito.core.aplicacion.general.modelo.Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(com.banquito.core.aplicacion.general.modelo.Paises idPais) {
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

    public Set<com.banquito.core.aplicacion.general.modelo.LocacionesGeograficas> getLocacionesGeograficas() {
        return locacionesGeograficas;
    }

    public void setLocacionesGeograficas(Set<com.banquito.core.aplicacion.general.modelo.LocacionesGeograficas> locacionesGeograficas) {
        this.locacionesGeograficas = locacionesGeograficas;
    }

}