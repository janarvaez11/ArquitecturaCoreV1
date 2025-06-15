package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "esquemas_amortizacion", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "ak_unique_nombre_esquema", columnNames = {"nombre"})
})
public class EsquemasAmortizaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('esquemas_amortizacion_id_esquema_seq')")
    @Column(name = "id_esquema", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_tipo_prestamo")
    private com.banquito.core.prestamos.modelo.TiposPrestamos idTipoPrestamo;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "permite_gracia", nullable = false)
    private Boolean permiteGracia = false;

    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.banquito.core.prestamos.modelo.TiposPrestamos getIdTipoPrestamo() {
        return idTipoPrestamo;
    }

    public void setIdTipoPrestamo(com.banquito.core.prestamos.modelo.TiposPrestamos idTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPermiteGracia() {
        return permiteGracia;
    }

    public void setPermiteGracia(Boolean permiteGracia) {
        this.permiteGracia = permiteGracia;
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