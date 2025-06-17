package com.banquito.core.clientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.clientes.modelo.ClientesSucursales;

@Repository
public interface ClientesSucursalesRepositorio  extends JpaRepository<ClientesSucursales, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar sucursales por cliente o cualquier otra lógica específica
    
}
