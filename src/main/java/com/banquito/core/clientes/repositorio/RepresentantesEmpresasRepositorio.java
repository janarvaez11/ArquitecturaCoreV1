package com.banquito.core.clientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.clientes.modelo.RepresentantesEmpresas;

@Repository
public interface RepresentantesEmpresasRepositorio extends JpaRepository<RepresentantesEmpresas, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar representantes por empresa o por tipo de documento
    
}
