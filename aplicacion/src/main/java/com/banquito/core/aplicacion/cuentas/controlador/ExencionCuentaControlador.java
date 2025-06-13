package com.banquito.core.aplicacion.cuentas.controlador;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.cuentas.modelo.ExencionCuenta;
import com.banquito.core.aplicacion.cuentas.servicio.ExencionCuentaServicio;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;

@RestController
@RequestMapping("/api/exenciones-cuentas")
@CrossOrigin(maxAge = 3600)
public class ExencionCuentaControlador {

    private final ExencionCuentaServicio exencionCuentaServicio;

    public ExencionCuentaControlador(ExencionCuentaServicio exencionCuentaServicio) {
        this.exencionCuentaServicio = exencionCuentaServicio;
    }

    @GetMapping
    public ResponseEntity<List<ExencionCuenta>> listarTodos() {
        try {
            List<ExencionCuenta> exenciones = exencionCuentaServicio.listarTodos();
            return ResponseEntity.ok(exenciones);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<ExencionCuenta>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        try {
            Page<ExencionCuenta> exenciones = exencionCuentaServicio.listarTodosPaginado(PageRequest.of(pagina, tamanio));
            return ResponseEntity.ok(exenciones);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExencionCuenta> buscarPorId(@PathVariable Integer id) {
        try {
            ExencionCuenta exencion = exencionCuentaServicio.buscarPorId(id);
            return ResponseEntity.ok(exencion);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ExencionCuenta> crear(@RequestBody ExencionCuenta exencionCuenta) {
        try {
            ExencionCuenta nuevaExencion = exencionCuentaServicio.crear(exencionCuenta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaExencion);
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExencionCuenta> actualizar(
            @PathVariable Integer id,
            @RequestBody ExencionCuenta exencionCuenta) {
        try {
            ExencionCuenta exencionActualizada = exencionCuentaServicio.actualizar(id, exencionCuenta);
            return ResponseEntity.ok(exencionActualizada);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            exencionCuentaServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ExencionCuenta>> buscarPorNombre(
            @RequestParam String nombre) {
        try {
            List<ExencionCuenta> exenciones = exencionCuentaServicio.buscarPorNombreParcial(nombre);
            return ResponseEntity.ok(exenciones);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/comision/{idComision}")
    public ResponseEntity<List<ExencionCuenta>> buscarPorComision(
            @PathVariable Integer idComision) {
        try {
            List<ExencionCuenta> exenciones = exencionCuentaServicio.buscarPorComision(idComision);
            return ResponseEntity.ok(exenciones);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }
}