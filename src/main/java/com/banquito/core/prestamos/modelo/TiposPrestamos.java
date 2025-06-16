package com.banquito.core.prestamos.modelo;

import com.banquito.core.general.modelo.Monedas;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipos_prestamos")
public class TiposPrestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_prestamo", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_moneda", referencedColumnName = "id_moneda", nullable = false)
    private Monedas idMoneda;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "requisitos", nullable = false, length = 300)
    private String requisitos;

    @Column(name = "tipo_cliente", nullable = false, length = 15)
    private String tipoCliente;

    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @Column(name = "fecha_modificacion", nullable = false)
    private Instant fechaModificacion;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idTipoPrestamo")
    private Set<EsquemasAmortizaciones> esquemasAmortizacions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTipoPrestamo")
    private Set<GarantiasTiposPrestamos> garantiasTiposPrestamos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTipoPrestamo")
    private Set<Prestamos> prestamos = new LinkedHashSet<>();

    public TiposPrestamos() {
    }

    public TiposPrestamos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Monedas getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Monedas idMoneda) {
        this.idMoneda = idMoneda;
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

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Instant fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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

    public Set<EsquemasAmortizaciones> getEsquemasAmortizacions() {
        return esquemasAmortizacions;
    }

    public void setEsquemasAmortizacions(Set<EsquemasAmortizaciones> esquemasAmortizacions) {
        this.esquemasAmortizacions = esquemasAmortizacions;
    }

    public Set<GarantiasTiposPrestamos> getGarantiasTiposPrestamos() {
        return garantiasTiposPrestamos;
    }

    public void setGarantiasTiposPrestamos(Set<GarantiasTiposPrestamos> garantiasTiposPrestamos) {
        this.garantiasTiposPrestamos = garantiasTiposPrestamos;
    }

    public Set<Prestamos> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<Prestamos> prestamos) {
        this.prestamos = prestamos;
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
        TiposPrestamos other = (TiposPrestamos) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TiposPrestamos [id=" + id + ", idMoneda=" + idMoneda + ", nombre=" + nombre + ", descripcion="
                + descripcion + ", requisitos=" + requisitos + ", tipoCliente=" + tipoCliente + ", fechaCreacion="
                + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", estado=" + estado + ", version="
                + version + ", esquemasAmortizacions=" + esquemasAmortizacions + ", garantiasTiposPrestamos="
                + garantiasTiposPrestamos + ", prestamos=" + prestamos + "]";
    }

}