package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.EsquemasAmortizaciones;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface EsquemasAmortizacionesRepositorio extends JpaRepository<EsquemasAmortizaciones, Integer> {

}
