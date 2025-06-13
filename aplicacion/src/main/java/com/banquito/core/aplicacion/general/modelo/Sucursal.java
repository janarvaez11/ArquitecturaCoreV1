package com.banquito.core.aplicacion.general.modelo;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSucursal")
    private Integer idSucursal;

    @Column(name = "Codigo", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "ClaveUnica", length = 36, nullable = false)
    private String claveUnica;

    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "Estado", length = 3, nullable = false)
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "CorreoElectronico", length = 50, nullable = false)
    private String correoElectronico;

    @Column(name = "Telefono", length = 10, nullable = false)
    private String telefono;

    @Column(name = "Linea1", length = 100, nullable = false)
    private String linea1;

    @Column(name = "Linea2", length = 100, nullable = false)
    private String linea2;

    @Column(name = "Latitud", nullable = false)
    private BigDecimal latitud;

    @Column(name = "Longitud", nullable = false)
    private BigDecimal longitud;

    @ManyToOne
    @JoinColumn(name = "IdLocacion", nullable = false)
    private LocacionGeografica locacion;

    @ManyToOne
    @JoinColumn(name = "IdEntidadBancaria", nullable = false)
    private EntidadBancaria entidadBancaria;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClaveUnica() {
        return claveUnica;
    }

    public void setClaveUnica(String claveUnica) {
        this.claveUnica = claveUnica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public LocacionGeografica getLocacion() {
        return locacion;
    }

    public void setLocacion(LocacionGeografica locacion) {
        this.locacion = locacion;
    }

    public EntidadBancaria getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(EntidadBancaria entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idSucursal == null) ? 0 : idSucursal.hashCode());
        result = prime * result + ((locacion == null) ? 0 : locacion.hashCode());
        result = prime * result + ((entidadBancaria == null) ? 0 : entidadBancaria.hashCode());
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
        Sucursal other = (Sucursal) obj;
        if (idSucursal == null) {
            if (other.idSucursal != null)
                return false;
        } else if (!idSucursal.equals(other.idSucursal))
            return false;
        if (locacion == null) {
            if (other.locacion != null)
                return false;
        } else if (!locacion.equals(other.locacion))
            return false;
        if (entidadBancaria == null) {
            if (other.entidadBancaria != null)
                return false;
        } else if (!entidadBancaria.equals(other.entidadBancaria))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Sucursal [idSucursal=" + idSucursal + ", codigo=" + codigo + ", claveUnica=" + claveUnica + ", nombre="
                + nombre + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", correoElectronico="
                + correoElectronico + ", telefono=" + telefono + ", linea1=" + linea1 + ", linea2=" + linea2
                + ", latitud=" + latitud + ", longitud=" + longitud + ", locacion=" + locacion + ", entidadBancaria="
                + entidadBancaria + "]";
    }

    
}
