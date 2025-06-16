package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.TiposPrestamos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposPrestamosRepositorio extends JpaRepository<TiposPrestamos, Integer> {

}
