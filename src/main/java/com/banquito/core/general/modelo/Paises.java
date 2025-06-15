package com.banquito.core.general.modelo;

import jakarta.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "paises")
public class Paises {
    @Id
    @Size(max = 2)
    @SequenceGenerator(name = "paises_id_gen", sequenceName = "clientes_sucursales_id_cliente_sucursal_seq", allocationSize = 1)
    @Column(name = "id_pais", nullable = false, length = 2)
    private String idPais;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 4)
    @NotNull
    @Column(name = "codigo_telefono", nullable = false, length = 4)
    private String codigoTelefono;

    @Size(max = 15)
    @NotNull
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idPais")
    private Set<EstructurasGeograficas> estructurasGeograficas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPais")
    private Set<Feriados> feriados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPais")
    private Set<Monedas> monedas = new LinkedHashSet<>();

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public Set<EstructurasGeograficas> getEstructurasGeograficas() {
        return estructurasGeograficas;
    }

    public void setEstructurasGeograficas(Set<EstructurasGeograficas> estructurasGeograficas) {
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

}