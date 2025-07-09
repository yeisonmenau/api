package com.pruebatecnica.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "viaje")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viaje_id")
    private Long viajeId;

    @Column(length = 188)
    private String destino;
    private LocalDate viajeFecha;

    public Viaje (String destino, LocalDate viajeFecha){
        this.destino = destino;
        this.viajeFecha = viajeFecha;
    }


}
