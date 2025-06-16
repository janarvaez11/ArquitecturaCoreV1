package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.CronogramasPagos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CronogramasPagosRepositorio extends JpaRepository<CronogramasPagos, Integer> {

}
