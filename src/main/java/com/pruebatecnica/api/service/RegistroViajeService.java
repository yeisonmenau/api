package com.pruebatecnica.api.service;

import com.pruebatecnica.api.dto.RegistroViajeRequestDTO;
import com.pruebatecnica.api.dto.RegistroViajeResponseDTO;
import com.pruebatecnica.api.model.Empleado;
import com.pruebatecnica.api.model.Empleador;
import com.pruebatecnica.api.model.RegistroViaje;
import com.pruebatecnica.api.model.Viaje;
import com.pruebatecnica.api.repository.EmpleadoRepository;
import com.pruebatecnica.api.repository.EmpleadorRepository;
import com.pruebatecnica.api.repository.RegistroViajeRepository;
import com.pruebatecnica.api.repository.ViajeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RegistroViajeService {
    private final RegistroViajeRepository registroViajeRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EmpleadorRepository empleadorRepository;
    private final ViajeRepository viajeRepository;

    public RegistroViajeService(RegistroViajeRepository registroViajeRepository, EmpleadoRepository empleadoRepository,
                                EmpleadorRepository empleadorRepository, ViajeRepository viajeRepository) {
        this.registroViajeRepository = registroViajeRepository;
        this.empleadoRepository = empleadoRepository;
        this.empleadorRepository = empleadorRepository;
        this.viajeRepository = viajeRepository;
    }
    public RegistroViajeResponseDTO convertirAResponseDTO(RegistroViaje registro) {
        RegistroViajeResponseDTO dto = new RegistroViajeResponseDTO();
        dto.setEmpleadoNombre(registro.getEmpleado().getNombre());
        dto.setEmpleadorNombre(registro.getEmpleador().getEmpleadorNombre());
        dto.setViajeDestino(registro.getViaje().getDestino());
        dto.setTotalGastos(registro.getTotalGastos());
        dto.setTitularGasto(registro.getTitularGasto());
        dto.setRegistroViajeFecha(registro.getRegistroViajeFecha());
        return dto;
    }

    public List<RegistroViajeResponseDTO> mostrarTotalGastoEmpleados() {
        return registroViajeRepository.findAll().stream()
                .sorted(Comparator.comparing(registro -> registro.getEmpleado().getNombre()))
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public void guardarRegistroViaje(@Valid @RequestBody RegistroViajeRequestDTO datosRegistroViaje){
        Empleado empleado = empleadoRepository.findById(datosRegistroViaje.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Empleador empleador = empleadorRepository.findById(datosRegistroViaje.getEmpleadorId())
                .orElseThrow(() -> new RuntimeException("Empleador no encontrado"));

        Viaje viaje = viajeRepository.findById(datosRegistroViaje.getViajeId())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        RegistroViaje registroViaje = new RegistroViaje(
                empleado,
                empleador,
                viaje,
                datosRegistroViaje.getTotalGastos(),
                datosRegistroViaje.getRegistroViajeFecha()
        );

        registroViajeRepository.save(registroViaje);
    }


}
