package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "feriados")
public class Feriados {
    @Id
    @ColumnDefault("nextval('feriados_id_feriado_seq')")
    @Column(name = "id_feriado", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais")
    private com.banquito.core.aplicacion.general.modelo.Paises idPais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_locacion")
    private com.banquito.core.aplicacion.general.modelo.LocacionesGeograficas idLocacion;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 15)
    @NotNull
    @Column(name = "tipo", nullable = false, length = 15)
    private String tipo;

    @Size(max = 15)
    @NotNull
    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public com.banquito.core.aplicacion.general.modelo.Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(com.banquito.core.aplicacion.general.modelo.Paises idPais) {
        this.idPais = idPais;
    }

    public com.banquito.core.aplicacion.general.modelo.LocacionesGeograficas getIdLocacion() {
        return idLocacion;
    }

    public void setIdLocacion(com.banquito.core.aplicacion.general.modelo.LocacionesGeograficas idLocacion) {
        this.idLocacion = idLocacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

}