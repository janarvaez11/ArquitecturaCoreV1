package com.banquito.core.general.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.general.modelo.Feriados;

@Repository
public interface FeriadosRepositorio  extends JpaRepository<Feriados, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    
}
