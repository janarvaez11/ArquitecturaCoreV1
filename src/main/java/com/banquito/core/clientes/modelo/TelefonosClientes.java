package com.banquito.core.clientes.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "telefonos_clientes", schema = "public")
public class TelefonosClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('telefonos_clientes_id_telefono_seq')")
    @Column(name = "id_telefono", nullable = false)
    private Integer id;

    @Column(name = "id_cliente", nullable = false)
    private Integer idClienteFk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false, insertable = false, updatable = false)
    private Clientes idCliente;

    @Column(name = "tipo", nullable = false, length = 10)
    private String tipo;

    @Column(name = "numero_telefono", nullable = false, length = 10)
    private String numeroTelefono;

    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @Column(name = "fechaactualizacion", nullable = false)
    private Instant fechaactualizacion;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public TelefonosClientes() {
    }

    public TelefonosClientes(Integer id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Instant fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
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
        TelefonosClientes other = (TelefonosClientes) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TelefonosClientes [id=" + id + ", idClienteFk=" + idClienteFk + ", tipo=" + tipo 
                + ", numeroTelefono=" + numeroTelefono + ", fechaCreacion=" + fechaCreacion 
                + ", fechaactualizacion=" + fechaactualizacion + ", estado=" + estado + ", version=" + version + "]";
    }
}