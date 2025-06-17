package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.SegurosPrestamoClientes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface SegurosPrestamoClientesRepositorio extends JpaRepository<SegurosPrestamoClientes, Integer> {

}
