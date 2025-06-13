package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class EstructuraGeograficaId implements Serializable {

    @Column(name = "IdPais", length = 2, nullable = false)
    private String paisId;

    @Column(name = "codigoNivel", length = 1, nullable = false)
    private Integer codigoNivel;

    public EstructuraGeograficaId() {
    }

    public EstructuraGeograficaId(String paisId, Integer codigoNivel) {
        this.paisId = paisId;
        this.codigoNivel = codigoNivel;
    }

    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisId) {
        this.paisId = paisId;
    }

    public Integer getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Integer codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((paisId == null) ? 0 : paisId.hashCode());
        result = prime * result + ((codigoNivel == null) ? 0 : codigoNivel.hashCode());
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
        EstructuraGeograficaId other = (EstructuraGeograficaId) obj;
        if (paisId == null) {
            if (other.paisId != null)
                return false;
        } else if (!paisId.equals(other.paisId))
            return false;
        if (codigoNivel == null) {
            if (other.codigoNivel != null)
                return false;
        } else if (!codigoNivel.equals(other.codigoNivel))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EstructuraGeograficaId [paisId=" + paisId + ", codigoNivel=" + codigoNivel + "]";
    }

    

}
