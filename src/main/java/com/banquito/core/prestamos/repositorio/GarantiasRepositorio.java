package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.Garantias;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface GarantiasRepositorio extends JpaRepository<Garantias, Integer> {

}
