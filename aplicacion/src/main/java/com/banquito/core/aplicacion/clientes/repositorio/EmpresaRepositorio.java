package com.banquito.core.aplicacion.clientes.repositorio;

import com.banquito.core.aplicacion.clientes.modelo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Integer> {

    Optional<Empresa> findByNumeroIdentificacion(String numeroIdentificacion);
    
    List<Empresa> findByEstado(Empresa.Estado estado);
    
    List<Empresa> findByTipo(Empresa.TipoEmpresa tipo);
    
    List<Empresa> findBySectorEconomico(Empresa.SectorEconomico sectorEconomico);

}
