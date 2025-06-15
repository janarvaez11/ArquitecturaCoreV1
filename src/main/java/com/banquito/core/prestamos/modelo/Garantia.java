package com.banquito.core.aplicacion.prestamos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Garantias")
public class Garantia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGarantia", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdTipoPrestamo", referencedColumnName = "IdTipoPrestamo")
    private TipoPrestamo tipoPrestamo;

    @Column(name = "Descripcion", length = 200, nullable = false)
    private String descripcion;

    @Column(name = "Valor", nullable = false)
    private Integer valor;

    @Column(name = "TipoGarantia", length = 50, nullable = false)
    private String tipoGarantia;

    public Garantia() {
    }

    public Garantia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoPrestamo getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(TipoPrestamo tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(String tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Garantia other = (Garantia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Garantia [id=" + id + ", tipoPrestamo=" + tipoPrestamo + ", descripcion=" + descripcion + ", valor="
                + valor + ", tipoGarantia=" + tipoGarantia + "]";
    }

}
