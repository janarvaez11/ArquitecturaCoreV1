package com.banquito.core.cuentas.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "comisiones_cargos_cuentas", schema = "public")
public class ComisionesCargosCuentas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('comisiones_cargos_cuentas_id_comision_cargo_cuenta_seq')")
    @Column(name = "id_comision_cargo_cuenta", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_cuenta", nullable = false)
    private com.banquito.core.cuentas.modelo.Cuentas idCuenta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_comision_cargo", nullable = false)
    private ComisionesCargos idComisionCargo;

    @Column(name = "fecha_asignacion", nullable = false)
    private Instant fechaAsignacion;

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

    public com.banquito.core.cuentas.modelo.Cuentas getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(com.banquito.core.cuentas.modelo.Cuentas idCuenta) {
        this.idCuenta = idCuenta;
    }

    public ComisionesCargos getIdComisionCargo() {
        return idComisionCargo;
    }

    public void setIdComisionCargo(ComisionesCargos idComisionCargo) {
        this.idComisionCargo = idComisionCargo;
    }

    public Instant getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Instant fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
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