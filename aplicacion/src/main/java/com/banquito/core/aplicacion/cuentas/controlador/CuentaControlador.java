package com.banquito.core.aplicacion.cuentas.controlador;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.cuentas.modelo.Cuenta;
import com.banquito.core.aplicacion.cuentas.servicio.CuentaServicio;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;

@RestController
@RequestMapping("/api/cuentas")
@CrossOrigin(maxAge = 3600)
public class CuentaControlador {

    private final CuentaServicio cuentaServicio;

    public CuentaControlador(CuentaServicio cuentaServicio) {
        this.cuentaServicio = cuentaServicio;
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> listarTodos() {
        try {
            List<Cuenta> cuentas = cuentaServicio.listarTodos();
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Cuenta>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        try {
            Page<Cuenta> cuentas = cuentaServicio.listarTodosPaginado(PageRequest.of(pagina, tamanio));
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> buscarPorId(@PathVariable Integer id) {
        try {
            Cuenta cuenta = cuentaServicio.buscarPorId(id);
            return ResponseEntity.ok(cuenta);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cuenta> crear(@RequestBody Cuenta cuenta) {
        try {
            Cuenta nuevaCuenta = cuentaServicio.crear(cuenta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizar(
            @PathVariable Integer id,
            @RequestBody Cuenta cuenta) {
        try {
            Cuenta cuentaActualizada = cuentaServicio.actualizar(id, cuenta);
            return ResponseEntity.ok(cuentaActualizada);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            cuentaServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tipo-cuenta/{idTipoCuenta}")
    public ResponseEntity<List<Cuenta>> buscarPorTipoCuenta(@PathVariable Integer idTipoCuenta) {
        try {
            List<Cuenta> cuentas = cuentaServicio.buscarPorTipoCuenta(idTipoCuenta);
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Cuenta>> buscarPorEstado(@PathVariable String estado) {
        try {
            List<Cuenta> cuentas = cuentaServicio.buscarPorEstado(estado.toUpperCase());
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipo-cuenta/{idTipoCuenta}/estado/{estado}")
    public ResponseEntity<List<Cuenta>> buscarPorTipoCuentaYEstado(
            @PathVariable Integer idTipoCuenta,
            @PathVariable String estado) {
        try {
            List<Cuenta> cuentas = cuentaServicio.buscarPorTipoCuentaYEstado(idTipoCuenta, estado.toUpperCase());
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/codigo/{codigoCuenta}")
    public ResponseEntity<Cuenta> buscarPorCodigoCuenta(@PathVariable String codigoCuenta) {
        try {
            Cuenta cuenta = cuentaServicio.buscarPorCodigoCuenta(codigoCuenta);
            return ResponseEntity.ok(cuenta);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }
}