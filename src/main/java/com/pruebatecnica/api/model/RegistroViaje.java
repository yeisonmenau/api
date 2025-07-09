package com.pruebatecnica.api.model;

import com.pruebatecnica.api.model.enums.TitularGasto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "registro_viaje")
@Data
@NoArgsConstructor
public class RegistroViaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_viaje_id")
    private Long registroViajeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empleador_id", nullable = false)
    private Empleador empleador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "viaje_id", nullable = false)
    private Viaje viaje;

    @Column(name = "total_gastos", nullable = false)
    @Min(0)
    private Double totalGastos; // El gasto debe ingresarse con IVA incluido para evitar manejar distintos
    // tipos de IVA según el destino.

    @Enumerated(EnumType.STRING)
    @Column(name = "titular_gasto")
    private TitularGasto titularGasto;

    @Column(name = "registro_viaje_fecha", nullable = false)
    private LocalDate registroViajeFecha;

    public RegistroViaje(Empleado empleado, Empleador empleador, Viaje viaje,
                         Double totalGastos, LocalDate registroViajeFecha) {
        this.empleado = empleado;
        this.empleador = empleador;
        this.viaje = viaje;
        this.totalGastos = totalGastos;
        this.titularGasto = totalGastos > 1000000 ? TitularGasto.EMPLEADOR : TitularGasto.EMPLEADO;
        this.registroViajeFecha = registroViajeFecha;
    }
}
