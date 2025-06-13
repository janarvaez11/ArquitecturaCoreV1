package com.banquito.core.aplicacion.clientes.repositorio;

import com.banquito.core.aplicacion.clientes.modelo.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepresentanteRepositorio extends JpaRepository<Representante, Integer> {

    List<Representante> findByIdEmpresa(Integer idEmpresa);
    
    List<Representante> findByIdCliente(Integer idCliente);
    
    List<Representante> findByIdEmpresaAndEstado(Integer idEmpresa, Representante.Estado estado);
    
    List<Representante> findByRol(Representante.Rol rol);
    
    List<Representante> findByIdEmpresaAndRolAndEstado(Integer idEmpresa, Representante.Rol rol, Representante.Estado estado);
    
    List<Representante> findByIdEmpresaAndIdClienteAndRol(Integer idEmpresa, Integer idCliente, Representante.Rol rol);
}
