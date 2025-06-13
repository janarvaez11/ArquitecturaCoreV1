package com.banquito.core.aplicacion.clientes.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="accionistas")
public class Accionista {

    public enum TipoEntidadParticipe {
        PERSONA, EMPRESA
    }

    public enum Estado {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accionista", nullable = false)
    private Integer idAccionista;

    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;

    @Column(name = "id_participe", nullable = false)
    private Integer idParticipe;

    @ManyToOne
    @JoinColumn(name = "id_empresa", insertable = false, updatable = false)
    private Empresa empresa;
    
    @ManyToOne
    @JoinColumn(name = "id_participe", insertable = false, updatable = false)
    private Persona participe;

    @Column(name = "participacion", precision = 5, scale = 2, nullable = false)
    private BigDecimal participacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entidad_participe", length = 10, nullable = false)
    private TipoEntidadParticipe tipoEntidadParticipe;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15, nullable = false)
    private Estado estado;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    public Accionista() {}

    public Accionista(Integer idAccionista){
        this.idAccionista = idAccionista;
    }

    public Integer getIdAccionista() {
        return idAccionista;
    }

    public void setIdAccionista(Integer idAccionista) {
        this.idAccionista = idAccionista;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdParticipe() {
        return idParticipe;
    }

    public void setIdParticipe(Integer idParticipe) {
        this.idParticipe = idParticipe;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Persona getParticipe() {
        return participe;
    }

    public void setParticipe(Persona participe) {
        this.participe = participe;
    }

    public BigDecimal getParticipacion() {
        return participacion;
    }

    public void setParticipacion(BigDecimal participacion) {
        this.participacion = participacion;
    }

    public TipoEntidadParticipe getTipoEntidadParticipe() {
        return tipoEntidadParticipe;
    }

    public void setTipoEntidadParticipe(TipoEntidadParticipe tipoEntidadParticipe) {
        this.tipoEntidadParticipe = tipoEntidadParticipe;
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
        Accionista other = (Accionista) obj;
        if (idAccionista == null) {
            if (other.idAccionista != null)
                return false;
        } else if (!idAccionista.equals(other.idAccionista))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idAccionista == null) ? 0 : idAccionista.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Accionista{" +
                "idAccionista=" + idAccionista +
                ", idEmpresa=" + idEmpresa +
                ", idParticipe=" + idParticipe +
                ", participacion=" + participacion +
                ", tipoEntidadParticipe=" + tipoEntidadParticipe +
                ", estado=" + estado +
                ", version=" + version +
                '}';
    }
}
