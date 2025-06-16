package com.banquito.core.prestamos.modelo;

import com.banquito.core.general.modelo.Monedas;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "prestamos")
public class Prestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_prestamo", nullable = false)
    private com.banquito.core.prestamos.modelo.TiposPrestamos idTipoPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_moneda", nullable = false)
    private Monedas idMoneda;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "fecha_modificacion", nullable = false)
    private Instant fechaModificacion;

    @Column(name = "base_calculo", nullable = false, length = 10)
    private String baseCalculo;

    @Column(name = "tasa_interes", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaInteres;

    @Column(name = "monto_minimo", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoMinimo;

    @Column(name = "monto_maximo", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoMaximo;

    @Column(name = "plazo_minimo_meses", nullable = false, precision = 2)
    private BigDecimal plazoMinimoMeses;

    @Column(name = "plazo_maximo_meses", nullable = false, precision = 2)
    private BigDecimal plazoMaximoMeses;

    @Column(name = "tipo_amortizacion", nullable = false, length = 20)
    private String tipoAmortizacion;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idPrestamo")
    private Set<ComisionesPrestamos> comisionesPrestamos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPrestamo")
    private Set<com.banquito.core.prestamos.modelo.PrestamosClientes> prestamosClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPrestamo")
    private Set<com.banquito.core.prestamos.modelo.SegurosPrestamos> segurosPrestamos = new LinkedHashSet<>();

    public Prestamos() {
    }

    public Prestamos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.banquito.core.prestamos.modelo.TiposPrestamos getIdTipoPrestamo() {
        return idTipoPrestamo;
    }

    public void setIdTipoPrestamo(com.banquito.core.prestamos.modelo.TiposPrestamos idTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
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

    public Instant getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Instant fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(String baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(BigDecimal montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public BigDecimal getPlazoMinimoMeses() {
        return plazoMinimoMeses;
    }

    public void setPlazoMinimoMeses(BigDecimal plazoMinimoMeses) {
        this.plazoMinimoMeses = plazoMinimoMeses;
    }

    public BigDecimal getPlazoMaximoMeses() {
        return plazoMaximoMeses;
    }

    public void setPlazoMaximoMeses(BigDecimal plazoMaximoMeses) {
        this.plazoMaximoMeses = plazoMaximoMeses;
    }

    public String getTipoAmortizacion() {
        return tipoAmortizacion;
    }

    public void setTipoAmortizacion(String tipoAmortizacion) {
        this.tipoAmortizacion = tipoAmortizacion;
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

    public Set<ComisionesPrestamos> getComisionesPrestamos() {
        return comisionesPrestamos;
    }

    public void setComisionesPrestamos(Set<ComisionesPrestamos> comisionesPrestamos) {
        this.comisionesPrestamos = comisionesPrestamos;
    }

    public Set<com.banquito.core.prestamos.modelo.PrestamosClientes> getPrestamosClientes() {
        return prestamosClientes;
    }

    public void setPrestamosClientes(Set<com.banquito.core.prestamos.modelo.PrestamosClientes> prestamosClientes) {
        this.prestamosClientes = prestamosClientes;
    }

    public Set<com.banquito.core.prestamos.modelo.SegurosPrestamos> getSegurosPrestamos() {
        return segurosPrestamos;
    }

    public void setSegurosPrestamos(Set<com.banquito.core.prestamos.modelo.SegurosPrestamos> segurosPrestamos) {
        this.segurosPrestamos = segurosPrestamos;
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
        Prestamos other = (Prestamos) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Prestamos [id=" + id + ", idTipoPrestamo=" + idTipoPrestamo + ", idMoneda=" + idMoneda + ", nombre="
                + nombre + ", descripcion=" + descripcion + ", fechaModificacion=" + fechaModificacion
                + ", baseCalculo=" + baseCalculo + ", tasaInteres=" + tasaInteres + ", montoMinimo=" + montoMinimo
                + ", montoMaximo=" + montoMaximo + ", plazoMinimoMeses=" + plazoMinimoMeses + ", plazoMaximoMeses="
                + plazoMaximoMeses + ", tipoAmortizacion=" + tipoAmortizacion + ", estado=" + estado + ", version="
                + version + ", comisionesPrestamos=" + comisionesPrestamos + ", prestamosClientes=" + prestamosClientes
                + ", segurosPrestamos=" + segurosPrestamos + "]";
    }

}