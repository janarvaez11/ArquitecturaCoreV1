package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.Seguros;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface SegurosRepositorio extends JpaRepository<Seguros, Integer> {

}
