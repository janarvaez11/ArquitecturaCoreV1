package com.banquito.core.general.modelo;

import com.banquito.core.general.enums.EstadoGeneralEnum;
import com.banquito.core.general.enums.TipoFeriadosEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "feriados")
public class Feriados {
    @Id
    @Column(name = "id_feriado", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais")
    private Paises idPais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_locacion")
    private LocacionesGeograficas idLocacion;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 15)
    private TipoFeriadosEnum tipo;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private EstadoGeneralEnum estado = EstadoGeneralEnum.ACTIVO;

    @Version
    @Column(name = "version", nullable = false, precision = 9)
    private Long version;

    public Feriados() {
    }

    public Feriados(Integer id) {
        this.id = id;
    }

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

    public Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(Paises idPais) {
        this.idPais = idPais;
    }

    public LocacionesGeograficas getIdLocacion() {
        return idLocacion;
    }

    public void setIdLocacion(LocacionesGeograficas idLocacion) {
        this.idLocacion = idLocacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoFeriadosEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoFeriadosEnum tipo) {
        this.tipo = tipo;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Feriados feriados = (Feriados) o;
        return Objects.equals(id, feriados.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Feriados{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", idPais=" + idPais +
                ", idLocacion=" + idLocacion +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", estado=" + estado +
                ", version=" + version +
                '}';
    }
}