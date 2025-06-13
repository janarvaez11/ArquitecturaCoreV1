package com.banquito.core.aplicacion.clientes.servicio;

import com.banquito.core.aplicacion.clientes.excepcion.ActualizarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.EliminarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.NoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.modelo.Representante;
import com.banquito.core.aplicacion.clientes.repositorio.RepresentanteRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.EmpresaRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.ClienteRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RepresentanteServicio {

    private static final Logger logger = LoggerFactory.getLogger(RepresentanteServicio.class);

    private final RepresentanteRepositorio representanteRepositorio;
    private final EmpresaRepositorio empresaRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public RepresentanteServicio(RepresentanteRepositorio representanteRepositorio,
                               EmpresaRepositorio empresaRepositorio,
                               ClienteRepositorio clienteRepositorio) {
        this.representanteRepositorio = representanteRepositorio;
        this.empresaRepositorio = empresaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        logger.info("RepresentanteServicio inicializado correctamente");
    }

    public Representante crear(Representante representante) {
        logger.info("Iniciando creación de representante para empresa ID: {} y cliente ID: {}", 
                   representante.getIdEmpresa(), representante.getIdCliente());
        
        try {
            validarRepresentanteParaCreacion(representante);
            validarEmpresaExiste(representante.getIdEmpresa());
            validarClienteExiste(representante.getIdCliente());
            validarRol(representante.getRol());
            validarRepresentanteUnico(representante.getIdEmpresa(), representante.getIdCliente(), representante.getRol());

            if (representante.getEstado() == null) {
                representante.setEstado(Representante.Estado.ACTIVO);
            }

            representante.setFechaAsignacion(new Date());

            Representante representanteCreado = representanteRepositorio.save(representante);
            logger.info("Representante creado exitosamente con ID: {}", representanteCreado.getIdRepresentante());
            
            return representanteCreado;
        } catch (RuntimeException e) {
            logger.error("Error al crear representante: {}", e.getMessage(), e);
            throw new CrearExcepcion("Error al crear el representante: " + e.getMessage(), "Representante");
        }
    }

    @Transactional(readOnly = true)
    public Page<Representante> buscarTodos(Pageable pageable) {
        logger.debug("Buscando todos los representantes con paginación: página {}, tamaño {}", 
                    pageable.getPageNumber(), pageable.getPageSize());
        
        Page<Representante> representantes = representanteRepositorio.findAll(pageable);
        logger.debug("Encontrados {} representantes", representantes.getTotalElements());
        
        return representantes;
    }

    @Transactional(readOnly = true)
    public List<Representante> buscarTodos() {
        logger.debug("Buscando todos los representantes sin paginación");
        
        List<Representante> representantes = representanteRepositorio.findAll();
        logger.debug("Encontrados {} representantes", representantes.size());
        
        return representantes;
    }

    @Transactional(readOnly = true)
    public Representante buscarPorId(Integer idRepresentante) {
        logger.debug("Buscando representante por ID: {}", idRepresentante);
        
        if (idRepresentante == null) {
            logger.warn("ID de representante es nulo");
            throw new IllegalArgumentException("El ID del representante no puede ser nulo");
        }

        Representante representante = representanteRepositorio.findById(idRepresentante)
                .orElseThrow(() -> {
                    logger.warn("Representante no encontrado con ID: {}", idRepresentante);
                    return new NoEncontradoExcepcion("No se encontró el representante con ID: " + idRepresentante, "Representante");
                });
        
        logger.debug("Representante encontrado: {}", representante.getIdRepresentante());
        return representante;
    }

    @Transactional(readOnly = true)
    public List<Representante> obtenerPorEmpresa(Integer idEmpresa) {
        logger.debug("Obteniendo representantes por empresa ID: {}", idEmpresa);
        
        validarEmpresaExiste(idEmpresa);
        
        List<Representante> representantes = representanteRepositorio.findByIdEmpresa(idEmpresa);
        logger.debug("Encontrados {} representantes para la empresa ID: {}", representantes.size(), idEmpresa);
        
        return representantes;
    }

    @Transactional(readOnly = true)
    public List<Representante> obtenerPorCliente(Integer idCliente) {
        logger.debug("Obteniendo representantes por cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        
        List<Representante> representantes = representanteRepositorio.findByIdCliente(idCliente);
        logger.debug("Encontrados {} representantes para el cliente ID: {}", representantes.size(), idCliente);
        
        return representantes;
    }

    @Transactional(readOnly = true)
    public List<Representante> obtenerActivosPorEmpresa(Integer idEmpresa) {
        logger.debug("Obteniendo representantes activos por empresa ID: {}", idEmpresa);
        
        validarEmpresaExiste(idEmpresa);
        
        List<Representante> representantes = representanteRepositorio.findByIdEmpresaAndEstado(idEmpresa, Representante.Estado.ACTIVO);
        logger.debug("Encontrados {} representantes activos para la empresa ID: {}", representantes.size(), idEmpresa);
        
        return representantes;
    }

    @Transactional(readOnly = true)
    public List<Representante> obtenerPorRol(Representante.Rol rol) {
        logger.debug("Obteniendo representantes por rol: {}", rol);
        
        List<Representante> representantes = representanteRepositorio.findByRol(rol);
        logger.debug("Encontrados {} representantes con rol: {}", representantes.size(), rol);
        
        return representantes;
    }

    public Representante actualizar(Representante representante) {
        logger.info("Actualizando representante ID: {}", representante.getIdRepresentante());
        
        try {
            validarRepresentanteParaActualizacion(representante);

            Representante representanteDB = representanteRepositorio.findById(representante.getIdRepresentante())
                    .orElseThrow(() -> {
                        logger.warn("Representante no encontrado para actualización ID: {}", representante.getIdRepresentante());
                        return new ActualizarExcepcion("No se encontró el representante con ID: " + representante.getIdRepresentante(), "Representante");
                    });

            if (representante.getEstado() != null) {
                representanteDB.setEstado(representante.getEstado());
            }

            if (representante.getRol() != null) {
                validarRol(representante.getRol());
                representanteDB.setRol(representante.getRol());
            }

            representanteDB.setFechaAsignacion(new Date());

            Representante representanteActualizado = representanteRepositorio.save(representanteDB);
            logger.info("Representante actualizado exitosamente ID: {}", representanteActualizado.getIdRepresentante());
            
            return representanteActualizado;
        } catch (RuntimeException e) {
            logger.error("Error al actualizar representante ID {}: {}", representante.getIdRepresentante(), e.getMessage(), e);
            throw new ActualizarExcepcion("Error al actualizar el representante: " + e.getMessage(), "Representante");
        }
    }

    public Representante cambiarEstado(Integer idRepresentante, Representante.Estado nuevoEstado) {
        logger.info("Cambiando estado del representante ID: {} a {}", idRepresentante, nuevoEstado);
        
        Representante representante = buscarPorId(idRepresentante);
        representante.setEstado(nuevoEstado);
        representante.setFechaAsignacion(new Date());
        
        Representante representanteActualizado = representanteRepositorio.save(representante);
        logger.info("Estado cambiado exitosamente para representante ID: {}", idRepresentante);
        
        return representanteActualizado;
    }

    public Representante activar(Integer idRepresentante) {
        logger.info("Activando representante ID: {}", idRepresentante);
        return cambiarEstado(idRepresentante, Representante.Estado.ACTIVO);
    }

    public Representante inactivar(Integer idRepresentante) {
        logger.info("Inactivando representante ID: {}", idRepresentante);
        return cambiarEstado(idRepresentante, Representante.Estado.INACTIVO);
    }

    @Transactional(readOnly = true)
    public boolean existeRepresentanteConRol(Integer idEmpresa, Representante.Rol rol) {
        logger.debug("Verificando existencia de representante con rol {} en empresa ID: {}", rol, idEmpresa);
        
        validarEmpresaExiste(idEmpresa);
        
        List<Representante> representantes = representanteRepositorio.findByIdEmpresaAndRolAndEstado(
                idEmpresa, rol, Representante.Estado.ACTIVO);
        
        boolean existe = !representantes.isEmpty();
        logger.debug("Existe representante con rol {} en empresa ID {}: {}", rol, idEmpresa, existe);
        
        return existe;
    }

    public void eliminar(Integer idRepresentante) {
        logger.info("Eliminando representante ID: {}", idRepresentante);
        
        try {
            Representante representante = buscarPorId(idRepresentante);
            
            if (representante.getRol() == Representante.Rol.REPRESENTANTE_LEGAL) {
                logger.warn("Intento de eliminar representante legal ID: {}", idRepresentante);
                throw new EliminarExcepcion("No se puede eliminar el representante legal de la empresa", "Representante");
            }

            representanteRepositorio.delete(representante);
            logger.info("Representante eliminado exitosamente ID: {}", idRepresentante);
            
        } catch (RuntimeException e) {
            logger.error("Error al eliminar representante ID {}: {}", idRepresentante, e.getMessage(), e);
            throw new EliminarExcepcion("Error al eliminar el representante: " + e.getMessage(), "Representante");
        }
    }

    private void validarRepresentanteParaCreacion(Representante representante) {
        if (representante == null) {
            throw new IllegalArgumentException("El representante no puede ser nulo");
        }
        if (representante.getIdEmpresa() == null) {
            throw new IllegalArgumentException("El ID de empresa es requerido");
        }
        if (representante.getIdCliente() == null) {
            throw new IllegalArgumentException("El ID de cliente es requerido");
        }
        if (representante.getRol() == null) {
            throw new IllegalArgumentException("El rol es requerido");
        }
    }

    private void validarRepresentanteParaActualizacion(Representante representante) {
        if (representante == null) {
            throw new IllegalArgumentException("El representante no puede ser nulo");
        }
        if (representante.getIdRepresentante() == null) {
            throw new IllegalArgumentException("El ID del representante es requerido para actualización");
        }
    }

    private void validarEmpresaExiste(Integer idEmpresa) {
        if (!empresaRepositorio.existsById(idEmpresa)) {
            logger.warn("Empresa no encontrada ID: {}", idEmpresa);
            throw new NoEncontradoExcepcion("No se encontró la empresa con ID: " + idEmpresa, "Empresa");
        }
    }

    private void validarClienteExiste(Integer idCliente) {
        if (!clienteRepositorio.existsById(idCliente)) {
            logger.warn("Cliente no encontrado ID: {}", idCliente);
            throw new NoEncontradoExcepcion("No se encontró el cliente con ID: " + idCliente, "Cliente");
        }
    }

    private void validarRol(Representante.Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }
    }

    private void validarRepresentanteUnico(Integer idEmpresa, Integer idCliente, Representante.Rol rol) {
        List<Representante> existentes = representanteRepositorio.findByIdEmpresaAndIdClienteAndRol(idEmpresa, idCliente, rol);
        if (!existentes.isEmpty()) {
            logger.warn("Ya existe un representante con empresa ID: {}, cliente ID: {} y rol: {}", idEmpresa, idCliente, rol);
            throw new CrearExcepcion("Ya existe un representante con la misma empresa, cliente y rol", "Representante");
        }
    }
}
