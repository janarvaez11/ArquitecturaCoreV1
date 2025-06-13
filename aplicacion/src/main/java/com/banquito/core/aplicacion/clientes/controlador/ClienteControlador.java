package com.banquito.core.aplicacion.clientes.controlador;

import com.banquito.core.aplicacion.clientes.excepcion.ActualizarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.NoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearExcepcion;
import com.banquito.core.aplicacion.clientes.modelo.Cliente;
import com.banquito.core.aplicacion.clientes.servicio.ClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/clientes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClienteControlador {

    private static final Logger logger = LoggerFactory.getLogger(ClienteControlador.class);

    private final ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> listarClientes(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio,
            @RequestParam(defaultValue = "fechaCreacion") String ordenarPor,
            @RequestParam(defaultValue = "desc") String direccion) {
        
        logger.info("Solicitud para listar clientes - página: {}, tamaño: {}, orden: {} {}", 
                   pagina, tamanio, ordenarPor, direccion);
        
        try {
            Sort sort = Sort.by(Sort.Direction.fromString(direccion), ordenarPor);
            Pageable pageable = PageRequest.of(pagina, tamanio, sort);
            Page<Cliente> clientes = clienteServicio.listarTodos(pageable);
            
            logger.info("Listado de clientes exitoso - {} elementos encontrados", clientes.getTotalElements());
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            logger.error("Error al listar clientes: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Integer id) {
        logger.debug("Solicitud para obtener cliente por ID: {}", id);
        
        try {
            Cliente cliente = clienteServicio.buscarPorId(id);
            logger.info("Cliente encontrado exitosamente: ID {}", id);
            return ResponseEntity.ok(cliente);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Cliente no encontrado con ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/completo")
    public ResponseEntity<Cliente> obtenerClienteCompleto(@PathVariable Integer id) {
        logger.debug("Solicitud para obtener cliente completo por ID: {}", id);
        
        try {
            Cliente cliente = clienteServicio.obtenerClienteCompleto(id);
            logger.info("Cliente completo obtenido exitosamente: ID {}", id);
            return ResponseEntity.ok(cliente);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Cliente no encontrado para obtener información completa: ID {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/identificacion/{numeroIdentificacion}")
    public ResponseEntity<Cliente> buscarPorIdentificacion(@PathVariable String numeroIdentificacion) {
        logger.debug("Solicitud para buscar cliente por identificación: {}", numeroIdentificacion);
        
        Optional<Cliente> cliente = clienteServicio.buscarPorNumeroIdentificacion(numeroIdentificacion);
        if (cliente.isPresent()) {
            logger.info("Cliente encontrado por identificación: {}", numeroIdentificacion);
            return ResponseEntity.ok(cliente.get());
        } else {
            logger.warn("Cliente no encontrado con identificación: {}", numeroIdentificacion);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Page<Cliente>> listarPorEstado(
            @PathVariable String estado,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio,
            @RequestParam(defaultValue = "fechaCreacion") String ordenarPor,
            @RequestParam(defaultValue = "desc") String direccion) {
        
        logger.info("Solicitud para listar clientes por estado: {}", estado);
        
        try {
            Sort sort = Sort.by(Sort.Direction.fromString(direccion), ordenarPor);
            Pageable pageable = PageRequest.of(pagina, tamanio, sort);
            Page<Cliente> clientes = clienteServicio.listarPorEstado(estado, pageable);
            
            logger.info("Listado por estado {} exitoso - {} elementos encontrados", estado, clientes.getTotalElements());
            return ResponseEntity.ok(clientes);
        } catch (ActualizarExcepcion e) {
            logger.error("Error al listar clientes por estado {}: {}", estado, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<Page<Cliente>> listarPorSucursal(
            @PathVariable Integer idSucursal,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio,
            @RequestParam(defaultValue = "fechaCreacion") String ordenarPor,
            @RequestParam(defaultValue = "desc") String direccion) {
        
        logger.info("Solicitud para listar clientes por sucursal: {}", idSucursal);
        
        try {
            Sort sort = Sort.by(Sort.Direction.fromString(direccion), ordenarPor);
            Pageable pageable = PageRequest.of(pagina, tamanio, sort);
            Page<Cliente> clientes = clienteServicio.listarPorSucursal(idSucursal, pageable);
            
            logger.info("Listado por sucursal {} exitoso - {} elementos encontrados", idSucursal, clientes.getTotalElements());
            return ResponseEntity.ok(clientes);
        } catch (CrearExcepcion e) {
            logger.error("Error al listar clientes por sucursal {}: {}", idSucursal, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Cliente>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio,
            @RequestParam(defaultValue = "nombre") String ordenarPor,
            @RequestParam(defaultValue = "asc") String direccion) {
        
        logger.info("Solicitud para buscar clientes por nombre: {}", nombre);
        
        try {
            Sort sort = Sort.by(Sort.Direction.fromString(direccion), ordenarPor);
            Pageable pageable = PageRequest.of(pagina, tamanio, sort);
            Page<Cliente> clientes = clienteServicio.buscarPorNombre(nombre, pageable);
            
            logger.info("Búsqueda por nombre '{}' exitosa - {} elementos encontrados", nombre, clientes.getTotalElements());
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            logger.error("Error al buscar clientes por nombre '{}': {}", nombre, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tipo-entidad/{tipoEntidad}")
    public ResponseEntity<Page<Cliente>> listarPorTipoEntidad(
            @PathVariable String tipoEntidad,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio,
            @RequestParam(defaultValue = "fechaCreacion") String ordenarPor,
            @RequestParam(defaultValue = "desc") String direccion) {
        
        logger.info("Solicitud para listar clientes por tipo de entidad: {}", tipoEntidad);
        
        try {
            Sort sort = Sort.by(Sort.Direction.fromString(direccion), ordenarPor);
            Pageable pageable = PageRequest.of(pagina, tamanio, sort);
            Page<Cliente> clientes = clienteServicio.listarPorTipoEntidad(tipoEntidad, pageable);
            
            logger.info("Listado por tipo entidad {} exitoso - {} elementos encontrados", tipoEntidad, clientes.getTotalElements());
            return ResponseEntity.ok(clientes);
        } catch (CrearExcepcion e) {
            logger.error("Error al listar clientes por tipo entidad {}: {}", tipoEntidad, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(
            @RequestBody Cliente cliente,
            @RequestHeader(value = "X-Request-ID", required = false) String requestId) {
        
        logger.info("Solicitud para crear cliente - Request ID: {}", requestId);
        
        try {
            Cliente clienteCreado = clienteServicio.crear(cliente);
            logger.info("Cliente creado exitosamente con ID: {}", clienteCreado.getIdCliente());
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
        } catch (CrearExcepcion e) {
            logger.error("Error al crear cliente: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("CLIENT_CREATION_ERROR", e.getMessage()));
        } catch (Exception e) {
            logger.error("Error interno al crear cliente: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("INTERNAL_ERROR", "Error interno del servidor"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(
            @PathVariable Integer id,
            @RequestBody Cliente cliente) {
        
        logger.info("Solicitud para actualizar cliente ID: {}", id);
        
        try {
            cliente.setIdCliente(id);
            Cliente clienteActualizado = clienteServicio.modificar(cliente);
            logger.info("Cliente actualizado exitosamente: ID {}", id);
            return ResponseEntity.ok(clienteActualizado);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Cliente no encontrado para actualización: ID {}", id);
            return ResponseEntity.notFound().build();
        } catch (ActualizarExcepcion e) {
            logger.error("Error al actualizar cliente ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("CLIENT_UPDATE_ERROR", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Integer id,
            @RequestParam String estado) {
        
        logger.info("Solicitud para cambiar estado del cliente ID {} a: {}", id, estado);
        
        try {
            Cliente cliente = clienteServicio.cambiarEstado(id, estado);
            logger.info("Estado del cliente ID {} cambiado exitosamente a: {}", id, estado);
            return ResponseEntity.ok(cliente);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Cliente no encontrado para cambio de estado: ID {}", id);
            return ResponseEntity.notFound().build();
        } catch (ActualizarExcepcion e) {
            logger.error("Error al cambiar estado del cliente ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("STATE_CHANGE_ERROR", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<?> activarCliente(@PathVariable Integer id) {
        logger.info("Solicitud para activar cliente ID: {}", id);
        
        try {
            Cliente cliente = clienteServicio.activarCliente(id);
            logger.info("Cliente activado exitosamente: ID {}", id);
            return ResponseEntity.ok(cliente);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Cliente no encontrado para activación: ID {}", id);
            return ResponseEntity.notFound().build();
        } catch (ActualizarExcepcion e) {
            logger.error("Error al activar cliente ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("ACTIVATION_ERROR", e.getMessage()));
        }
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<ClienteServicio.ClienteEstadisticas> obtenerEstadisticas() {
        logger.info("Solicitud para obtener estadísticas de clientes");
        
        try {
            ClienteServicio.ClienteEstadisticas estadisticas = clienteServicio.obtenerEstadisticas();
            logger.info("Estadísticas de clientes obtenidas exitosamente");
            return ResponseEntity.ok(estadisticas);
        } catch (Exception e) {
            logger.error("Error al obtener estadísticas de clientes: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Manejo de excepciones globales para este controlador
    @ExceptionHandler(NoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponse> manejarClienteNoEncontrado(NoEncontradoExcepcion e) {
        logger.warn("Excepción de cliente no encontrado: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("CLIENT_NOT_FOUND", e.getMessage()));
    }

    @ExceptionHandler(CrearExcepcion.class)
    public ResponseEntity<ErrorResponse> manejarErrorCreacion(CrearExcepcion e) {
        logger.error("Excepción de creación de cliente: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("CLIENT_CREATION_ERROR", e.getMessage()));
    }

    @ExceptionHandler(ActualizarExcepcion.class)
    public ResponseEntity<ErrorResponse> manejarErrorActualizacion(ActualizarExcepcion e) {
        logger.error("Excepción de actualización de cliente: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("CLIENT_UPDATE_ERROR", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarErrorGeneral(Exception e) {
        logger.error("Excepción general en controlador de clientes: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("INTERNAL_ERROR", "Error interno del servidor"));
    }

    // Clases para respuestas estructuradas
    public static class ErrorResponse {
        private String codigo;
        private String mensaje;
        private long timestamp;

        public ErrorResponse(String codigo, String mensaje) {
            this.codigo = codigo;
            this.mensaje = mensaje;
            this.timestamp = System.currentTimeMillis();
        }

        public String getCodigo() { return codigo; }
        public void setCodigo(String codigo) { this.codigo = codigo; }
        
        public String getMensaje() { return mensaje; }
        public void setMensaje(String mensaje) { this.mensaje = mensaje; }
        
        public long getTimestamp() { return timestamp; }
        public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    }
}