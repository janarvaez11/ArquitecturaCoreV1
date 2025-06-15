package com.banquito.core.prestamos.modelo;

import com.banquito.core.general.modelo.Monedas;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipos_prestamos", schema = "public")
public class TiposPrestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('tipos_prestamos_id_tipo_prestamo_seq')")
    @Column(name = "id_tipo_prestamo", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_moneda", nullable = false)
    private Monedas idMoneda;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "requisitos", nullable = false, length = 300)
    private String requisitos;

    @Column(name = "tipo_cliente", nullable = false, length = 15)
    private String tipoCliente;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha_modificacion", nullable = false)
    private Instant fechaModificacion;

    @ColumnDefault("'ACTIVO'")
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

}