package com.banquito.core.clientes.modelo;

import com.banquito.core.general.modelo.Paises;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @Column(name = "tipo_entidad", nullable = false, length = 10)
    private String tipoEntidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_entidad", nullable = false)
    private Empresas idEntidad;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "nacionalidad", nullable = false)
    private Paises nacionalidad;

    @Column(name = "tipo_identificacion", nullable = false, length = 10)
    private String tipoIdentificacion;

    @Column(name = "numero_identificacion", nullable = false, length = 13)
    private String numeroIdentificacion;

    @Column(name = "tipo_cliente", nullable = false, length = 20)
    private String tipoCliente;

    @Column(name = "segmento", nullable = false, length = 20)
    private String segmento;

    @Column(name = "canal_afiliacion", nullable = false, length = 15)
    private String canalAfiliacion;

    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private Instant fechaActualizacion;

    @Column(name = "comentarios", length = 100)
    private String comentarios;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Version
    @Column(name = "version", nullable = false, precision = 9)
    private Long version;

    @OneToMany(mappedBy = "idCliente")
    private Set<ClientesSucursales> clientesSucursales = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<ContactosTransaccionalesClientes> contactosTransaccionalesClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<DireccionesClientes> direccionesClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<RepresentantesEmpresas> representantesEmpresas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<TelefonosClientes> telefonosClientes = new LinkedHashSet<>();

    public Clientes() {
    }
    public Clientes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public Empresas getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Empresas idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Paises getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Paises nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getCanalAfiliacion() {
        return canalAfiliacion;
    }

    public void setCanalAfiliacion(String canalAfiliacion) {
        this.canalAfiliacion = canalAfiliacion;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
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

    public Set<ClientesSucursales> getClientesSucursales() {
        return clientesSucursales;
    }

    public void setClientesSucursales(Set<ClientesSucursales> clientesSucursales) {
        this.clientesSucursales = clientesSucursales;
    }

    public Set<ContactosTransaccionalesClientes> getContactosTransaccionalesClientes() {
        return contactosTransaccionalesClientes;
    }

    public void setContactosTransaccionalesClientes(Set<ContactosTransaccionalesClientes> contactosTransaccionalesClientes) {
        this.contactosTransaccionalesClientes = contactosTransaccionalesClientes;
    }

    public Set<DireccionesClientes> getDireccionesClientes() {
        return direccionesClientes;
    }

    public void setDireccionesClientes(Set<DireccionesClientes> direccionesClientes) {
        this.direccionesClientes = direccionesClientes;
    }

    public Set<RepresentantesEmpresas> getRepresentantesEmpresas() {
        return representantesEmpresas;
    }

    public void setRepresentantesEmpresas(Set<RepresentantesEmpresas> representantesEmpresas) {
        this.representantesEmpresas = representantesEmpresas;
    }

    public Set<TelefonosClientes> getTelefonosClientes() {
        return telefonosClientes;
    }

    public void setTelefonosClientes(Set<TelefonosClientes> telefonosClientes) {
        this.telefonosClientes = telefonosClientes;
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
        Clientes other = (Clientes) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Clientes [id=" + id + ", tipoEntidad=" + tipoEntidad + ", idEntidad=" + idEntidad + ", nombre=" + nombre
                + ", nacionalidad=" + nacionalidad + ", tipoIdentificacion=" + tipoIdentificacion
                + ", numeroIdentificacion=" + numeroIdentificacion + ", tipoCliente=" + tipoCliente + ", segmento="
                + segmento + ", canalAfiliacion=" + canalAfiliacion + ", fechaCreacion=" + fechaCreacion
                + ", fechaActualizacion=" + fechaActualizacion + ", comentarios=" + comentarios + ", estado=" + estado
                + ", version=" + version + ", clientesSucursales=" + clientesSucursales
                + ", contactosTransaccionalesClientes=" + contactosTransaccionalesClientes + ", direccionesClientes="
                + direccionesClientes + ", representantesEmpresas=" + representantesEmpresas + ", telefonosClientes="
                + telefonosClientes + "]";
    }

    



    

}