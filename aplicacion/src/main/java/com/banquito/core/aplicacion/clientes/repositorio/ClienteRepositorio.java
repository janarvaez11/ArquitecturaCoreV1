package com.banquito.core.aplicacion.clientes.repositorio;

import com.banquito.core.aplicacion.clientes.modelo.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    
    // Búsquedas por identificación
    Optional<Cliente> findByNumeroIdentificacion(String numeroIdentificacion);
    Optional<Cliente> findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
    
    // Búsquedas por estado
    List<Cliente> findByEstado(String estado);
    Page<Cliente> findByEstado(String estado, Pageable pageable);
    
    // Búsquedas por tipo de entidad
    List<Cliente> findByTipoEntidad(String tipoEntidad);
    Page<Cliente> findByTipoEntidad(String tipoEntidad, Pageable pageable);
    
    // Búsquedas por sucursal
    List<Cliente> findBySucursal_IdSucursal(Integer idSucursal);
    Page<Cliente> findBySucursal_IdSucursal(Integer idSucursal, Pageable pageable);
    
    // Búsquedas por segmento
    List<Cliente> findBySegmento(String segmento);
    Page<Cliente> findBySegmento(String segmento, Pageable pageable);
    
    // Búsquedas por tipo de cliente
    List<Cliente> findByTipoCliente(String tipoCliente);
    Page<Cliente> findByTipoCliente(String tipoCliente, Pageable pageable);
    
    // Búsquedas combinadas
    Page<Cliente> findByEstadoAndTipoEntidad(String estado, String tipoEntidad, Pageable pageable);
    Page<Cliente> findByEstadoAndSucursal_IdSucursal(String estado, Integer idSucursal, Pageable pageable);
    
    // Búsquedas por nombre (case insensitive)
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    Page<Cliente> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    
    // Verificar existencia
    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
    boolean existsByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
    
    // Contar por estado
    long countByEstado(String estado);
    long countByTipoEntidad(String tipoEntidad);
    
    // Búsqueda por tipo de entidad e ID de entidad
    List<Cliente> findByTipoEntidadAndIdEntidad(String tipoEntidad, Integer idEntidad);
}

