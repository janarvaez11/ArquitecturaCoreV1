package com.banquito.core.general.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.general.modelo.Sucursales;

@Repository
public interface SucursalesRepositorio extends JpaRepository<Sucursales, String> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, buscar sucursales por nombre, ubicación, etc.
    
}
