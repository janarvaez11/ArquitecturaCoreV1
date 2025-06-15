package com.banquito.core.aplicacion.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "PagosPrestamos")
public class PagosPrestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PagosPrestamos_id_gen")
    @Column(name = "IdPago", nullable = false)
    private Integer id;

    @Column(name = "IdPrestamo", nullable = false)
    private Integer idPrestamo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCuota", referencedColumnName = "IdCuota")
    private CronogramasPagos idCuota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPrestamoCliente", referencedColumnName = "IdPrestamoCliente")
    private PrestamosClientes idPrestamoCliente;

    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "Capital", nullable = false)
    private BigDecimal capital;

    @Column(name = "Interes", nullable = false)
    private BigDecimal interes;

    @ColumnDefault("0")
    @Column(name = "Mora")
    private BigDecimal mora;

    @ColumnDefault("0")
    @Column(name = "Comisiones")
    private BigDecimal comisiones;

    @ColumnDefault("0")
    @Column(name = "Seguros")
    private BigDecimal seguros;

    @Column(name = "Total", nullable = false)
    private BigDecimal total;

    @Column(name = "TipoPago", nullable = false, length = 20)
    private String tipoPago;

    @Column(name = "CuentaOrigen")
    private Integer cuentaOrigen;

    @Column(name = "Referencia", length = 100)
    private String referencia;

    @Column(name = "UsuarioRegistro", length = 50)
    private String usuarioRegistro;

    public PagosPrestamos() {
    }

    public PagosPrestamos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public BigDecimal getComisiones() {
        return comisiones;
    }

    public void setComisiones(BigDecimal comisiones) {
        this.comisiones = comisiones;
    }

    public BigDecimal getSeguros() {
        return seguros;
    }

    public void setSeguros(BigDecimal seguros) {
        this.seguros = seguros;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Integer getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Integer cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public CronogramasPagos getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(CronogramasPagos idCuota) {
        this.idCuota = idCuota;
    }

    public PrestamosClientes getIdPrestamoCliente() {
        return idPrestamoCliente;
    }

    public void setIdPrestamoCliente(PrestamosClientes idPrestamoCliente) {
        this.idPrestamoCliente = idPrestamoCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        PagosPrestamos that = (PagosPrestamos) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PagosPrestamos [id=" + id + ", idPrestamo=" + idPrestamo + ", idCuota=" + idCuota
                + ", idPrestamoCliente=" + idPrestamoCliente + ", fecha=" + fecha + ", capital=" + capital
                + ", interes=" + interes + ", mora=" + mora + ", comisiones=" + comisiones + ", seguros=" + seguros
                + ", total=" + total + ", tipoPago=" + tipoPago + ", cuentaOrigen=" + cuentaOrigen + ", referencia="
                + referencia + ", usuarioRegistro=" + usuarioRegistro + "]";
    }

}