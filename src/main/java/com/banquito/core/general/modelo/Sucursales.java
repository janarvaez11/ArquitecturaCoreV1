package com.banquito.core.aplicacion.general.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sucursales")
public class Sucursales {
    @Id
    @Size(max = 10)
    @SequenceGenerator(name = "sucursales_id_gen", sequenceName = "clientes_sucursales_id_cliente_sucursal_seq", allocationSize = 1)
    @Column(name = "codigo", nullable = false, length = 10)
    private String codigo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_entidad_bancaria", nullable = false)
    private EntidadesBancarias idEntidadBancaria;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_locacion", nullable = false)
    private LocacionesGeograficas idLocacion;

    @Size(max = 30)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Size(max = 40)
    @NotNull
    @Column(name = "correo_electronico", nullable = false, length = 40)
    private String correoElectronico;

    @Size(max = 10)
    @NotNull
    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Size(max = 150)
    @NotNull
    @Column(name = "direccion_linea1", nullable = false, length = 150)
    private String direccionLinea1;

    @Size(max = 150)
    @Column(name = "direccion_linea2", length = 150)
    private String direccionLinea2;

    @NotNull
    @Column(name = "latitud", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitud;

    @NotNull
    @Column(name = "longitud", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitud;

    @Size(max = 15)
    @NotNull
    @ColumnDefault("'ACTIVO'")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public EntidadesBancarias getIdEntidadBancaria() {
        return idEntidadBancaria;
    }

    public void setIdEntidadBancaria(EntidadesBancarias idEntidadBancaria) {
        this.idEntidadBancaria = idEntidadBancaria;
    }

    public LocacionesGeograficas getIdLocacion() {
        return idLocacion;
    }

    public void setIdLocacion(LocacionesGeograficas idLocacion) {
        this.idLocacion = idLocacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccionLinea1() {
        return direccionLinea1;
    }

    public void setDireccionLinea1(String direccionLinea1) {
        this.direccionLinea1 = direccionLinea1;
    }

    public String getDireccionLinea2() {
        return direccionLinea2;
    }

    public void setDireccionLinea2(String direccionLinea2) {
        this.direccionLinea2 = direccionLinea2;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
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

}