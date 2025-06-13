package com.banquito.core.aplicacion.cuentas.controlador;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.cuentas.modelo.ComisionCargo;
import com.banquito.core.aplicacion.cuentas.servicio.ComisionCargoServicio;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;

@RestController
@RequestMapping("/api/comisiones-cargos")
@CrossOrigin(maxAge = 3600)
public class ComisionCargoControlador {

    private final ComisionCargoServicio comisionCargoServicio;

    public ComisionCargoControlador(ComisionCargoServicio comisionCargoServicio) {
        this.comisionCargoServicio = comisionCargoServicio;
    }

    @GetMapping
    public ResponseEntity<List<ComisionCargo>> listarTodos() {
        try {
            List<ComisionCargo> comisiones = comisionCargoServicio.listarTodos();
            return ResponseEntity.ok(comisiones);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<ComisionCargo>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        try {
            Page<ComisionCargo> comisiones = comisionCargoServicio.listarTodosPaginado(PageRequest.of(pagina, tamanio));
            return ResponseEntity.ok(comisiones);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComisionCargo> buscarPorId(@PathVariable Integer id) {
        try {
            ComisionCargo comision = comisionCargoServicio.buscarPorId(id);
            return ResponseEntity.ok(comision);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ComisionCargo> crear(@RequestBody ComisionCargo comisionCargo) {
        try {
            ComisionCargo nuevaComision = comisionCargoServicio.crear(comisionCargo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaComision);
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComisionCargo> actualizar(
            @PathVariable Integer id,
            @RequestBody ComisionCargo comisionCargo) {
        try {
            ComisionCargo comisionActualizada = comisionCargoServicio.actualizar(id, comisionCargo);
            return ResponseEntity.ok(comisionActualizada);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            comisionCargoServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /*

    @GetMapping("/tipo-cuenta/{idTipoCuenta}")
    public ResponseEntity<List<ComisionCargo>> buscarPorTipoCuenta(
            @PathVariable Integer idTipoCuenta) {
        try {
            List<ComisionCargo> comisiones = comisionCargoServicio.buscarPorTipoCuenta(idTipoCuenta);
            return ResponseEntity.ok(comisiones);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }
         */

    @GetMapping("/tipo-comision/{tipoComision}")
    public ResponseEntity<List<ComisionCargo>> buscarPorTipoComision(
            @PathVariable String tipoComision) {
        try {
            List<ComisionCargo> comisiones = comisionCargoServicio.buscarPorTipoComision(tipoComision);
            return ResponseEntity.ok(comisiones);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vigentes")
    public ResponseEntity<List<ComisionCargo>> buscarComisionesVigentes() {
        try {
            List<ComisionCargo> comisiones = comisionCargoServicio.buscarComisionesVigentes();
            return ResponseEntity.ok(comisiones);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }
}