package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.PrestamosClientes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamosClientesRepositorio extends JpaRepository<PrestamosClientes, Integer> {

}
