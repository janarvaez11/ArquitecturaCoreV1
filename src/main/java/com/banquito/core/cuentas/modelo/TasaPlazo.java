package com.banquito.core.aplicacion.cuentas.modelo;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "TasaPlazos")
public class TasaPlazo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPlazo", nullable = false)
    private Integer idPlazo;

    @Column(name = "PlazoMinimo", nullable = false)
    private Integer plazoMinimo;

    @Column(name = "PlazoMaximo", nullable = false)
    private Integer plazoMaximo;

    @Column(name = "Tasa", precision = 5, scale = 2, nullable = false)
    private BigDecimal tasa;

    @ManyToOne
    @JoinColumn(name = "IdTasaInteres", referencedColumnName = "IdTasaInteres", nullable = false)
    private TasaInteres tasaInteres;

    @Version
    @Column(name = "Version")
    private Long version;

    // Constructores
    protected TasaPlazo() {
        // Constructor protegido para JPA
    }

    public TasaPlazo(Integer idPlazo) {
        this.idPlazo = idPlazo;
    }

    // Getters y Setters

    public Integer getIdPlazo() {
        return idPlazo;
    }

    public void setIdPlazo(Integer idPlazo) {
        this.idPlazo = idPlazo;
    }

    public Integer getPlazoMinimo() {
        return plazoMinimo;
    }

    public void setPlazoMinimo(Integer plazoMinimo) {
        this.plazoMinimo = plazoMinimo;
    }

    public Integer getPlazoMaximo() {
        return plazoMaximo;
    }

    public void setPlazoMaximo(Integer plazoMaximo) {
        this.plazoMaximo = plazoMaximo;
    }

    public BigDecimal getTasa() {
        return tasa;
    }

    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
    }

    public TasaInteres getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(TasaInteres tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Long getVersion() {
        return version;
    }

    protected void setVersion(Long version) {
        this.version = version;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPlazo == null) ? 0 : idPlazo.hashCode());
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
        TasaPlazo other = (TasaPlazo) obj;
        if (idPlazo == null) {
            if (other.idPlazo != null)
                return false;
        } else if (!idPlazo.equals(other.idPlazo))
            return false;
        return true;
    }

    // Método toString
    @Override
    public String toString() {
        return "TasaPlazo [idPlazo=" + idPlazo + ", plazoMinimo=" + plazoMinimo + ", plazoMaximo=" + plazoMaximo
                + ", tasa=" + tasa + ", tasaInteres=" + tasaInteres + ", version=" + version + "]";
    }
}