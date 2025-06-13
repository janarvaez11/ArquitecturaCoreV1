package com.banquito.core.aplicacion.cuentas.controlador;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.banquito.core.aplicacion.cuentas.modelo.CuentaCliente;
import com.banquito.core.aplicacion.cuentas.servicio.CuentaClienteServicio;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;

@RestController
@RequestMapping("/api/cuentas-clientes")
@CrossOrigin(maxAge = 3600)
public class CuentaClienteControlador {

    private final CuentaClienteServicio cuentaClienteServicio;

    public CuentaClienteControlador(CuentaClienteServicio cuentaClienteServicio) {
        this.cuentaClienteServicio = cuentaClienteServicio;
    }

    @GetMapping
    public ResponseEntity<List<CuentaCliente>> listarTodos() {
        try {
            List<CuentaCliente> cuentas = cuentaClienteServicio.listarTodos();
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<CuentaCliente>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        try {
            Page<CuentaCliente> cuentas = cuentaClienteServicio.listarTodosPaginado(PageRequest.of(pagina, tamanio));
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaCliente> buscarPorId(@PathVariable Integer id) {
        try {
            CuentaCliente cuenta = cuentaClienteServicio.buscarPorId(id);
            return ResponseEntity.ok(cuenta);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CuentaCliente> crear(@RequestBody CuentaCliente cuentaCliente) {
        try {
            CuentaCliente nuevaCuenta = cuentaClienteServicio.crear(cuentaCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaCliente> actualizar(
            @PathVariable Integer id,
            @RequestBody CuentaCliente cuentaCliente) {
        try {
            CuentaCliente cuentaActualizada = cuentaClienteServicio.actualizar(id, cuentaCliente);
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
            cuentaClienteServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<CuentaCliente>> buscarPorCliente(@PathVariable Integer idCliente) {
        try {
            List<CuentaCliente> cuentas = cuentaClienteServicio.buscarPorCliente(idCliente);
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CuentaCliente>> buscarPorEstado(@PathVariable String estado) {
        try {
            List<CuentaCliente> cuentas = cuentaClienteServicio.buscarPorEstado(estado);
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/saldo-disponible")
    public ResponseEntity<List<CuentaCliente>> buscarPorSaldoDisponibleMayorQue(
            @RequestParam BigDecimal saldoMinimo) {
        try {
            List<CuentaCliente> cuentas = cuentaClienteServicio.buscarPorSaldoDisponibleMayorQue(saldoMinimo);
            return ResponseEntity.ok(cuentas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/numero-cuenta/{numeroCuenta}")
    public ResponseEntity<CuentaCliente> buscarPorNumeroCuenta(@PathVariable String numeroCuenta) {
        try {
            CuentaCliente cuenta = cuentaClienteServicio.buscarPorNumeroCuenta(numeroCuenta);
            return ResponseEntity.ok(cuenta);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }
}