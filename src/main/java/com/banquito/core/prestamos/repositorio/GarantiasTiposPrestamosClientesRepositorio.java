package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.GarantiasTiposPrestamosClientes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface GarantiasTiposPrestamosClientesRepositorio
        extends JpaRepository<GarantiasTiposPrestamosClientes, Integer> {

}
