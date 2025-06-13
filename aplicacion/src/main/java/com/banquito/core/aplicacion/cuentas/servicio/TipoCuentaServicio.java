package com.banquito.core.aplicacion.cuentas.servicio;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.modelo.TipoCuenta;
import com.banquito.core.aplicacion.cuentas.modelo.TasaInteres;
import com.banquito.core.aplicacion.cuentas.repositorio.TipoCuentaRepositorio;
import com.banquito.core.aplicacion.cuentas.repositorio.TasaInteresRepositorio;

import jakarta.transaction.Transactional;

@Service
public class TipoCuentaServicio {
    
    private final TipoCuentaRepositorio tipoCuentaRepositorio;
    private final TasaInteresRepositorio tasaInteresRepositorio;

    public TipoCuentaServicio(TipoCuentaRepositorio tipoCuentaRepositorio,
                             TasaInteresRepositorio tasaInteresRepositorio) {
        this.tipoCuentaRepositorio = tipoCuentaRepositorio;
        this.tasaInteresRepositorio = tasaInteresRepositorio;
    }

    public List<TipoCuenta> listarTodos() {
        List<TipoCuenta> tipos = tipoCuentaRepositorio.findAll();
        if (tipos.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TipoCuenta", "No existen tipos de cuenta registrados");
        }
        return tipos;
    }

    public Page<TipoCuenta> listarTodosPaginado(Pageable pageable) {
        Page<TipoCuenta> page = tipoCuentaRepositorio.findAll(pageable);
        if (page.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TipoCuenta", "No existen tipos de cuenta registrados");
        }
        return page;
    }

    public TipoCuenta buscarPorId(Integer id) {
        return tipoCuentaRepositorio.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaExcepcion("TipoCuenta", 
                    "No se encontró el tipo de cuenta con ID: " + id));
    }

    @Transactional
    public TipoCuenta crear(TipoCuenta tipoCuenta) {
        try {
            //validarTipoCuenta(tipoCuenta);
            validarTasaInteresPorDefecto(tipoCuenta.getTasaInteresPorDefecto());
            return this.tipoCuentaRepositorio.save(tipoCuenta);
        } catch (RuntimeException e) {
            throw new CrearEntidadExcepcion("TipoCuenta", 
                "Error al crear el tipo de cuenta: " + e.getMessage());
        }
    }

    @Transactional
    public TipoCuenta actualizar(Integer id, TipoCuenta tipoCuenta) {
        try {
            TipoCuenta tipoCuentaDb = buscarPorId(id);
            validarTipoCuenta(tipoCuenta);
            validarTasaInteresPorDefecto(tipoCuenta.getTasaInteresPorDefecto());
            
            tipoCuentaDb.setNombre(tipoCuenta.getNombre());
            tipoCuentaDb.setDescripcion(tipoCuenta.getDescripcion());
            tipoCuentaDb.setTipoCliente(tipoCuenta.getTipoCliente());
            tipoCuentaDb.setEstado(tipoCuenta.getEstado());
            tipoCuentaDb.setTasaInteresPorDefecto(tipoCuenta.getTasaInteresPorDefecto());
            
            return this.tipoCuentaRepositorio.save(tipoCuentaDb);
        } catch (RuntimeException e) {
            throw new ActualizarEntidadExcepcion("TipoCuenta", 
                "Error al actualizar el tipo de cuenta: " + e.getMessage());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        try {
            TipoCuenta tipoCuenta = buscarPorId(id);
            if ("ACTIVO".equals(tipoCuenta.getEstado())) {
                throw new EliminarEntidadExcepcion("TipoCuenta", 
                    "No se puede eliminar un tipo de cuenta en estado ACTIVO");
            }
            this.tipoCuentaRepositorio.delete(tipoCuenta);
        } catch (RuntimeException e) {
            throw new EliminarEntidadExcepcion("TipoCuenta", 
                "Error al eliminar el tipo de cuenta: " + e.getMessage());
        }
    }

    public List<TipoCuenta> buscarPorNombre(String nombre) {
        List<TipoCuenta> tipos = tipoCuentaRepositorio.findByNombreContainingIgnoreCase(nombre);
        if (tipos.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TipoCuenta", 
                "No se encontraron tipos de cuenta con el nombre: " + nombre);
        }
        return tipos;
    }

    public List<TipoCuenta> buscarPorMoneda(String idMoneda) {
        List<TipoCuenta> tipos = tipoCuentaRepositorio.findByMonedaId(idMoneda);
        if (tipos.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TipoCuenta", 
                "No se encontraron tipos de cuenta para la moneda: " + idMoneda);
        }
        return tipos;
    }

    public List<TipoCuenta> buscarPorTipoCliente(String tipoCliente) {
        List<TipoCuenta> tipos = tipoCuentaRepositorio.findByTipoCliente(tipoCliente);
        if (tipos.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TipoCuenta", 
                "No se encontraron tipos de cuenta para el tipo de cliente: " + tipoCliente);
        }
        return tipos;
    }

    private void validarTasaInteresPorDefecto(TasaInteres tasaInteres) {
        if (tasaInteres == null || tasaInteres.getIdTasaInteres() == null) {
            throw new CrearEntidadExcepcion("TipoCuenta", 
                "La tasa de interés por defecto es obligatoria");
        }

        TasaInteres tasaExistente = this.tasaInteresRepositorio.findById(tasaInteres.getIdTasaInteres())
            .orElseThrow(() -> new CrearEntidadExcepcion("TipoCuenta", 
                "La tasa de interés por defecto con ID " + tasaInteres.getIdTasaInteres() + " no existe"));

        // Verificar que la tasa esté activa
        if (!"ACT".equals(tasaExistente.getEstado())) {
            throw new CrearEntidadExcepcion("TipoCuenta", 
                "La tasa de interés por defecto con ID " + tasaInteres.getIdTasaInteres() + " no está activa");
        }

        // Verificar que la tasa esté vigente
        Date fechaActual = new Date();
        if (fechaActual.before(tasaExistente.getFechaInicioVigencia()) || 
            fechaActual.after(tasaExistente.getFechaFinVigencia())) {
            throw new CrearEntidadExcepcion("TipoCuenta", 
                "La tasa de interés por defecto con ID " + tasaInteres.getIdTasaInteres() + 
                " no está vigente. Vigencia: " + tasaExistente.getFechaInicioVigencia() + 
                " hasta " + tasaExistente.getFechaFinVigencia());
        }
    }

    private void validarTipoCuenta(TipoCuenta tipoCuenta) {
        if (tipoCuenta.getNombre() == null || tipoCuenta.getNombre().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("TipoCuenta", "El nombre es obligatorio");
        }
        if (tipoCuenta.getDescripcion() == null || tipoCuenta.getDescripcion().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("TipoCuenta", "La descripción es obligatoria");
        }
        if (tipoCuenta.getEstado() == null || tipoCuenta.getEstado().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("TipoCuenta", "El estado es obligatorio");
        }
        if (!tipoCuenta.getEstado().equals("ACTIVO") && !tipoCuenta.getEstado().equals("INACTIVO")) {
            throw new CrearEntidadExcepcion("TipoCuenta", "El estado debe ser ACTIVO o INACTIVO");
        }
        if (tipoCuenta.getTipoCliente() == null || tipoCuenta.getTipoCliente().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("TipoCuenta", "El tipo de cliente es obligatorio");
        }
    }
}
