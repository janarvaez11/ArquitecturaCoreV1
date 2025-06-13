package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Monedas")
public class Moneda {

    @Id
    @Column(name = "IdMoneda", length = 3, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdPais", referencedColumnName = "IdPais", nullable = false)
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "IdEntidadBancaria", referencedColumnName = "IdEntidadBancaria", nullable = true)
    private EntidadBancaria entidadBancaria;

    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "Simbolo", length = 5, nullable = false)
    private String simbolo;

    @Column(name = "codigo", length = 3, nullable = false)
    private String codigo;

    @Version
    @Column(name = "version", precision = 9, nullable = false)
    private Long version;

    public Moneda() {
    }

    public Moneda(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public EntidadBancaria getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(EntidadBancaria entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
