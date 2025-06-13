package com.banquito.core.aplicacion.clientes.servicio;

import com.banquito.core.aplicacion.clientes.excepcion.ActualizarDireccionExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearDireccionExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.DireccionNoEncontradaExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.ClienteNoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.modelo.DireccionCliente;
import com.banquito.core.aplicacion.clientes.modelo.Cliente;
import com.banquito.core.aplicacion.clientes.repositorio.DireccionClienteRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.ClienteRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DireccionClienteServicio {

    private static final Logger logger = LoggerFactory.getLogger(DireccionClienteServicio.class);
    
    private static final int MAX_DIRECCIONES_POR_CLIENTE = 5;
    private static final int MIN_LONGITUD_LINEA1 = 10;
    private static final int MAX_LONGITUD_LINEA1 = 150;
    private static final int LONGITUD_CODIGO_POSTAL = 6;

    private final DireccionClienteRepositorio direccionRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public DireccionClienteServicio(DireccionClienteRepositorio direccionRepositorio,
                                  ClienteRepositorio clienteRepositorio) {
        this.direccionRepositorio = direccionRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        logger.info("DireccionClienteServicio inicializado correctamente");
    }

    public DireccionCliente crear(DireccionCliente direccion) {
        logger.info("Iniciando creación de dirección para cliente ID: {}", direccion.getIdCliente());
        
        try {
            validarDatosObligatorios(direccion);
            validarClienteExiste(direccion.getIdCliente());
            validarLimiteDirecciones(direccion.getIdCliente());
            validarDireccionPrincipal(direccion);
            
            direccion.setEstado(DireccionCliente.Estado.ACTIVO);
            direccion.setFechaCreacion(new Date());
            direccion.setFechaActualizacion(new Date());
            
            DireccionCliente direccionCreada = direccionRepositorio.save(direccion);
            logger.info("Dirección creada exitosamente con ID: {} para cliente ID: {}", 
                       direccionCreada.getIdDireccion(), direccionCreada.getIdCliente());
            
            return direccionCreada;
        } catch (Exception e) {
            logger.error("Error al crear dirección para cliente ID {}: {}", 
                        direccion.getIdCliente(), e.getMessage(), e);
            throw new CrearDireccionExcepcion("Error al crear dirección: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public DireccionCliente buscarPorId(Integer id) {
        logger.debug("Buscando dirección por ID: {}", id);
        
        if (id == null) {
            logger.warn("ID de dirección es nulo");
            throw new IllegalArgumentException("El ID de la dirección no puede ser nulo");
        }
        
        DireccionCliente direccion = direccionRepositorio.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Dirección no encontrada con ID: {}", id);
                    return new DireccionNoEncontradaExcepcion(id);
                });
        
        limpiarRelacionesDireccion(direccion);
        logger.debug("Dirección encontrada: {}", direccion.getIdDireccion());
        return direccion;
    }

    private void limpiarRelacionesDireccion(DireccionCliente direccion) {
        if (direccion == null || direccion.getCliente() == null) return;
        
        direccion.getCliente().setTelefonos(null);
        direccion.getCliente().setDirecciones(null);
        direccion.getCliente().setContacto(null);
        
        if (direccion.getCliente().getPaisNacionalidad() != null) {
            direccion.getCliente().getPaisNacionalidad().setEstructurasGeograficas(null);
            direccion.getCliente().getPaisNacionalidad().setLocacionesGeograficas(null);
        }
        
        if (direccion.getCliente().getSucursal() != null) {
            direccion.getCliente().getSucursal().setLocacion(null);
            direccion.getCliente().getSucursal().setEntidadBancaria(null);
        }
    }

    @Transactional(readOnly = true)
    public Page<DireccionCliente> listarTodas(Pageable pageable) {
        logger.debug("Listando todas las direcciones con paginación: página {}, tamaño {}", 
                    pageable.getPageNumber(), pageable.getPageSize());
        
        Page<DireccionCliente> direcciones = direccionRepositorio.findAll(pageable);
        direcciones.getContent().forEach(this::limpiarRelacionesDireccion);
        
        logger.debug("Encontradas {} direcciones", direcciones.getTotalElements());
        return direcciones;
    }

    @Transactional(readOnly = true)
    public List<DireccionCliente> obtenerPorCliente(Integer idCliente) {
        logger.debug("Obteniendo direcciones por cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        List<DireccionCliente> direcciones = direccionRepositorio.findByIdCliente(idCliente);
        
        direcciones.forEach(this::limpiarRelacionesDireccion);
        
        logger.debug("Encontradas {} direcciones para cliente ID: {}", direcciones.size(), idCliente);
        return direcciones;
    }

    @Transactional(readOnly = true)
    public List<DireccionCliente> obtenerActivasPorCliente(Integer idCliente) {
        logger.debug("Obteniendo direcciones activas por cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        List<DireccionCliente> direcciones = direccionRepositorio.findByIdClienteAndEstado(idCliente, DireccionCliente.Estado.ACTIVO);
        
        direcciones.forEach(this::limpiarRelacionesDireccion);
        
        logger.debug("Encontradas {} direcciones activas para cliente ID: {}", direcciones.size(), idCliente);
        return direcciones;
    }

    @Transactional(readOnly = true)
    public Page<DireccionCliente> obtenerPorClientePaginado(Integer idCliente, Pageable pageable) {
        logger.debug("Obteniendo direcciones por cliente ID: {} con paginación", idCliente);
        
        validarClienteExiste(idCliente);
        Page<DireccionCliente> direcciones = direccionRepositorio.findByIdCliente(idCliente, pageable);
        
        logger.debug("Encontradas {} direcciones para cliente ID: {}", direcciones.getTotalElements(), idCliente);
        return direcciones;
    }

    @Transactional(readOnly = true)
    public List<DireccionCliente> buscarPorTipo(DireccionCliente.TipoDireccion tipo) {
        logger.debug("Buscando direcciones por tipo: {}", tipo);
        
        validarTipoDireccion(tipo);
        List<DireccionCliente> direcciones = direccionRepositorio.findByTipo(tipo);
        
        direcciones.forEach(this::limpiarRelacionesDireccion);
        
        logger.debug("Encontradas {} direcciones de tipo: {}", direcciones.size(), tipo);
        return direcciones;
    }

    @Transactional(readOnly = true)
    public Optional<DireccionCliente> obtenerDireccionPrincipal(Integer idCliente) {
        logger.debug("Obteniendo dirección principal para cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        Optional<DireccionCliente> direccion = direccionRepositorio.findByIdClienteAndTipoAndEstado(
                idCliente, DireccionCliente.TipoDireccion.DOMICILIO, DireccionCliente.Estado.ACTIVO);
        
        logger.debug("Dirección principal encontrada para cliente ID {}: {}", idCliente, direccion.isPresent());
        return direccion;
    }

    public DireccionCliente actualizar(DireccionCliente direccion) {
        logger.info("Actualizando dirección ID: {}", direccion.getIdDireccion());
        
        DireccionCliente direccionExistente = buscarPorId(direccion.getIdDireccion());
        
        try {
            if (direccion.getLinea1() != null) {
                validarLinea1(direccion.getLinea1());
                direccionExistente.setLinea1(direccion.getLinea1());
            }
            
            if (direccion.getLinea2() != null) {
                direccionExistente.setLinea2(direccion.getLinea2());
            }
            
            if (direccion.getCodigoPostal() != null) {
                validarCodigoPostal(direccion.getCodigoPostal());
                direccionExistente.setCodigoPostal(direccion.getCodigoPostal());
            }
            
            if (direccion.getCodigoGeografico() != null) {
                direccionExistente.setCodigoGeografico(direccion.getCodigoGeografico());
            }
            
            direccionExistente.setFechaActualizacion(new Date());
            
            DireccionCliente direccionActualizada = direccionRepositorio.save(direccionExistente);
            logger.info("Dirección actualizada exitosamente ID: {}", direccionActualizada.getIdDireccion());
            
            return direccionActualizada;
        } catch (Exception e) {
            logger.error("Error al actualizar dirección ID {}: {}", direccion.getIdDireccion(), e.getMessage(), e);
            throw new ActualizarDireccionExcepcion("Error al actualizar dirección: " + e.getMessage());
        }
    }

    public DireccionCliente cambiarTipo(Integer id, DireccionCliente.TipoDireccion nuevoTipo) {
        logger.info("Cambiando tipo de dirección ID: {} a {}", id, nuevoTipo);
        
        DireccionCliente direccion = buscarPorId(id);
        validarTipoDireccion(nuevoTipo);
        
        if (DireccionCliente.TipoDireccion.DOMICILIO.equals(nuevoTipo)) {
            validarDireccionPrincipalUnica(direccion.getIdCliente(), id);
        }
        
        direccion.setTipo(nuevoTipo);
        direccion.setFechaActualizacion(new Date());
        
        DireccionCliente direccionActualizada = direccionRepositorio.save(direccion);
        logger.info("Tipo de dirección cambiado exitosamente ID: {}", id);
        
        return direccionActualizada;
    }

    public DireccionCliente cambiarEstado(Integer id, DireccionCliente.Estado nuevoEstado) {
        logger.info("Cambiando estado de dirección ID: {} a {}", id, nuevoEstado);
        
        DireccionCliente direccion = buscarPorId(id);
        
        if (DireccionCliente.TipoDireccion.DOMICILIO.equals(direccion.getTipo()) && 
            DireccionCliente.Estado.INACTIVO.equals(nuevoEstado)) {
            long direccionesDomicilio = direccionRepositorio.countByIdClienteAndTipoAndEstado(
                    direccion.getIdCliente(), DireccionCliente.TipoDireccion.DOMICILIO, DireccionCliente.Estado.ACTIVO);
            if (direccionesDomicilio <= 1) {
                throw new ActualizarDireccionExcepcion("No se puede inactivar la única dirección de domicilio del cliente");
            }
        }
        
        direccion.setEstado(nuevoEstado);
        direccion.setFechaActualizacion(new Date());
        
        DireccionCliente direccionActualizada = direccionRepositorio.save(direccion);
        logger.info("Estado de dirección cambiado exitosamente ID: {}", id);
        
        return direccionActualizada;
    }

    public void eliminarLogicamente(Integer id) {
        logger.info("Eliminando lógicamente dirección ID: {}", id);
        cambiarEstado(id, DireccionCliente.Estado.INACTIVO);
    }

    public DireccionCliente activar(Integer id) {
        logger.info("Activando dirección ID: {}", id);
        return cambiarEstado(id, DireccionCliente.Estado.ACTIVO);
    }

    public DireccionCliente inactivar(Integer id) {
        logger.info("Inactivando dirección ID: {}", id);
        return cambiarEstado(id, DireccionCliente.Estado.INACTIVO);
    }

    @Transactional(readOnly = true)
    public List<DireccionCliente> buscarPorCodigoPostal(String codigoPostal) {
        logger.debug("Buscando direcciones por código postal: {}", codigoPostal);
        
        if (codigoPostal == null || codigoPostal.trim().isEmpty()) {
            logger.warn("Código postal es nulo o vacío");
            return List.of();
        }
        
        List<DireccionCliente> direcciones = direccionRepositorio.findByCodigoPostal(codigoPostal);
        logger.debug("Encontradas {} direcciones con código postal: {}", direcciones.size(), codigoPostal);
        
        return direcciones;
    }

    @Transactional(readOnly = true)
    public List<DireccionCliente> buscarPorTexto(String texto) {
        logger.debug("Buscando direcciones por texto: {}", texto);
        
        if (texto == null || texto.trim().isEmpty()) {
            logger.warn("Texto de búsqueda es nulo o vacío");
            return List.of();
        }
        
        List<DireccionCliente> direcciones = direccionRepositorio.findByLinea1ContainingIgnoreCase(texto);
        logger.debug("Encontradas {} direcciones que contienen: {}", direcciones.size(), texto);
        
        return direcciones;
    }

    @Transactional(readOnly = true)
    public DireccionEstadisticas obtenerEstadisticasPorCliente(Integer idCliente) {
        logger.debug("Obteniendo estadísticas de direcciones para cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        
        DireccionEstadisticas stats = new DireccionEstadisticas();
        stats.setTotalDirecciones(direccionRepositorio.countByIdCliente(idCliente));
        stats.setDireccionesActivas(direccionRepositorio.countByIdClienteAndEstado(idCliente, DireccionCliente.Estado.ACTIVO));
        stats.setTieneDomicilio(direccionRepositorio.existsByIdClienteAndTipoAndEstado(
                idCliente, DireccionCliente.TipoDireccion.DOMICILIO, DireccionCliente.Estado.ACTIVO));
        
        logger.debug("Estadísticas calculadas para cliente ID {}: {} direcciones totales, {} activas", 
                    idCliente, stats.getTotalDirecciones(), stats.getDireccionesActivas());
        
        return stats;
    }

    private void validarDatosObligatorios(DireccionCliente direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException("La dirección no puede ser nula");
        }
        
        if (direccion.getIdCliente() == null) {
            throw new CrearDireccionExcepcion("El cliente es obligatorio");
        }
        
        if (direccion.getTipo() == null) {
            throw new CrearDireccionExcepcion("El tipo de dirección es obligatorio");
        }
        
        validarTipoDireccion(direccion.getTipo());
        validarLinea1(direccion.getLinea1());
        
        if (direccion.getCodigoPostal() != null) {
            validarCodigoPostal(direccion.getCodigoPostal());
        }
        
        if (direccion.getCodigoGeografico() == null || direccion.getCodigoGeografico().trim().isEmpty()) {
            throw new CrearDireccionExcepcion("El código geográfico es obligatorio");
        }
    }

    private void validarClienteExiste(Integer idCliente) {
        if (!clienteRepositorio.existsById(idCliente)) {
            logger.warn("Cliente no encontrado ID: {}", idCliente);
            throw new ClienteNoEncontradoExcepcion(idCliente);
        }
    }

    private void validarLimiteDirecciones(Integer idCliente) {
        long cantidadDirecciones = direccionRepositorio.countByIdClienteAndEstado(idCliente, DireccionCliente.Estado.ACTIVO);
        if (cantidadDirecciones >= MAX_DIRECCIONES_POR_CLIENTE) {
            logger.warn("Cliente ID {} ha alcanzado el límite de direcciones: {}", idCliente, cantidadDirecciones);
            throw new CrearDireccionExcepcion("El cliente ha alcanzado el límite máximo de " + 
                    MAX_DIRECCIONES_POR_CLIENTE + " direcciones");
        }
    }

    private void validarDireccionPrincipal(DireccionCliente direccion) {
        if (DireccionCliente.TipoDireccion.DOMICILIO.equals(direccion.getTipo())) {
            validarDireccionPrincipalUnica(direccion.getIdCliente(), null);
        }
    }

    private void validarDireccionPrincipalUnica(Integer idCliente, Integer idExcluir) {
        boolean yaExisteDomicilio = direccionRepositorio.existsByIdClienteAndTipoAndEstado(
                idCliente, DireccionCliente.TipoDireccion.DOMICILIO, DireccionCliente.Estado.ACTIVO);
        
        if (yaExisteDomicilio && idExcluir == null) {
            logger.warn("Cliente ID {} ya tiene una dirección de domicilio activa", idCliente);
            throw new CrearDireccionExcepcion("El cliente ya tiene una dirección de domicilio activa");
        }
    }

    private void validarTipoDireccion(DireccionCliente.TipoDireccion tipo) {
        if (tipo == null) {
            throw new CrearDireccionExcepcion("Tipo de dirección inválido");
        }
    }

    private void validarLinea1(String linea1) {
        if (linea1 == null || linea1.trim().isEmpty()) {
            throw new CrearDireccionExcepcion("La línea 1 de la dirección es obligatoria");
        }
        
        if (linea1.trim().length() < MIN_LONGITUD_LINEA1) {
            throw new CrearDireccionExcepcion("La línea 1 debe tener al menos " + MIN_LONGITUD_LINEA1 + " caracteres");
        }
        
        if (linea1.trim().length() > MAX_LONGITUD_LINEA1) {
            throw new CrearDireccionExcepcion("La línea 1 no puede exceder " + MAX_LONGITUD_LINEA1 + " caracteres");
        }
    }

    private void validarCodigoPostal(String codigoPostal) {
        if (codigoPostal != null && !codigoPostal.trim().isEmpty()) {
            if (!codigoPostal.matches("\\d{" + LONGITUD_CODIGO_POSTAL + "}")) {
                throw new CrearDireccionExcepcion("El código postal debe tener " + LONGITUD_CODIGO_POSTAL + " dígitos numéricos");
            }
        }
    }

    public static class DireccionEstadisticas {
        private long totalDirecciones;
        private long direccionesActivas;
        private boolean tieneDomicilio;
        
        public long getTotalDirecciones() { return totalDirecciones; }
        public void setTotalDirecciones(long totalDirecciones) { this.totalDirecciones = totalDirecciones; }
        
        public long getDireccionesActivas() { return direccionesActivas; }
        public void setDireccionesActivas(long direccionesActivas) { this.direccionesActivas = direccionesActivas; }
        
        public boolean isTieneDomicilio() { return tieneDomicilio; }
        public void setTieneDomicilio(boolean tieneDomicilio) { this.tieneDomicilio = tieneDomicilio; }
    }
}
