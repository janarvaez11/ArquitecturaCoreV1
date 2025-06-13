package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Feriados")
public class Feriado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFeriado", nullable = false)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "FechaFeriado", nullable = false)
    private Date fechaFeriado;

    @ManyToOne
    @JoinColumn(name = "IdPais", referencedColumnName = "IdPais",nullable = false)
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "IdLocacion", referencedColumnName = "IdLocacion", nullable = true)
    private LocacionGeografica locacion;

    @Column(name = "Nombre", length = 25, nullable = false)
    private String nombre;

    @Column(name = "TipoFeriado", length = 3, nullable = false)
    private String tipoFeriado;

    @Version
    @Column(name = "Version", precision = 9, nullable = false)
    private Long version;

    public Feriado() {
    }

    public Feriado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaFeriado() {
        return fechaFeriado;
    }

    public void setFechaFeriado(Date fechaFeriado) {
        this.fechaFeriado = fechaFeriado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public LocacionGeografica getLocacion() {
        return locacion;
    }

    public void setLocacion(LocacionGeografica locacion) {
        this.locacion = locacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoFeriado() {
        return tipoFeriado;
    }

    public void setTipoFeriado(String tipoFeriado) {
        this.tipoFeriado = tipoFeriado;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
