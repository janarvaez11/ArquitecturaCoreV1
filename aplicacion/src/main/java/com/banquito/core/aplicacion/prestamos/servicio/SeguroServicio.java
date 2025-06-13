package com.banquito.core.aplicacion.prestamos.servicio;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.SeguroNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.Seguro;
import com.banquito.core.aplicacion.prestamos.repositorio.SeguroRepositorio;
import com.banquito.core.aplicacion.prestamos.repositorio.PrestamoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class SeguroServicio {

    private final SeguroRepositorio repositorio;
    private final PrestamoRepositorio prestamoRepositorio;

    public SeguroServicio(SeguroRepositorio repositorio, PrestamoRepositorio prestamoRepositorio) {
        this.prestamoRepositorio = prestamoRepositorio;
        this.repositorio = repositorio;
    }

    public Seguro findById(Integer id) {
        Optional<Seguro> seguroOpcional = this.repositorio.findById(id);
        if (seguroOpcional.isPresent()) {
            return seguroOpcional.get();
        } else {
            throw new SeguroNoEncontradoExcepcion("Seguro", "Seguro no encontrado con ID: " + id);
        }
    }

    @Transactional
    public void create(Seguro Seguro) {
        try {
            this.repositorio.save(Seguro);
        } catch (Exception rte) {
            throw new CrearEntidadExcepcion("Seguro", "Error al crear el Seguro. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void update(Seguro Seguro) {
        try {
            Optional<Seguro> seguroOpcional = this.repositorio.findById(Seguro.getId());

            if (seguroOpcional.isPresent()) {
                Seguro seguroDb = seguroOpcional.get();
                seguroDb.setPrestamo(Seguro.getPrestamo());
                seguroDb.setTipoSeguro(Seguro.getTipoSeguro());
                seguroDb.setCompania(Seguro.getCompania());
                seguroDb.setMontoAsegurado(Seguro.getMontoAsegurado());
                seguroDb.setFechaInicio(Seguro.getFechaInicio());
                seguroDb.setFechaFin(Seguro.getFechaFin());
                seguroDb.setEstado(Seguro.getEstado());

                this.repositorio.save(seguroDb);
            } else {
                throw new SeguroNoEncontradoExcepcion("Seguro",
                        "Error al actualizar el Seguro. No se encontró la Seguro con ID: " + Seguro.getId());
            }
        } catch (Exception rte) {
            throw new ActualizarEntidadExcepcion("Seguro",
                    "Error al actualizar la Seguro. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            Optional<Seguro> seguroOpcional = this.repositorio.findById(id);
            if (seguroOpcional.isPresent()) {
                this.repositorio.delete(seguroOpcional.get());
            } else {
                throw new SeguroNoEncontradoExcepcion("Seguro",
                        "Error al eliminar la Seguro. No se encontró el Seguro con ID: " + id);
            }
        } catch (Exception rte) {
            throw new EliminarEntidadExcepcion("Seguro",
                    "Error al eliminar el Seguro. Texto del error: " + rte.getMessage());
        }
    }

    // encontrar prestamo asociado a un seguro
    public Optional<Seguro> buscarPrimerSeguroPorIdPrestamo(Integer idPrestamo) {
        return prestamoRepositorio.findById(idPrestamo)
                .flatMap(prestamo -> repositorio.findFirstByPrestamo(prestamo));
    }

}
