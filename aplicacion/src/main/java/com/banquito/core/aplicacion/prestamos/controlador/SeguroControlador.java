package com.banquito.core.aplicacion.prestamos.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.SeguroNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.Seguro;
import com.banquito.core.aplicacion.prestamos.servicio.SeguroServicio;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/seguros")
public class SeguroControlador {

    private final SeguroServicio seguroServicio;

    public SeguroControlador(SeguroServicio seguroServicio) {
        this.seguroServicio = seguroServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seguro> obtenerPorId(@PathVariable Integer id) {
        try {
            Seguro seguro = seguroServicio.findById(id);
            return ResponseEntity.ok(seguro);
        } catch (SeguroNoEncontradoExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> crear(@RequestBody Seguro seguro) {
        try {
            seguroServicio.create(seguro);
            return ResponseEntity.ok().build();
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id, @RequestBody Seguro seguro) {
        try {
            seguro.setId(id);
            seguroServicio.update(seguro);
            return ResponseEntity.ok().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            seguroServicio.delete(id);
            return ResponseEntity.ok().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 