package com.banquito.core.aplicacion.clientes.servicio;

import com.banquito.core.aplicacion.clientes.excepcion.ActualizarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.CrearExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.EliminarExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.NoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.modelo.Persona;
import com.banquito.core.aplicacion.clientes.repositorio.PersonaRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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
public class PersonaServicio {

    private static final Logger logger = LoggerFactory.getLogger(PersonaServicio.class);

    // Constantes de negocio
    private static final String ESTADO_ACTIVO = "ACTIVO";
    private static final String ESTADO_INACTIVO = "INACTIVO";
    
    private static final String TIPO_IDENTIFICACION_CEDULA = "CEDULA";
    private static final String TIPO_IDENTIFICACION_PASAPORTE = "PASAPORTE";
    
    private static final String GENERO_MASCULINO = "MASCULINO";
    private static final String GENERO_FEMENINO = "FEMENINO";
    
    private static final int EDAD_MINIMA = 18;
    private static final int EDAD_MAXIMA = 100;
    private static final int EDAD_MINIMA_PRODUCTOS_ESPECIALES = 21;
    private static final int EDAD_MAXIMA_CREDITOS = 75;
    private static final int LONGITUD_CEDULA = 10;
    private static final int MIN_LONGITUD_PASAPORTE = 6;
    private static final int MAX_LONGITUD_PASAPORTE = 15;

    private final PersonaRepositorio personaRepositorio;

    public PersonaServicio(PersonaRepositorio personaRepositorio) {
        this.personaRepositorio = personaRepositorio;
    }

    /**
     * Crear una nueva persona
     */
    public Persona crear(Persona persona) {
        logger.info("Iniciando creación de persona con identificación: {}", persona.getNumeroIdentificacion());
        
        try {
            validarDatosObligatorios(persona);
            validarCorreo(persona.getCorreoElectronico());
            validarTipoIdentificacion(persona.getTipoIdentificacion());
            validarNumeroIdentificacionUnico(persona.getNumeroIdentificacion());
            validarNumeroIdentificacion(persona.getTipoIdentificacion(), persona.getNumeroIdentificacion());
            validarFechaNacimiento(persona.getFechaNacimiento());
            validarGenero(persona.getGenero());
            validarReglasNegocioBancario(persona);

            if (persona.getFechaRegistro() == null) {
                persona.setFechaRegistro(Date.from(Instant.now()));
                logger.debug("Fecha de registro asignada automáticamente");
            }
            if (persona.getEstado() == null) {
                persona.setEstado(Persona.Estado.ACTIVO);
                logger.debug("Estado por defecto asignado: ACTIVO");
            }

            persona.setFechaActualizacion(Date.from(Instant.now()));

            Persona personaCreada = personaRepositorio.save(persona);
            logger.info("Persona creada exitosamente con ID: {} e identificación: {}", 
                       personaCreada.getIdPersona(), personaCreada.getNumeroIdentificacion());
            
            return personaCreada;
        } catch (RuntimeException rte) {
            logger.error("Error al crear persona con identificación {}: {}", persona.getNumeroIdentificacion(), rte.getMessage());
            throw new CrearExcepcion("Error al crear una persona: " + rte.getMessage(), "Persona");
        }
    }

    /**
     * Buscar todas las personas con paginación
     */
    @Transactional(readOnly = true)
    public Page<Persona> buscarTodas(Pageable pageable) {
        logger.debug("Buscando todas las personas con paginación: página {}, tamaño {}", 
                    pageable.getPageNumber(), pageable.getPageSize());
        
        Page<Persona> personas = personaRepositorio.findAll(pageable);
        logger.info("Encontradas {} personas en total", personas.getTotalElements());
        
        return personas;
    }

    /**
     * Listar todas las personas (sin paginación)
     */
    @Transactional(readOnly = true)
    public List<Persona> buscarTodos() {
        logger.debug("Buscando todas las personas sin paginación");
        
        List<Persona> personas = personaRepositorio.findAll();
        logger.info("Encontradas {} personas en total", personas.size());
        
        return personas;
    }

