package com.pruebatecnica.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_id")
    private Long empleadoId;

    @Column(length = 20)
    private String cedula;

    private String nombre;

    @Min(12)
    private Integer edad;

    public Empleado(String cedula, String nombre, Integer edad){
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
    }

}
