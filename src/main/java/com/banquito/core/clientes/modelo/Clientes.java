package com.banquito.core.clientes.modelo;

import com.banquito.core.general.modelo.Paises;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "clientes", schema = "public")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('clientes_id_cliente_seq')")
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @Column(name = "tipo_entidad", nullable = false, length = 10)
    private String tipoEntidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_entidad", nullable = false)
    private com.banquito.core.clientes.modelo.Empresas idEntidad;

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

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private Instant fechaActualizacion;

    @Column(name = "comentarios", length = 100)
    private String comentarios;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idCliente")
    private Set<com.banquito.core.clientes.modelo.ClientesSucursales> clientesSucursales = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<com.banquito.core.clientes.modelo.ContactosTransaccionalesClientes> contactosTransaccionalesClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<com.banquito.core.clientes.modelo.DireccionesClientes> direccionesClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<com.banquito.core.clientes.modelo.RepresentantesEmpresas> representantesEmpresas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<com.banquito.core.clientes.modelo.TelefonosClientes> telefonosClientes = new LinkedHashSet<>();

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

    public com.banquito.core.clientes.modelo.Empresas getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(com.banquito.core.clientes.modelo.Empresas idEntidad) {
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

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public Set<com.banquito.core.clientes.modelo.ClientesSucursales> getClientesSucursales() {
        return clientesSucursales;
    }

    public void setClientesSucursales(Set<com.banquito.core.clientes.modelo.ClientesSucursales> clientesSucursales) {
        this.clientesSucursales = clientesSucursales;
    }

    public Set<com.banquito.core.clientes.modelo.ContactosTransaccionalesClientes> getContactosTransaccionalesClientes() {
        return contactosTransaccionalesClientes;
    }

    public void setContactosTransaccionalesClientes(Set<com.banquito.core.clientes.modelo.ContactosTransaccionalesClientes> contactosTransaccionalesClientes) {
        this.contactosTransaccionalesClientes = contactosTransaccionalesClientes;
    }

    public Set<com.banquito.core.clientes.modelo.DireccionesClientes> getDireccionesClientes() {
        return direccionesClientes;
    }

    public void setDireccionesClientes(Set<com.banquito.core.clientes.modelo.DireccionesClientes> direccionesClientes) {
        this.direccionesClientes = direccionesClientes;
    }

    public Set<com.banquito.core.clientes.modelo.RepresentantesEmpresas> getRepresentantesEmpresas() {
        return representantesEmpresas;
    }

    public void setRepresentantesEmpresas(Set<com.banquito.core.clientes.modelo.RepresentantesEmpresas> representantesEmpresas) {
        this.representantesEmpresas = representantesEmpresas;
    }

    public Set<com.banquito.core.clientes.modelo.TelefonosClientes> getTelefonosClientes() {
        return telefonosClientes;
    }

    public void setTelefonosClientes(Set<com.banquito.core.clientes.modelo.TelefonosClientes> telefonosClientes) {
        this.telefonosClientes = telefonosClientes;
    }

}