package com.pruebatecnica.api.dto;

import com.pruebatecnica.api.model.enums.TitularGasto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistroViajeResponseDTO {
    private String empleadoNombre;
    private String empleadorNombre;
    private String viajeDestino;
    private Double totalGastos;
    private TitularGasto titularGasto;
    private LocalDate registroViajeFecha;
}
