package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;
import java.util.List;



@Entity
@Table(name = "LocacionGeografica")
public class LocacionGeografica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLocacion")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdLocacionPadreId", referencedColumnName = "IdLocacion")
    private LocacionGeografica locacionPadre;


    @ManyToOne
    @JoinColumn(name = "IdPais", referencedColumnName = "IdPais")
    private Pais pais;

    @Column(name = "CodigoNivel")
    private Integer codigoNivel;

    @Column(name = "Nombre", length = 25, nullable = false)
    private String nombre;

    @Column(name = "CodigoTelefonoArea", length = 3)
    private String codigoTelefonoArea;

    @Column(name = "CodigoPostal", length = 10)
    private String codigoPostal;

    @Column(name = "Estado", length = 3, nullable = false)
    private String estado;

    @OneToMany(mappedBy = "locacionPadre")
    private List<LocacionGeografica> locacionesHijas;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "IdPais", referencedColumnName = "IdPais", insertable = false, updatable = false),
            @JoinColumn(name = "CodigoNivel", referencedColumnName = "CodigoNivel", insertable = false, updatable = false)
    })
    private EstructuraGeografica estructuraGeografica;

    public LocacionGeografica() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocacionGeografica getLocacionPadre() {
        return locacionPadre;
    }

    public void setLocacionPadre(LocacionGeografica locacionPadre) {
        this.locacionPadre = locacionPadre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Integer getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Integer codigoNivel) {
        this.codigoNivel = codigoNivel;
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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public List<LocacionGeografica> getLocacionesHijas() {
        return locacionesHijas;
    }

    public void setLocacionesHijas(List<LocacionGeografica> locacionesHijas) {
        this.locacionesHijas = locacionesHijas;
    }

    public EstructuraGeografica getEstructuraGeografica() {
        return estructuraGeografica;
    }

    public void setEstructuraGeografica(EstructuraGeografica estructuraGeografica) {
        this.estructuraGeografica = estructuraGeografica;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((locacionPadre == null) ? 0 : locacionPadre.hashCode());
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
        LocacionGeografica other = (LocacionGeografica) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (locacionPadre == null) {
            if (other.locacionPadre != null)
                return false;
        } else if (!locacionPadre.equals(other.locacionPadre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LocacionGeografica [id=" + id + ", locacionPadre=" + locacionPadre + ", pais=" + pais + ", codigoNivel="
                + codigoNivel + ", nombre=" + nombre + ", codigoTelefonoArea=" + codigoTelefonoArea + ", codigoPostal="
                + codigoPostal + ", locacionesHijas=" + locacionesHijas + ", estructuraGeografica="
                + estructuraGeografica + "]";
    }

}