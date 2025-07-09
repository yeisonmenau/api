package com.pruebatecnica.api.config;

import com.pruebatecnica.api.model.Empleado;
import com.pruebatecnica.api.model.Empleador;
import com.pruebatecnica.api.model.RegistroViaje;
import com.pruebatecnica.api.model.Viaje;
import com.pruebatecnica.api.repository.EmpleadoRepository;
import com.pruebatecnica.api.repository.EmpleadorRepository;
import com.pruebatecnica.api.repository.RegistroViajeRepository;
import com.pruebatecnica.api.repository.ViajeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DatosIniciales {
    @Bean(name = "informacionInicial")
    CommandLineRunner datosIniciales(EmpleadorRepository empleadorRepository, EmpleadoRepository empleadoRepository,
                                     ViajeRepository viajeRepository, RegistroViajeRepository registroViajeRepository){
        return x -> {
            Empleador empleador = new Empleador("000","SURA");

            Empleado empleado1 = new Empleado("12345", "Yeison", 22);
            Empleado empleado2 = new Empleado("12346", "Ana", 30);
            Empleado empleado3 = new Empleado("12347", "Juan", 28);

            Viaje viaje1 = new Viaje("SUCURSAL CORPORATIVA MEDELLIN", LocalDate.parse("2025-05-01"));
            Viaje viaje2 = new Viaje("SUCURSAL INDUSTRIALES", LocalDate.parse("2025-07-05"));
            Viaje viaje3 = new Viaje("SUCURSAL PROMOTORA PANAMERICANA", LocalDate.parse("2025-07-01"));

            Viaje viaje4 = new Viaje("SUCURSAL EL POBLADO", LocalDate.parse("2025-07-05"));
            Viaje viaje5 = new Viaje("SUCURSAL SAN FERNANDO", LocalDate.parse("2025-03-15"));
            Viaje viaje6 = new Viaje("SUCURSAL CORPORATIVA CHILE", LocalDate.parse("2025-06-28"));

            Viaje viaje7 = new Viaje("SUCURSAL CORPORATIVA CDMX", LocalDate.parse("2025-07-05"));
            Viaje viaje8 = new Viaje("SUCURSAL SAN FERNANDO", LocalDate.parse("2025-03-15"));
            Viaje viaje9 = new Viaje("SUCURSAL CORPORATIVA CHILE", LocalDate.parse("2025-06-28"));

            RegistroViaje registroViaje1 = new RegistroViaje(empleado1, empleador, viaje1, 500000.0, LocalDate.parse("2025-05-01"));
            RegistroViaje registroViaje2 = new RegistroViaje(empleado2, empleador, viaje2, 750000.0, LocalDate.parse("2025-07-05"));
            RegistroViaje registroViaje3 = new RegistroViaje(empleado3, empleador, viaje3, 1200000.5, LocalDate.parse("2025-07-03"));
            RegistroViaje registroViaje4 = new RegistroViaje(empleado1, empleador, viaje4, 470000.0, LocalDate.parse("2025-07-05"));
            RegistroViaje registroViaje5 = new RegistroViaje(empleado2, empleador, viaje5, 395000.0, LocalDate.parse("2025-03-15"));
            RegistroViaje registroViaje6 = new RegistroViaje(empleado3, empleador, viaje6, 2000000.95, LocalDate.parse("2025-06-29"));
            RegistroViaje registroViaje7 = new RegistroViaje(empleado1, empleador, viaje7, 2200000.0, LocalDate.parse("2025-07-05"));
            RegistroViaje registroViaje8 = new RegistroViaje(empleado2, empleador, viaje8, 410000.0, LocalDate.parse("2025-03-15"));
            RegistroViaje registroViaje9 = new RegistroViaje(empleado3, empleador, viaje9, 1935000.0, LocalDate.parse("2025-06-28"));

            empleadorRepository.save(empleador);
            empleadoRepository.saveAll(List.of(empleado1,empleado2,empleado3));
            viajeRepository.saveAll(List.of(viaje1,viaje2,viaje3,viaje4,viaje5,viaje6,viaje7,viaje8,viaje9));
            registroViajeRepository.saveAll(List.of(registroViaje1,registroViaje2,registroViaje3,registroViaje4,
                    registroViaje5,registroViaje6,registroViaje7,registroViaje8,registroViaje9));

        };
    }
}
