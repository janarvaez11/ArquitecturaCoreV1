package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "monedas")
public class Monedas {
    @Id
    @Size(max = 3)
    @Column(name = "id_moneda", nullable = false, length = 3)
    private String idMoneda;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_pais")
    private com.banquito.core.aplicacion.general.modelo.Paises idPais;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 5)
    @NotNull
    @Column(name = "simbolo", nullable = false, length = 5)
    private String simbolo;

    @Size(max = 15)
    @NotNull
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @NotNull
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idMoneda")
    private Set<EntidadesBancariasMonedas> entidadesBancariasMonedas = new LinkedHashSet<>();

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public com.banquito.core.aplicacion.general.modelo.Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(com.banquito.core.aplicacion.general.modelo.Paises idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
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

    public Set<EntidadesBancariasMonedas> getEntidadesBancariasMonedas() {
        return entidadesBancariasMonedas;
    }

    public void setEntidadesBancariasMonedas(Set<EntidadesBancariasMonedas> entidadesBancariasMonedas) {
        this.entidadesBancariasMonedas = entidadesBancariasMonedas;
    }

}