    /**
     * Buscar persona por ID
     */
    @Transactional(readOnly = true)
    public Persona buscarPorId(Integer id) {
        logger.debug("Buscando persona por ID: {}", id);
        
        Persona persona = personaRepositorio.findById(id)
                .orElseThrow(() -> {
                    logger.warn("No se encontró persona con ID: {}", id);
                    return new NoEncontradoExcepcion("Persona", "No se encontró persona con ID: " + id);
                });
        
        logger.info("Persona encontrada: {} - {}", persona.getIdPersona(), persona.getNombre());
        return persona;
    }

    /**
     * Buscar persona por número de identificación
     */
    @Transactional(readOnly = true)
    public Persona buscarPorNumeroIdentificacion(String numeroIdentificacion) {
        logger.debug("Buscando persona por número de identificación: {}", numeroIdentificacion);
        
        Persona persona = personaRepositorio.findByNumeroIdentificacion(numeroIdentificacion)
                .orElseThrow(() -> {
                    logger.warn("No se encontró persona con identificación: {}", numeroIdentificacion);
                    return new NoEncontradoExcepcion("Persona", "No se encontró persona con número de identificación: " + numeroIdentificacion);
                });
        
        logger.info("Persona encontrada por identificación {}: {}", numeroIdentificacion, persona.getNombre());
        return persona;
    }

    /**
     * Buscar persona por número de identificación (opcional)
     */
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPersonaPorIdentificacion(String numeroIdentificacion) {
        logger.debug("Búsqueda opcional de persona por identificación: {}", numeroIdentificacion);
        
        Optional<Persona> persona = personaRepositorio.findByNumeroIdentificacion(numeroIdentificacion);
        if (persona.isPresent()) {
            logger.debug("Persona encontrada: {}", persona.get().getNombre());
        } else {
            logger.debug("No se encontró persona con identificación: {}", numeroIdentificacion);
        }
        
        return persona;
    }

    /**
     * Actualizar persona existente
     */
    public Persona actualizar(Persona persona) {
        logger.info("Iniciando actualización de persona con ID: {}", persona.getIdPersona());
        
        try {
            Persona personaDB = personaRepositorio.findById(persona.getIdPersona())
                    .orElseThrow(() -> {
                        logger.error("Persona no encontrada para actualización con ID: {}", persona.getIdPersona());
                        return new ActualizarExcepcion(String.valueOf(persona.getIdPersona()), "Persona");
                    });

            boolean cambiosRealizados = false;

            if (persona.getTipoIdentificacion() != null && !persona.getTipoIdentificacion().equals(personaDB.getTipoIdentificacion())) {
                validarTipoIdentificacion(persona.getTipoIdentificacion());
                personaDB.setTipoIdentificacion(persona.getTipoIdentificacion());
                cambiosRealizados = true;
                logger.debug("Tipo de identificación actualizado a: {}", persona.getTipoIdentificacion());
            }

            if (persona.getEstadoCivil() != null && !persona.getEstadoCivil().equals(personaDB.getEstadoCivil())) {
                validarEstadoCivil(persona.getEstadoCivil());
                personaDB.setEstadoCivil(persona.getEstadoCivil());
                cambiosRealizados = true;
                logger.debug("Estado civil actualizado a: {}", persona.getEstadoCivil());
            }

            if (persona.getNivelEstudio() != null && !persona.getNivelEstudio().equals(personaDB.getNivelEstudio())) {
                validarNivelEstudio(persona.getNivelEstudio());
                personaDB.setNivelEstudio(persona.getNivelEstudio());
                cambiosRealizados = true;
                logger.debug("Nivel de estudio actualizado a: {}", persona.getNivelEstudio());
            }

            if (persona.getCorreoElectronico() != null && !persona.getCorreoElectronico().equals(personaDB.getCorreoElectronico())) {
                validarCorreo(persona.getCorreoElectronico());
                personaDB.setCorreoElectronico(persona.getCorreoElectronico());
                cambiosRealizados = true;
                logger.debug("Correo electrónico actualizado a: {}", persona.getCorreoElectronico());
            }

            if (persona.getEstado() != null && !persona.getEstado().equals(personaDB.getEstado())) {
                validarEstado(persona.getEstado());
                validarCambioEstado(personaDB.getEstado(), persona.getEstado());
                personaDB.setEstado(persona.getEstado());
                cambiosRealizados = true;
                logger.debug("Estado actualizado de {} a {}", personaDB.getEstado(), persona.getEstado());
            }

            if (cambiosRealizados) {
                personaDB.setFechaActualizacion(Date.from(Instant.now()));
                Persona personaActualizada = personaRepositorio.save(personaDB);
                logger.info("Persona actualizada exitosamente con ID: {}", personaActualizada.getIdPersona());
                return personaActualizada;
            } else {
                logger.info("No se realizaron cambios en la persona con ID: {}", persona.getIdPersona());
                return personaDB;
            }

        } catch (RuntimeException rte) {
            logger.error("Error al actualizar persona con ID {}: {}", persona.getIdPersona(), rte.getMessage());
            throw new ActualizarExcepcion("Error al actualizar la persona: " + rte.getMessage(), "Persona");
        }
    }

