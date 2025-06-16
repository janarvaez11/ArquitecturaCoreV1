package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.SegurosPrestamos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface SegurosPrestamosRepositorio extends JpaRepository<SegurosPrestamos, Integer> {

}
