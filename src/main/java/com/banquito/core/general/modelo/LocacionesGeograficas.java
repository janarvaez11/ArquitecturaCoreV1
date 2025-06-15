package com.banquito.core.general.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "locaciones_geograficas", schema = "public")
public class LocacionesGeograficas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('locaciones_geograficas_id_locacion_seq')")
    @Column(name = "id_locacion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_locacion_padre")
    private LocacionesGeograficas idLocacionPadre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EstructurasGeografica estructurasGeograficas;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo_telefono_area", nullable = false, length = 3)
    private String codigoTelefonoArea;

    @Column(name = "codigo_geografico", nullable = false, length = 20)
    private String codigoGeografico;

    @Column(name = "codigo_postal", nullable = false, length = 6)
    private String codigoPostal;

    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @ColumnDefault("0")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idLocacion")
    private Set<Feriados> feriados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idLocacionPadre")
    private Set<LocacionesGeograficas> locacionesGeograficas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idLocacion")
    private Set<com.banquito.core.general.modelo.Sucursales> sucursales = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocacionesGeograficas getIdLocacionPadre() {
        return idLocacionPadre;
    }

    public void setIdLocacionPadre(LocacionesGeograficas idLocacionPadre) {
        this.idLocacionPadre = idLocacionPadre;
    }

    public EstructurasGeografica getEstructurasGeograficas() {
        return estructurasGeograficas;
    }

    public void setEstructurasGeograficas(EstructurasGeografica estructurasGeograficas) {
        this.estructurasGeograficas = estructurasGeograficas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoTelefonoArea() {
        return codigoTelefonoArea;
    }

    public void setCodigoTelefonoArea(String codigoTelefonoArea) {
        this.codigoTelefonoArea = codigoTelefonoArea;
    }

    public String getCodigoGeografico() {
        return codigoGeografico;
    }

    public void setCodigoGeografico(String codigoGeografico) {
        this.codigoGeografico = codigoGeografico;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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

    public Set<Feriados> getFeriados() {
        return feriados;
    }

    public void setFeriados(Set<Feriados> feriados) {
        this.feriados = feriados;
    }

    public Set<LocacionesGeograficas> getLocacionesGeograficas() {
        return locacionesGeograficas;
    }

    public void setLocacionesGeograficas(Set<LocacionesGeograficas> locacionesGeograficas) {
        this.locacionesGeograficas = locacionesGeograficas;
    }

    public Set<com.banquito.core.general.modelo.Sucursales> getSucursales() {
        return sucursales;
    }

    public void setSucursales(Set<com.banquito.core.general.modelo.Sucursales> sucursales) {
        this.sucursales = sucursales;
    }

}