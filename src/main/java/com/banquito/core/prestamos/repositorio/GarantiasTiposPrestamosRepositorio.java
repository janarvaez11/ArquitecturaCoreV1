package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.GarantiasTiposPrestamos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface GarantiasTiposPrestamosRepositorio extends JpaRepository<GarantiasTiposPrestamos, Integer> {

}
