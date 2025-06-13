package com.banquito.core.aplicacion.clientes.servicio;

import com.banquito.core.aplicacion.clientes.modelo.ContactoTransaccionCliente;
import com.banquito.core.aplicacion.clientes.repositorio.ContactoTransaccionClienteRepositorio;
import com.banquito.core.aplicacion.clientes.repositorio.ClienteRepositorio;
import com.banquito.core.aplicacion.clientes.excepcion.CrearContactoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.ContactoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.ActualizarContactoExcepcion;
import com.banquito.core.aplicacion.clientes.excepcion.ClienteNoEncontradoExcepcion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class ContactoTransaccionClienteServicio {

    private static final Logger logger = LoggerFactory.getLogger(ContactoTransaccionClienteServicio.class);

    private static final int MIN_LONGITUD_TELEFONO = 7;
    private static final int MAX_LONGITUD_TELEFONO = 15;
    private static final int MAX_LONGITUD_CORREO = 40;
    
    private static final Pattern PATRON_CORREO = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PATRON_TELEFONO_MOVIL_EC = Pattern.compile("^09\\d{8}$");
    private static final Pattern PATRON_TELEFONO_FIJO_EC = Pattern.compile("^0[2-7]\\d{7}$");

    private final ContactoTransaccionClienteRepositorio contactoRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public ContactoTransaccionClienteServicio(ContactoTransaccionClienteRepositorio contactoRepositorio,
                                            ClienteRepositorio clienteRepositorio) {
        this.contactoRepositorio = contactoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        logger.info("ContactoTransaccionClienteServicio inicializado correctamente");
    }

    public ContactoTransaccionCliente crear(ContactoTransaccionCliente contacto) {
        logger.info("Iniciando creación de contacto transaccional para cliente ID: {}", 
                   contacto.getIdCliente());
        
        try {
            validarDatosObligatorios(contacto);
            validarClienteExiste(contacto.getIdCliente());
            validarContactoNoExiste(contacto.getIdCliente());
            
            contacto.setFechaCreacion(new Date());
            
            ContactoTransaccionCliente contactoCreado = contactoRepositorio.save(contacto);
            logger.info("Contacto transaccional creado exitosamente para cliente ID: {}", 
                       contactoCreado.getIdCliente());
            
            return contactoCreado;
        } catch (Exception e) {
            logger.error("Error al crear contacto transaccional para cliente ID {}: {}", 
                        contacto.getIdCliente(), e.getMessage(), e);
            throw new CrearContactoExcepcion("Error al crear contacto transaccional: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public ContactoTransaccionCliente buscarPorId(Integer idCliente) {
        logger.debug("Buscando contacto transaccional por ID cliente: {}", idCliente);
        
        if (idCliente == null) {
            logger.warn("ID de cliente es nulo");
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        
        ContactoTransaccionCliente contacto = contactoRepositorio.findById(idCliente)
                .orElseThrow(() -> {
                    logger.warn("Contacto transaccional no encontrado para cliente ID: {}", idCliente);
                    return new ContactoNoEncontradoExcepcion(idCliente);
                });
        
        limpiarRelacionesContacto(contacto);
        logger.debug("Contacto transaccional encontrado para cliente ID: {}", idCliente);
        return contacto;
    }

    @Transactional(readOnly = true)
    public ContactoTransaccionCliente obtenerPorCliente(Integer idCliente) {
        logger.debug("Obteniendo contacto transaccional por cliente ID: {}", idCliente);
        
        validarClienteExiste(idCliente);
        ContactoTransaccionCliente contacto = contactoRepositorio.findByCliente_idCliente(idCliente)
                .orElseThrow(() -> {
                    logger.warn("Contacto transaccional no encontrado para cliente ID: {}", idCliente);
                    return new ContactoNoEncontradoExcepcion(idCliente);
                });
        
        limpiarRelacionesContacto(contacto);
        logger.debug("Contacto transaccional obtenido para cliente ID: {}", idCliente);
        return contacto;
    }

    @Transactional(readOnly = true)
    public Optional<ContactoTransaccionCliente> buscarContactoPorCliente(Integer idCliente) {
        logger.debug("Buscando contacto opcional por cliente ID: {}", idCliente);
        
        if (idCliente == null) {
            logger.warn("ID de cliente es nulo para búsqueda opcional");
            return Optional.empty();
        }
        
        validarClienteExiste(idCliente);
        Optional<ContactoTransaccionCliente> contacto = contactoRepositorio.findByCliente_idCliente(idCliente);
        
        logger.debug("Contacto encontrado para cliente ID {}: {}", idCliente, contacto.isPresent());
        return contacto;
    }

    private void limpiarRelacionesContacto(ContactoTransaccionCliente contacto) {
        if (contacto == null || contacto.getCliente() == null) return;
        
        contacto.getCliente().setTelefonos(null);
        contacto.getCliente().setDirecciones(null);
        contacto.getCliente().setContacto(null);
        
        if (contacto.getCliente().getPaisNacionalidad() != null) {
            contacto.getCliente().getPaisNacionalidad().setEstructurasGeograficas(null);
            contacto.getCliente().getPaisNacionalidad().setLocacionesGeograficas(null);
        }
        
        if (contacto.getCliente().getSucursal() != null) {
            contacto.getCliente().getSucursal().setLocacion(null);
            contacto.getCliente().getSucursal().setEntidadBancaria(null);
        }
    }

    @Transactional(readOnly = true)
    public Page<ContactoTransaccionCliente> listarTodos(Pageable pageable) {
        logger.debug("Listando todos los contactos transaccionales con paginación: página {}, tamaño {}", 
                    pageable.getPageNumber(), pageable.getPageSize());
        
        Page<ContactoTransaccionCliente> contactos = contactoRepositorio.findAll(pageable);
        contactos.getContent().forEach(this::limpiarRelacionesContacto);
        
        logger.debug("Encontrados {} contactos transaccionales", contactos.getTotalElements());
        return contactos;
    }

    public ContactoTransaccionCliente actualizar(ContactoTransaccionCliente contacto) {
        logger.info("Actualizando contacto transaccional para cliente ID: {}", contacto.getIdCliente());
        
        ContactoTransaccionCliente contactoExistente = buscarPorId(contacto.getIdCliente());
        
        try {
            if (contacto.getTelefono() != null) {
                validarTelefono(contacto.getTelefono());
                contactoExistente.setTelefono(contacto.getTelefono());
            }
            
            if (contacto.getCorreoElectronico() != null) {
                validarCorreoElectronico(contacto.getCorreoElectronico());
                contactoExistente.setCorreoElectronico(contacto.getCorreoElectronico());
            }
            
            contactoExistente.setFechaActualizacion(new Date());
            
            ContactoTransaccionCliente contactoActualizado = contactoRepositorio.save(contactoExistente);
            logger.info("Contacto transaccional actualizado exitosamente para cliente ID: {}", 
                       contactoActualizado.getIdCliente());
            
            return contactoActualizado;
        } catch (Exception e) {
            logger.error("Error al actualizar contacto transaccional para cliente ID {}: {}", 
                        contacto.getIdCliente(), e.getMessage(), e);
            throw new ActualizarContactoExcepcion("Error al actualizar contacto transaccional: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public boolean tieneContacto(Integer idCliente) {
        logger.debug("Verificando si cliente ID {} tiene contacto transaccional", idCliente);
        
        if (idCliente == null) {
            logger.warn("ID de cliente es nulo para verificación de contacto");
            return false;
        }
        
        validarClienteExiste(idCliente);
        boolean tieneContacto = contactoRepositorio.findByCliente_idCliente(idCliente).isPresent();
        
        logger.debug("Cliente ID {} tiene contacto: {}", idCliente, tieneContacto);
        return tieneContacto;
    }

    @Transactional(readOnly = true)
    public ContactoEstadisticas obtenerEstadisticas() {
        logger.debug("Obteniendo estadísticas de contactos transaccionales");
        
        ContactoEstadisticas estadisticas = new ContactoEstadisticas();
        estadisticas.setTotalContactos(contactoRepositorio.count());
        
        logger.debug("Estadísticas calculadas: {} contactos totales", estadisticas.getTotalContactos());
        return estadisticas;
    }

    public void eliminar(Integer idCliente) {
        logger.info("Eliminando contacto transaccional para cliente ID: {}", idCliente);
        
        ContactoTransaccionCliente contacto = buscarPorId(idCliente);
        contactoRepositorio.delete(contacto);
        
        logger.info("Contacto transaccional eliminado exitosamente para cliente ID: {}", idCliente);
    }

    private void validarDatosObligatorios(ContactoTransaccionCliente contacto) {
        if (contacto == null) {
            throw new IllegalArgumentException("El contacto transaccional no puede ser nulo");
        }
        
        if (contacto.getIdCliente() == null) {
            throw new CrearContactoExcepcion("El cliente es obligatorio");
        }
        
        validarTelefono(contacto.getTelefono());
        validarCorreoElectronico(contacto.getCorreoElectronico());
    }

    private void validarClienteExiste(Integer idCliente) {
        if (!clienteRepositorio.existsById(idCliente)) {
            logger.warn("Cliente no encontrado ID: {}", idCliente);
            throw new ClienteNoEncontradoExcepcion(idCliente);
        }
    }

    private void validarContactoNoExiste(Integer idCliente) {
        Optional<ContactoTransaccionCliente> contactoExistente = contactoRepositorio.findByCliente_idCliente(idCliente);
        if (contactoExistente.isPresent()) {
            logger.warn("Ya existe contacto transaccional para cliente ID: {}", idCliente);
            throw new CrearContactoExcepcion("El cliente ya tiene un contacto transaccional registrado");
        }
    }

    private void validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new CrearContactoExcepcion("El teléfono es obligatorio");
        }
        
        String telefonoLimpio = telefono.replaceAll("[^0-9+]", "");
        
        if (telefonoLimpio.length() < MIN_LONGITUD_TELEFONO) {
            throw new CrearContactoExcepcion("El teléfono debe tener al menos " + MIN_LONGITUD_TELEFONO + " dígitos");
        }
        
        if (telefonoLimpio.length() > MAX_LONGITUD_TELEFONO) {
            throw new CrearContactoExcepcion("El teléfono no puede exceder " + MAX_LONGITUD_TELEFONO + " dígitos");
        }
        
        if (PATRON_TELEFONO_MOVIL_EC.matcher(telefonoLimpio).matches()) {
            logger.debug("Teléfono móvil ecuatoriano válido: {}", telefonoLimpio);
        } else if (PATRON_TELEFONO_FIJO_EC.matcher(telefonoLimpio).matches()) {
            logger.debug("Teléfono fijo ecuatoriano válido: {}", telefonoLimpio);
        } else if (telefonoLimpio.startsWith("09") && telefonoLimpio.length() != 10) {
            throw new CrearContactoExcepcion("Los números móviles ecuatorianos deben tener 10 dígitos");
        } else if (telefonoLimpio.startsWith("0") && !telefonoLimpio.startsWith("09") && telefonoLimpio.length() != 9) {
            throw new CrearContactoExcepcion("Los números fijos ecuatorianos deben tener 9 dígitos");
        }
    }

    private void validarCorreoElectronico(String correoElectronico) {
        if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
            throw new CrearContactoExcepcion("El correo electrónico es obligatorio");
        }
        
        if (!PATRON_CORREO.matcher(correoElectronico).matches()) {
            throw new CrearContactoExcepcion("El formato del correo electrónico es inválido");
        }
        
        if (correoElectronico.length() > MAX_LONGITUD_CORREO) {
            throw new CrearContactoExcepcion("El correo electrónico no puede exceder " + MAX_LONGITUD_CORREO + " caracteres");
        }
        
        String dominio = correoElectronico.substring(correoElectronico.indexOf("@") + 1);
        if (dominio.length() < 4) {
            throw new CrearContactoExcepcion("El dominio del correo electrónico es demasiado corto");
        }
    }

    public static class ContactoEstadisticas {
        private long totalContactos;

        public long getTotalContactos() { 
            return totalContactos; 
        }
        
        public void setTotalContactos(long totalContactos) { 
            this.totalContactos = totalContactos; 
        }
    }
}

