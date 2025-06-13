package com.banquito.core.aplicacion.clientes.servicio;

import com.banquito.core.aplicacion.clientes.modelo.TelefonoCliente;
import com.banquito.core.aplicacion.clientes.repositorio.TelefonoClienteRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.ClienteRepositorio;
import com.banquito.core.aplicacion.clientes.excepcion.CrearTelefonoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.TelefonoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.ActualizarTelefonoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.ClienteNoEncontradoExcepcion;
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
public class TelefonoClienteServicio {

    private static final Logger logger = LoggerFactory.getLogger(TelefonoClienteServicio.class);
    
    private static final int MAX_TELEFONOS_POR_CLIENTE = 4;
    private static final int MIN_LONGITUD_NUMERO = 7;
    private static final int MAX_LONGITUD_NUMERO = 15;

    private final TelefonoClienteRepositorio telefonoRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public TelefonoClienteServicio(TelefonoClienteRepositorio telefonoRepositorio,
                                  ClienteRepositorio clienteRepositorio) {
        this.telefonoRepositorio = telefonoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        logger.info("TelefonoClienteServicio inicializado correctamente");
    }

    /**
     * Crear un nuevo teléfono para un cliente
     */
    public TelefonoCliente crear(TelefonoCliente telefono) {
        logger.info("Iniciando creación de teléfono para cliente ID: {}", telefono.getIdCliente());
        
        try {
            validarDatosObligatorios(telefono);
            validarClienteExiste(telefono.getIdCliente());
            validarLimiteTelefonos(telefono.getIdCliente());
            validarNumeroUnico(telefono.getNumeroTelefono());
            validarTelefonoPrincipal(telefono);
            
            telefono.setEstado(TelefonoCliente.Estado.ACTIVO);
            telefono.setFechaCreacion(new Date());
            telefono.setFechaActualizacion(new Date());
            
            TelefonoCliente telefonoCreado = telefonoRepositorio.save(telefono);
            logger.info("Teléfono creado exitosamente con ID: {}", telefonoCreado.getIdTelefono());
            
            return telefonoCreado;
        } catch (Exception e) {
            logger.error("Error al crear teléfono para cliente ID {}: {}", telefono.getIdCliente(), e.getMessage(), e);
            throw new CrearTelefonoExcepcion("Error al crear teléfono: " + e.getMessage());
        }
    }

