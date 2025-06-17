package com.banquito.core.clientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.clientes.modelo.DireccionesClientes;

@Repository
public interface DireccionesClientesRepositorio extends JpaRepository<DireccionesClientes, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    
}