    /**
     * Cambiar estado de la persona
     */
    public Persona cambiarEstado(Integer id, String nuevoEstado) {
        logger.info("Cambiando estado de persona ID {} a: {}", id, nuevoEstado);
        
        try {
            Persona.Estado estadoEnum = Persona.Estado.valueOf(nuevoEstado.toUpperCase());
            Persona persona = buscarPorId(id);
            
            validarCambioEstado(persona.getEstado(), estadoEnum);
            
            persona.setEstado(estadoEnum);
            persona.setFechaActualizacion(Date.from(Instant.now()));
            
            Persona personaActualizada = personaRepositorio.save(persona);
            logger.info("Estado de persona ID {} cambiado exitosamente a: {}", id, nuevoEstado);
            
            return personaActualizada;
        } catch (IllegalArgumentException e) {
            logger.error("Estado inválido proporcionado: {}", nuevoEstado);
            throw new ActualizarExcepcion("Estado inválido: " + nuevoEstado, "Persona");
        }
    }

    /**
     * Activar persona
     */
    public Persona activar(Integer id) {
        logger.info("Activando persona con ID: {}", id);
        return cambiarEstado(id, "ACTIVO");
    }

    /**
     * Inactivar persona
     */
    public Persona inactivar(Integer id) {
        logger.info("Inactivando persona con ID: {}", id);
        return cambiarEstado(id, "INACTIVO");
    }

    /**
     * Verificar si una persona existe por identificación
     */
    @Transactional(readOnly = true)
    public boolean existePorIdentificacion(String numeroIdentificacion) {
        logger.debug("Verificando existencia de persona con identificación: {}", numeroIdentificacion);
        
        boolean existe = personaRepositorio.findByNumeroIdentificacion(numeroIdentificacion).isPresent();
        logger.debug("Persona con identificación {} {}", numeroIdentificacion, existe ? "existe" : "no existe");
        
        return existe;
    }

    /**
     * Calcular edad de la persona
     */
    @Transactional(readOnly = true)
    public int calcularEdad(Integer id) {
        logger.debug("Calculando edad para persona ID: {}", id);
        
        Persona persona = buscarPorId(id);
        int edad = calcularEdad(persona.getFechaNacimiento());
        
        logger.debug("Persona ID {} tiene {} años", id, edad);
        return edad;
    }

    @Transactional(readOnly = true)
    public boolean esElegibleParaCreditos(Integer id) {
        logger.debug("Verificando elegibilidad crediticia para persona ID: {}", id);
        
        Persona persona = buscarPorId(id);
        int edad = calcularEdad(persona.getFechaNacimiento());
        
        boolean elegible = edad >= EDAD_MINIMA_PRODUCTOS_ESPECIALES && 
                          edad <= EDAD_MAXIMA_CREDITOS && 
                          persona.getEstado() == Persona.Estado.ACTIVO;
        
        logger.info("Persona ID {} {} elegible para créditos (edad: {} años, estado: {})", 
                   id, elegible ? "es" : "no es", edad, persona.getEstado());
        
        return elegible;
    }

