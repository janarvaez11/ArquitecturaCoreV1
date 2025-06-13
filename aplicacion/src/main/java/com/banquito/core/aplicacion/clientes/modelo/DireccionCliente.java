package com.banquito.core.aplicacion.clientes.modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "direccion_cliente")
public class DireccionCliente {

    public enum TipoDireccion {
        DOMICILIO, LABORAL
    }

    public enum Estado {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion", nullable = false)
    private Integer idDireccion;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 15, nullable = false)
    private TipoDireccion tipo;

    @Column(name = "linea1", length = 150, nullable = false)
    private String linea1;

    @Column(name = "linea2", length = 150)
    private String linea2;

    @Column(name = "codigo_postal", length = 6, nullable = false)
    private String codigoPostal;

    @Column(name = "codigo_geografico", length = 20, nullable = false)
    private String codigoGeografico;

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

    public DireccionCliente() {
    }

    public DireccionCliente(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
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

    public TipoDireccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoDireccion tipo) {
        this.tipo = tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getLinea1() {
        return linea1;
    }

    public void setLinea1(String linea1) {
        this.linea1 = linea1;
    }

    public String getLinea2() {
        return linea2;
    }

    public void setLinea2(String linea2) {
        this.linea2 = linea2;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoGeografico() {
        return codigoGeografico;
    }

    public void setCodigoGeografico(String codigoGeografico) {
        this.codigoGeografico = codigoGeografico;
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
        result = prime * result + ((idDireccion == null) ? 0 : idDireccion.hashCode());
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
        DireccionCliente other = (DireccionCliente) obj;
        if (idDireccion == null) {
            if (other.idDireccion != null)
                return false;
        } else if (!idDireccion.equals(other.idDireccion))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DireccionCliente [idDireccion=" + idDireccion + ", idCliente=" + idCliente + ", tipo=" + tipo 
                + ", linea1=" + linea1 + ", linea2=" + linea2 + ", codigoPostal=" + codigoPostal
                + ", codigoGeografico=" + codigoGeografico + ", fechaCreacion=" + fechaCreacion
                + ", fechaActualizacion=" + fechaActualizacion + ", estado=" + estado + ", version=" + version + "]";
    }
}
