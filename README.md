# Sistema de Registro de Viajes

Este proyecto es una API REST en Spring Boot que gestiona registros de viajes corporativos. Se registra qué empleado hizo un viaje, para qué empleador, cuál fue el destino, el total de gastos y quién asumió dichos gastos. Se implementa un MVP con entidades, DTOs, servicio, repositorio y controlador.

## Descripción del Proyecto

Este sistema permite registrar y consultar viajes realizados por empleados de diferentes del empleador SURA, con un control automático de gastos que determina quién debe asumir los costos según el monto total (empleado o empleador).

### Entidades

#### Empleado
- `empleadoId` (Long): Identificador único autogenerado
- `cedula` (String): Número de identificación (máximo 20 caracteres)
- `nombre` (String): Nombre del empleado
- `edad` (Integer): Edad del empleado (mínimo 12 años)

#### Empleador
- `empleadorId` (String): Identificador único
- `empleadorNombre` (String): Nombre de la empresa (único y obligatorio)

#### Viaje
- `viajeId` (Long): Identificador único autogenerado
- `destino` (String): Destino del viaje (máximo 188 caracteres)
- `viajeFecha` (LocalDate): Fecha del viaje

#### RegistroViaje
- `registroViajeId` (Long): Identificador único autogenerado
- `empleado` (Empleado): Empleado que realizó el viaje
- `empleador` (Empleador): Empleador responsable
- `viaje` (Viaje): Información del viaje
- `totalGastos` (Double): Monto total de gastos (con IVA incluido)
- `titularGasto` (TitularGasto): Enum quién asume el gasto
- `registroViajeFecha` (LocalDate): Fecha del registro

## Modelo de datos
Una vez ejecutado el proyecto se puede ver la base de datos H2 con las siguientes credenciales en la base de datos estarán los siguientes datos de prueba

## Acceso a H2

http://localhost:8080/h2-console/

![image](https://github.com/user-attachments/assets/b594b1ef-deff-4ef2-abc1-8bbe26590059)

### Modelo ER



## Lógica de Negocio

### Asignación de Titular de Gasto
El sistema determina automáticamente quién debe asumir los gastos del viaje:
- **Gastos > $1,000,000**: El empleador asume el costo
- **Gastos ≤ $1,000,000**: El empleado asume el costo

### Validaciones
- Edad mínima del empleado: 12 años
- Gastos mínimos: $0 (no negativos)
- Nombres de empleadores únicos
- Todos los gastos deben incluir IVA

## API Endpoints

### GET /registroviajes
Obtiene todos los registros de viajes ordenados por nombre del empleado.

**Respuesta:**
```json
[
  {
    "empleadoNombre": "Ana",
    "empleadorNombre": "SURA",
    "viajeDestino": "SUCURSAL INDUSTRIALES",
    "totalGastos": 750000,
    "titularGasto": "EMPLEADO",
    "registroViajeFecha": "2025-07-05"
  },
  {
    "empleadoNombre": "Ana",
    "empleadorNombre": "SURA",
    "viajeDestino": "SUCURSAL SAN FERNANDO",
    "totalGastos": 395000,
    "titularGasto": "EMPLEADO",
    "registroViajeFecha": "2025-03-15"
  },
  {
    "empleadoNombre": "Ana",
    "empleadorNombre": "SURA",
    "viajeDestino": "SUCURSAL SAN FERNANDO",
    "totalGastos": 410000,
    "titularGasto": "EMPLEADO",
    "registroViajeFecha": "2025-03-15"
  },
  {
    "empleadoNombre": "Juan",
    "empleadorNombre": "SURA",
    "viajeDestino": "SUCURSAL PROMOTORA PANAMERICANA",
    "totalGastos": 1200000.5,
    "titularGasto": "EMPLEADOR",
    "registroViajeFecha": "2025-07-03"
  },
  {
    "empleadoNombre": "Juan",
    "empleadorNombre": "SURA",
    "viajeDestino": "SUCURSAL CORPORATIVA CHILE",
    "totalGastos": 2000000.95,
    "titularGasto": "EMPLEADOR",
    "registroViajeFecha": "2025-06-29"
  },
  {
    "empleadoNombre": "Juan",
    "empleadorNombre": "SURA",
    "viajeDestino": "SUCURSAL CORPORATIVA CHILE",
    "totalGastos": 1935000,
    "titularGasto": "EMPLEADOR",
    "registroViajeFecha": "2025-06-28"
  },
  {
    "empleadoNombre": "Yeison",
    "empleadorNombre": "SURA",
    "viajeDestino": "SUCURSAL CORPORATIVA MEDELLIN",
    "totalGastos": 500000,
    "titularGasto": "EMPLEADO",
    "registroViajeFecha": "2025-05-01"
  },
  {
    "empleadoNombre": "Yeison",
    "empleadorNombre": "SURA",
    "viajeDestino": "SUCURSAL EL POBLADO",
    "totalGastos": 470000,
    "titularGasto": "EMPLEADO",
    "registroViajeFecha": "2025-07-05"
  },
  {
    "empleadoNombre": "Yeison",
    "empleadorNombre": "SURA",
    "viajeDestino": "SUCURSAL CORPORATIVA CDMX",
    "totalGastos": 2200000,
    "titularGasto": "EMPLEADOR",
    "registroViajeFecha": "2025-07-05"
  }
]
```

### POST /registroviajes
Crea un nuevo registro de viaje.

**Cuerpo de la petición:**
```json
{
  "empleadoId": 1,
  "empleadorId": "000",
  "viajeId": 1,
  "totalGastos": 1200000.0,
  "registroViajeFecha": "2024-01-20"
}
```

**Respuestas:**
- `201 Created`: Registro creado exitosamente
- `400 Bad Request`: Error en los datos (empleado/empleador/viaje no encontrado)
- `500 Internal Server Error`: Error interno del servidor

## Tecnologías Utilizadas

- **Spring Boot**: Framework principal
- **Spring Data JPA**: Persistencia de datos
- **Jakarta Persistence**: Especificación JPA
- **Jakarta Validation**: Validación de datos
- **Lombok**: Reducción de código boilerplate
- **Base de Datos**: Compatible con cualquier BD relacional

## Estructura del Proyecto

```
src/main/java/com/pruebatecnica/api/
├── controller/
│   └── RegistroViajeController.java
├── dto/
│   ├── RegistroViajeRequestDTO.java
│   └── RegistroViajeResponseDTO.java
├── model/
│   ├── Empleado.java
│   ├── Empleador.java
│   ├── RegistroViaje.java
│   ├── Viaje.java
│   └── enums/
│       └── TitularGasto.java
├── repository/
│   ├── EmpleadoRepository.java
│   ├── EmpleadorRepository.java
│   ├── RegistroViajeRepository.java
│   └── ViajeRepository.java
└── service/
    └── RegistroViajeService.java
```

## Instalación y Ejecución
1. Clona el repositorio
```
https://github.com/yeisonmenau/api
```
2. Ejecuta la aplicación desde la clase RegistroViajeService

3. Una vez iniciado el servidor, accede al siguiente endpoint en tu navegado

```
http://localhost:8080/registroviajes
```
- Al iniciar la aplicación, se cargarán automáticamente datos de prueba en la base de datos en memoria (H2).

- El endpoint /registroviajes muestra la lista total de gastos de viaje registrados.

## Consideraciones Importantes

- Los gastos deben ingresarse con IVA incluido para evitar manejar diferentes tipos de IVA según el destino
