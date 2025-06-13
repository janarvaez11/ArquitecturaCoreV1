package com.banquito.core.aplicacion.clientes.repositorio;

import com.banquito.core.aplicacion.clientes.modelo.Accionista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccionistaRepositorio extends JpaRepository<Accionista, Integer> {

    List<Accionista> findByIdEmpresa(Integer idEmpresa);
    
    List<Accionista> findByIdEmpresaAndEstado(Integer idEmpresa, Accionista.Estado estado);
    
    List<Accionista> findByIdEmpresaAndIdParticipe(Integer idEmpresa, Integer idParticipe);
    
    List<Accionista> findByEstado(Accionista.Estado estado);
    
    List<Accionista> findByTipoEntidadParticipe(Accionista.TipoEntidadParticipe tipoEntidadParticipe);
    
    long countByIdEmpresa(Integer idEmpresa);
    
    long countByIdEmpresaAndEstado(Integer idEmpresa, Accionista.Estado estado);
}
