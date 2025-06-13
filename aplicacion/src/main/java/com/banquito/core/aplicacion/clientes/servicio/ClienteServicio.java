package com.banquito.core.aplicacion.clientes.servicio;

import com.banquito.core.aplicacion.general.repositorio.SucursalRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.PersonaRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.ClienteRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.EmpresaRepositorio;
import com.banquito.core.aplicacion.general.repositorio.PaisRepositorio;
import com.banquito.core.aplicacion.clientes.excepcion.ActualizarClienteExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.ClienteNoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearClienteExcepcion;
import com.banquito.core.aplicacion.clientes.modelo.Persona;
import com.banquito.core.aplicacion.clientes.modelo.Cliente;
import com.banquito.core.aplicacion.clientes.modelo.Empresa;
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
public class ClienteServicio {

    private static final Logger logger = LoggerFactory.getLogger(ClienteServicio.class);

    private final ClienteRepositorio clienteRepo;
    private final SucursalRepositorio sucursalRepo;
    private final PersonaRepositorio personaRepo;
    private final EmpresaRepositorio empresaRepo;
    private final PaisRepositorio paisRepo;

    public ClienteServicio(ClienteRepositorio clienteRepo,
            SucursalRepositorio sucursalRepo,
            PersonaRepositorio personaRepo,
            EmpresaRepositorio empresaRepo,
            PaisRepositorio paisRepo) {
        this.paisRepo = paisRepo;
        this.clienteRepo = clienteRepo;
        this.sucursalRepo = sucursalRepo;
        this.personaRepo = personaRepo;
        this.empresaRepo = empresaRepo;
        logger.info("ClienteServicio inicializado correctamente");
    }

    /**
     * Crear un nuevo cliente desde una persona o empresa
     */
    public Cliente crear(Cliente cliente) {
        logger.info("Iniciando creación de cliente para entidad tipo: {} con ID: {}", 
                   cliente.getTipoEntidad(), cliente.getIdEntidad());
        
        try {
            validarDatosObligatorios(cliente);
            validarClienteNoExiste(cliente);
            configurarDatosEntidad(cliente);
            asignarSegmentoAutomatico(cliente);

            cliente.setFechaCreacion(new Date());
            cliente.setEstado(Cliente.Estado.ACTIVO);
            cliente.setFechaActualizacion(new Date());

            Cliente clienteCreado = clienteRepo.save(cliente);
            logger.info("Cliente creado exitosamente con ID: {}", clienteCreado.getIdCliente());
            
            return clienteCreado;
        } catch (Exception e) {
            logger.error("Error al crear cliente: {}", e.getMessage(), e);
            throw new CrearClienteExcepcion("Error al crear cliente: " + e.getMessage());
        }
    }

    /**
     * Limpiar todas las relaciones de un cliente para evitar referencias circulares
     */
    private void limpiarRelacionesCliente(Cliente cliente) {
        if (cliente == null) return;
        
        // Limpiar relaciones propias del cliente
        cliente.setTelefonos(null);
        cliente.setDirecciones(null);
        cliente.setContacto(null);
        
        // Limpiar relaciones del País
        if (cliente.getPaisNacionalidad() != null) {
            cliente.getPaisNacionalidad().setEstructurasGeograficas(null);
            cliente.getPaisNacionalidad().setLocacionesGeograficas(null);
        }
        
        // Limpiar relaciones de la Sucursal
        if (cliente.getSucursal() != null) {
            cliente.getSucursal().setLocacion(null);
            cliente.getSucursal().setEntidadBancaria(null);
        }
    }

