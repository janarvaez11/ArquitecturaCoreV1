package com.banquito.core.general.repositorio;

import org.springframework.stereotype.Repository;

import com.banquito.core.general.modelo.EstructurasGeograficaId;
import com.banquito.core.general.modelo.EstructurasGeograficas;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EstructurasGeograficasRepositorio extends JpaRepository<EstructurasGeograficas, EstructurasGeograficaId> {
    // Aquí puedes agregar métodos personalizados si es necesario
    
}
