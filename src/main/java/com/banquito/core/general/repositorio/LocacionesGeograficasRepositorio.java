package com.banquito.core.general.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.general.modelo.LocacionesGeograficas;

@Repository
public interface LocacionesGeograficasRepositorio extends JpaRepository<LocacionesGeograficas, Integer> {
}