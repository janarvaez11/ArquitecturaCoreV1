package com.banquito.core.aplicacion.clientes.controlador;

import com.banquito.core.aplicacion.clientes.modelo.DireccionCliente;
import com.banquito.core.aplicacion.clientes.servicio.DireccionClienteServicio;
import com.banquito.core.aplicacion.clientes.excepcion.DireccionNoEncontradaExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearDireccionExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.ActualizarDireccionExcepcion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/direcciones")
public class DireccionClienteControlador {
 
    private final DireccionClienteServicio servicio;

    public DireccionClienteControlador(DireccionClienteServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<Page<DireccionCliente>> obtenerTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio,
            @RequestParam(defaultValue = "idDireccion") String ordenarPor,
            @RequestParam(defaultValue = "asc") String direccion) {
        
        Sort sort = direccion.equalsIgnoreCase("desc") ? 
            Sort.by(ordenarPor).descending() : Sort.by(ordenarPor).ascending();
        Pageable pageable = PageRequest.of(pagina, tamanio, sort);
        
        return ResponseEntity.ok(servicio.listarTodas(pageable));
    }

    @GetMapping("/todas")
    public ResponseEntity<List<DireccionCliente>> obtenerTodasSinPaginacion() {
        // Para este caso usaremos paginación con tamaño grande
        Pageable pageable = PageRequest.of(0, 10000);
        return ResponseEntity.ok(servicio.listarTodas(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionCliente> obtenerPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(servicio.buscarPorId(id));
        } catch (DireccionNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<DireccionCliente>> obtenerPorCliente(@PathVariable Integer idCliente) {
        try {
            return ResponseEntity.ok(servicio.obtenerPorCliente(idCliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cliente/{idCliente}/activas")
    public ResponseEntity<List<DireccionCliente>> obtenerActivasPorCliente(@PathVariable Integer idCliente) {
        try {
            return ResponseEntity.ok(servicio.obtenerActivasPorCliente(idCliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cliente/{idCliente}/principal")
    public ResponseEntity<DireccionCliente> obtenerDireccionPrincipal(@PathVariable Integer idCliente) {
        try {
            Optional<DireccionCliente> direccion = servicio.obtenerDireccionPrincipal(idCliente);
            return direccion.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<DireccionCliente>> buscarPorTipo(@PathVariable String tipo) {
        try {
            DireccionCliente.TipoDireccion tipoEnum = DireccionCliente.TipoDireccion.valueOf(tipo.toUpperCase());
            return ResponseEntity.ok(servicio.buscarPorTipo(tipoEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody DireccionCliente direccion) {
        try {
            DireccionCliente nuevaDireccion = servicio.crear(direccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDireccion);
        } catch (CrearDireccionExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                               .body(new ErrorResponse("Error al crear dirección", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body(new ErrorResponse("Error interno", "Error inesperado al crear la dirección"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody DireccionCliente direccion) {
        try {
        direccion.setIdDireccion(id);
            DireccionCliente direccionActualizada = servicio.actualizar(direccion);
            return ResponseEntity.ok(direccionActualizada);
        } catch (DireccionNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (ActualizarDireccionExcepcion e) {
            return ResponseEntity.badRequest()
                               .body(new ErrorResponse("Error al actualizar", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body(new ErrorResponse("Error interno", "Error inesperado al actualizar la dirección"));
        }
    }

    @PatchMapping("/{id}/tipo")
    public ResponseEntity<?> cambiarTipo(
            @PathVariable Integer id, 
            @RequestParam String nuevoTipo) {
        try {
            DireccionCliente.TipoDireccion tipoEnum = DireccionCliente.TipoDireccion.valueOf(nuevoTipo.toUpperCase());
            DireccionCliente direccion = servicio.cambiarTipo(id, tipoEnum);
            return ResponseEntity.ok(direccion);
        } catch (DireccionNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                               .body(new ErrorResponse("Error al cambiar tipo", "Tipo de dirección inválido: " + nuevoTipo));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                               .body(new ErrorResponse("Error al cambiar tipo", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            servicio.eliminarLogicamente(id);
            return ResponseEntity.noContent().build();
        } catch (DireccionNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                               .body(new ErrorResponse("Error al eliminar", e.getMessage()));
        }
    }

    @GetMapping("/cliente/{idCliente}/estadisticas")
    public ResponseEntity<?> obtenerEstadisticas(@PathVariable Integer idCliente) {
        try {
            DireccionClienteServicio.DireccionEstadisticas stats = servicio.obtenerEstadisticasPorCliente(idCliente);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                               .body(new ErrorResponse("Error al obtener estadísticas", e.getMessage()));
        }
    }

    // Clase para manejo de errores
    public static class ErrorResponse {
        private String error;
        private String mensaje;
        
        public ErrorResponse(String error, String mensaje) {
            this.error = error;
            this.mensaje = mensaje;
        }
        
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        
        public String getMensaje() { return mensaje; }
        public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    }

    }
