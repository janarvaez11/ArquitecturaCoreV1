package com.banquito.core.aplicacion.clientes.modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "telefono_cliente")
public class TelefonoCliente {

    public enum TipoTelefono {
        CELULAR, RESIDENCIA, LABORAL
    }

    public enum Estado {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefono", nullable = false)
    private Integer idTelefono;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 10, nullable = false)
    private TipoTelefono tipo;

    @Column(name = "numero_telefono", length = 10, nullable = false)
    private String numeroTelefono;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion", nullable = false)
    private Date fechaActualizacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15, nullable = false)
    private Estado estado;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    public TelefonoCliente() {
    }

    public TelefonoCliente(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public Integer getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoTelefono getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefono tipo) {
        this.tipo = tipo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTelefono == null) ? 0 : idTelefono.hashCode());
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
        TelefonoCliente other = (TelefonoCliente) obj;
        if (idTelefono == null) {
            if (other.idTelefono != null)
                return false;
        } else if (!idTelefono.equals(other.idTelefono))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TelefonoCliente [idTelefono=" + idTelefono + ", idCliente=" + idCliente + ", tipo=" + tipo 
                + ", numeroTelefono=" + numeroTelefono + ", fechaCreacion=" + fechaCreacion 
                + ", fechaActualizacion=" + fechaActualizacion + ", estado=" + estado + ", version=" + version + "]";
    }
}
