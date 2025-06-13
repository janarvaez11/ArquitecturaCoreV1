package com.banquito.core.aplicacion.clientes.controlador;

import com.banquito.core.aplicacion.clientes.modelo.TelefonoCliente;
import com.banquito.core.aplicacion.clientes.servicio.TelefonoClienteServicio;
import com.banquito.core.aplicacion.clientes.excepcion.TelefonoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearTelefonoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.ActualizarTelefonoExcepcion;

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
@RequestMapping("/v1/telefonos")
public class TelefonoClienteControlador {
/* 
    private final TelefonoClienteServicio servicio;

    public TelefonoClienteControlador(TelefonoClienteServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<Page<TelefonoCliente>> obtenerTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio,
            @RequestParam(defaultValue = "idTelefonoCliente") String ordenarPor,
            @RequestParam(defaultValue = "asc") String direccion) {
        
        Sort sort = direccion.equalsIgnoreCase("desc") ? 
            Sort.by(ordenarPor).descending() : Sort.by(ordenarPor).ascending();
        Pageable pageable = PageRequest.of(pagina, tamanio, sort);
        
        return ResponseEntity.ok(servicio.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefonoCliente> obtenerPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(servicio.buscarPorId(id));
        } catch (TelefonoNoEncontradoExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<TelefonoCliente>> obtenerPorCliente(@PathVariable Integer idCliente) {
        try {
            return ResponseEntity.ok(servicio.obtenerPorCliente(idCliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cliente/{idCliente}/activos")
    public ResponseEntity<List<TelefonoCliente>> obtenerActivosPorCliente(@PathVariable Integer idCliente) {
        try {
            return ResponseEntity.ok(servicio.obtenerActivosPorCliente(idCliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cliente/{idCliente}/principal")
    public ResponseEntity<TelefonoCliente> obtenerTelefonoPrincipal(@PathVariable Integer idCliente) {
        try {
            Optional<TelefonoCliente> telefono = servicio.obtenerTelefonoPrincipal(idCliente);
            return telefono.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<TelefonoCliente>> buscarPorTipo(@PathVariable String tipo) {
        try {
            return ResponseEntity.ok(servicio.buscarPorTipo(tipo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<TelefonoCliente> buscarPorNumero(@PathVariable String numero) {
        try {
            Optional<TelefonoCliente> telefono = servicio.buscarPorNumero(numero);
            return telefono.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TelefonoCliente>> buscarPorNumeroPartial(@RequestParam String numeroPartial) {
        try {
            return ResponseEntity.ok(servicio.buscarPorNumeroPartial(numeroPartial));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody TelefonoCliente telefono) {
        try {
            TelefonoCliente nuevoTelefono = servicio.crear(telefono);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTelefono);
        } catch (CrearTelefonoExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                               .body(new ErrorResponse("Error al crear teléfono", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body(new ErrorResponse("Error interno", "Error inesperado al crear el teléfono"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody TelefonoCliente telefono) {
        try {
            telefono.setIdTelefonoCliente(id);
            TelefonoCliente telefonoActualizado = servicio.actualizar(telefono);
            return ResponseEntity.ok(telefonoActualizado);
        } catch (TelefonoNoEncontradoExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (ActualizarTelefonoExcepcion e) {
            return ResponseEntity.badRequest()
                               .body(new ErrorResponse("Error al actualizar", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body(new ErrorResponse("Error interno", "Error inesperado al actualizar el teléfono"));
        }
    }

    @PatchMapping("/{id}/tipo")
    public ResponseEntity<?> cambiarTipo(
            @PathVariable Integer id, 
            @RequestParam String nuevoTipo) {
        try {
            TelefonoCliente telefono = servicio.cambiarTipo(id, nuevoTipo);
            return ResponseEntity.ok(telefono);
        } catch (TelefonoNoEncontradoExcepcion e) {
            return ResponseEntity.notFound().build();
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
        } catch (TelefonoNoEncontradoExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                               .body(new ErrorResponse("Error al eliminar", e.getMessage()));
        }
    }

    @GetMapping("/cliente/{idCliente}/estadisticas")
    public ResponseEntity<?> obtenerEstadisticas(@PathVariable Integer idCliente) {
        try {
            TelefonoClienteServicio.TelefonoEstadisticas stats = servicio.obtenerEstadisticasPorCliente(idCliente);
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
*/
    }
