package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "comisiones_prestamos", schema = "public")
public class ComisionesPrestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('comisiones_prestamos_id_comision_prestamo_seq')")
    @Column(name = "id_comision_prestamo", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_tipo_comision", nullable = false)
    private com.banquito.core.prestamos.modelo.TiposComisiones idTipoComision;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_prestamo", nullable = false)
    private com.banquito.core.prestamos.modelo.Prestamos idPrestamo;

    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @ColumnDefault("1")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idComisionPrestamo")
    private Set<ComisionesPrestamoClientes> comisionesPrestamoClientes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.banquito.core.prestamos.modelo.TiposComisiones getIdTipoComision() {
        return idTipoComision;
    }

    public void setIdTipoComision(com.banquito.core.prestamos.modelo.TiposComisiones idTipoComision) {
        this.idTipoComision = idTipoComision;
    }

    public com.banquito.core.prestamos.modelo.Prestamos getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(com.banquito.core.prestamos.modelo.Prestamos idPrestamo) {
        this.idPrestamo = idPrestamo;
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

    public Set<ComisionesPrestamoClientes> getComisionesPrestamoClientes() {
        return comisionesPrestamoClientes;
    }

    public void setComisionesPrestamoClientes(Set<ComisionesPrestamoClientes> comisionesPrestamoClientes) {
        this.comisionesPrestamoClientes = comisionesPrestamoClientes;
    }

}