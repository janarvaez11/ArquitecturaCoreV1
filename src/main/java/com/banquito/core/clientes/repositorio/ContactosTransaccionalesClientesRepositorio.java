package com.banquito.core.clientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.clientes.modelo.ContactosTransaccionalesClientes;

@Repository
public interface ContactosTransaccionalesClientesRepositorio  extends JpaRepository<ContactosTransaccionalesClientes, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar contactos por cliente o tipo de contacto
    
}
