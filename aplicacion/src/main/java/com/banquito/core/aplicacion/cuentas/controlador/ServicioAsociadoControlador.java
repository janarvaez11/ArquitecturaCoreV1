package com.banquito.core.aplicacion.cuentas.controlador;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.cuentas.modelo.ServicioAsociado;
import com.banquito.core.aplicacion.cuentas.modelo.ServicioTipoCuenta;
import com.banquito.core.aplicacion.cuentas.servicio.ServicioAsociadoServicio;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;

@RestController
@RequestMapping("/api/servicios-asociados")
@CrossOrigin(maxAge = 3600)
public class ServicioAsociadoControlador {

    private final ServicioAsociadoServicio servicioAsociadoServicio;

    public ServicioAsociadoControlador(ServicioAsociadoServicio servicioAsociadoServicio) {
        this.servicioAsociadoServicio = servicioAsociadoServicio;
    }

    @GetMapping
    public ResponseEntity<List<ServicioAsociado>> listarTodos() {
        try {
            List<ServicioAsociado> servicios = servicioAsociadoServicio.listarTodos();
            return ResponseEntity.ok(servicios);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<ServicioAsociado>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        try {
            Page<ServicioAsociado> servicios = servicioAsociadoServicio.listarTodosPaginado(PageRequest.of(pagina, tamanio));
            return ResponseEntity.ok(servicios);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioAsociado> buscarPorId(@PathVariable Integer id) {
        try {
            ServicioAsociado servicio = servicioAsociadoServicio.buscarPorId(id);
            return ResponseEntity.ok(servicio);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ServicioAsociado> crear(@RequestBody ServicioAsociado servicioAsociado) {
        try {
            ServicioAsociado nuevoServicio = servicioAsociadoServicio.crear(servicioAsociado);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoServicio);
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioAsociado> actualizar(
            @PathVariable Integer id,
            @RequestBody ServicioAsociado servicioAsociado) {
        try {
            ServicioAsociado servicioActualizado = servicioAsociadoServicio.actualizar(id, servicioAsociado);
            return ResponseEntity.ok(servicioActualizado);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            servicioAsociadoServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ServicioAsociado>> buscarPorNombre(
            @RequestParam String nombre) {
        try {
            List<ServicioAsociado> servicios = servicioAsociadoServicio.buscarPorNombre(nombre);
            return ResponseEntity.ok(servicios);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ServicioAsociado>> buscarPorEstado(
            @PathVariable String estado) {
        try {
            List<ServicioAsociado> servicios = servicioAsociadoServicio.buscarPorEstado(estado);
            return ResponseEntity.ok(servicios);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/{idServicio}/cuenta/{idCuenta}")
    public ResponseEntity<ServicioTipoCuenta> asignarServicioACuenta(
            @PathVariable Integer idServicio,
            @PathVariable Integer idCuenta) {
        try {
            ServicioTipoCuenta servicioTipoCuenta = servicioAsociadoServicio.asignarServicioACuenta(idServicio, idCuenta);
            return ResponseEntity.status(HttpStatus.CREATED).body(servicioTipoCuenta);
        } catch (CrearEntidadExcepcion | EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }
}