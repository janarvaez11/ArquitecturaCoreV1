package com.banquito.core.general.modelo;

import com.banquito.core.general.enums.EstadoGeneralEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "paises", schema = "public")
public class Paises {
    @Id
    @Column(name = "id_pais", nullable = false, length = 2)
    private String idPais;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "codigo_telefono", nullable = false, length = 4)
    private String codigoTelefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 15)
    private EstadoGeneralEnum estado;

    @ColumnDefault("0")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idPais")
    private Set<EstructurasGeografica> estructurasGeograficas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPais")
    private Set<Feriados> feriados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPais")
    private Set<Monedas> monedas = new LinkedHashSet<>();

    public Paises() {
    }

    public Paises(String idPais) {
        this.idPais = idPais;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoTelefono() {
        return codigoTelefono;
    }

    public void setCodigoTelefono(String codigoTelefono) {
        this.codigoTelefono = codigoTelefono;
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

    public Set<EstructurasGeografica> getEstructurasGeograficas() {
        return estructurasGeograficas;
    }

    public void setEstructurasGeograficas(Set<EstructurasGeografica> estructurasGeograficas) {
        this.estructurasGeograficas = estructurasGeograficas;
    }

    public Set<Feriados> getFeriados() {
        return feriados;
    }

    public void setFeriados(Set<Feriados> feriados) {
        this.feriados = feriados;
    }

    public Set<Monedas> getMonedas() {
        return monedas;
    }

    public void setMonedas(Set<Monedas> monedas) {
        this.monedas = monedas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Paises paises = (Paises) o;
        return Objects.equals(idPais, paises.idPais);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPais);
    }

    @Override
    public String toString() {
        return "Paises{" +
                "idPais='" + idPais + '\'' +
                ", nombre='" + nombre + '\'' +
                ", codigoTelefono='" + codigoTelefono + '\'' +
                ", estado=" + estado +
                ", version=" + version +
                ", estructurasGeograficas=" + estructurasGeograficas +
                ", feriados=" + feriados +
                ", monedas=" + monedas +
                '}';
    }
}