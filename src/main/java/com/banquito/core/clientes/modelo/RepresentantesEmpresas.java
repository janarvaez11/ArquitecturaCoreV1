package com.banquito.core.clientes.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "representantes_empresas", schema = "public")
public class RepresentantesEmpresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('representantes_empresas_id_representante_seq')")
    @Column(name = "id_representante", nullable = false)
    private Integer id;

    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresaFk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_empresa", nullable = false, insertable = false, updatable = false)
    private Empresas idEmpresa;

    @Column(name = "id_cliente", nullable = false)
    private Integer idClienteFk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_cliente", nullable = false, insertable = false, updatable = false)
    private Clientes idCliente;

    @Column(name = "rol", nullable = false, length = 25)
    private String rol;

    @Column(name = "fecha_asignacion", nullable = false)
    private Instant fechaAsignacion;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public RepresentantesEmpresas() {
    }

    public RepresentantesEmpresas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpresaFk() {
        return idEmpresaFk;
    }

    public void setIdEmpresaFk(Integer idEmpresaFk) {
        this.idEmpresaFk = idEmpresaFk;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdClienteFk() {
        return idClienteFk;
    }

    public void setIdClienteFk(Integer idClienteFk) {
        this.idClienteFk = idClienteFk;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RepresentantesEmpresas other = (RepresentantesEmpresas) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RepresentantesEmpresas [id=" + id + ", idEmpresaFk=" + idEmpresaFk + ", idClienteFk=" + idClienteFk 
                + ", rol=" + rol + ", fechaAsignacion=" + fechaAsignacion + ", estado=" + estado + ", version=" + version + "]";
    }
}