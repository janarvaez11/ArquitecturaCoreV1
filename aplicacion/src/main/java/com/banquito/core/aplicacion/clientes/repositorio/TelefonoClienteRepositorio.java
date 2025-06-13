package com.banquito.core.aplicacion.clientes.repositorio;

import com.banquito.core.aplicacion.clientes.modelo.TelefonoCliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelefonoClienteRepositorio extends JpaRepository<TelefonoCliente, Integer> {
    
    // Búsquedas por cliente
    List<TelefonoCliente> findByIdCliente(Integer idCliente);
    List<TelefonoCliente> findByIdClienteAndEstado(Integer idCliente, TelefonoCliente.Estado estado);
    Page<TelefonoCliente> findByIdCliente(Integer idCliente, Pageable pageable);
    
    // Búsquedas por estado
    List<TelefonoCliente> findByEstado(TelefonoCliente.Estado estado);
    Page<TelefonoCliente> findByEstado(TelefonoCliente.Estado estado, Pageable pageable);
    
    // Búsquedas por tipo de teléfono
    List<TelefonoCliente> findByTipo(TelefonoCliente.TipoTelefono tipo);
    List<TelefonoCliente> findByIdClienteAndTipo(Integer idCliente, TelefonoCliente.TipoTelefono tipo);
    
    // Búsquedas por número
    Optional<TelefonoCliente> findByNumeroTelefono(String numeroTelefono);
    List<TelefonoCliente> findByNumeroTelefonoContaining(String numeroTelefono);
    
    // Contar teléfonos por cliente
    long countByIdCliente(Integer idCliente);
    long countByIdClienteAndEstado(Integer idCliente, TelefonoCliente.Estado estado);
    
    // Verificar existencia
    boolean existsByNumeroTelefono(String numeroTelefono);
    boolean existsByIdClienteAndTipoAndEstado(Integer idCliente, TelefonoCliente.TipoTelefono tipo, TelefonoCliente.Estado estado);
    
    // Buscar teléfono principal por cliente
    Optional<TelefonoCliente> findByIdClienteAndTipoAndEstado(Integer idCliente, TelefonoCliente.TipoTelefono tipo, TelefonoCliente.Estado estado);
}
