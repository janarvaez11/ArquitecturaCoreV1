package com.banquito.core.aplicacion.clientes.modelo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="representantes")
public class Representante {

    public enum Rol {
        REPRESENTANTE_LEGAL, FIRMA_AUTORIZADA, ADMINISTRADOR, OPERADOR
    }

    public enum Estado {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_representante", nullable = false)
    private Integer idRepresentante;

    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", length = 25, nullable = false)
    private Rol rol;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_asignacion", nullable = false)
    private Date fechaAsignacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15, nullable = false)
    private Estado estado;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    public Representante() {}

    public Representante(Integer idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public Integer getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(Integer idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Representante that = (Representante) o;
        return Objects.equals(idRepresentante, that.idRepresentante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepresentante);
    }

    @Override
    public String toString() {
        return "Representante{" +
                "idRepresentante=" + idRepresentante +
                ", idEmpresa=" + idEmpresa +
                ", idCliente=" + idCliente +
                ", rol=" + rol +
                ", fechaAsignacion=" + fechaAsignacion +
                ", estado=" + estado +
                ", version=" + version +
                '}';
    }
}
