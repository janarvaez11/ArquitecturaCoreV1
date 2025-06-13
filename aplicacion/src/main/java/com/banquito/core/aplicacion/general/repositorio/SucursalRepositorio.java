package com.banquito.core.aplicacion.general.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.general.modelo.Sucursal;

@Repository
public interface SucursalRepositorio extends JpaRepository<Sucursal, Integer> {

    Optional<Sucursal> findByCodigo(String codigo);
    
    List<Sucursal> findByEntidadBancariaIdAndEstado(Integer idEntidadBancaria, String estado);
    
    List<Sucursal> findByEstado(String estado);
    
    boolean existsByCodigo(String codigo);
    
    List<Sucursal> findByLocacionId(Integer idLocacion);
    
    List<Sucursal> findByEntidadBancariaId(Integer idEntidadBancaria);
}