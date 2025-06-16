package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.ComisionesPrestamos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ComisionesPrestamosRepositorio extends JpaRepository<ComisionesPrestamos, Integer> {

}
