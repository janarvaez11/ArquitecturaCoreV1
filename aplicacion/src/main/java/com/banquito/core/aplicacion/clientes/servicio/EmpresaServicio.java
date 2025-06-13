package com.banquito.core.aplicacion.clientes.servicio;

import com.banquito.core.aplicacion.clientes.excepcion.ActualizarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.EliminarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.NoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.modelo.Empresa;
import com.banquito.core.aplicacion.clientes.repositorio.EmpresaRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@Service
@Transactional
public class EmpresaServicio {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaServicio.class);

    private static final int LONGITUD_RUC = 13;
    private static final int AÑOS_MINIMOS_CONSTITUCION = 0;
    private static final int AÑOS_MAXIMOS_CONSTITUCION = 150;
    private static final int AÑOS_MINIMOS_OPERACION_BANCARIA = 1;

    private final EmpresaRepositorio empresaRepositorio;

    public EmpresaServicio(EmpresaRepositorio empresaRepositorio) {
        this.empresaRepositorio = empresaRepositorio;
    }

    public Empresa crear(Empresa empresa) {
        logger.info("Iniciando creación de empresa con RUC: {}", empresa.getNumeroIdentificacion());
        
        try {
            validarCamposObligatorios(empresa);
            validarTipoIdentificacion(empresa.getTipoIdentificacion());
            validarRUC(empresa.getNumeroIdentificacion());
            validarNumeroIdentificacionUnico(empresa.getNumeroIdentificacion());
            validarCorreoElectronico(empresa.getCorreoElectronico());
            validarTipoEmpresa(empresa.getTipo());
            validarSectorEconomico(empresa.getSectorEconomico());
            validarFechaConstitucion(empresa.getFechaConstitucion());
            validarReglasNegocioBancario(empresa);
            
            if (empresa.getEstado() == null) {
                empresa.setEstado(Empresa.Estado.ACTIVO);
                logger.debug("Estado por defecto asignado: ACTIVO");
            }

            empresa.setFechaRegistro(new Date());
            empresa.setFechaActualizacion(new Date());
            
            Empresa empresaCreada = empresaRepositorio.save(empresa);
            logger.info("Empresa creada exitosamente con ID: {} y RUC: {}", 
                       empresaCreada.getIdEmpresa(), empresaCreada.getNumeroIdentificacion());
            
            return empresaCreada;
        } catch (RuntimeException e) {
            logger.error("Error al crear empresa con RUC {}: {}", empresa.getNumeroIdentificacion(), e.getMessage());
            throw new CrearExcepcion("Error al crear la empresa: " + e.getMessage(), "Empresa");
        }
    }

    @Transactional(readOnly = true)
    public Page<Empresa> buscarTodas(Pageable pageable) {
        logger.debug("Buscando todas las empresas con paginación: página {}, tamaño {}", 
                    pageable.getPageNumber(), pageable.getPageSize());
        
        Page<Empresa> empresas = empresaRepositorio.findAll(pageable);
        logger.info("Encontradas {} empresas en total", empresas.getTotalElements());
        
        return empresas;
    }

    @Transactional(readOnly = true)
    public List<Empresa> buscarTodas() {
        logger.debug("Buscando todas las empresas sin paginación");
        
        List<Empresa> empresas = empresaRepositorio.findAll();
        logger.info("Encontradas {} empresas en total", empresas.size());
        
        return empresas;
    }

    @Transactional(readOnly = true)
    public List<Empresa> buscarPorEstado(Empresa.Estado estado) {
        logger.debug("Buscando empresas por estado: {}", estado);
        
        List<Empresa> empresas = empresaRepositorio.findByEstado(estado);
        logger.info("Encontradas {} empresas con estado {}", empresas.size(), estado);
        
        return empresas;
    }

    @Transactional(readOnly = true)
    public List<Empresa> buscarPorTipo(Empresa.TipoEmpresa tipo) {
        logger.debug("Buscando empresas por tipo: {}", tipo);
        
        List<Empresa> empresas = empresaRepositorio.findByTipo(tipo);
        logger.info("Encontradas {} empresas de tipo {}", empresas.size(), tipo);
        
        return empresas;
    }

    @Transactional(readOnly = true)
    public List<Empresa> buscarPorSectorEconomico(Empresa.SectorEconomico sector) {
        logger.debug("Buscando empresas por sector económico: {}", sector);
        
        List<Empresa> empresas = empresaRepositorio.findBySectorEconomico(sector);
        logger.info("Encontradas {} empresas del sector {}", empresas.size(), sector);
        
        return empresas;
    }

    @Transactional(readOnly = true)
    public Empresa buscarPorId(Integer id) {
        logger.debug("Buscando empresa por ID: {}", id);
        
        Empresa empresa = empresaRepositorio.findById(id)
                .orElseThrow(() -> {
                    logger.warn("No se encontró empresa con ID: {}", id);
                    return new NoEncontradoExcepcion("Empresa", "No se encontró empresa con ID: " + id);
                });
        
        logger.info("Empresa encontrada: {} - {}", empresa.getIdEmpresa(), empresa.getRazonSocial());
        return empresa;
    }

    @Transactional(readOnly = true)
    public Empresa buscarPorNumeroIdentificacion(String numeroIdentificacion) {
        logger.debug("Buscando empresa por número de identificación: {}", numeroIdentificacion);
        
        Empresa empresa = empresaRepositorio.findByNumeroIdentificacion(numeroIdentificacion)
                .orElseThrow(() -> {
                    logger.warn("No se encontró empresa con RUC: {}", numeroIdentificacion);
                    return new NoEncontradoExcepcion("Empresa", "Empresa no encontrada con número de identificación: " + numeroIdentificacion);
                });
        
        logger.info("Empresa encontrada por RUC {}: {}", numeroIdentificacion, empresa.getRazonSocial());
        return empresa;
    }

    @Transactional(readOnly = true)
    public Optional<Empresa> buscarEmpresaPorIdentificacion(String numeroIdentificacion) {
        logger.debug("Búsqueda opcional de empresa por identificación: {}", numeroIdentificacion);
        
        Optional<Empresa> empresa = empresaRepositorio.findByNumeroIdentificacion(numeroIdentificacion);
        if (empresa.isPresent()) {
            logger.debug("Empresa encontrada: {}", empresa.get().getRazonSocial());
        } else {
            logger.debug("No se encontró empresa con identificación: {}", numeroIdentificacion);
        }
        
        return empresa;
    }

    public Empresa actualizar(Empresa empresa) {
        logger.info("Iniciando actualización de empresa con ID: {}", empresa.getIdEmpresa());
        
        try {
            Empresa empresaDB = empresaRepositorio.findById(empresa.getIdEmpresa())
                    .orElseThrow(() -> {
                        logger.error("Empresa no encontrada para actualización con ID: {}", empresa.getIdEmpresa());
                        return new ActualizarExcepcion(String.valueOf(empresa.getIdEmpresa()), "Empresa");
                    });

            boolean cambiosRealizados = false;

            if (empresa.getNombreComercial() != null && !empresa.getNombreComercial().equals(empresaDB.getNombreComercial())) {
                validarNombreComercial(empresa.getNombreComercial());
                empresaDB.setNombreComercial(empresa.getNombreComercial());
                cambiosRealizados = true;
                logger.debug("Nombre comercial actualizado a: {}", empresa.getNombreComercial());
            }

            if (empresa.getCorreoElectronico() != null && !empresa.getCorreoElectronico().equals(empresaDB.getCorreoElectronico())) {
                validarCorreoElectronico(empresa.getCorreoElectronico());
                empresaDB.setCorreoElectronico(empresa.getCorreoElectronico());
                cambiosRealizados = true;
                logger.debug("Correo electrónico actualizado a: {}", empresa.getCorreoElectronico());
            }

            if (empresa.getTipo() != null && !empresa.getTipo().equals(empresaDB.getTipo())) {
                validarTipoEmpresa(empresa.getTipo());
                empresaDB.setTipo(empresa.getTipo());
                cambiosRealizados = true;
                logger.debug("Tipo de empresa actualizado a: {}", empresa.getTipo());
            }

            if (empresa.getEstado() != null && !empresa.getEstado().equals(empresaDB.getEstado())) {
                validarEstado(empresa.getEstado());
                validarCambioEstado(empresaDB.getEstado(), empresa.getEstado());
                empresaDB.setEstado(empresa.getEstado());
                cambiosRealizados = true;
                logger.debug("Estado actualizado de {} a {}", empresaDB.getEstado(), empresa.getEstado());
            }

            if (empresa.getSectorEconomico() != null && !empresa.getSectorEconomico().equals(empresaDB.getSectorEconomico())) {
                validarSectorEconomico(empresa.getSectorEconomico());
                empresaDB.setSectorEconomico(empresa.getSectorEconomico());
                cambiosRealizados = true;
                logger.debug("Sector económico actualizado a: {}", empresa.getSectorEconomico());
            }

            if (cambiosRealizados) {
                empresaDB.setFechaActualizacion(new Date());
                Empresa empresaActualizada = empresaRepositorio.save(empresaDB);
                logger.info("Empresa actualizada exitosamente con ID: {}", empresaActualizada.getIdEmpresa());
                return empresaActualizada;
            } else {
                logger.info("No se realizaron cambios en la empresa con ID: {}", empresa.getIdEmpresa());
                return empresaDB;
            }

        } catch (RuntimeException e) {
            logger.error("Error al actualizar empresa con ID {}: {}", empresa.getIdEmpresa(), e.getMessage());
            throw new ActualizarExcepcion("Error al actualizar la empresa: " + e.getMessage(), "Empresa");
        }
    }

    public Empresa cambiarEstado(Integer id, String nuevoEstado) {
        logger.info("Cambiando estado de empresa ID {} a: {}", id, nuevoEstado);
        
        try {
            Empresa.Estado estadoEnum = Empresa.Estado.valueOf(nuevoEstado.toUpperCase());
            Empresa empresa = buscarPorId(id);
            
            validarCambioEstado(empresa.getEstado(), estadoEnum);
            
            empresa.setEstado(estadoEnum);
            empresa.setFechaActualizacion(new Date());
            
            Empresa empresaActualizada = empresaRepositorio.save(empresa);
            logger.info("Estado de empresa ID {} cambiado exitosamente a: {}", id, nuevoEstado);
            
            return empresaActualizada;
        } catch (IllegalArgumentException e) {
            logger.error("Estado inválido proporcionado: {}", nuevoEstado);
            throw new ActualizarExcepcion("Estado inválido: " + nuevoEstado, "Empresa");
        }
    }

    public Empresa activar(Integer id) {
        logger.info("Activando empresa con ID: {}", id);
        return cambiarEstado(id, "ACTIVO");
    }

    public Empresa inactivar(Integer id) {
        logger.info("Inactivando empresa con ID: {}", id);
        return cambiarEstado(id, "INACTIVO");
    }

    public Empresa suspender(Integer id) {
        logger.warn("Función suspender no disponible - solo estados ACTIVO/INACTIVO permitidos");
        throw new ActualizarExcepcion("Estado SUSPENDIDO no está disponible en el modelo actual", "Empresa");
    }

    @Transactional(readOnly = true)
    public boolean existePorRUC(String ruc) {
        logger.debug("Verificando existencia de empresa con RUC: {}", ruc);
        
        boolean existe = empresaRepositorio.findByNumeroIdentificacion(ruc).isPresent();
        logger.debug("Empresa con RUC {} {}", ruc, existe ? "existe" : "no existe");
        
        return existe;
    }

    @Transactional(readOnly = true)
    public int calcularAñosConstitucion(Integer id) {
        logger.debug("Calculando años de constitución para empresa ID: {}", id);
        
        Empresa empresa = buscarPorId(id);
        int años = calcularAñosConstitucion(empresa.getFechaConstitucion());
        
        logger.debug("Empresa ID {} tiene {} años de constitución", id, años);
        return años;
    }

    @Transactional(readOnly = true)
    public EmpresaEstadisticas obtenerEstadisticas() {
        logger.debug("Generando estadísticas de empresas");
        
        List<Empresa> todasEmpresas = empresaRepositorio.findAll();
        
        EmpresaEstadisticas estadisticas = new EmpresaEstadisticas();
        estadisticas.totalEmpresas = todasEmpresas.size();
        
        Map<Empresa.Estado, Long> porEstado = new HashMap<>();
        Map<Empresa.TipoEmpresa, Long> porTipo = new HashMap<>();
        Map<Empresa.SectorEconomico, Long> porSector = new HashMap<>();
        
        int empresasNuevas = 0;
        int empresasConsolidadas = 0;
        
        for (Empresa empresa : todasEmpresas) {
            // Estadísticas por estado
            porEstado.merge(empresa.getEstado(), 1L, Long::sum);
            
            // Estadísticas por tipo
            porTipo.merge(empresa.getTipo(), 1L, Long::sum);
            
            // Estadísticas por sector
            porSector.merge(empresa.getSectorEconomico(), 1L, Long::sum);
            
            // Clasificación por años de constitución
            int años = calcularAñosConstitucion(empresa.getFechaConstitucion());
            if (años < 5) {
                empresasNuevas++;
            } else {
                empresasConsolidadas++;
            }
        }
        
        estadisticas.empresasPorEstado = porEstado;
        estadisticas.empresasPorTipo = porTipo;
        estadisticas.empresasPorSector = porSector;
        estadisticas.empresasNuevas = empresasNuevas;
        estadisticas.empresasConsolidadas = empresasConsolidadas;
        
        logger.info("Estadísticas generadas: {} empresas totales, {} activas, {} nuevas", 
                   estadisticas.totalEmpresas, 
                   porEstado.getOrDefault(Empresa.Estado.ACTIVO, 0L),
                   empresasNuevas);
        
        return estadisticas;
    }

    public void eliminar(Integer id) {
        logger.warn("Iniciando eliminación de empresa con ID: {}", id);
        
        try {
            Empresa empresa = empresaRepositorio.findById(id)
                    .orElseThrow(() -> {
                        logger.error("Empresa no encontrada para eliminación con ID: {}", id);
                        return new EliminarExcepcion(String.valueOf(id), "Empresa");
                    });

            // Validar que la empresa pueda ser eliminada
            validarEliminacionEmpresa(empresa);

            empresaRepositorio.delete(empresa);
            logger.warn("Empresa eliminada: ID {}, RUC {}", id, empresa.getNumeroIdentificacion());
            
        } catch (RuntimeException e) {
            logger.error("Error al eliminar empresa con ID {}: {}", id, e.getMessage());
            throw new EliminarExcepcion("Error al eliminar la empresa: " + e.getMessage(), "Empresa");
        }
    }

    // Métodos de validación privados

    private void validarCamposObligatorios(Empresa empresa) {
        if (empresa.getTipoIdentificacion() == null || empresa.getNumeroIdentificacion() == null ||
                empresa.getNombreComercial() == null || empresa.getRazonSocial() == null ||
                empresa.getFechaConstitucion() == null || empresa.getCorreoElectronico() == null ||
                empresa.getTipo() == null || empresa.getSectorEconomico() == null) {

            logger.error("Campos obligatorios faltantes en empresa");
            throw new CrearExcepcion("Faltan campos obligatorios para la creación de la empresa", "Empresa");
        }
    }

    private void validarTipoIdentificacion(Empresa.TipoIdentificacion tipoIdentificacion) {
        if (tipoIdentificacion != Empresa.TipoIdentificacion.RUC) {
            logger.error("Tipo de identificación inválido para empresa: {}", tipoIdentificacion);
            throw new CrearExcepcion("Tipo de identificación inválido", "Empresa");
        }
    }

    private void validarRUC(String ruc) {
        if (ruc.length() != LONGITUD_RUC) {
            logger.error("RUC con longitud incorrecta: {} (esperado: {})", ruc.length(), LONGITUD_RUC);
            throw new CrearExcepcion("El RUC debe tener exactamente " + LONGITUD_RUC + " dígitos", "Empresa");
        }
        
        if (!ruc.matches("\\d+")) {
            logger.error("RUC contiene caracteres no numéricos: {}", ruc);
            throw new CrearExcepcion("El RUC debe contener solo números", "Empresa");
        }
        
        if (!validarDigitoVerificadorRUC(ruc)) {
            logger.error("RUC con dígito verificador inválido: {}", ruc);
            throw new CrearExcepcion("El RUC no es válido", "Empresa");
        }
    }

    private void validarNumeroIdentificacionUnico(String numeroIdentificacion) {
        if (empresaRepositorio.findByNumeroIdentificacion(numeroIdentificacion).isPresent()) {
            logger.error("RUC duplicado: {}", numeroIdentificacion);
            throw new CrearExcepcion("Ya existe una empresa con ese número de identificación", "Empresa");
        }
    }

    private void validarCorreoElectronico(String correo) {
        if (correo == null || !correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            logger.error("Correo electrónico inválido: {}", correo);
            throw new CrearExcepcion("Correo electrónico inválido", "Empresa");
        }
        
        // Validación adicional para dominios empresariales
        if (correo.length() > 100) {
            logger.error("Correo electrónico demasiado largo: {} caracteres", correo.length());
            throw new CrearExcepcion("El correo electrónico no puede exceder 100 caracteres", "Empresa");
        }
    }

    private void validarNombreComercial(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            logger.error("Nombre comercial vacío o nulo");
            throw new ActualizarExcepcion("El nombre comercial es obligatorio", "Empresa");
        }
        
        if (nombre.length() > 100) {
            logger.error("Nombre comercial demasiado largo: {} caracteres", nombre.length());
            throw new ActualizarExcepcion("El nombre comercial no puede exceder 100 caracteres", "Empresa");
        }
    }

    private void validarTipoEmpresa(Empresa.TipoEmpresa tipoEmpresa) {
        if (tipoEmpresa == null) {
            logger.error("Tipo de empresa nulo");
            throw new CrearExcepcion("Tipo de empresa inválido", "Empresa");
        }
    }

    private void validarSectorEconomico(Empresa.SectorEconomico sectorEconomico) {
        if (sectorEconomico == null) {
            logger.error("Sector económico nulo");
            throw new CrearExcepcion("Sector económico inválido", "Empresa");
        }
    }

    private void validarFechaConstitucion(Date fechaConstitucion) {
        if (fechaConstitucion == null) {
            logger.error("Fecha de constitución nula");
            throw new CrearExcepcion("La fecha de constitución es obligatoria", "Empresa");
        }
        
        if (fechaConstitucion.after(new Date())) {
            logger.error("Fecha de constitución en el futuro: {}", fechaConstitucion);
            throw new CrearExcepcion("La fecha de constitución no puede ser en el futuro", "Empresa");
        }
        
        int años = calcularAñosConstitucion(fechaConstitucion);
        
        if (años < AÑOS_MINIMOS_CONSTITUCION) {
            logger.error("Años de constitución insuficientes: {}", años);
            throw new CrearExcepcion("Los años de constitución deben ser al menos " + AÑOS_MINIMOS_CONSTITUCION, "Empresa");
        }
        
        if (años > AÑOS_MAXIMOS_CONSTITUCION) {
            logger.error("Años de constitución excesivos: {}", años);
            throw new CrearExcepcion("Los años de constitución no pueden exceder " + AÑOS_MAXIMOS_CONSTITUCION, "Empresa");
        }
    }

    private void validarReglasNegocioBancario(Empresa empresa) {
        // Validar años mínimos para operaciones bancarias
        int años = calcularAñosConstitucion(empresa.getFechaConstitucion());
        if (años < AÑOS_MINIMOS_OPERACION_BANCARIA) {
            logger.warn("Empresa con menos de {} años de constitución: {} años", AÑOS_MINIMOS_OPERACION_BANCARIA, años);
        }
        
        // Validar sectores económicos
        if (empresa.getSectorEconomico() == Empresa.SectorEconomico.TERCIARIO) {
            logger.info("Empresa del sector terciario (servicios) requiere validaciones adicionales");
        }
    }

    private void validarCambioEstado(Empresa.Estado estadoActual, Empresa.Estado nuevoEstado) {
        // Reglas de transición de estados (solo ACTIVO/INACTIVO disponibles)
        if (estadoActual == Empresa.Estado.INACTIVO && nuevoEstado == Empresa.Estado.ACTIVO) {
            logger.info("Reactivando empresa inactiva - requiere validaciones adicionales");
        }
        
        if (estadoActual == Empresa.Estado.ACTIVO && nuevoEstado == Empresa.Estado.INACTIVO) {
            logger.warn("Inactivando empresa activa - verificar dependencias");
        }
    }

    private void validarEliminacionEmpresa(Empresa empresa) {
        // En un sistema bancario real, verificaríamos relaciones con clientes, préstamos, etc.
        if (empresa.getEstado() == Empresa.Estado.ACTIVO) {
            logger.error("Intento de eliminar empresa activa: {}", empresa.getIdEmpresa());
            throw new EliminarExcepcion("No se puede eliminar una empresa activa", "Empresa");
        }
    }

    private int calcularAñosConstitucion(Date fechaConstitucion) {
        LocalDate fechaConst = fechaConstitucion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(fechaConst, LocalDate.now()).getYears();
    }

    private void validarEstado(Empresa.Estado estado) {
        if (estado == null) {
            logger.error("Estado de empresa nulo");
            throw new ActualizarExcepcion("Estado inválido", "Empresa");
        }
    }

    private boolean validarDigitoVerificadorRUC(String ruc) {
        try {
            // Validación simplificada para RUC empresarial (tercer dígito = 9)
            if (ruc.charAt(2) == '9') {
                return validarRUCEmpresa(ruc);
            } else {
                // RUC basado en cédula
                return validarRUCPersona(ruc);
            }
        } catch (Exception e) {
            logger.error("Error validando RUC {}: {}", ruc, e.getMessage());
            return false;
        }
    }

    private boolean validarRUCEmpresa(String ruc) {
        try {
            int[] coeficientes = {4, 3, 2, 7, 6, 5, 4, 3, 2};
            int suma = 0;
            
            for (int i = 0; i < 9; i++) {
                suma += Character.getNumericValue(ruc.charAt(i)) * coeficientes[i];
            }
            
            int residuo = suma % 11;
            int digitoVerificador = residuo == 0 ? 0 : 11 - residuo;
            
            return digitoVerificador == Character.getNumericValue(ruc.charAt(9));
        } catch (Exception e) {
            logger.error("Error validando RUC empresarial {}: {}", ruc, e.getMessage());
            return false;
        }
    }

    private boolean validarRUCPersona(String ruc) {
        try {
            String cedula = ruc.substring(0, 10);
            int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int suma = 0;
            
            for (int i = 0; i < 9; i++) {
                int digito = Character.getNumericValue(cedula.charAt(i));
                int multiplicacion = digito * coeficientes[i];
                
                if (multiplicacion > 9) {
                    multiplicacion = multiplicacion - 9;
                }
                
                suma += multiplicacion;
            }
            
            int residuo = suma % 10;
            int digitoVerificador = residuo == 0 ? 0 : 10 - residuo;
            
            return digitoVerificador == Character.getNumericValue(cedula.charAt(9));
        } catch (Exception e) {
            logger.error("Error validando RUC persona {}: {}", ruc, e.getMessage());
            return false;
        }
    }

    // Clase interna para estadísticas
    public static class EmpresaEstadisticas {
        public int totalEmpresas;
        public int empresasNuevas;
        public int empresasConsolidadas;
        public Map<Empresa.Estado, Long> empresasPorEstado;
        public Map<Empresa.TipoEmpresa, Long> empresasPorTipo;
        public Map<Empresa.SectorEconomico, Long> empresasPorSector;
    }
}
