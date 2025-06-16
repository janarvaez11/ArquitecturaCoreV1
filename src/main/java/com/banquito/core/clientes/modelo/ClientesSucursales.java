package com.banquito.core.clientes.modelo;

import com.banquito.core.general.modelo.Sucursales;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "clientes_sucursales", schema = "public")
public class ClientesSucursales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('clientes_sucursales_id_cliente_sucursal_seq')")
    @Column(name = "id_cliente_sucursal", nullable = false)
    private Integer id;

    @Column(name = "id_cliente", nullable = false)
    private Integer idClienteFk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false, insertable = false, updatable = false)
    private Clientes idCliente;

    @Column(name = "codigo_sucursal", nullable = false)
    private String codigoSucursalFk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigo_sucursal", nullable = false, insertable = false, updatable = false)
    private Sucursales codigoSucursal;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public ClientesSucursales() {
    }

    public ClientesSucursales(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCodigoSucursalFk() {
        return codigoSucursalFk;
    }

    public void setCodigoSucursalFk(String codigoSucursalFk) {
        this.codigoSucursalFk = codigoSucursalFk;
    }

    public Sucursales getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(Sucursales codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
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
        ClientesSucursales other = (ClientesSucursales) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ClientesSucursales [id=" + id + ", idClienteFk=" + idClienteFk + ", codigoSucursalFk=" + codigoSucursalFk 
                + ", estado=" + estado + ", version=" + version + "]";
    }
}