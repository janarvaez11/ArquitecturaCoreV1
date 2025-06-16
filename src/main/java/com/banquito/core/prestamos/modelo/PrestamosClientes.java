package com.banquito.core.prestamos.modelo;

import com.banquito.core.clientes.modelo.Clientes;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "prestamos_clientes")
public class PrestamosClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo_cliente", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private Clientes idCliente;

    @ManyToOne
    @JoinColumn(name = "id_prestamo", referencedColumnName = "id_prestamo", nullable = false)
    private Prestamos idPrestamo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_aprobacion", nullable = false)
    private LocalDate fechaAprobacion;

    @Column(name = "fecha_desembolso", nullable = false)
    private LocalDate fechaDesembolso;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "monto_solicitado", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoSolicitado;

    @Column(name = "plazo_meses", nullable = false)
    private Integer plazoMeses;

    @Column(name = "tasa_interes_aplicada", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaInteresAplicada;

    @Column(name = "saldo_pendiente", nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoPendiente;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "idPrestamoCliente")
    private Set<ComisionesPrestamoClientes> comisionesPrestamoClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPrestamoCliente")
    private Set<CronogramasPagos> cronogramasPagos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPrestamoCliente")
    private Set<GarantiasTiposPrestamosClientes> garantiasTiposPrestamosClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPrestamoCliente")
    private Set<com.banquito.core.prestamos.modelo.SegurosPrestamoClientes> segurosPrestamoClientes = new LinkedHashSet<>();

    public PrestamosClientes() {
    }

    public PrestamosClientes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Prestamos getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Prestamos idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(LocalDate fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public LocalDate getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(LocalDate fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public BigDecimal getTasaInteresAplicada() {
        return tasaInteresAplicada;
    }

    public void setTasaInteresAplicada(BigDecimal tasaInteresAplicada) {
        this.tasaInteresAplicada = tasaInteresAplicada;
    }

    public BigDecimal getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(BigDecimal saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
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

    public Set<ComisionesPrestamoClientes> getComisionesPrestamoClientes() {
        return comisionesPrestamoClientes;
    }

    public void setComisionesPrestamoClientes(Set<ComisionesPrestamoClientes> comisionesPrestamoClientes) {
        this.comisionesPrestamoClientes = comisionesPrestamoClientes;
    }

    public Set<CronogramasPagos> getCronogramasPagos() {
        return cronogramasPagos;
    }

    public void setCronogramasPagos(Set<CronogramasPagos> cronogramasPagos) {
        this.cronogramasPagos = cronogramasPagos;
    }

    public Set<GarantiasTiposPrestamosClientes> getGarantiasTiposPrestamosClientes() {
        return garantiasTiposPrestamosClientes;
    }

    public void setGarantiasTiposPrestamosClientes(
            Set<GarantiasTiposPrestamosClientes> garantiasTiposPrestamosClientes) {
        this.garantiasTiposPrestamosClientes = garantiasTiposPrestamosClientes;
    }

    public Set<com.banquito.core.prestamos.modelo.SegurosPrestamoClientes> getSegurosPrestamoClientes() {
        return segurosPrestamoClientes;
    }

    public void setSegurosPrestamoClientes(
            Set<com.banquito.core.prestamos.modelo.SegurosPrestamoClientes> segurosPrestamoClientes) {
        this.segurosPrestamoClientes = segurosPrestamoClientes;
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
        PrestamosClientes other = (PrestamosClientes) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PrestamosClientes [id=" + id + ", idCliente=" + idCliente + ", idPrestamo=" + idPrestamo
                + ", fechaInicio=" + fechaInicio + ", fechaAprobacion=" + fechaAprobacion + ", fechaDesembolso="
                + fechaDesembolso + ", fechaVencimiento=" + fechaVencimiento + ", montoSolicitado=" + montoSolicitado
                + ", plazoMeses=" + plazoMeses + ", tasaInteresAplicada=" + tasaInteresAplicada + ", saldoPendiente="
                + saldoPendiente + ", estado=" + estado + ", version=" + version + ", comisionesPrestamoClientes="
                + comisionesPrestamoClientes + ", cronogramasPagos=" + cronogramasPagos
                + ", garantiasTiposPrestamosClientes=" + garantiasTiposPrestamosClientes + ", segurosPrestamoClientes="
                + segurosPrestamoClientes + "]";
    }

}