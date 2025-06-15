package com.banquito.core.cuentas.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cuentas", schema = "public")
public class Cuentas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('cuentas_id_cuenta_seq')")
    @Column(name = "id_cuenta", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_tipo_cuenta", nullable = false)
    private com.banquito.core.cuentas.modelo.TiposCuentas idTipoCuenta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_tasa_interes", nullable = false)
    private com.banquito.core.cuentas.modelo.TasasIntereses idTasaInteres;

    @Column(name = "codigo_cuenta", nullable = false, length = 20)
    private String codigoCuenta;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 150)
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @Column(name = "fecha_modificacion", nullable = false)
    private Instant fechaModificacion;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idCuenta")
    private Set<ComisionesCargosCuentas> comisionesCargosCuentas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCuenta")
    private Set<com.banquito.core.cuentas.modelo.CuentasClientes> cuentasClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCuenta")
    private Set<com.banquito.core.cuentas.modelo.ServiciosAsociadosCuentas> serviciosAsociadosCuentas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.banquito.core.cuentas.modelo.TiposCuentas getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(com.banquito.core.cuentas.modelo.TiposCuentas idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public com.banquito.core.cuentas.modelo.TasasIntereses getIdTasaInteres() {
        return idTasaInteres;
    }

    public void setIdTasaInteres(com.banquito.core.cuentas.modelo.TasasIntereses idTasaInteres) {
        this.idTasaInteres = idTasaInteres;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
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

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Instant fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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

    public Set<ComisionesCargosCuentas> getComisionesCargosCuentas() {
        return comisionesCargosCuentas;
    }

    public void setComisionesCargosCuentas(Set<ComisionesCargosCuentas> comisionesCargosCuentas) {
        this.comisionesCargosCuentas = comisionesCargosCuentas;
    }

    public Set<com.banquito.core.cuentas.modelo.CuentasClientes> getCuentasClientes() {
        return cuentasClientes;
    }

    public void setCuentasClientes(Set<com.banquito.core.cuentas.modelo.CuentasClientes> cuentasClientes) {
        this.cuentasClientes = cuentasClientes;
    }

    public Set<com.banquito.core.cuentas.modelo.ServiciosAsociadosCuentas> getServiciosAsociadosCuentas() {
        return serviciosAsociadosCuentas;
    }

    public void setServiciosAsociadosCuentas(Set<com.banquito.core.cuentas.modelo.ServiciosAsociadosCuentas> serviciosAsociadosCuentas) {
        this.serviciosAsociadosCuentas = serviciosAsociadosCuentas;
    }

}