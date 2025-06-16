package com.banquito.core.clientes.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "accionistas_empresas", schema = "public")
public class AccionistasEmpresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('accionistas_empresas_id_accionista_seq')")
    @Column(name = "id_accionista", nullable = false)
    private Integer id;

    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresaFk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false, insertable = false, updatable = false)
    private Empresas idEmpresa;

    @Column(name = "id_participe", nullable = false)
    private Integer idParticipeFk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_participe", referencedColumnName = "id_empresa", nullable = false, insertable = false, updatable = false)
    private Empresas idParticipe;

    @Column(name = "participacion", nullable = false, precision = 5, scale = 2)
    private BigDecimal participacion;

    @Column(name = "tipo_entidad_participe", nullable = false, length = 10)
    private String tipoEntidadParticipe;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public AccionistasEmpresas() {
    }

    public AccionistasEmpresas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpresaFk() {
        return idEmpresaFk;
    }

    public void setIdEmpresaFk(Integer idEmpresaFk) {
        this.idEmpresaFk = idEmpresaFk;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdParticipeFk() {
        return idParticipeFk;
    }

    public void setIdParticipeFk(Integer idParticipeFk) {
        this.idParticipeFk = idParticipeFk;
    }

    public Empresas getIdParticipe() {
        return idParticipe;
    }

    public void setIdParticipe(Empresas idParticipe) {
        this.idParticipe = idParticipe;
    }

    public BigDecimal getParticipacion() {
        return participacion;
    }

    public void setParticipacion(BigDecimal participacion) {
        this.participacion = participacion;
    }

    public String getTipoEntidadParticipe() {
        return tipoEntidadParticipe;
    }

    public void setTipoEntidadParticipe(String tipoEntidadParticipe) {
        this.tipoEntidadParticipe = tipoEntidadParticipe;
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
        AccionistasEmpresas other = (AccionistasEmpresas) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AccionistasEmpresas [id=" + id + ", idEmpresaFk=" + idEmpresaFk + ", idParticipeFk=" + idParticipeFk 
                + ", participacion=" + participacion + ", tipoEntidadParticipe=" + tipoEntidadParticipe 
                + ", estado=" + estado + ", version=" + version + "]";
    }
}