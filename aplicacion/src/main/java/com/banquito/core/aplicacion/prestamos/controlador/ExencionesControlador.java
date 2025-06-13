package com.banquito.core.aplicacion.prestamos.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.ExencionesPrestamoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.ExencionesPrestamo;
import com.banquito.core.aplicacion.prestamos.servicio.ExencionesServicio;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/exencionesPrestamos")
public class ExencionesControlador {

    private final ExencionesServicio exencionesServicio;

    public ExencionesControlador(ExencionesServicio exencionesServicio) {
        this.exencionesServicio = exencionesServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExencionesPrestamo> obtenerPorId(@PathVariable Integer id) {
        try {
            ExencionesPrestamo exencion = exencionesServicio.findById(id);
            return ResponseEntity.ok(exencion);
        } catch (ExencionesPrestamoNoEncontradoExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> crear(@RequestBody ExencionesPrestamo exencionesPrestamo) {
        try {
            exencionesServicio.create(exencionesPrestamo);
            return ResponseEntity.ok().build();
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id, @RequestBody ExencionesPrestamo exencionesPrestamo) {
        try {
            exencionesPrestamo.setId(id);
            exencionesServicio.update(exencionesPrestamo);
            return ResponseEntity.ok().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            exencionesServicio.delete(id);
            return ResponseEntity.ok().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 