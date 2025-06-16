package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "garantias_tipos_prestamos_cliente")
public class GarantiasTiposPrestamosClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_garantia_tipo_prestamo_cliente", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_prestamo_cliente", nullable = false)
    private com.banquito.core.prestamos.modelo.PrestamosClientes idPrestamoCliente;

    @ManyToOne
    @JoinColumn(name = "id_garantia_tipo_prestamo", nullable = false)
    private GarantiasTiposPrestamos idGarantiaTipoPrestamo;

    @Column(name = "monto_tasado", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoTasado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Column(name = "documento_referencia", nullable = false, length = 100)
    private String documentoReferencia;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public GarantiasTiposPrestamosClientes() {
    }

    public GarantiasTiposPrestamosClientes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.banquito.core.prestamos.modelo.PrestamosClientes getIdPrestamoCliente() {
        return idPrestamoCliente;
    }

    public void setIdPrestamoCliente(com.banquito.core.prestamos.modelo.PrestamosClientes idPrestamoCliente) {
        this.idPrestamoCliente = idPrestamoCliente;
    }

    public GarantiasTiposPrestamos getIdGarantiaTipoPrestamo() {
        return idGarantiaTipoPrestamo;
    }

    public void setIdGarantiaTipoPrestamo(GarantiasTiposPrestamos idGarantiaTipoPrestamo) {
        this.idGarantiaTipoPrestamo = idGarantiaTipoPrestamo;
    }

    public BigDecimal getMontoTasado() {
        return montoTasado;
    }

    public void setMontoTasado(BigDecimal montoTasado) {
        this.montoTasado = montoTasado;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(String documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
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
        GarantiasTiposPrestamosClientes other = (GarantiasTiposPrestamosClientes) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GarantiasTiposPrestamosClientes [id=" + id + ", idPrestamoCliente=" + idPrestamoCliente
                + ", idGarantiaTipoPrestamo=" + idGarantiaTipoPrestamo + ", montoTasado=" + montoTasado
                + ", fechaRegistro=" + fechaRegistro + ", descripcion=" + descripcion + ", documentoReferencia="
                + documentoReferencia + ", estado=" + estado + ", version=" + version + "]";
    }

}