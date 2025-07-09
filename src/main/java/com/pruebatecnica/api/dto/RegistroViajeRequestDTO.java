package com.pruebatecnica.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistroViajeRequestDTO {
    @NotNull(message = "el ID del empleado es obligatorio")
    private Long empleadoId;
    @NotNull(message = "el ID del empleador es obligatorio")
    private String empleadorId;
    @NotNull(message = "el ID del viaje es obligatorio")
    private Long viajeId;
    @NotNull(message = "El ingrieso del total de los gastos por viaje es obligatorio")
    @Positive(message = "El total de gastos debe ser positivo")
    private Double totalGastos;
    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate registroViajeFecha;
}
