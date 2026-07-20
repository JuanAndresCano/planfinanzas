# ADR-0001: Monolito modular en lugar de microservicios

- **Fecha**: 2026-07-09
- **Estado**: Aceptada

## Contexto

El proyecto es desarrollado inicialmente por un solo desarrollador, con posibilidad de crecer el equipo más adelante. Maneja datos financieros personales sensibles (ingresos, gastos, créditos, inversiones), lo que exige buenas prácticas y capacidad de evolucionar sin fricción, pero no existe hoy ningún driver organizacional (varios equipos necesitando desplegar independientemente) ni de escala (necesidad de escalar un componente órdenes de magnitud más que otro) que justifique una arquitectura de microservicios.

## Decisión

Se construye como un **monolito modular**: un único deployable (un JAR / contenedor) que expone la API REST completa, en lugar de dividir el sistema en servicios independientes desde el inicio.

## Alternativas consideradas

- **Microservicios desde el día uno** — rechazada. Introduce transacciones distribuidas, latencia de red entre servicios, múltiples pipelines de despliegue y observabilidad mucho más compleja (tracing distribuido, service discovery). Ese costo operativo se pagaría desde ya sin ningún beneficio real, dado que no hay equipos separados ni necesidad de escalar un componente de forma independiente.

## Consecuencias

**Positivas**
- Un solo pipeline de CI/CD y un solo artefacto para desplegar, monitorear y depurar.
- Menor costo de infraestructura y menor carga operativa para un equipo de una persona.
- Consistencia transaccional simple (una sola base de datos, transacciones ACID nativas) para operaciones financieras que deben ser atómicas (ej. registrar un abono a capital y actualizar el saldo).

**A vigilar**
- Un monolito sin disciplina interna puede degenerar en un "big ball of mud" con el tiempo. Se mitiga con límites de módulo explícitos y verificados automáticamente (ver [ADR-0002](0002-spring-modulith-en-lugar-de-monolito-de-capas.md)).
- Si en el futuro aparece un driver real (crecimiento de equipo, necesidad de escalar un módulo específico de forma independiente, por ejemplo `banking` o `reporting`), los límites de módulo ya definidos permiten extraerlo a un servicio propio sin reescribir el resto del sistema.
