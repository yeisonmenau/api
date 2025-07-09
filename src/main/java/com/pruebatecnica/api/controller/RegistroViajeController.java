package com.pruebatecnica.api.controller;

import com.pruebatecnica.api.dto.RegistroViajeRequestDTO;
import com.pruebatecnica.api.dto.RegistroViajeResponseDTO;
import com.pruebatecnica.api.service.RegistroViajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registroviajes")
public class RegistroViajeController {

    private final RegistroViajeService registroViajeService;

    public RegistroViajeController(RegistroViajeService registroViajeService) {
        this.registroViajeService = registroViajeService;
    }

    @GetMapping
    public ResponseEntity<List<RegistroViajeResponseDTO>> listarRegistrosDeViajes() {
        List<RegistroViajeResponseDTO> respuesta = registroViajeService.mostrarTotalGastoEmpleados();
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<String> guardarRegistroViaje(@RequestBody RegistroViajeRequestDTO datosRegistroViaje){
        try {
            registroViajeService.guardarRegistroViaje(datosRegistroViaje);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Registro de viaje creado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }
}