    /**
     * Buscar cliente por ID SIN relaciones
     */
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Integer id) {
        logger.debug("Buscando cliente por ID: {}", id);
        
        if (id == null) {
            logger.warn("ID de cliente es nulo");
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Cliente no encontrado con ID: {}", id);
                    return new ClienteNoEncontradoExcepcion(id);
                });
        
        limpiarRelacionesCliente(cliente);
        logger.debug("Cliente encontrado: {}", cliente.getIdCliente());
        return cliente;
    }

    /**
     * Obtener información completa del cliente (con direcciones y teléfonos activos)
     */
    @Transactional(readOnly = true)
    public Cliente obtenerClienteCompleto(Integer id) {
        logger.debug("Obteniendo cliente completo por ID: {}", id);
        
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Cliente no encontrado para obtener completo ID: {}", id);
                    return new ClienteNoEncontradoExcepcion(id);
                });
        
        // Filtrar solo direcciones activas
        if (cliente.getDirecciones() != null) {
            cliente.getDirecciones().removeIf(d -> d.getEstado() != com.banquito.core.aplicacion.clientes.modelo.DireccionCliente.Estado.ACTIVO);
            // Limpiar referencias circulares en direcciones
            cliente.getDirecciones().forEach(d -> d.setCliente(null));
        }
        
        // Filtrar solo teléfonos activos
        if (cliente.getTelefonos() != null) {
            cliente.getTelefonos().removeIf(t -> t.getEstado() != com.banquito.core.aplicacion.clientes.modelo.TelefonoCliente.Estado.ACTIVO);
            // Limpiar referencias circulares en teléfonos
            cliente.getTelefonos().forEach(t -> t.setCliente(null));
        }
        
        // Limpiar referencia circular en contacto
        if (cliente.getContacto() != null) {
            cliente.getContacto().setCliente(null);
        }
        
        // Limpiar relaciones del País y Sucursal
        if (cliente.getPaisNacionalidad() != null) {
            cliente.getPaisNacionalidad().setEstructurasGeograficas(null);
            cliente.getPaisNacionalidad().setLocacionesGeograficas(null);
        }
        
        if (cliente.getSucursal() != null) {
            cliente.getSucursal().setLocacion(null);
            cliente.getSucursal().setEntidadBancaria(null);
        }
        
        logger.debug("Cliente completo obtenido: {}", cliente.getIdCliente());
        return cliente;
    }

    /**
     * Buscar cliente por número de identificación
     */
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorNumeroIdentificacion(String numeroIdentificacion) {
        logger.debug("Buscando cliente por número de identificación: {}", numeroIdentificacion);
        
        if (numeroIdentificacion == null || numeroIdentificacion.trim().isEmpty()) {
            logger.warn("Número de identificación es nulo o vacío");
            return Optional.empty();
        }
        
        Optional<Cliente> cliente = clienteRepo.findByNumeroIdentificacion(numeroIdentificacion);
        logger.debug("Cliente encontrado por identificación: {}", cliente.isPresent());
        
        return cliente;
    }

    /**
     * Listar todos los clientes con paginación
     */
    @Transactional(readOnly = true)
    public Page<Cliente> listarTodos(Pageable pageable) {
        logger.debug("Listando todos los clientes con paginación: página {}, tamaño {}", 
                    pageable.getPageNumber(), pageable.getPageSize());
        
        Page<Cliente> clientes = clienteRepo.findAll(pageable);
        
        // Limpiar relaciones para evitar problemas de serialización
        clientes.getContent().forEach(this::limpiarRelacionesCliente);
        
        logger.debug("Encontrados {} clientes", clientes.getTotalElements());
        return clientes;
    }

    /**
     * Listar clientes por estado con paginación
     */
    @Transactional(readOnly = true)
    public Page<Cliente> listarPorEstado(String estado, Pageable pageable) {
        logger.debug("Listando clientes por estado: {} con paginación", estado);
        
        try {
            Cliente.Estado.valueOf(estado.toUpperCase()); // Validar que el estado existe
            Page<Cliente> clientes = clienteRepo.findByEstado(estado, pageable);
            
            // Limpiar relaciones para evitar problemas de serialización
            clientes.getContent().forEach(this::limpiarRelacionesCliente);
            
            logger.debug("Encontrados {} clientes con estado: {}", clientes.getTotalElements(), estado);
            return clientes;
        } catch (IllegalArgumentException e) {
            logger.warn("Estado inválido proporcionado: {}", estado);
            throw new ActualizarClienteExcepcion("Estado inválido: " + estado);
        }
    }

    /**
     * Listar clientes por sucursal con paginación
     */
    @Transactional(readOnly = true)
    public Page<Cliente> listarPorSucursal(Integer idSucursal, Pageable pageable) {
        logger.debug("Listando clientes por sucursal ID: {} con paginación", idSucursal);
        
        Page<Cliente> clientes = clienteRepo.findBySucursal_IdSucursal(idSucursal, pageable);
        
        // Limpiar relaciones para evitar problemas de serialización
        clientes.getContent().forEach(this::limpiarRelacionesCliente);
        
        logger.debug("Encontrados {} clientes para sucursal ID: {}", clientes.getTotalElements(), idSucursal);
        return clientes;
    }

    /**
     * Buscar clientes por nombre con paginación
     */
    @Transactional(readOnly = true)
    public Page<Cliente> buscarPorNombre(String nombre, Pageable pageable) {
        logger.debug("Buscando clientes por nombre: {} con paginación", nombre);
        
        if (nombre == null || nombre.trim().isEmpty()) {
            logger.warn("Nombre de búsqueda es nulo o vacío");
            return Page.empty(pageable);
        }
        
        Page<Cliente> clientes = clienteRepo.findByNombreContainingIgnoreCase(nombre, pageable);
        
        // Limpiar relaciones para evitar problemas de serialización
        clientes.getContent().forEach(this::limpiarRelacionesCliente);
        
        logger.debug("Encontrados {} clientes con nombre que contiene: {}", clientes.getTotalElements(), nombre);
        return clientes;
    }

    /**
     * Listar clientes por tipo de entidad con paginación
     */
    @Transactional(readOnly = true)
    public Page<Cliente> listarPorTipoEntidad(String tipoEntidad, Pageable pageable) {
        logger.debug("Listando clientes por tipo de entidad: {} con paginación", tipoEntidad);
        
        try {
            Cliente.TipoEntidad.valueOf(tipoEntidad.toUpperCase()); // Validar que el tipo existe
            Page<Cliente> clientes = clienteRepo.findByTipoEntidad(tipoEntidad, pageable);
            
            // Limpiar relaciones para evitar problemas de serialización
            clientes.getContent().forEach(this::limpiarRelacionesCliente);
            
            logger.debug("Encontrados {} clientes con tipo de entidad: {}", clientes.getTotalElements(), tipoEntidad);
            return clientes;
        } catch (IllegalArgumentException e) {
            logger.warn("Tipo de entidad inválido proporcionado: {}", tipoEntidad);
            throw new ActualizarClienteExcepcion("Tipo de entidad inválido: " + tipoEntidad);
        }
    }

    /**
     * Modificar cliente existente
     */
    public Cliente modificar(Cliente cliente) {
        logger.info("Modificando cliente ID: {}", cliente.getIdCliente());
        
        Cliente clienteExistente = buscarPorId(cliente.getIdCliente());
        
        try {
            // Solo permitir actualizar ciertos campos
            if (cliente.getNombre() != null) {
                clienteExistente.setNombre(cliente.getNombre());
            }
            if (cliente.getSegmento() != null) {
                validarSegmento(cliente.getSegmento());
                clienteExistente.setSegmento(cliente.getSegmento());
            }
            if (cliente.getComentarios() != null) {
                clienteExistente.setComentarios(cliente.getComentarios());
            }
            
            clienteExistente.setFechaActualizacion(new Date());
            Cliente clienteActualizado = clienteRepo.save(clienteExistente);
            
            logger.info("Cliente modificado exitosamente ID: {}", clienteActualizado.getIdCliente());
            return clienteActualizado;
        } catch (Exception e) {
            logger.error("Error al modificar cliente ID {}: {}", cliente.getIdCliente(), e.getMessage(), e);
            throw new ActualizarClienteExcepcion("Error al actualizar cliente: " + e.getMessage());
        }
    }

    /**
     * Cambiar estado del cliente
     */
    public Cliente cambiarEstado(Integer id, String nuevoEstado) {
        logger.info("Cambiando estado del cliente ID: {} a {}", id, nuevoEstado);
        
        try {
            Cliente.Estado estadoEnum = Cliente.Estado.valueOf(nuevoEstado.toUpperCase());
            Cliente cliente = buscarPorId(id);
            validarCambioEstado(cliente.getEstado(), estadoEnum);
            
            cliente.setEstado(estadoEnum);
            cliente.setFechaActualizacion(new Date());
            
            Cliente clienteActualizado = clienteRepo.save(cliente);
            logger.info("Estado cambiado exitosamente para cliente ID: {}", id);
            
            return clienteActualizado;
        } catch (IllegalArgumentException e) {
            logger.warn("Estado inválido proporcionado: {}", nuevoEstado);
            throw new ActualizarClienteExcepcion("Estado inválido: " + nuevoEstado);
        }
    }

    /**
     * Activar cliente
     */
    public Cliente activarCliente(Integer id) {
        logger.info("Activando cliente ID: {}", id);
        return cambiarEstado(id, "ACTIVO");
    }

    /**
     * Suspender cliente
     */
    public Cliente suspenderCliente(Integer id) {
        logger.info("Suspendiendo cliente ID: {}", id);
        return cambiarEstado(id, "SUSPENDIDO");
    }

    /**
     * Bloquear cliente
     */
    public Cliente bloquearCliente(Integer id) {
        logger.info("Bloqueando cliente ID: {}", id);
        return cambiarEstado(id, "BLOQUEADO");
    }

    /**
     * Obtener estadísticas de clientes
     */
    @Transactional(readOnly = true)
    public ClienteEstadisticas obtenerEstadisticas() {
        logger.debug("Obteniendo estadísticas de clientes");
        
        ClienteEstadisticas estadisticas = new ClienteEstadisticas();
        estadisticas.setTotalClientes(clienteRepo.count());
        
        // Contar por estado usando consultas básicas
        List<Cliente> todosClientes = clienteRepo.findAll();
        long clientesActivos = todosClientes.stream()
                .filter(c -> c.getEstado() == Cliente.Estado.ACTIVO)
                .count();
        long clientesInactivos = todosClientes.stream()
                .filter(c -> c.getEstado() == Cliente.Estado.INACTIVO)
                .count();
        long personasNaturales = todosClientes.stream()
                .filter(c -> c.getTipoCliente() == Cliente.TipoCliente.PERSONA_NATURAL)
                .count();
        long personasJuridicas = todosClientes.stream()
                .filter(c -> c.getTipoCliente() == Cliente.TipoCliente.PERSONA_JURIDICA)
                .count();
        
        estadisticas.setClientesActivos(clientesActivos);
        estadisticas.setClientesInactivos(clientesInactivos);
        estadisticas.setPersonasNaturales(personasNaturales);
        estadisticas.setPersonasJuridicas(personasJuridicas);
        
        logger.debug("Estadísticas calculadas: {} clientes totales, {} activos", 
                    estadisticas.getTotalClientes(), estadisticas.getClientesActivos());
        
        return estadisticas;
    }

    private void validarDatosObligatorios(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if (cliente.getTipoEntidad() == null) {
            throw new CrearClienteExcepcion("Tipo de entidad es obligatorio");
        }
        if (cliente.getIdEntidad() == null) {
            throw new CrearClienteExcepcion("ID de entidad es obligatorio");
        }
        if (cliente.getIdSucursal() == null) {
            throw new CrearClienteExcepcion("Sucursal es obligatoria");
        }
        validarTipoEntidad(cliente.getTipoEntidad());
    }

    private void validarTipoEntidad(Cliente.TipoEntidad tipoEntidad) {
        if (tipoEntidad == null) {
            throw new CrearClienteExcepcion("Tipo de entidad no válido");
        }
    }

    private void validarClienteNoExiste(Cliente cliente) {
        // Verificar si ya existe un cliente para esta entidad
        List<Cliente> clientesExistentes;
        
        if (cliente.getTipoEntidad() == Cliente.TipoEntidad.PERSONA) {
            Optional<Persona> persona = personaRepo.findById(cliente.getIdEntidad());
            if (persona.isEmpty()) {
                throw new CrearClienteExcepcion("La persona especificada no existe");
            }
            clientesExistentes = clienteRepo.findByTipoEntidadAndIdEntidad(
                cliente.getTipoEntidad().name(), cliente.getIdEntidad());
        } else {
            Optional<Empresa> empresa = empresaRepo.findById(cliente.getIdEntidad());
            if (empresa.isEmpty()) {
                throw new CrearClienteExcepcion("La empresa especificada no existe");
            }
            clientesExistentes = clienteRepo.findByTipoEntidadAndIdEntidad(
                cliente.getTipoEntidad().name(), cliente.getIdEntidad());
        }
        
        if (!clientesExistentes.isEmpty()) {
            throw new CrearClienteExcepcion("Ya existe un cliente para esta entidad");
        }
    }

    private void configurarDatosEntidad(Cliente cliente) {
        if (cliente.getTipoEntidad() == Cliente.TipoEntidad.PERSONA) {
            Optional<Persona> persona = personaRepo.findById(cliente.getIdEntidad());
            if (persona.isPresent()) {
                cliente.setNombre(persona.get().getNombre());
                cliente.setTipoIdentificacion(convertirTipoIdentificacionPersona(persona.get().getTipoIdentificacion()));
                cliente.setNumeroIdentificacion(persona.get().getNumeroIdentificacion());
                cliente.setTipoCliente(Cliente.TipoCliente.PERSONA_NATURAL);
            }
        } else {
            Optional<Empresa> empresa = empresaRepo.findById(cliente.getIdEntidad());
            if (empresa.isPresent()) {
                cliente.setNombre(empresa.get().getRazonSocial());
                cliente.setTipoIdentificacion(Cliente.TipoIdentificacion.RUC);
                cliente.setNumeroIdentificacion(empresa.get().getNumeroIdentificacion());
                cliente.setTipoCliente(Cliente.TipoCliente.PERSONA_JURIDICA);
            }
        }
    }

    private Cliente.TipoIdentificacion convertirTipoIdentificacionPersona(Persona.TipoIdentificacion tipoPersona) {
        switch (tipoPersona) {
            case CEDULA:
                return Cliente.TipoIdentificacion.CEDULA;
            case PASAPORTE:
                return Cliente.TipoIdentificacion.PASAPORTE;
            default:
                throw new CrearClienteExcepcion("Tipo de identificación no válido");
        }
    }

    private void asignarSegmentoAutomatico(Cliente cliente) {
        // Lógica simple de asignación de segmento
        if (cliente.getTipoCliente() == Cliente.TipoCliente.PERSONA_JURIDICA) {
            cliente.setSegmento(Cliente.Segmento.CORPORATIVO);
        } else {
            cliente.setSegmento(Cliente.Segmento.MASIVO);
        }
    }

    private void validarSegmento(Cliente.Segmento segmento) {
        if (segmento == null) {
            throw new ActualizarClienteExcepcion("Segmento no válido");
        }
    }

    private void validarCambioEstado(Cliente.Estado estadoActual, Cliente.Estado nuevoEstado) {
        if (estadoActual == nuevoEstado) {
            throw new ActualizarClienteExcepcion("El cliente ya se encuentra en el estado: " + nuevoEstado);
        }
        
        // Validar transiciones de estado válidas
        if (estadoActual == Cliente.Estado.BLOQUEADO && nuevoEstado != Cliente.Estado.ACTIVO) {
            throw new ActualizarClienteExcepcion("Un cliente bloqueado solo puede ser activado");
        }
    }

    public static class ClienteEstadisticas {
        private long totalClientes;
        private long clientesActivos;
        private long clientesInactivos;
        private long personasNaturales;
        private long personasJuridicas;

        public long getTotalClientes() { return totalClientes; }
        public void setTotalClientes(long totalClientes) { this.totalClientes = totalClientes; }

        public long getClientesActivos() { return clientesActivos; }
        public void setClientesActivos(long clientesActivos) { this.clientesActivos = clientesActivos; }

        public long getClientesInactivos() { return clientesInactivos; }
        public void setClientesInactivos(long clientesInactivos) { this.clientesInactivos = clientesInactivos; }

        public long getPersonasNaturales() { return personasNaturales; }
        public void setPersonasNaturales(long personasNaturales) { this.personasNaturales = personasNaturales; }

        public long getPersonasJuridicas() { return personasJuridicas; }
        public void setPersonasJuridicas(long personasJuridicas) { this.personasJuridicas = personasJuridicas; }
    }
}