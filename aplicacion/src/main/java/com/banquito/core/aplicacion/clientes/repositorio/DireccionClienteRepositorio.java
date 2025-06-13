package com.banquito.core.aplicacion.clientes.repositorio;

import com.banquito.core.aplicacion.clientes.modelo.DireccionCliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DireccionClienteRepositorio extends JpaRepository<DireccionCliente, Integer> {
    
    // Búsquedas por cliente (usando idCliente directamente)
    List<DireccionCliente> findByIdCliente(Integer idCliente);
    List<DireccionCliente> findByIdClienteAndEstado(Integer idCliente, DireccionCliente.Estado estado);
    Page<DireccionCliente> findByIdCliente(Integer idCliente, Pageable pageable);
    
    // Búsquedas por cliente (usando relación Cliente)
    List<DireccionCliente> findByCliente_IdCliente(Integer idCliente);
    List<DireccionCliente> findByCliente_IdClienteAndEstado(Integer idCliente, String estado);
    Page<DireccionCliente> findByCliente_IdCliente(Integer idCliente, Pageable pageable);
    
    // Búsquedas por estado
    List<DireccionCliente> findByEstado(String estado);
    List<DireccionCliente> findByEstado(DireccionCliente.Estado estado);
    Page<DireccionCliente> findByEstado(String estado, Pageable pageable);
    
    // Búsquedas por tipo
    List<DireccionCliente> findByTipo(String tipo);
    List<DireccionCliente> findByTipo(DireccionCliente.TipoDireccion tipo);
    List<DireccionCliente> findByCliente_IdClienteAndTipo(Integer idCliente, String tipo);
    List<DireccionCliente> findByIdClienteAndTipo(Integer idCliente, DireccionCliente.TipoDireccion tipo);
    
    // Búsquedas por código postal
    List<DireccionCliente> findByCodigoPostal(String codigoPostal);
    
    // Búsquedas en líneas de dirección
    List<DireccionCliente> findByLinea1ContainingIgnoreCase(String linea1);
    List<DireccionCliente> findByCliente_IdClienteAndLinea1ContainingIgnoreCase(Integer idCliente, String linea1);
    
    // Contar direcciones por cliente
    long countByIdCliente(Integer idCliente);
    long countByIdClienteAndEstado(Integer idCliente, DireccionCliente.Estado estado);
    long countByCliente_IdCliente(Integer idCliente);
    long countByCliente_IdClienteAndEstado(Integer idCliente, String estado);
    
    // Contar por tipo y estado
    long countByIdClienteAndTipoAndEstado(Integer idCliente, DireccionCliente.TipoDireccion tipo, DireccionCliente.Estado estado);
    
    // Verificar existencia
    boolean existsByCliente_IdClienteAndTipoAndEstado(Integer idCliente, String tipo, String estado);
    boolean existsByIdClienteAndTipoAndEstado(Integer idCliente, DireccionCliente.TipoDireccion tipo, DireccionCliente.Estado estado);
    
    // Buscar dirección principal por cliente
    Optional<DireccionCliente> findByCliente_IdClienteAndTipoAndEstado(Integer idCliente, String tipo, String estado);
    Optional<DireccionCliente> findByIdClienteAndTipoAndEstado(Integer idCliente, DireccionCliente.TipoDireccion tipo, DireccionCliente.Estado estado);
}
