package com.pruebatecnica.api.dto;

import com.pruebatecnica.api.model.enums.TitularGasto;
import lombok.Data;


import java.time.LocalDate;

@Data
public class RegistroViajeRequestDTO {
    private Long empleadoId;
    private String empleadorId;
    private Long viajeId;
    private Double totalGastos;
    private LocalDate registroViajeFecha;
}
