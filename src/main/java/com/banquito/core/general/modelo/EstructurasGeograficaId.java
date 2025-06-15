package com.banquito.core.general.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class EstructurasGeograficaId implements java.io.Serializable {
    private static final long serialVersionUID = 8406983717900472111L;
    @Column(name = "id_pais", nullable = false, length = 2)
    private String idPais;

    @Column(name = "codigo_nivel", nullable = false, precision = 1)
    private BigDecimal codigoNivel;

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public BigDecimal getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(BigDecimal codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EstructurasGeograficaId entity = (EstructurasGeograficaId) o;
        return Objects.equals(this.codigoNivel, entity.codigoNivel) &&
                Objects.equals(this.idPais, entity.idPais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoNivel, idPais);
    }

}