package com.banquito.core.aplicacion.clientes.repositorio;

import com.banquito.core.aplicacion.clientes.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {

    Optional<Persona> findByNumeroIdentificacion(String numeroIdentificacion);
    
    List<Persona> findByEstado(Persona.Estado estado);
    
    List<Persona> findByGenero(Persona.Genero genero);
    
    List<Persona> findByEstadoCivil(Persona.EstadoCivil estadoCivil);
    
    List<Persona> findByNivelEstudio(Persona.NivelEstudio nivelEstudio);
    
    List<Persona> findByFechaNacimientoBetween(Date fechaInicio, Date fechaFin);
}
