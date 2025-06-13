package com.banquito.core.aplicacion.prestamos.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CondicionComisiones")
public class CondicionComision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCondicion", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdComisionPrestamo", referencedColumnName = "idComisionPrestamo", nullable = false)
    private ComisionPrestamo comisionPrestamo;

    @Column(name = "TipoCondicion", length = 30)
    private String tipoCondicion;

    @Column(name = "Valor", precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "ValorTexto", length = 30)
    private String valorTexto;

    public CondicionComision() {
    }

    public CondicionComision(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ComisionPrestamo getComisionPrestamo() {
        return comisionPrestamo;
    }

    public void setComisionPrestamo(ComisionPrestamo comisionPrestamo) {
        this.comisionPrestamo = comisionPrestamo;
    }

    public String getTipoCondicion() {
        return tipoCondicion;
    }

    public void setTipoCondicion(String tipoCondicion) {
        this.tipoCondicion = tipoCondicion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(String valorTexto) {
        this.valorTexto = valorTexto;
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
        CondicionComision other = (CondicionComision) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CondicionComisiones [id=" + id + ", tipoCondicion=" + tipoCondicion + ", valor=" + valor + "]";
    }

} 