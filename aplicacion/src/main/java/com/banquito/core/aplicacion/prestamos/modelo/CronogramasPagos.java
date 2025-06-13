package com.banquito.core.aplicacion.prestamos.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "CronogramasPagos")
public class CronogramasPagos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CronogramasPagos_id_gen")
    @Column(name = "IdCuota", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPrestamoCliente", referencedColumnName = "IdPrestamoCliente")
    private PrestamosClientes idPrestamoCliente;

    @Column(name = "IdPrestamo", nullable = false)
    private Integer idPrestamo;

    @Column(name = "NumeroCuota", nullable = false)
    private Integer numeroCuota;

    @Column(name = "Vencimiento", nullable = false)
    private LocalDate vencimiento;

    @Column(name = "Capital", nullable = false)
    private BigDecimal capital;

    @Column(name = "Interes", nullable = false)
    private BigDecimal interes;

    @ColumnDefault("0")
    @Column(name = "Comisiones")
    private BigDecimal comisiones;

    @ColumnDefault("0")
    @Column(name = "Seguros")
    private BigDecimal seguros;

    @Column(name = "Total", nullable = false)
    private BigDecimal total;

    @Column(name = "Saldo", nullable = false)
    private BigDecimal saldo;

    @ColumnDefault("'PENDIENTE'")
    @Column(name = "Estado", length = 20)
    private String estado;

    @Column(name = "FechaPago")
    private LocalDate fechaPago;

    public CronogramasPagos() {
    }

    public CronogramasPagos(Integer id) {
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

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
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
        CronogramasPagos that = (CronogramasPagos) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CronogramasPagos [id=" + id + ", idPrestamoCliente=" + idPrestamoCliente + ", idPrestamo=" + idPrestamo
                + ", numeroCuota=" + numeroCuota + ", vencimiento=" + vencimiento + ", capital=" + capital
                + ", interes=" + interes + ", comisiones=" + comisiones + ", seguros=" + seguros + ", total=" + total
                + ", saldo=" + saldo + ", estado=" + estado + ", fechaPago=" + fechaPago + "]";
    }

}
