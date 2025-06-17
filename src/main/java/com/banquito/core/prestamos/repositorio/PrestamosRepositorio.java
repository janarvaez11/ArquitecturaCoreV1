package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.Prestamos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamosRepositorio extends JpaRepository<Prestamos, Integer> {

}
