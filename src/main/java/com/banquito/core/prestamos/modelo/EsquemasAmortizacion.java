package com.banquito.core.aplicacion.prestamos.modelo;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EsquemasAmortizacion")
public class EsquemasAmortizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EsquemasAmortizacion_id_gen")
    @Column(name = "IdEsquema", nullable = false)
    private Integer id;

    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "Descripcion", length = 200)
    private String descripcion;

    @Column(name = "PermiteGracia")
    private Boolean permiteGracia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTipoPrestamo")
    private TipoPrestamo tipoPrestamo;

    public EsquemasAmortizacion() {
    }

    public EsquemasAmortizacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPermiteGracia() {
        return permiteGracia;
    }

    public void setPermiteGracia(Boolean permiteGracia) {
        this.permiteGracia = permiteGracia;
    }

    public TipoPrestamo getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(TipoPrestamo tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        EsquemasAmortizacion that = (EsquemasAmortizacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EsquemasAmortizacion [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", permiteGracia=" + permiteGracia + ", tipoPrestamo=" + tipoPrestamo + "]";
    }

}
