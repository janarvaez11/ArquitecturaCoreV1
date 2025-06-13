package com.banquito.core.aplicacion.clientes.controlador;

import com.banquito.core.aplicacion.clientes.excepcion.ActualizarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.NoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearExcepcion;
import com.banquito.core.aplicacion.clientes.modelo.Empresa;
import com.banquito.core.aplicacion.clientes.servicio.EmpresaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/empresas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmpresaControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaControlador.class);

    private final EmpresaServicio empresaServicio;

    public EmpresaControlador(EmpresaServicio empresaServicio) {
        this.empresaServicio = empresaServicio;
    }

    @GetMapping
    public ResponseEntity<Page<Empresa>> listarTodas(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio,
            @RequestParam(defaultValue = "fechaCreacion") String ordenarPor,
            @RequestParam(defaultValue = "desc") String direccion) {
        
        logger.info("Solicitud para listar empresas - página: {}, tamaño: {}, orden: {} {}", 
                   pagina, tamanio, ordenarPor, direccion);
        
        try {
            Sort sort = Sort.by(Sort.Direction.fromString(direccion), ordenarPor);
            Pageable pageable = PageRequest.of(pagina, tamanio, sort);
            Page<Empresa> empresas = empresaServicio.buscarTodas(pageable);
            
            logger.info("Listado de empresas exitoso - {} elementos encontrados", empresas.getTotalElements());
            return ResponseEntity.ok(empresas);
        } catch (Exception e) {
            logger.error("Error al listar empresas: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerPorId(@PathVariable Integer id) {
        logger.debug("Solicitud para obtener empresa por ID: {}", id);
        
        try {
            Empresa empresa = empresaServicio.buscarPorId(id);
            logger.info("Empresa encontrada exitosamente: ID {}", id);
            return ResponseEntity.ok(empresa);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Empresa no encontrada con ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/identificacion/{numero}")
    public ResponseEntity<Empresa> obtenerPorIdentificacion(@PathVariable String numero) {
        logger.debug("Solicitud para buscar empresa por identificación: {}", numero);
        
        try {
            Empresa empresa = empresaServicio.buscarPorNumeroIdentificacion(numero);
            logger.info("Empresa encontrada por identificación: {}", numero);
            return ResponseEntity.ok(empresa);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Empresa no encontrada con identificación: {}", numero);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Empresa>> listarPorEstado(@PathVariable String estado) {
        logger.info("Solicitud para listar empresas por estado: {}", estado);
        
        try {
            Empresa.Estado estadoEnum = Empresa.Estado.valueOf(estado.toUpperCase());
            List<Empresa> empresas = empresaServicio.buscarPorEstado(estadoEnum);
            logger.info("Listado por estado {} exitoso - {} elementos encontrados", estado, empresas.size());
            return ResponseEntity.ok(empresas);
        } catch (IllegalArgumentException e) {
            logger.error("Estado inválido: {}", estado);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Error al listar empresas por estado {}: {}", estado, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Empresa>> listarPorTipo(@PathVariable String tipo) {
        logger.info("Solicitud para listar empresas por tipo: {}", tipo);
        
        try {
            Empresa.TipoEmpresa tipoEnum = Empresa.TipoEmpresa.valueOf(tipo.toUpperCase());
            List<Empresa> empresas = empresaServicio.buscarPorTipo(tipoEnum);
            logger.info("Listado por tipo {} exitoso - {} elementos encontrados", tipo, empresas.size());
            return ResponseEntity.ok(empresas);
        } catch (IllegalArgumentException e) {
            logger.error("Tipo de empresa inválido: {}", tipo);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Error al listar empresas por tipo {}: {}", tipo, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/sector/{sector}")
    public ResponseEntity<List<Empresa>> listarPorSectorEconomico(@PathVariable String sector) {
        logger.info("Solicitud para listar empresas por sector económico: {}", sector);
        
        try {
            Empresa.SectorEconomico sectorEnum = Empresa.SectorEconomico.valueOf(sector.toUpperCase());
            List<Empresa> empresas = empresaServicio.buscarPorSectorEconomico(sectorEnum);
            logger.info("Listado por sector {} exitoso - {} elementos encontrados", sector, empresas.size());
            return ResponseEntity.ok(empresas);
        } catch (IllegalArgumentException e) {
            logger.error("Sector económico inválido: {}", sector);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Error al listar empresas por sector {}: {}", sector, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(
            @RequestBody Empresa empresa,
            @RequestHeader(value = "X-Request-ID", required = false) String requestId) {
        
        logger.info("Solicitud para crear empresa - Request ID: {}", requestId);
        
        try {
            Empresa nueva = empresaServicio.crear(empresa);
            logger.info("Empresa creada exitosamente con ID: {}", nueva.getIdEmpresa());
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (CrearExcepcion e) {
            logger.error("Error al crear empresa: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("COMPANY_CREATION_ERROR", e.getMessage()));
        } catch (Exception e) {
            logger.error("Error interno al crear empresa: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("INTERNAL_ERROR", "Error interno del servidor"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer id, 
            @RequestBody Empresa empresa) {
        
        logger.info("Solicitud para actualizar empresa ID: {}", id);
        
        try {
            empresa.setIdEmpresa(id);
            Empresa actualizada = empresaServicio.actualizar(empresa);
            logger.info("Empresa actualizada exitosamente: ID {}", id);
            return ResponseEntity.ok(actualizada);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Empresa no encontrada para actualización: ID {}", id);
            return ResponseEntity.notFound().build();
        } catch (ActualizarExcepcion e) {
            logger.error("Error al actualizar empresa ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("COMPANY_UPDATE_ERROR", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Integer id, 
            @RequestParam String estado) {
        
        logger.info("Solicitud para cambiar estado de empresa ID {} a: {}", id, estado);
        
        try {
            Empresa empresa = empresaServicio.cambiarEstado(id, estado);
            logger.info("Estado de empresa ID {} cambiado exitosamente a: {}", id, estado);
            return ResponseEntity.ok(empresa);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Empresa no encontrada para cambio de estado: ID {}", id);
            return ResponseEntity.notFound().build();
        } catch (ActualizarExcepcion e) {
            logger.error("Error al cambiar estado de empresa ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("STATE_CHANGE_ERROR", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<?> activar(@PathVariable Integer id) {
        logger.info("Solicitud para activar empresa ID: {}", id);
        
        try {
            Empresa empresa = empresaServicio.activar(id);
            logger.info("Empresa activada exitosamente: ID {}", id);
            return ResponseEntity.ok(empresa);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Empresa no encontrada para activación: ID {}", id);
            return ResponseEntity.notFound().build();
        } catch (ActualizarExcepcion e) {
            logger.error("Error al activar empresa ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("ACTIVATION_ERROR", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/inactivar")
    public ResponseEntity<?> inactivar(@PathVariable Integer id) {
        logger.info("Solicitud para inactivar empresa ID: {}", id);
        
        try {
            Empresa empresa = empresaServicio.inactivar(id);
            logger.info("Empresa inactivada exitosamente: ID {}", id);
            return ResponseEntity.ok(empresa);
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Empresa no encontrada para inactivación: ID {}", id);
            return ResponseEntity.notFound().build();
        } catch (ActualizarExcepcion e) {
            logger.error("Error al inactivar empresa ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("INACTIVATION_ERROR", e.getMessage()));
        }
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<EmpresaServicio.EmpresaEstadisticas> obtenerEstadisticas() {
        logger.info("Solicitud para obtener estadísticas de empresas");
        
        try {
            EmpresaServicio.EmpresaEstadisticas estadisticas = empresaServicio.obtenerEstadisticas();
            logger.info("Estadísticas de empresas obtenidas exitosamente");
            return ResponseEntity.ok(estadisticas);
        } catch (Exception e) {
            logger.error("Error al obtener estadísticas de empresas: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        logger.info("Solicitud para eliminar empresa ID: {}", id);
        
        try {
            empresaServicio.eliminar(id);
            logger.info("Empresa eliminada exitosamente: ID {}", id);
            return ResponseEntity.noContent().build();
        } catch (NoEncontradoExcepcion e) {
            logger.warn("Empresa no encontrada para eliminación: ID {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error al eliminar empresa ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("DELETION_ERROR", "Error al eliminar empresa"));
        }
    }

    // Manejo de excepciones globales para este controlador
    @ExceptionHandler(NoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponse> manejarEmpresaNoEncontrada(NoEncontradoExcepcion e) {
        logger.warn("Excepción de empresa no encontrada: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("COMPANY_NOT_FOUND", e.getMessage()));
    }

    @ExceptionHandler(CrearExcepcion.class)
    public ResponseEntity<ErrorResponse> manejarErrorCreacion(CrearExcepcion e) {
        logger.error("Excepción de creación de empresa: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("COMPANY_CREATION_ERROR", e.getMessage()));
    }

    @ExceptionHandler(ActualizarExcepcion.class)
    public ResponseEntity<ErrorResponse> manejarErrorActualizacion(ActualizarExcepcion e) {
        logger.error("Excepción de actualización de empresa: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("COMPANY_UPDATE_ERROR", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarErrorGeneral(Exception e) {
        logger.error("Excepción general en controlador de empresas: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("INTERNAL_ERROR", "Error interno del servidor"));
    }

    // Clase para respuestas de error estructuradas
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
