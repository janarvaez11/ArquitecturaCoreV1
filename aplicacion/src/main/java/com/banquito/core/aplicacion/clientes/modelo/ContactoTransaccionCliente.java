package com.banquito.core.aplicacion.clientes.modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contacto_transaccion_cliente")
public class ContactoTransaccionCliente {

    public enum Estado {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto_transaccion", nullable = false)
    private Integer idContactoTransaccion;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "telefono", length = 10, nullable = false)
    private String telefono;

    @Column(name = "correo_electronico", length = 40, nullable = false)
    private String correoElectronico;

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

    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    public ContactoTransaccionCliente() {
    }

    public ContactoTransaccionCliente(Integer idContactoTransaccion) {
        this.idContactoTransaccion = idContactoTransaccion;
    }

    public Integer getIdContactoTransaccion() {
        return idContactoTransaccion;
    }

    public void setIdContactoTransaccion(Integer idContactoTransaccion) {
        this.idContactoTransaccion = idContactoTransaccion;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
        result = prime * result
                + ((idContactoTransaccion == null) ? 0 : idContactoTransaccion.hashCode());
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
        ContactoTransaccionCliente other = (ContactoTransaccionCliente) obj;
        if (idContactoTransaccion == null) {
            if (other.idContactoTransaccion != null)
                return false;
        } else if (!idContactoTransaccion.equals(other.idContactoTransaccion))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ContactoTransaccionCliente [idContactoTransaccion=" + idContactoTransaccion + ", idCliente="
                + idCliente + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + ", fechaCreacion="
                + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + ", estado=" + estado + ", version="
                + version + "]";
    }
}

