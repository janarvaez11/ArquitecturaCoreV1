package com.banquito.core.aplicacion.prestamos.servicio;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.ComisionPrestamoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.ComisionPrestamo;
import com.banquito.core.aplicacion.prestamos.repositorio.ComisionPrestamoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ComisionPrestamoServicio {
    
    private final ComisionPrestamoRepositorio repositorio;
    private static final List<String> TIPOS_COMISION_VALIDOS = Arrays.asList(
        "ORIGINACION", "PAGO ATRASADO", "PREPAGO", "MODIFICACION", "SERVICIO ADICIONAL"
    );
    private static final List<String> TIPOS_CALCULO_VALIDOS = Arrays.asList(
        "FIJA", "PORCENTAJE"
    );

    public ComisionPrestamoServicio(ComisionPrestamoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public ComisionPrestamo findById(Integer id) {
        Optional<ComisionPrestamo> comisionOpcional = this.repositorio.findById(id);
        if (comisionOpcional.isPresent()) {
            return comisionOpcional.get();
        } else {
            throw new ComisionPrestamoNoEncontradoExcepcion("Comision Prestamo","Comision Prestamo no se encontrado con ID: " + id);
        }
    }

    public List<ComisionPrestamo> findByTipoComision(String tipoComision) {
        if (!TIPOS_COMISION_VALIDOS.contains(tipoComision)) {
            throw new BusquedaExcepcion("ComisionPrestamo", 
                "Tipo de comisión no válido. Debe ser uno de: " + TIPOS_COMISION_VALIDOS);
        }
        return this.repositorio.findByTipoComision(tipoComision);
    }

    @Transactional
    public void create(ComisionPrestamo comisionPrestamo) {
        try {
            validarComision(comisionPrestamo);
            comisionPrestamo.setFechaCreacion(new Date());
            this.repositorio.save(comisionPrestamo);
        } catch (Exception rte) {
            throw new CrearEntidadExcepcion("Comision Prestamo", "Error al crear la Comision del Prestamo. Texto del error: "+rte.getMessage());
        }
    }

    @Transactional
    public void update(ComisionPrestamo comisionPrestamo) {
        try {
            Optional<ComisionPrestamo> comisionOpcional = this.repositorio.findById(comisionPrestamo.getId());

            if (comisionOpcional.isPresent()) {
                ComisionPrestamo comisionDb = comisionOpcional.get();
                comisionDb.setNombre(comisionPrestamo.getNombre());
                comisionDb.setTipoComision(comisionPrestamo.getTipoComision());
                comisionDb.setTipoCalculo(comisionPrestamo.getTipoCalculo());
                comisionDb.setValor(comisionPrestamo.getValor());

                validarComision(comisionDb);
                this.repositorio.save(comisionDb);
            } else {
                throw new ComisionPrestamoNoEncontradoExcepcion("ComisionPrestamo", "Error al actualizar la ComisionPrestamo. No se encontró la ComisionPrestamo con ID: " + comisionPrestamo.getId());
            }
        } catch (Exception rte) {
            throw new ActualizarEntidadExcepcion("ComisionPrestamo", "Error al actualizar la ComisionPrestamo. Texto del error: "+rte.getMessage());
        }
    }

    private void validarComision(ComisionPrestamo comision) {
        // Validar tipo de comisión
        if (!TIPOS_COMISION_VALIDOS.contains(comision.getTipoComision())) {
            throw new BusquedaExcepcion("ComisionPrestamo", 
                "Tipo de comisión no válido. Debe ser uno de: " + TIPOS_COMISION_VALIDOS);
        }

        // Validar tipo de cálculo
        if (!TIPOS_CALCULO_VALIDOS.contains(comision.getTipoCalculo())) {
            throw new BusquedaExcepcion("ComisionPrestamo", 
                "Tipo de cálculo no válido. Debe ser uno de: " + TIPOS_CALCULO_VALIDOS);
        }

        // Validar valor para porcentaje
        if ("PORCENTAJE".equals(comision.getTipoCalculo()) && 
            comision.getValor().compareTo(new BigDecimal("100")) > 0) {
            throw new BusquedaExcepcion("ComisionPrestamo", 
                "El porcentaje no puede ser mayor al 100%");
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            Optional<ComisionPrestamo> comisionOpcional = this.repositorio.findById(id);
            if (comisionOpcional.isPresent()) {
                this.repositorio.delete(comisionOpcional.get());
            } else {
                throw new ComisionPrestamoNoEncontradoExcepcion("ComisionPrestamo", "Error al eliminar la ComisionPrestamo. No se encontró la ComisionPrestamo con ID: " + id);
            }
        } catch (Exception rte) {
            throw new EliminarEntidadExcepcion("ComisionPrestamo", "Error al eliminar la ComisionPrestamo. Texto del error: "+rte.getMessage());
        }
    }

    
}
