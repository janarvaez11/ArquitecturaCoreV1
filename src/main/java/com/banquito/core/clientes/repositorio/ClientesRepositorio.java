package com.banquito.core.clientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.clientes.modelo.Clientes;

@Repository
public interface ClientesRepositorio extends JpaRepository<Clientes, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario   
    
}
