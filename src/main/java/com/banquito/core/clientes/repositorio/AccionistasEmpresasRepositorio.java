package com.banquito.core.clientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.clientes.modelo.AccionistasEmpresas;


@Repository
public interface AccionistasEmpresasRepositorio  extends JpaRepository<AccionistasEmpresas, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    
}
