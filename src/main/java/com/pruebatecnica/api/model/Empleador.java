package com.pruebatecnica.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "empleador")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleador {
    @Id
    @Column(name = "empleador_id")
    private String empleadorId;

    @Column(name = "empleador_nombre", nullable = false, unique = true)
    private String empleadorNombre;

}
