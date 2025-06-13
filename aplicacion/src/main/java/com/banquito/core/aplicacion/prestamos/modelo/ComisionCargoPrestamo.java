package com.banquito.core.aplicacion.prestamos.modelo;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "comisiones_cargos_prestamos")
public class ComisionCargoPrestamo {

    public enum Estado {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comision_cargo_prestamo", nullable = false)
    private Integer idComisionCargoPrestamo;

    @Column(name = "id_prestamo", nullable = false)
    private Integer idPrestamo;

    @Column(name = "id_comision_prestamo", nullable = false)
    private Integer idComisionPrestamo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_asignacion", nullable = false)
    private Date fechaAsignacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15, nullable = false)
    private Estado estado;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "id_prestamo", referencedColumnName = "IdPrestamo", insertable = false, updatable = false)
    private Prestamo prestamo;

    @ManyToOne
    @JoinColumn(name = "id_comision_prestamo", referencedColumnName = "idComisionPrestamo", insertable = false, updatable = false)
    private ComisionPrestamo comisionPrestamo;

    public ComisionCargoPrestamo() {
    }

    public ComisionCargoPrestamo(Integer idComisionCargoPrestamo) {
        this.idComisionCargoPrestamo = idComisionCargoPrestamo;
    }

    public Integer getIdComisionCargoPrestamo() {
        return idComisionCargoPrestamo;
    }

    public void setIdComisionCargoPrestamo(Integer idComisionCargoPrestamo) {
        this.idComisionCargoPrestamo = idComisionCargoPrestamo;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdComisionPrestamo() {
        return idComisionPrestamo;
    }

    public void setIdComisionPrestamo(Integer idComisionPrestamo) {
        this.idComisionPrestamo = idComisionPrestamo;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
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

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public ComisionPrestamo getComisionPrestamo() {
        return comisionPrestamo;
    }

    public void setComisionPrestamo(ComisionPrestamo comisionPrestamo) {
        this.comisionPrestamo = comisionPrestamo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idComisionCargoPrestamo == null) ? 0 : idComisionCargoPrestamo.hashCode());
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
        ComisionCargoPrestamo other = (ComisionCargoPrestamo) obj;
        if (idComisionCargoPrestamo == null) {
            if (other.idComisionCargoPrestamo != null)
                return false;
        } else if (!idComisionCargoPrestamo.equals(other.idComisionCargoPrestamo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ComisionCargoPrestamo [idComisionCargoPrestamo=" + idComisionCargoPrestamo + ", idPrestamo=" + idPrestamo
                + ", idComisionPrestamo=" + idComisionPrestamo + ", fechaAsignacion=" + fechaAsignacion + ", estado="
                + estado + ", version=" + version + "]";
    }
} 