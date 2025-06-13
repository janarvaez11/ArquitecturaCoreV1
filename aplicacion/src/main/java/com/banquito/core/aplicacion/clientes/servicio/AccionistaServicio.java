package com.banquito.core.aplicacion.clientes.servicio;

import com.banquito.core.aplicacion.clientes.excepcion.ActualizarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.EliminarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.NoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.modelo.Accionista;
import com.banquito.core.aplicacion.clientes.repositorio.AccionistaRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.EmpresaRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.PersonaRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccionistaServicio {

    private static final Logger logger = LoggerFactory.getLogger(AccionistaServicio.class);
    
    private static final BigDecimal PARTICIPACION_MINIMA = new BigDecimal("0.01");
    private static final BigDecimal PARTICIPACION_MAXIMA = new BigDecimal("100.00");
    private static final BigDecimal PARTICIPACION_MAYORITARIA = new BigDecimal("50.00");
    private static final BigDecimal PARTICIPACION_TOTAL_MAXIMA = new BigDecimal("100.00");

    private final AccionistaRepositorio accionistaRepositorio;
    private final EmpresaRepositorio empresaRepositorio;
    private final PersonaRepositorio personaRepositorio;

    public AccionistaServicio(AccionistaRepositorio accionistaRepositorio,
                             EmpresaRepositorio empresaRepositorio,
                             PersonaRepositorio personaRepositorio) {
        this.accionistaRepositorio = accionistaRepositorio;
        this.empresaRepositorio = empresaRepositorio;
        this.personaRepositorio = personaRepositorio;
        logger.info("AccionistaServicio inicializado correctamente");
    }

    public Accionista crear(Accionista accionista) {
        logger.info("Iniciando creación de accionista para empresa ID: {} y partícipe ID: {}", 
                   accionista.getIdEmpresa(), accionista.getIdParticipe());
        
        try {
            validarCamposObligatorios(accionista);
            validarEmpresaExiste(accionista.getIdEmpresa());
            validarParticipleExiste(accionista.getIdParticipe());
            validarParticipacionValida(accionista.getParticipacion());
            validarTipoEntidadParticipe(accionista.getTipoEntidadParticipe());
            validarParticipacionTotalNoExceda(accionista.getIdEmpresa(), accionista.getParticipacion(), null);
            validarAccionistaUnico(accionista.getIdEmpresa(), accionista.getIdParticipe());
            
            if (accionista.getEstado() == null) {
                accionista.setEstado(Accionista.Estado.ACTIVO);
            }
            
            Accionista accionistaCreado = accionistaRepositorio.save(accionista);
            logger.info("Accionista creado exitosamente con ID: {}", accionistaCreado.getIdAccionista());
            
            return accionistaCreado;
        } catch (RuntimeException rte) {
            logger.error("Error al crear accionista: {}", rte.getMessage(), rte);
            throw new CrearExcepcion("Error al crear el accionista: " + rte.getMessage(), "Accionista");
        }
    }

    @Transactional(readOnly = true)
    public Page<Accionista> buscarTodos(Pageable pageable) {
        logger.debug("Buscando todos los accionistas con paginación: página {}, tamaño {}", 
                    pageable.getPageNumber(), pageable.getPageSize());
        
        Page<Accionista> accionistas = accionistaRepositorio.findAll(pageable);
        logger.debug("Encontrados {} accionistas", accionistas.getTotalElements());
        
        return accionistas;
    }

    @Transactional(readOnly = true)
    public List<Accionista> buscarTodos() {
        logger.debug("Buscando todos los accionistas sin paginación");
        
        List<Accionista> accionistas = accionistaRepositorio.findAll();
        logger.debug("Encontrados {} accionistas", accionistas.size());
        
        return accionistas;
    }

    @Transactional(readOnly = true)
    public Accionista buscarPorId(Integer id) {
        logger.debug("Buscando accionista por ID: {}", id);
        
        if (id == null) {
            logger.warn("ID de accionista es nulo");
            throw new IllegalArgumentException("El ID del accionista no puede ser nulo");
        }

        Accionista accionista = accionistaRepositorio.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Accionista no encontrado con ID: {}", id);
                    return new NoEncontradoExcepcion("Error al buscar el accionista: " + id, "Accionista");
                });
        
        logger.debug("Accionista encontrado: {}", accionista.getIdAccionista());
        return accionista;
    }

    public Accionista actualizar(Accionista accionista) {
        logger.info("Actualizando accionista ID: {}", accionista.getIdAccionista());
        
        if (accionista.getIdAccionista() == null) {
            logger.warn("ID de accionista no proporcionado para actualización");
            throw new ActualizarExcepcion("ID de accionista no proporcionado", "Accionista");
        }

        try {
            Accionista accionistaDB = accionistaRepositorio.findById(accionista.getIdAccionista())
                    .orElseThrow(() -> {
                        logger.warn("Accionista no encontrado para actualización ID: {}", accionista.getIdAccionista());
                        return new ActualizarExcepcion("Accionista no encontrado con ID: " + accionista.getIdAccionista(), "Accionista");
                    });

            if (accionista.getIdEmpresa() != null) {
                validarEmpresaExiste(accionista.getIdEmpresa());
                accionistaDB.setIdEmpresa(accionista.getIdEmpresa());
            }
            
            if (accionista.getParticipacion() != null) {
                validarParticipacionValida(accionista.getParticipacion());
                validarParticipacionTotalNoExceda(accionistaDB.getIdEmpresa(), accionista.getParticipacion(), accionistaDB.getIdAccionista());
                accionistaDB.setParticipacion(accionista.getParticipacion());
            }
            
            if (accionista.getTipoEntidadParticipe() != null) {
                validarTipoEntidadParticipe(accionista.getTipoEntidadParticipe());
                accionistaDB.setTipoEntidadParticipe(accionista.getTipoEntidadParticipe());
            }

            if (accionista.getEstado() != null) {
                accionistaDB.setEstado(accionista.getEstado());
            }

            Accionista accionistaActualizado = accionistaRepositorio.save(accionistaDB);
            logger.info("Accionista actualizado exitosamente ID: {}", accionistaActualizado.getIdAccionista());
            
            return accionistaActualizado;
        } catch (RuntimeException rte) {
            logger.error("Error al actualizar accionista ID {}: {}", accionista.getIdAccionista(), rte.getMessage(), rte);
            throw new ActualizarExcepcion("Error al actualizar el accionista: " + rte.getMessage(), "Accionista");
        }
    }

    @Transactional(readOnly = true)
    public boolean esMayoritario(Integer id) {
        logger.debug("Verificando si accionista ID {} es mayoritario", id);
        
        Accionista accionista = buscarPorId(id);
        boolean esMayoritario = accionista.getParticipacion().compareTo(PARTICIPACION_MAYORITARIA) > 0;
        
        logger.debug("Accionista ID {} es mayoritario: {}", id, esMayoritario);
        return esMayoritario;
    }

    @Transactional(readOnly = true)
    public BigDecimal calcularParticipacionTotal(Integer idEmpresa) {
        logger.debug("Calculando participación total para empresa ID: {}", idEmpresa);
        
        validarEmpresaExiste(idEmpresa);
        
        List<Accionista> accionistas = accionistaRepositorio.findByIdEmpresaAndEstado(idEmpresa, Accionista.Estado.ACTIVO);
        BigDecimal total = accionistas.stream()
                .map(Accionista::getParticipacion)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        logger.debug("Participación total calculada para empresa ID {}: {}%", idEmpresa, total);
        return total;
    }

    @Transactional(readOnly = true)
    public List<Accionista> obtenerPorEmpresa(Integer idEmpresa) {
        logger.debug("Obteniendo accionistas por empresa ID: {}", idEmpresa);
        
        validarEmpresaExiste(idEmpresa);
        
        List<Accionista> accionistas = accionistaRepositorio.findByIdEmpresa(idEmpresa);
        logger.debug("Encontrados {} accionistas para empresa ID: {}", accionistas.size(), idEmpresa);
        
        return accionistas;
    }

    @Transactional(readOnly = true)
    public List<Accionista> obtenerActivosPorEmpresa(Integer idEmpresa) {
        logger.debug("Obteniendo accionistas activos por empresa ID: {}", idEmpresa);
        
        validarEmpresaExiste(idEmpresa);
        
        List<Accionista> accionistas = accionistaRepositorio.findByIdEmpresaAndEstado(idEmpresa, Accionista.Estado.ACTIVO);
        logger.debug("Encontrados {} accionistas activos para empresa ID: {}", accionistas.size(), idEmpresa);
        
        return accionistas;
    }

    @Transactional(readOnly = true)
    public List<Accionista> obtenerMayoritarios(Integer idEmpresa) {
        logger.debug("Obteniendo accionistas mayoritarios para empresa ID: {}", idEmpresa);
        
        List<Accionista> accionistas = obtenerActivosPorEmpresa(idEmpresa);
        List<Accionista> mayoritarios = accionistas.stream()
                .filter(a -> a.getParticipacion().compareTo(PARTICIPACION_MAYORITARIA) > 0)
                .toList();
        
        logger.debug("Encontrados {} accionistas mayoritarios para empresa ID: {}", mayoritarios.size(), idEmpresa);
        return mayoritarios;
    }

    public Accionista cambiarEstado(Integer id, Accionista.Estado nuevoEstado) {
        logger.info("Cambiando estado del accionista ID: {} a {}", id, nuevoEstado);
        
        Accionista accionista = buscarPorId(id);
        accionista.setEstado(nuevoEstado);
        
        Accionista accionistaActualizado = accionistaRepositorio.save(accionista);
        logger.info("Estado cambiado exitosamente para accionista ID: {}", id);
        
        return accionistaActualizado;
    }

    public Accionista activar(Integer id) {
        logger.info("Activando accionista ID: {}", id);
        return cambiarEstado(id, Accionista.Estado.ACTIVO);
    }

    public Accionista inactivar(Integer id) {
        logger.info("Inactivando accionista ID: {}", id);
        return cambiarEstado(id, Accionista.Estado.INACTIVO);
    }

    public void eliminar(Integer id) {
        logger.info("Eliminando accionista ID: {}", id);
        
        if (id == null) {
            logger.warn("ID de accionista no proporcionado para eliminación");
            throw new EliminarExcepcion("ID de accionista no proporcionado", "Accionista");
        }

        try {
            Accionista accionista = accionistaRepositorio.findById(id)
                    .orElseThrow(() -> {
                        logger.warn("Accionista no encontrado para eliminación ID: {}", id);
                        return new EliminarExcepcion("Accionista no encontrado con ID: " + id, "Accionista");
                    });

            accionistaRepositorio.delete(accionista);
            logger.info("Accionista eliminado exitosamente ID: {}", id);
            
        } catch (RuntimeException rte) {
            logger.error("Error al eliminar accionista ID {}: {}", id, rte.getMessage(), rte);
            throw new EliminarExcepcion("Error al eliminar el accionista: " + rte.getMessage(), "Accionista");
        }
    }

    private void validarCamposObligatorios(Accionista accionista) {
        if (accionista == null) {
            throw new IllegalArgumentException("El accionista no puede ser nulo");
        }
        if (accionista.getIdEmpresa() == null) {
            throw new CrearExcepcion("El accionista debe estar asociado a una empresa", "Accionista");
        }
        if (accionista.getIdParticipe() == null) {
            throw new CrearExcepcion("El ID del partícipe es requerido", "Accionista");
        }
        if (accionista.getParticipacion() == null) {
            throw new CrearExcepcion("La participación no puede ser nula", "Accionista");
        }
        if (accionista.getTipoEntidadParticipe() == null) {
            throw new CrearExcepcion("El tipo de entidad partícipe es obligatorio", "Accionista");
        }
    }

    private void validarEmpresaExiste(Integer idEmpresa) {
        if (!empresaRepositorio.existsById(idEmpresa)) {
            logger.warn("Empresa no encontrada ID: {}", idEmpresa);
            throw new CrearExcepcion("No existe una empresa con ID: " + idEmpresa, "Accionista");
        }
    }

    private void validarParticipleExiste(Integer idParticipe) {
        if (!personaRepositorio.existsById(idParticipe)) {
            logger.warn("Partícipe no encontrado ID: {}", idParticipe);
            throw new CrearExcepcion("No existe una persona con ID: " + idParticipe, "Accionista");
        }
    }

    private void validarParticipacionValida(BigDecimal participacion) {
        if (participacion.compareTo(PARTICIPACION_MINIMA) < 0 ||
                participacion.compareTo(PARTICIPACION_MAXIMA) > 0) {
            throw new CrearExcepcion("La participación debe estar entre " + PARTICIPACION_MINIMA + "% y " + PARTICIPACION_MAXIMA + "%", "Accionista");
        }
        
        if (participacion.scale() > 2) {
            throw new CrearExcepcion("La participación puede tener máximo 2 decimales", "Accionista");
        }
    }

    private void validarTipoEntidadParticipe(Accionista.TipoEntidadParticipe tipoEntidadParticipe) {
        if (tipoEntidadParticipe == null) {
            throw new CrearExcepcion("El tipo de entidad partícipe no puede ser nulo", "Accionista");
        }
    }

    private void validarParticipacionTotalNoExceda(Integer idEmpresa, BigDecimal nuevaParticipacion, Integer idAccionistaExcluir) {
        BigDecimal participacionActual = calcularParticipacionTotal(idEmpresa);
        
        if (idAccionistaExcluir != null) {
            Accionista accionistaExistente = buscarPorId(idAccionistaExcluir);
            participacionActual = participacionActual.subtract(accionistaExistente.getParticipacion());
        }
        
        BigDecimal participacionTotal = participacionActual.add(nuevaParticipacion);
        
        if (participacionTotal.compareTo(PARTICIPACION_TOTAL_MAXIMA) > 0) {
            logger.warn("Participación total excedería 100% para empresa ID: {}. Total sería: {}%", idEmpresa, participacionTotal);
            throw new CrearExcepcion("La participación total no puede exceder el 100%. Total actual: " + participacionActual + "%, nueva participación: " + nuevaParticipacion + "%", "Accionista");
        }
    }

    private void validarAccionistaUnico(Integer idEmpresa, Integer idParticipe) {
        List<Accionista> existentes = accionistaRepositorio.findByIdEmpresaAndIdParticipe(idEmpresa, idParticipe);
        if (!existentes.isEmpty()) {
            logger.warn("Ya existe un accionista con empresa ID: {} y partícipe ID: {}", idEmpresa, idParticipe);
            throw new CrearExcepcion("Ya existe un accionista con la misma empresa y partícipe", "Accionista");
        }
    }
}
