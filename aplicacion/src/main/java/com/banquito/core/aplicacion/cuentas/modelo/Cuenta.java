package com.banquito.core.aplicacion.cuentas.modelo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Cuentas")

public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCuenta", nullable = false)
    private Integer idCuenta;

    @Column(name = "CodigoCuenta", length = 20, nullable = false, unique = true)
    private String codigoCuenta;

    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "Descripcion", length = 50, nullable = false)
    private String descripcion;

    @Column(name = "Estado", length = 10, nullable = false)
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FechaModificacion", nullable = false)
    private Date fechaModificacion;

    // Relación con la tabla TasasInteres
    @ManyToOne
    @JoinColumn(name = "IdTasaInteres", referencedColumnName = "IdTasaInteres", nullable = false)
    private TasaInteres tasaInteres;

    // Relación con la tabla TipoCuenta
    @ManyToOne
    @JoinColumn(name = "IdTipoCuenta", referencedColumnName = "IdTipoCuenta", nullable = false)
    private TipoCuenta tipoCuenta;

    // Relación inversa con CuentasClientes
    @OneToMany(mappedBy = "cuenta")
    private List<CuentaCliente> cuentasClientes;

    // Relación inversa con ServicioTipoCuenta
    @OneToMany(mappedBy = "cuenta")
    private List<ServicioTipoCuenta> servicioTipoCuentas;

    // Constructores
    public Cuenta() {
    }

    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    // Getters y Setters
    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public TasaInteres getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(TasaInteres tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public List<CuentaCliente> getCuentasClientes() {
        return cuentasClientes;
    }

    public void setCuentasClientes(List<CuentaCliente> cuentasClientes) {
        this.cuentasClientes = cuentasClientes;
    }

    public List<ServicioTipoCuenta> getServicioTipoCuentas() {
        return servicioTipoCuentas;
    }

    public void setServicioTipoCuentas(List<ServicioTipoCuenta> servicioTipoCuentas) {
        this.servicioTipoCuentas = servicioTipoCuentas;
    }

    // Metodo hashCode y equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCuenta == null) ? 0 : idCuenta.hashCode());
        result = prime * result + ((codigoCuenta == null) ? 0 : codigoCuenta.hashCode());
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
        Cuenta other = (Cuenta) obj;
        if (idCuenta == null) {
            if (other.idCuenta != null)
                return false;
        } else if (!idCuenta.equals(other.idCuenta))
            return false;
        if (codigoCuenta == null) {
            if (other.codigoCuenta != null)
                return false;
        } else if (!codigoCuenta.equals(other.codigoCuenta))
            return false;
        return true;
    }

    // Método toString
    @Override
    public String toString() {
        return "Cuenta [idCuenta=" + idCuenta + ", codigoCuenta=" + codigoCuenta + ", nombre=" + nombre
                + ", descripcion=" + descripcion + ", estado="
                + estado + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion
                + ", tasaInteres=" + tasaInteres + ", tipoCuenta=" + tipoCuenta + "]";
    }

}