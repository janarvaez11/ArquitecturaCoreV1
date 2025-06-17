package com.banquito.core.general.modelo;

import com.banquito.core.general.enums.EstadoGeneralEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "locaciones_geograficas")
public class LocacionesGeograficas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locacion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_locacion_padre")
    private LocacionesGeograficas idLocacionPadre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EstructurasGeograficas estructurasGeograficas;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo_telefono_area", nullable = false, length = 3)
    private String codigoTelefonoArea;

    @Column(name = "codigo_geografico", nullable = false, length = 20)
    private String codigoGeografico;

    @Column(name = "codigo_postal", nullable = false, length = 6)
    private String codigoPostal;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private EstadoGeneralEnum estado = EstadoGeneralEnum.ACTIVO;

    @Version
    @Column(name = "version", nullable = false, precision = 9)
    private Long version;

    @OneToMany(mappedBy = "idLocacion")
    private Set<Feriados> feriados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idLocacionPadre")
    private Set<LocacionesGeograficas> locacionesGeograficas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idLocacion")
    private Set<Sucursales> sucursales = new LinkedHashSet<>();

    public LocacionesGeograficas() {
    }

    public LocacionesGeograficas(Integer id) {
        this.id = id;
    }

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

    public EstructurasGeograficas getEstructurasGeograficas() {
        return estructurasGeograficas;
    }

    public void setEstructurasGeograficas(EstructurasGeograficas estructurasGeograficas) {
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

    public Set<Sucursales> getSucursales() {
        return sucursales;
    }

    public void setSucursales(Set<Sucursales> sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LocacionesGeograficas that = (LocacionesGeograficas) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LocacionesGeograficas{" +
                "id=" + id +
                ", idLocacionPadre=" + idLocacionPadre +
                ", estructurasGeograficas=" + estructurasGeograficas +
                ", nombre='" + nombre + '\'' +
                ", codigoTelefonoArea='" + codigoTelefonoArea + '\'' +
                ", codigoGeografico='" + codigoGeografico + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", estado=" + estado +
                ", version=" + version +
                ", feriados=" + feriados +
                ", locacionesGeograficas=" + locacionesGeograficas +
                ", sucursales=" + sucursales +
                '}';
    }
}