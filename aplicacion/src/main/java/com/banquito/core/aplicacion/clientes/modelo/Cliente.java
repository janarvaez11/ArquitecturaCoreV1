package com.banquito.core.aplicacion.clientes.modelo;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import com.banquito.core.aplicacion.general.modelo.Pais;
import com.banquito.core.aplicacion.general.modelo.Sucursal;

@Entity
@Table(name = "clientes")
public class Cliente {

    public enum TipoEntidad {
        PERSONA, EMPRESA
    }

    public enum TipoIdentificacion {
        CEDULA, PASAPORTE, RUC
    }

    public enum TipoCliente {
        PERSONA_NATURAL, PERSONA_JURIDICA
    }

    public enum Segmento {
        MASIVO, PREFERENCIAL, CORPORATIVO, EMPRESARIAL, PYMES, MICROFINANZAS
    }

    public enum CanalAfiliacion {
        PAGINA_WEB, AGENCIA, EXTERNO, CALL_CENTER
    }

    public enum Estado {
        ACTIVO, INACTIVO, SUSPENDIDO, BLOQUEADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entidad", nullable = false, length = 10)
    private TipoEntidad tipoEntidad;

    @Column(name = "id_entidad", nullable = false)
    private Integer idEntidad;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "nacionalidad", length = 2, nullable = false)
    private String nacionalidad;

    @ManyToOne
    @JoinColumn(name = "nacionalidad", referencedColumnName = "IdPais", insertable = false, updatable = false)
    private Pais paisNacionalidad;

    @Column(name = "id_sucursal", nullable = false)
    private Integer idSucursal;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificacion", length = 10, nullable = false)
    private TipoIdentificacion tipoIdentificacion;

    @Column(name = "numero_identificacion", length = 13, nullable = false)
    private String numeroIdentificacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", length = 20, nullable = false)
    private TipoCliente tipoCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "segmento", length = 20, nullable = false)
    private Segmento segmento;

    @Enumerated(EnumType.STRING)
    @Column(name = "canal_afiliacion", length = 15, nullable = false)
    private CanalAfiliacion canalAfiliacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion", nullable = false)
    private Date fechaActualizacion;

    @Column(name = "comentarios", length = 100, nullable = false)
    private String comentarios;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15, nullable = false)
    private Estado estado;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "id_sucursal", referencedColumnName = "IdSucursal", insertable = false, updatable = false)
    private Sucursal sucursal;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TelefonoCliente> telefonos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DireccionCliente> direcciones;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ContactoTransaccionCliente contacto;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public TipoEntidad getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(TipoEntidad tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Pais getPaisNacionalidad() {
        return paisNacionalidad;
    }

    public void setPaisNacionalidad(Pais paisNacionalidad) {
        this.paisNacionalidad = paisNacionalidad;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

    public CanalAfiliacion getCanalAfiliacion() {
        return canalAfiliacion;
    }

    public void setCanalAfiliacion(CanalAfiliacion canalAfiliacion) {
        this.canalAfiliacion = canalAfiliacion;
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<TelefonoCliente> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoCliente> telefonos) {
        this.telefonos = telefonos;
    }

    public List<DireccionCliente> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionCliente> direcciones) {
        this.direcciones = direcciones;
    }

    public ContactoTransaccionCliente getContacto() {
        return contacto;
    }

    public void setContacto(ContactoTransaccionCliente contacto) {
        this.contacto = contacto;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
        Cliente other = (Cliente) obj;
        if (idCliente == null) {
            if (other.idCliente != null)
                return false;
        } else if (!idCliente.equals(other.idCliente))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", tipoEntidad=" + tipoEntidad + ", idEntidad=" + idEntidad
                + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", idSucursal=" + idSucursal
                + ", tipoIdentificacion=" + tipoIdentificacion + ", numeroIdentificacion=" + numeroIdentificacion
                + ", tipoCliente=" + tipoCliente + ", segmento=" + segmento + ", canalAfiliacion=" + canalAfiliacion
                + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion
                + ", comentarios=" + comentarios + ", estado=" + estado + ", version=" + version + "]";
    }
}