    /**
     * Buscar teléfono por ID
     */
    @Transactional(readOnly = true)
    public TelefonoCliente buscarPorId(Integer id) {
        logger.debug("Buscando teléfono por ID: {}", id);
        
        TelefonoCliente telefono = telefonoRepositorio.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Teléfono no encontrado con ID: {}", id);
                    return new TelefonoNoEncontradoExcepcion(id);
                });
        
        logger.debug("Teléfono encontrado: {}", telefono.getIdTelefono());
        return telefono;
    }

    /**
     * Limpiar relaciones de teléfonos para evitar referencias circulares
     */


    /**
     * Listar todos los teléfonos con paginación
     */
    @Transactional(readOnly = true)
    public Page<TelefonoCliente> listarTodos(Pageable pageable) {
        logger.debug("Listando todos los teléfonos con paginación: página {}, tamaño {}", 
                    pageable.getPageNumber(), pageable.getPageSize());
        
        Page<TelefonoCliente> telefonos = telefonoRepositorio.findAll(pageable);
        logger.debug("Encontrados {} teléfonos", telefonos.getTotalElements());
        
        return telefonos;
    }

    /**
     * Obtener teléfonos por cliente
     */
    @Transactional(readOnly = true)
    public List<TelefonoCliente> obtenerPorCliente(Integer idCliente) {
        logger.debug("Obteniendo teléfonos por cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        List<TelefonoCliente> telefonos = telefonoRepositorio.findByIdCliente(idCliente);
        
        logger.debug("Encontrados {} teléfonos para cliente ID: {}", telefonos.size(), idCliente);
        return telefonos;
    }

    /**
     * Obtener teléfonos activos por cliente
     */
    @Transactional(readOnly = true)
    public List<TelefonoCliente> obtenerActivosPorCliente(Integer idCliente) {
        logger.debug("Obteniendo teléfonos activos por cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        List<TelefonoCliente> telefonos = telefonoRepositorio.findByIdClienteAndEstado(idCliente, TelefonoCliente.Estado.ACTIVO);
        
        logger.debug("Encontrados {} teléfonos activos para cliente ID: {}", telefonos.size(), idCliente);
        return telefonos;
    }

    /**
     * Obtener teléfonos por cliente con paginación
     */
    @Transactional(readOnly = true)
    public Page<TelefonoCliente> obtenerPorCliente(Integer idCliente, Pageable pageable) {
        logger.debug("Obteniendo teléfonos por cliente ID: {} con paginación", idCliente);
        
        validarClienteExiste(idCliente);
        Page<TelefonoCliente> telefonos = telefonoRepositorio.findByIdCliente(idCliente, pageable);
        
        logger.debug("Encontrados {} teléfonos para cliente ID: {}", telefonos.getTotalElements(), idCliente);
        return telefonos;
    }

    /**
     * Buscar teléfonos por tipo
     */
    @Transactional(readOnly = true)
    public List<TelefonoCliente> buscarPorTipo(TelefonoCliente.TipoTelefono tipoTelefono) {
        logger.debug("Buscando teléfonos por tipo: {}", tipoTelefono);
        
        List<TelefonoCliente> telefonos = telefonoRepositorio.findByTipo(tipoTelefono);
        logger.debug("Encontrados {} teléfonos de tipo: {}", telefonos.size(), tipoTelefono);
        
        return telefonos;
    }

    /**
     * Buscar teléfono por número
     */
    @Transactional(readOnly = true)
    public Optional<TelefonoCliente> buscarPorNumero(String numeroTelefono) {
        logger.debug("Buscando teléfono por número: {}", numeroTelefono);
        
        Optional<TelefonoCliente> telefono = telefonoRepositorio.findByNumeroTelefono(numeroTelefono);
        logger.debug("Teléfono encontrado por número {}: {}", numeroTelefono, telefono.isPresent());
        
        return telefono;
    }

    /**
     * Obtener teléfono principal del cliente (móvil)
     */
    @Transactional(readOnly = true)
    public Optional<TelefonoCliente> obtenerTelefonoPrincipal(Integer idCliente) {
        logger.debug("Obteniendo teléfono principal para cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        Optional<TelefonoCliente> telefono = telefonoRepositorio.findByIdClienteAndTipoAndEstado(
                idCliente, TelefonoCliente.TipoTelefono.CELULAR, TelefonoCliente.Estado.ACTIVO);
        
        logger.debug("Teléfono principal encontrado para cliente ID {}: {}", idCliente, telefono.isPresent());
        return telefono;
    }

    /**
     * Actualizar teléfono existente
     */
    public TelefonoCliente actualizar(TelefonoCliente telefono) {
        logger.info("Actualizando teléfono ID: {}", telefono.getIdTelefono());
        
        TelefonoCliente telefonoExistente = buscarPorId(telefono.getIdTelefono());
        
        try {
            if (telefono.getNumeroTelefono() != null) {
                validarNumeroTelefono(telefono.getNumeroTelefono());
                if (!telefono.getNumeroTelefono().equals(telefonoExistente.getNumeroTelefono())) {
                    validarNumeroUnico(telefono.getNumeroTelefono());
                }
                telefonoExistente.setNumeroTelefono(telefono.getNumeroTelefono());
            }

            if (telefono.getTipo() != null) {
                telefonoExistente.setTipo(telefono.getTipo());
            }

            if (telefono.getEstado() != null) {
                telefonoExistente.setEstado(telefono.getEstado());
            }

            telefonoExistente.setFechaActualizacion(new Date());

            TelefonoCliente telefonoActualizado = telefonoRepositorio.save(telefonoExistente);
            logger.info("Teléfono actualizado exitosamente ID: {}", telefonoActualizado.getIdTelefono());
            
            return telefonoActualizado;
        } catch (RuntimeException e) {
            logger.error("Error al actualizar teléfono ID {}: {}", telefono.getIdTelefono(), e.getMessage(), e);
            throw new ActualizarTelefonoExcepcion("Error al actualizar teléfono: " + e.getMessage());
        }
    }

    /**
     * Cambiar tipo de teléfono
     */
    public TelefonoCliente cambiarTipo(Integer id, TelefonoCliente.TipoTelefono nuevoTipo) {
        logger.info("Cambiando tipo de teléfono ID: {} a {}", id, nuevoTipo);
        
        TelefonoCliente telefono = buscarPorId(id);
        
        if (nuevoTipo == TelefonoCliente.TipoTelefono.CELULAR) {
            validarTelefonoPrincipalUnico(telefono.getIdCliente(), id);
        }
        
        telefono.setTipo(nuevoTipo);
        telefono.setFechaActualizacion(new Date());
        
        TelefonoCliente telefonoActualizado = telefonoRepositorio.save(telefono);
        logger.info("Tipo de teléfono cambiado exitosamente ID: {}", id);
        
        return telefonoActualizado;
    }

    /**
     * Eliminar teléfono lógicamente
     */
    public void eliminarLogicamente(Integer id) {
        logger.info("Eliminando lógicamente teléfono ID: {}", id);
        
        TelefonoCliente telefono = buscarPorId(id);
        telefono.setEstado(TelefonoCliente.Estado.INACTIVO);
        telefono.setFechaActualizacion(new Date());
        
        telefonoRepositorio.save(telefono);
        logger.info("Teléfono eliminado lógicamente ID: {}", id);
    }

    /**
     * Activar teléfono
     */
    public TelefonoCliente activar(Integer id) {
        logger.info("Activando teléfono ID: {}", id);
        
        TelefonoCliente telefono = buscarPorId(id);
        telefono.setEstado(TelefonoCliente.Estado.ACTIVO);
        telefono.setFechaActualizacion(new Date());
        
        TelefonoCliente telefonoActivado = telefonoRepositorio.save(telefono);
        logger.info("Teléfono activado exitosamente ID: {}", id);
        
        return telefonoActivado;
    }

    /**
     * Buscar teléfonos por número parcial
     */
    @Transactional(readOnly = true)
    public List<TelefonoCliente> buscarPorNumeroPartial(String numeroPartial) {
        logger.debug("Buscando teléfonos por número parcial: {}", numeroPartial);
        
        List<TelefonoCliente> telefonos = telefonoRepositorio.findByNumeroTelefonoContaining(numeroPartial);
        logger.debug("Encontrados {} teléfonos con número parcial: {}", telefonos.size(), numeroPartial);
        
        return telefonos;
    }

    /**
     * Obtener estadísticas de teléfonos por cliente
     */
    @Transactional(readOnly = true)
    public TelefonoEstadisticas obtenerEstadisticasPorCliente(Integer idCliente) {
        logger.debug("Obteniendo estadísticas de teléfonos para cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        
        List<TelefonoCliente> todosTelefonos = telefonoRepositorio.findByIdCliente(idCliente);
        List<TelefonoCliente> telefonosActivos = telefonoRepositorio.findByIdClienteAndEstado(idCliente, TelefonoCliente.Estado.ACTIVO);
        
        boolean tieneMovil = telefonosActivos.stream()
                .anyMatch(t -> t.getTipo() == TelefonoCliente.TipoTelefono.CELULAR);

        TelefonoEstadisticas estadisticas = new TelefonoEstadisticas();
        estadisticas.setTotalTelefonos(todosTelefonos.size());
        estadisticas.setTelefonosActivos(telefonosActivos.size());
        estadisticas.setTieneMovil(tieneMovil);

        logger.debug("Estadísticas calculadas para cliente ID {}: total={}, activos={}, tieneMovil={}", 
                    idCliente, estadisticas.getTotalTelefonos(), estadisticas.getTelefonosActivos(), estadisticas.isTieneMovil());
        
        return estadisticas;
    }

    // Métodos de validación privados

    private void validarDatosObligatorios(TelefonoCliente telefono) {
        if (telefono == null) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo");
        }
        if (telefono.getIdCliente() == null) {
            throw new IllegalArgumentException("El ID del cliente es requerido");
        }
        if (telefono.getNumeroTelefono() == null || telefono.getNumeroTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono es requerido");
        }
        if (telefono.getTipo() == null) {
            throw new IllegalArgumentException("El tipo de teléfono es requerido");
        }
        
        validarNumeroTelefono(telefono.getNumeroTelefono());
    }

    private void validarClienteExiste(Integer idCliente) {
        if (!clienteRepositorio.existsById(idCliente)) {
            logger.warn("Cliente no encontrado ID: {}", idCliente);
            throw new ClienteNoEncontradoExcepcion(idCliente);
        }
    }

    private void validarLimiteTelefonos(Integer idCliente) {
        long cantidadTelefonos = telefonoRepositorio.countByIdClienteAndEstado(idCliente, TelefonoCliente.Estado.ACTIVO);
        if (cantidadTelefonos >= MAX_TELEFONOS_POR_CLIENTE) {
            logger.warn("Cliente ID {} ha alcanzado el límite máximo de teléfonos: {}", idCliente, MAX_TELEFONOS_POR_CLIENTE);
            throw new CrearTelefonoExcepcion("El cliente ha alcanzado el límite máximo de " + MAX_TELEFONOS_POR_CLIENTE + " teléfonos");
        }
    }

    private void validarNumeroUnico(String numeroTelefono) {
        if (telefonoRepositorio.findByNumeroTelefono(numeroTelefono).isPresent()) {
            logger.warn("Número de teléfono ya existe: {}", numeroTelefono);
            throw new CrearTelefonoExcepcion("El número de teléfono ya está registrado: " + numeroTelefono);
        }
    }

    private void validarTelefonoPrincipal(TelefonoCliente telefono) {
        if (telefono.getTipo() == TelefonoCliente.TipoTelefono.CELULAR) {
            validarTelefonoPrincipalUnico(telefono.getIdCliente(), null);
        }
    }

    private void validarTelefonoPrincipalUnico(Integer idCliente, Integer idExcluir) {
        Optional<TelefonoCliente> telefonoExistente = telefonoRepositorio.findByIdClienteAndTipoAndEstado(
                idCliente, TelefonoCliente.TipoTelefono.CELULAR, TelefonoCliente.Estado.ACTIVO);
        
        if (telefonoExistente.isPresent() && 
            (idExcluir == null || !telefonoExistente.get().getIdTelefono().equals(idExcluir))) {
            logger.warn("Cliente ID {} ya tiene un teléfono celular principal", idCliente);
            throw new CrearTelefonoExcepcion("El cliente ya tiene un teléfono celular principal");
        }
    }

    private void validarNumeroTelefono(String numeroTelefono) {
        if (numeroTelefono == null || numeroTelefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono no puede estar vacío");
        }
        
        String numeroLimpio = numeroTelefono.replaceAll("[^0-9]", "");
        
        if (numeroLimpio.length() < MIN_LONGITUD_NUMERO || numeroLimpio.length() > MAX_LONGITUD_NUMERO) {
            throw new IllegalArgumentException("El número de teléfono debe tener entre " + MIN_LONGITUD_NUMERO + 
                                             " y " + MAX_LONGITUD_NUMERO + " dígitos");
        }
        
        if (numeroLimpio.startsWith("0")) {
            throw new IllegalArgumentException("El número de teléfono no puede comenzar con 0");
        }
        
        if (numeroLimpio.startsWith("09") && numeroLimpio.length() != 10) {
            throw new IllegalArgumentException("Los números celulares deben tener exactamente 10 dígitos");
        }
        
        if ((numeroLimpio.startsWith("02") || numeroLimpio.startsWith("03") || 
             numeroLimpio.startsWith("04") || numeroLimpio.startsWith("05") || 
             numeroLimpio.startsWith("06") || numeroLimpio.startsWith("07")) && 
            numeroLimpio.length() != 9) {
            throw new IllegalArgumentException("Los números fijos deben tener exactamente 9 dígitos");
        }
    }

    public static class TelefonoEstadisticas {
        private long totalTelefonos;
        private long telefonosActivos;
        private boolean tieneMovil;

        public long getTotalTelefonos() { return totalTelefonos; }
        public void setTotalTelefonos(long totalTelefonos) { this.totalTelefonos = totalTelefonos; }

        public long getTelefonosActivos() { return telefonosActivos; }
        public void setTelefonosActivos(long telefonosActivos) { this.telefonosActivos = telefonosActivos; }

        public boolean isTieneMovil() { return tieneMovil; }
        public void setTieneMovil(boolean tieneMovil) { this.tieneMovil = tieneMovil; }
    }
}