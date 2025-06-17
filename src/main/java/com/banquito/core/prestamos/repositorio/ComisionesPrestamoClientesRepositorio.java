package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.ComisionesPrestamoClientes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ComisionesPrestamoClientesRepositorio extends JpaRepository<ComisionesPrestamoClientes, Integer> {

}