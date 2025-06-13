package com.banquito.core.aplicacion.clientes.controlador;

import com.banquito.core.aplicacion.clientes.modelo.Persona;
import com.banquito.core.aplicacion.clientes.servicio.PersonaServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/personas")
public class PersonaControlador {

    private final PersonaServicio personaServicio;

    public PersonaControlador(PersonaServicio personaServicio) {
        this.personaServicio = personaServicio;
    }

    @GetMapping
    public ResponseEntity<Page<Persona>> listarTodas(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio) {
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            Page<Persona> personas = personaServicio.buscarTodas(pageable);
            return ResponseEntity.ok(personas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable Integer id) {
        try {
            Persona persona = personaServicio.buscarPorId(id);
            return ResponseEntity.ok(persona);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/identificacion/{numeroIdentificacion}")
    public ResponseEntity<Persona> obtenerPorNumeroIdentificacion(@PathVariable String numeroIdentificacion) {
        try {
            Persona persona = personaServicio.buscarPorNumeroIdentificacion(numeroIdentificacion);
            return ResponseEntity.ok(persona);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Persona persona) {
        try {
            Persona nueva = personaServicio.crear(persona);
            return ResponseEntity.status(201).body(nueva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear persona: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Persona persona) {
        try {
            persona.setIdPersona(id);
            Persona actualizada = personaServicio.actualizar(persona);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar persona: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @RequestParam String estado) {
        try {
            Persona persona = personaServicio.cambiarEstado(id, estado);
            return ResponseEntity.ok(persona);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al cambiar estado: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            personaServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar persona: " + e.getMessage());
        }
    }
}
