package com.banquito.core.general.modelo;

import com.banquito.core.general.enums.EstadoGeneralEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "entidades_bancarias", schema = "public")
public class EntidadesBancarias {
    @Id
    @Column(name = "id_entidad_bancaria", nullable = false)
    private Integer id;

    @Column(name = "codigo_local", nullable = false, length = 6)
    private String codigoLocal;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo_internacional", nullable = false, length = 20)
    private String codigoInternacional;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 15)
    private EstadoGeneralEnum estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idEntidadBancaria")
    private Set<com.banquito.core.general.modelo.EntidadesBancariasMonedas> entidadesBancariasMonedas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEntidadBancaria")
    private Set<com.banquito.core.general.modelo.Sucursales> sucursales = new LinkedHashSet<>();

    public EntidadesBancarias() {
    }

    public EntidadesBancarias(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoInternacional() {
        return codigoInternacional;
    }

    public void setCodigoInternacional(String codigoInternacional) {
        this.codigoInternacional = codigoInternacional;
    }

    public EstadoGeneralEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneralEnum estado) {
        this.estado = estado;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public Set<com.banquito.core.general.modelo.EntidadesBancariasMonedas> getEntidadesBancariasMonedas() {
        return entidadesBancariasMonedas;
    }

    public void setEntidadesBancariasMonedas(Set<com.banquito.core.general.modelo.EntidadesBancariasMonedas> entidadesBancariasMonedas) {
        this.entidadesBancariasMonedas = entidadesBancariasMonedas;
    }

    public Set<com.banquito.core.general.modelo.Sucursales> getSucursales() {
        return sucursales;
    }

    public void setSucursales(Set<com.banquito.core.general.modelo.Sucursales> sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EntidadesBancarias that = (EntidadesBancarias) o;
        return Objects.equals(id, that.id);
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
        return "EntidadesBancarias{" +
                "id=" + id +
                ", codigoLocal='" + codigoLocal + '\'' +
                ", nombre='" + nombre + '\'' +
                ", codigoInternacional='" + codigoInternacional + '\'' +
                ", estado=" + estado +
                ", version=" + version +
                ", entidadesBancariasMonedas=" + entidadesBancariasMonedas +
                ", sucursales=" + sucursales +
                '}';
    }


}