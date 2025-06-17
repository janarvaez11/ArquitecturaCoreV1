package com.banquito.core.prestamos.modelo;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "comisiones_prestamos")
public class ComisionesPrestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comision_prestamo", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_comision", nullable = false)
    private TiposComisiones idTipoComision;

    @ManyToOne
    @JoinColumn(name = "id_prestamo", nullable = false)
    private Prestamos idPrestamo;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Version
    @Column(name = "version", nullable = false, precision = 9)
    private Long version;

    @OneToMany(mappedBy = "idComisionPrestamo")
    private Set<ComisionesPrestamoClientes> comisionesPrestamoClientes = new LinkedHashSet<>();

    public ComisionesPrestamos() {
    }

    public ComisionesPrestamos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TiposComisiones getIdTipoComision() {
        return idTipoComision;
    }

    public void setIdTipoComision(TiposComisiones idTipoComision) {
        this.idTipoComision = idTipoComision;
    }

    public Prestamos getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Prestamos idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Set<ComisionesPrestamoClientes> getComisionesPrestamoClientes() {
        return comisionesPrestamoClientes;
    }

    public void setComisionesPrestamoClientes(Set<ComisionesPrestamoClientes> comisionesPrestamoClientes) {
        this.comisionesPrestamoClientes = comisionesPrestamoClientes;
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
        ComisionesPrestamos other = (ComisionesPrestamos) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ComisionesPrestamos [id=" + id + ", idTipoComision=" + idTipoComision + ", idPrestamo=" + idPrestamo
                + ", estado=" + estado + ", version=" + version + ", comisionesPrestamoClientes="
                + comisionesPrestamoClientes + "]";
    }

}