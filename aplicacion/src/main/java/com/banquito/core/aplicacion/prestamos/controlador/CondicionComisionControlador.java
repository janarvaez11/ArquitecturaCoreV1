package com.banquito.core.aplicacion.prestamos.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CondicionComisionNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.CondicionComision;
import com.banquito.core.aplicacion.prestamos.servicio.CondicionComisionServicio;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/condicionesComisiones")
public class CondicionComisionControlador {

    private final CondicionComisionServicio condicionComisionServicio;

    public CondicionComisionControlador(CondicionComisionServicio condicionComisionServicio) {
        this.condicionComisionServicio = condicionComisionServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondicionComision> obtenerPorId(@PathVariable Integer id) {
        try {
            CondicionComision condicion = condicionComisionServicio.findById(id);
            return ResponseEntity.ok(condicion);
        } catch (CondicionComisionNoEncontradoExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipo/{tipoCondicion}")
    public ResponseEntity<List<CondicionComision>> obtenerPorTipoCondicion(@PathVariable String tipoCondicion) {
        try {
            List<CondicionComision> condiciones = condicionComisionServicio.findByTipoCondicion(tipoCondicion);
            return ResponseEntity.ok(condiciones);
        } catch (BusquedaExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> crear(@RequestBody CondicionComision condicionComision) {
        try {
            condicionComisionServicio.create(condicionComision);
            return ResponseEntity.ok().build();
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id, @RequestBody CondicionComision condicionComision) {
        try {
            condicionComision.setId(id);
            condicionComisionServicio.update(condicionComision);
            return ResponseEntity.ok().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            condicionComisionServicio.delete(id);
            return ResponseEntity.ok().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 