    @Transactional(readOnly = true)
    public PersonaEstadisticas obtenerEstadisticas() {
        logger.debug("Generando estadísticas de personas");
        
        List<Persona> todasPersonas = personaRepositorio.findAll();
        
        PersonaEstadisticas estadisticas = new PersonaEstadisticas();
        estadisticas.totalPersonas = todasPersonas.size();
        
        Map<Persona.Estado, Long> porEstado = new HashMap<>();
        Map<Persona.Genero, Long> porGenero = new HashMap<>();
        Map<Persona.EstadoCivil, Long> porEstadoCivil = new HashMap<>();
        Map<Persona.NivelEstudio, Long> porNivelEstudio = new HashMap<>();
        
        int menores25 = 0;
        int entre25y40 = 0;
        int entre41y60 = 0;
        int mayores60 = 0;
        int elegiblesCredito = 0;
        
        for (Persona persona : todasPersonas) {
            // Estadísticas por estado
            porEstado.merge(persona.getEstado(), 1L, Long::sum);
            
            // Estadísticas por género
            porGenero.merge(persona.getGenero(), 1L, Long::sum);
            
            // Estadísticas por estado civil
            porEstadoCivil.merge(persona.getEstadoCivil(), 1L, Long::sum);
            
            // Estadísticas por nivel de estudio
            porNivelEstudio.merge(persona.getNivelEstudio(), 1L, Long::sum);
            
            // Clasificación por edad
            int edad = calcularEdad(persona.getFechaNacimiento());
            if (edad < 25) {
                menores25++;
            } else if (edad <= 40) {
                entre25y40++;
            } else if (edad <= 60) {
                entre41y60++;
            } else {
                mayores60++;
            }
            
            // Elegibilidad crediticia
            if (edad >= EDAD_MINIMA_PRODUCTOS_ESPECIALES && 
                edad <= EDAD_MAXIMA_CREDITOS && 
                persona.getEstado() == Persona.Estado.ACTIVO) {
                elegiblesCredito++;
            }
        }
        
        estadisticas.personasPorEstado = porEstado;
        estadisticas.personasPorGenero = porGenero;
        estadisticas.personasPorEstadoCivil = porEstadoCivil;
        estadisticas.personasPorNivelEstudio = porNivelEstudio;
        estadisticas.menores25 = menores25;
        estadisticas.entre25y40 = entre25y40;
        estadisticas.entre41y60 = entre41y60;
        estadisticas.mayores60 = mayores60;
        estadisticas.elegiblesCredito = elegiblesCredito;
        
        logger.info("Estadísticas generadas: {} personas totales, {} activas, {} elegibles para crédito", 
                   estadisticas.totalPersonas, 
                   porEstado.getOrDefault(Persona.Estado.ACTIVO, 0L),
                   elegiblesCredito);
        
        return estadisticas;
    }

    /**
     * Eliminar persona (solo si no está siendo utilizada)
     */
    public void eliminar(Integer id) {
        logger.warn("Iniciando eliminación de persona con ID: {}", id);
        
        try {
            Persona persona = personaRepositorio.findById(id)
                    .orElseThrow(() -> {
                        logger.error("Persona no encontrada para eliminación con ID: {}", id);
                        return new EliminarExcepcion(String.valueOf(id), "Persona");
                    });

            // Validar que la persona pueda ser eliminada
            validarEliminacionPersona(persona);

            personaRepositorio.delete(persona);
            logger.warn("Persona eliminada: ID {}, identificación {}", id, persona.getNumeroIdentificacion());
            
        } catch (RuntimeException rte) {
            logger.error("Error al eliminar persona con ID {}: {}", id, rte.getMessage());
            throw new EliminarExcepcion("Error al eliminar la persona: " + rte.getMessage(), "Persona");
        }
    }

    // Métodos de validación privados

    private void validarDatosObligatorios(Persona persona) {
        if (persona.getTipoIdentificacion() == null || persona.getNumeroIdentificacion() == null ||
                persona.getNombre() == null || persona.getGenero() == null || persona.getFechaNacimiento() == null ||
                persona.getEstadoCivil() == null || persona.getNivelEstudio() == null ||
                persona.getCorreoElectronico() == null) {
            logger.error("Campos obligatorios faltantes en persona");
            throw new CrearExcepcion("Todos los campos obligatorios deben estar presentes", "Persona");
        }
    }

    private void validarCorreo(String correo) {
        if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            logger.error("Correo electrónico inválido: {}", correo);
            throw new CrearExcepcion("Correo electrónico inválido", "Persona");
        }
        
