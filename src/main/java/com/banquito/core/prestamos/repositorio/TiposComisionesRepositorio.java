package com.banquito.core.prestamos.repositorio;

import com.banquito.core.prestamos.modelo.TiposComisiones;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposComisionesRepositorio extends JpaRepository<TiposComisiones, Integer> {

}
