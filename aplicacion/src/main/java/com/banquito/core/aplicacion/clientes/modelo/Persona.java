package com.banquito.core.aplicacion.clientes.modelo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name="personas")
public class Persona {

    public enum TipoIdentificacion {
        CEDULA, PASAPORTE
    }

    public enum Genero {
        MASCULINO, FEMENINO, OTROS
    }

    public enum EstadoCivil {
        SOLTERO, CASADO, DIVORICIADO, VIUDO, UNIONLIBRE
    }

    public enum NivelEstudio {
        PRIMARIA, SECUNDARIA, UNIVERSITARIA, POSGRADO, DOCTORADO
    }

    public enum Estado {
        ACTIVO, INACTIVO, SUSPENDIDO, BLOQUEADO, PROSPECTO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificacion", length = 10, nullable = false)
    private TipoIdentificacion tipoIdentificacion;

    @Column(name = "numero_identificacion", length = 13, nullable = false)
    private String numeroIdentificacion;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 10, nullable = false)
    private Genero genero;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", length = 15, nullable = false)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_estudio", length = 15, nullable = false)
    private NivelEstudio nivelEstudio;

    @Column(name = "correo_electronico", length = 40, nullable = false)
    private String correoElectronico;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion", nullable = false)
    private Date fechaActualizacion;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15, nullable = false)
    private Estado estado;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    public Persona() {
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public NivelEstudio getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(NivelEstudio nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
        Persona other = (Persona) obj;
        if (idPersona == null) {
            if (other.idPersona != null)
                return false;
        } else if (!idPersona.equals(other.idPersona))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", tipoIdentificacion=" + tipoIdentificacion +
                ", numeroIdentificacion='" + numeroIdentificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", genero=" + genero +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estadoCivil=" + estadoCivil +
                ", nivelEstudio=" + nivelEstudio +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", estado=" + estado +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaActualizacion=" + fechaActualizacion +
                ", version=" + version +
                '}';
    }
}
