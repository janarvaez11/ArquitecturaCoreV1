package com.banquito.core.general.modelo;

import com.banquito.core.general.enums.EstadoGeneralEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "entidades_bancarias_monedas")
public class EntidadesBancariasMonedas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad_bancaria_moneda", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_entidad_bancaria", nullable = false)
    private EntidadesBancarias idEntidadBancaria;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_moneda", nullable = false)
    private com.banquito.core.general.modelo.Monedas idMoneda;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 15)
    private EstadoGeneralEnum estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public EntidadesBancariasMonedas() {
    }

    public EntidadesBancariasMonedas(Integer id) {
        this.id = id;
    }

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

    public com.banquito.core.general.modelo.Monedas getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(com.banquito.core.general.modelo.Monedas idMoneda) {
        this.idMoneda = idMoneda;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EntidadesBancariasMonedas that = (EntidadesBancariasMonedas) o;
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
        return "EntidadesBancariasMonedas{" +
                "id=" + id +
                ", idEntidadBancaria=" + idEntidadBancaria +
                ", idMoneda=" + idMoneda +
                ", estado=" + estado +
                ", version=" + version +
                '}';
    }
}