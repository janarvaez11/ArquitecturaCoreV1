package com.banquito.core.aplicacion.prestamos.servicio;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CondicionComisionNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.CondicionComision;
import com.banquito.core.aplicacion.prestamos.modelo.ComisionPrestamo;
import com.banquito.core.aplicacion.prestamos.repositorio.CondicionComisionRepositorio;
import com.banquito.core.aplicacion.prestamos.repositorio.ComisionPrestamoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CondicionComisionServicio {

    private final CondicionComisionRepositorio repositorio;
    private final ComisionPrestamoRepositorio comisionPrestamoRepositorio;

    private static final List<String> TIPOS_CONDICION_VALOR = Arrays.asList(
            "MONTO MINIMO", "PLAZO MINIMO", "PUNTAJE CREDITO", "EDAD CLIENTE_MINIMA"
    );

    private static final List<String> TIPOS_CONDICION_TEXTO = Arrays.asList(
            "TIPO CLIENTE", "SEGMENTO CLIENTE", "ESTADO PRESTAMO"
    );

    public CondicionComisionServicio(CondicionComisionRepositorio repositorio, 
                                   ComisionPrestamoRepositorio comisionPrestamoRepositorio) {
        this.repositorio = repositorio;
        this.comisionPrestamoRepositorio = comisionPrestamoRepositorio;
    }

    public List<CondicionComision> findByTipoCondicion(String tipoCondicion) {
        if (!TIPOS_CONDICION_VALOR.contains(tipoCondicion) && !TIPOS_CONDICION_TEXTO.contains(tipoCondicion)) {
            throw new BusquedaExcepcion("CondicionComision",
                    "Tipo de condición no válido. Debe ser uno de: " +
                            String.join(", ", TIPOS_CONDICION_VALOR) + " o " +
                            String.join(", ", TIPOS_CONDICION_TEXTO));
        }
        return this.repositorio.findByTipoCondicion(tipoCondicion);
    }

    public CondicionComision findById(Integer id) {
        Optional<CondicionComision> condicionOpcional = this.repositorio.findById(id);
        if (condicionOpcional.isPresent()) {
            return condicionOpcional.get();
        } else {
            throw new CondicionComisionNoEncontradoExcepcion("Condicion Comision","Condicion Comision no se encontrado con ID: " + id);
        }
    }

    @Transactional
    public void create(CondicionComision condicionComision) {
        try {
            // Verificar que la comisión de préstamo exista
            if (condicionComision.getComisionPrestamo() == null || 
                condicionComision.getComisionPrestamo().getId() == null) {
                throw new CrearEntidadExcepcion("Condicion Comision", 
                    "La comisión de préstamo es requerida");
            }

            Optional<ComisionPrestamo> comisionOpcional = comisionPrestamoRepositorio
                .findById(condicionComision.getComisionPrestamo().getId());

            if (!comisionOpcional.isPresent()) {
                throw new CrearEntidadExcepcion("Condicion Comision", 
                    "No se encontró la comisión de préstamo con ID: " + 
                    condicionComision.getComisionPrestamo().getId());
            }

            // Establecer la comisión de préstamo existente
            condicionComision.setComisionPrestamo(comisionOpcional.get());
            
            validarCondicionComision(condicionComision);
            this.repositorio.save(condicionComision);
        } catch (Exception rte) {
            throw new CrearEntidadExcepcion("Condicion Comision", 
                "Error al crear la Condicion de la Comision. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void update(CondicionComision condicionComision) {
        try {
            Optional<CondicionComision> condicionOpcional = this.repositorio.findById(condicionComision.getId());

            if (condicionOpcional.isPresent()) {
                CondicionComision comisionDb = condicionOpcional.get();
                comisionDb.setComisionPrestamo(condicionComision.getComisionPrestamo());
                comisionDb.setTipoCondicion(condicionComision.getTipoCondicion());
                comisionDb.setValor(condicionComision.getValor());
                comisionDb.setValorTexto(condicionComision.getValorTexto());

                validarCondicionComision(comisionDb);
                this.repositorio.save(comisionDb);
            } else {
                throw new CondicionComisionNoEncontradoExcepcion("Condicion Comision", "Error al actualizar la Condicion Comision. No se encontró la Condicion Comision con ID: " + condicionComision.getId());
            }
        } catch (Exception rte) {
            throw new ActualizarEntidadExcepcion("Condicion Comision", "Error al actualizar la Condicion Comision. Texto del error: "+rte.getMessage());
        }
    }

    private void validarCondicionComision(CondicionComision condicion) {
        String tipoCondicion = condicion.getTipoCondicion();

        // Validar que el tipo de condición sea válido
        if (!TIPOS_CONDICION_VALOR.contains(tipoCondicion) && !TIPOS_CONDICION_TEXTO.contains(tipoCondicion)) {
            throw new BusquedaExcepcion("CondicionComision",
                    "Tipo de condición no válido. Debe ser uno de: " +
                            String.join(", ", TIPOS_CONDICION_VALOR) + " o " +
                            String.join(", ", TIPOS_CONDICION_TEXTO));
        }

        // Validar según el tipo de condición
        if (TIPOS_CONDICION_VALOR.contains(tipoCondicion)) {
            if (condicion.getValor() == null) {
                throw new BusquedaExcepcion("CondicionComision",
                        "Para el tipo de condición " + tipoCondicion + " se requiere un valor numérico");
            }
            // Para condiciones de valor, establecer un valor por defecto para valorTexto
            condicion.setValorTexto("N/A");
        } else if (TIPOS_CONDICION_TEXTO.contains(tipoCondicion)) {
            if (condicion.getValorTexto() == null || condicion.getValorTexto().trim().isEmpty()) {
                throw new BusquedaExcepcion("CondicionComision",
                        "Para el tipo de condición " + tipoCondicion + " se requiere un valor de texto");
            }
            // Para condiciones de texto, establecer un valor por defecto para valor
            condicion.setValor(BigDecimal.ZERO);
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            Optional<CondicionComision> condicionOpcional = this.repositorio.findById(id);
            if (condicionOpcional.isPresent()) {
                this.repositorio.delete(condicionOpcional.get());
            } else {
                throw new CondicionComisionNoEncontradoExcepcion("Condicion Comision", "Error al eliminar la Condicion Comision. No se encontró la Condicion Comision con ID: " + id);
            }
        } catch (Exception rte) {
            throw new EliminarEntidadExcepcion("Condicion Comision", "Error al eliminar la Condicion Comision. Texto del error: "+rte.getMessage());
        }
    }
}
