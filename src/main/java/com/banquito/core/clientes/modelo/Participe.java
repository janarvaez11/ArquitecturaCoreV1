package com.banquito.core.clientes.modelo;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Participe {

    @Id
    private Integer id;

}