        if (correo.length() > 100) {
            logger.error("Correo electrónico demasiado largo: {} caracteres", correo.length());
            throw new CrearExcepcion("El correo electrónico no puede exceder 100 caracteres", "Persona");
        }
    }

    private void validarTipoIdentificacion(Persona.TipoIdentificacion tipoIdentificacion) {
        if (tipoIdentificacion == null) {
            logger.error("Tipo de identificación nulo");
            throw new CrearExcepcion("Tipo de identificación inválido", "Persona");
        }
    }

    private void validarNumeroIdentificacionUnico(String numeroIdentificacion) {
        if (personaRepositorio.findByNumeroIdentificacion(numeroIdentificacion).isPresent()) {
            logger.error("Identificación duplicada: {}", numeroIdentificacion);
            throw new CrearExcepcion("Ya existe una persona con ese número de identificación", "Persona");
        }
    }

    private void validarNumeroIdentificacion(Persona.TipoIdentificacion tipoIdentificacion, String numeroIdentificacion) {
        if (tipoIdentificacion == Persona.TipoIdentificacion.CEDULA) {
            validarCedula(numeroIdentificacion);
        } else if (tipoIdentificacion == Persona.TipoIdentificacion.PASAPORTE) {
            validarPasaporte(numeroIdentificacion);
        }
    }

    private void validarCedula(String cedula) {
        if (cedula.length() != LONGITUD_CEDULA) {
            logger.error("Cédula con longitud incorrecta: {} (esperado: {})", cedula.length(), LONGITUD_CEDULA);
            throw new CrearExcepcion("La cédula debe tener exactamente " + LONGITUD_CEDULA + " dígitos", "Persona");
        }
        
        if (!cedula.matches("\\d{10}")) {
            logger.error("Cédula contiene caracteres no numéricos: {}", cedula);
            throw new CrearExcepcion("La cédula debe contener solo números", "Persona");
        }
        
        if (!validarDigitoVerificadorCedula(cedula)) {
            logger.error("Cédula con dígito verificador inválido: {}", cedula);
            throw new CrearExcepcion("El número de cédula no es válido", "Persona");
        }
    }

    private void validarPasaporte(String pasaporte) {
        if (pasaporte.length() < MIN_LONGITUD_PASAPORTE || pasaporte.length() > MAX_LONGITUD_PASAPORTE) {
            logger.error("Pasaporte con longitud incorrecta: {} (esperado: {}-{})", 
                        pasaporte.length(), MIN_LONGITUD_PASAPORTE, MAX_LONGITUD_PASAPORTE);
            throw new CrearExcepcion("El pasaporte debe tener entre " + MIN_LONGITUD_PASAPORTE + " y " + MAX_LONGITUD_PASAPORTE + " caracteres", "Persona");
        }
        
        if (!pasaporte.matches("^[A-Z0-9]+$")) {
            logger.error("Pasaporte con formato inválido: {}", pasaporte);
            throw new CrearExcepcion("El pasaporte debe contener solo letras mayúsculas y números", "Persona");
        }
    }

    private boolean validarDigitoVerificadorCedula(String cedula) {
        try {
            int[] digitos = new int[10];
            for (int i = 0; i < 10; i++) {
                digitos[i] = Integer.parseInt(cedula.substring(i, i + 1));
            }
            
            int provincia = digitos[0] * 10 + digitos[1];
            if (provincia < 1 || provincia > 24) {
                return false;
            }
            
            int tercerDigito = digitos[2];
            if (tercerDigito < 0 || tercerDigito > 5) {
                return false;
            }
            
            int suma = 0;
            for (int i = 0; i < 9; i++) {
                int digito = digitos[i];
                if (i % 2 == 0) {
                    digito *= 2;
                    if (digito > 9) {
                        digito -= 9;
                    }
                }
                suma += digito;
            }
            
            int digitoVerificador = 10 - (suma % 10);
            if (digitoVerificador == 10) {
                digitoVerificador = 0;
            }
            
            return digitoVerificador == digitos[9];
        } catch (Exception e) {
            logger.error("Error validando cédula {}: {}", cedula, e.getMessage());
            return false;
        }
    }

    private void validarFechaNacimiento(Date fechaNacimiento) {
        if (fechaNacimiento.after(new Date())) {
            logger.error("Fecha de nacimiento en el futuro: {}", fechaNacimiento);
            throw new CrearExcepcion("La fecha de nacimiento no puede ser en el futuro", "Persona");
        }
        
        int edad = calcularEdad(fechaNacimiento);
        if (edad < EDAD_MINIMA) {
            logger.error("Edad insuficiente: {} años (mínimo: {})", edad, EDAD_MINIMA);
            throw new CrearExcepcion("La persona debe ser mayor de " + EDAD_MINIMA + " años", "Persona");
        }
        
        if (edad > EDAD_MAXIMA) {
            logger.error("Edad excesiva: {} años (máximo: {})", edad, EDAD_MAXIMA);
            throw new CrearExcepcion("La edad no puede ser mayor a " + EDAD_MAXIMA + " años", "Persona");
        }
    }

    private void validarReglasNegocioBancario(Persona persona) {
        int edad = calcularEdad(persona.getFechaNacimiento());
        
        // Validar edad para productos especiales
        if (edad < EDAD_MINIMA_PRODUCTOS_ESPECIALES) {
            logger.info("Persona menor de {} años - productos limitados disponibles", EDAD_MINIMA_PRODUCTOS_ESPECIALES);
        }
        
        // Validar elegibilidad crediticia
        if (edad > EDAD_MAXIMA_CREDITOS) {
            logger.warn("Persona mayor de {} años - no elegible para créditos", EDAD_MAXIMA_CREDITOS);
        }
        
        // Validar nivel educativo para productos especializados
        if (persona.getNivelEstudio() == Persona.NivelEstudio.UNIVERSITARIA) {
            logger.info("Persona con educación universitaria - elegible para productos preferenciales");
        }
    }

    private void validarCambioEstado(Persona.Estado estadoActual, Persona.Estado nuevoEstado) {
        if (estadoActual == Persona.Estado.INACTIVO && nuevoEstado == Persona.Estado.ACTIVO) {
            logger.info("Reactivando persona inactiva - requiere validaciones adicionales");
        }
        
        if (estadoActual == Persona.Estado.ACTIVO && nuevoEstado == Persona.Estado.INACTIVO) {
            logger.warn("Inactivando persona activa - verificar dependencias");
        }
    }

    private void validarEliminacionPersona(Persona persona) {
        // En un sistema bancario real, verificaríamos relaciones con clientes, cuentas, etc.
        if (persona.getEstado() == Persona.Estado.ACTIVO) {
            logger.error("Intento de eliminar persona activa: {}", persona.getIdPersona());
            throw new EliminarExcepcion("No se puede eliminar una persona activa", "Persona");
        }
    }

    private int calcularEdad(Date fechaNacimiento) {
        LocalDate fechaNac = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(fechaNac, LocalDate.now()).getYears();
    }

    private void validarGenero(Persona.Genero genero) {
        if (genero == null) {
            logger.error("Género nulo");
            throw new CrearExcepcion("El género es obligatorio", "Persona");
        }
    }

    private void validarEstadoCivil(Persona.EstadoCivil estadoCivil) {
        if (estadoCivil == null) {
            logger.error("Estado civil nulo");
            throw new CrearExcepcion("Estado civil inválido", "Persona");
        }
    }

    private void validarNivelEstudio(Persona.NivelEstudio nivelEstudio) {
        if (nivelEstudio == null) {
            logger.error("Nivel de estudio nulo");
            throw new CrearExcepcion("Nivel de estudio inválido", "Persona");
        }
    }

    private void validarEstado(Persona.Estado estado) {
        if (estado == null) {
            logger.error("Estado de persona nulo");
            throw new ActualizarExcepcion("Estado inválido", "Persona");
        }
    }

    // Clase interna para estadísticas
    public static class PersonaEstadisticas {
        public int totalPersonas;
        public int menores25;
        public int entre25y40;
        public int entre41y60;
        public int mayores60;
        public int elegiblesCredito;
        public Map<Persona.Estado, Long> personasPorEstado;
        public Map<Persona.Genero, Long> personasPorGenero;
        public Map<Persona.EstadoCivil, Long> personasPorEstadoCivil;
        public Map<Persona.NivelEstudio, Long> personasPorNivelEstudio;
    }
}
