package com.banquito.core.general.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.general.modelo.Monedas;

@Repository
public interface MonedasRespositorio extends JpaRepository<Monedas, String> {
    // Aquí puedes agregar métodos personalizados si es necesario
    
}
