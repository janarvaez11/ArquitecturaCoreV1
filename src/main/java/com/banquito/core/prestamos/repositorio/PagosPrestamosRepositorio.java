package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.PagosPrestamos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosPrestamosRepositorio extends JpaRepository<PagosPrestamos, Integer> {

}
