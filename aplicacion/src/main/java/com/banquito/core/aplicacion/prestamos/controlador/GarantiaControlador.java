package com.banquito.core.aplicacion.prestamos.controlador;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.GarantiaNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.Garantia;
import com.banquito.core.aplicacion.prestamos.servicio.GarantiaServicio;

@RestController
@RequestMapping("/api/garantias")
public class GarantiaControlador {

    private final GarantiaServicio garantiaServicio;

    public GarantiaControlador(GarantiaServicio garantiaServicio) {
        this.garantiaServicio = garantiaServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            Garantia garantia = garantiaServicio.findById(id);
            return ResponseEntity.ok(garantia);
        } catch (GarantiaNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/tipoPrestamo/{idTipoPrestamo}")
    public ResponseEntity<?> obtenerPorTipoPrestamo(@PathVariable Integer idTipoPrestamo) {
        try {
            List<Garantia> garantias = garantiaServicio.findByTipoPrestamo(idTipoPrestamo);
            return ResponseEntity.ok(garantias);
        } catch (BusquedaExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/tipo/{tipoGarantia}")
    public ResponseEntity<?> obtenerPorTipoGarantia(@PathVariable String tipoGarantia) {
        try {
            List<Garantia> garantias = garantiaServicio.findByTipoGarantia(tipoGarantia);
            return ResponseEntity.ok(garantias);
        } catch (BusquedaExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> crear(@RequestBody Garantia garantia) {
        try {
            garantiaServicio.create(garantia);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Garantia garantia) {
        try {
            garantia.setId(id);
            garantiaServicio.update(garantia);
            return ResponseEntity.ok().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            garantiaServicio.delete(id);
            return ResponseEntity.ok().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar: " + e.getMessage());
        }
    }
} 