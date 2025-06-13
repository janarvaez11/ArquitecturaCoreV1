package com.banquito.core.aplicacion.clientes.modelo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="empresas")
public class Empresa {

    public enum TipoIdentificacion {
        RUC
    }

    public enum TipoEmpresa {
        PRIVADA, PUBLICA, MIXTA
    }

    public enum SectorEconomico {
        PRIMARIO, SECUNDARIO, TERCIARIO
    }

    public enum Estado {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificacion", length = 10, nullable = false)
    private TipoIdentificacion tipoIdentificacion;

    @Column(name = "numero_identificacion",  length = 13, nullable = false)
    private String numeroIdentificacion;

    @Column(name = "nombre_comercial",  length = 30, nullable = false)
    private String nombreComercial;

    @Column(name = "razon_social",  length = 50, nullable = false)
    private String razonSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo",  length = 10, nullable = false)
    private TipoEmpresa tipo;

    @Column(name = "fecha_constitucion", nullable = false)
    private Date fechaConstitucion;

    @Column(name = "correo_electronico",  length = 40, nullable = false)
    private String correoElectronico;

    @Enumerated(EnumType.STRING)
    @Column(name = "sector_economico",  length = 15, nullable = false)
    private SectorEconomico sectorEconomico;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion", nullable = false)
    private Date fechaActualizacion;
      
    @Enumerated(EnumType.STRING)
    @Column(name = "estado",  length = 15, nullable = false)
    private Estado estado;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    public Empresa() {}

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Date getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(Date fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public TipoEmpresa getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpresa tipo) {
        this.tipo = tipo;
    }

    public SectorEconomico getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(SectorEconomico sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Empresa other = (Empresa) obj;
        if (idEmpresa == null) {
            if (other.idEmpresa != null)
                return false;
        } else if (!idEmpresa.equals(other.idEmpresa))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", tipoIdentificacion=" + tipoIdentificacion +
                ", numeroIdentificacion='" + numeroIdentificacion + '\'' +
                ", nombreComercial='" + nombreComercial + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", fechaConstitucion=" + fechaConstitucion +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", tipo=" + tipo +
                ", sectorEconomico=" + sectorEconomico +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaActualizacion=" + fechaActualizacion +
                ", estado=" + estado +
                ", version=" + version +
                '}';
    }
}
