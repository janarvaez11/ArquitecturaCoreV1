package com.banquito.core.general.modelo;

import com.banquito.core.general.enums.EstadoGeneralEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "estructuras_geograficas")
public class EstructurasGeograficas {
    @EmbeddedId
    private EstructurasGeograficaId id;

    @MapsId("idPais")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pais", nullable = false)
    private Paises idPais;

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private EstadoGeneralEnum estado = EstadoGeneralEnum.ACTIVO;

    @Version
    @Column(name = "version", nullable = false, precision = 9)
    private Long version;

    @OneToMany
    private Set<LocacionesGeograficas> locacionesGeograficas = new LinkedHashSet<>();


    public EstructurasGeograficas() {
    }

    public EstructurasGeograficas(EstructurasGeograficaId id) {
        this.id = id;
    }

    public EstructurasGeograficaId getId() {
        return id;
    }

    public void setId(EstructurasGeograficaId id) {
        this.id = id;
    }

    public Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(Paises idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoGeneralEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneralEnum estado) {
        this.estado = estado;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Set<LocacionesGeograficas> getLocacionesGeograficas() {
        return locacionesGeograficas;
    }

    public void setLocacionesGeograficas(Set<LocacionesGeograficas> locacionesGeograficas) {
        this.locacionesGeograficas = locacionesGeograficas;
    }


    @Override
    public String toString() {
        return "EstructurasGeografica{" +
                "id=" + id +
                ", idPais=" + idPais +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                ", version=" + version +
                ", locacionesGeograficas=" + locacionesGeograficas +
                '}';
    }
}