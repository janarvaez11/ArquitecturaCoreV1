package com.banquito.core.general.modelo;

import jakarta.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "entidades_bancarias_monedas")
public class EntidadesBancariasMonedas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('entidades_bancarias_monedas_id_entidad_bancaria_moneda_seq')")
    @Column(name = "id_entidad_bancaria_moneda", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_entidad_bancaria", nullable = false)
    private EntidadesBancarias idEntidadBancaria;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_moneda", nullable = false)
    private com.banquito.core.aplicacion.general.modelo.Monedas idMoneda;

    @Size(max = 15)
    @NotNull
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @NotNull
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EntidadesBancarias getIdEntidadBancaria() {
        return idEntidadBancaria;
    }

    public void setIdEntidadBancaria(EntidadesBancarias idEntidadBancaria) {
        this.idEntidadBancaria = idEntidadBancaria;
    }

    public com.banquito.core.aplicacion.general.modelo.Monedas getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(com.banquito.core.aplicacion.general.modelo.Monedas idMoneda) {
        this.idMoneda = idMoneda;
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

}