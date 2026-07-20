---
tipo: decision
estado: activo
fuentes: [docs/adr/0001-monolito-modular-en-lugar-de-microservicios.md]
actualizado: 2026-07-13
---

# Decisión: Monolito modular en lugar de microservicios

## Resumen
Un único deployable expone toda la API — no se divide el sistema en servicios independientes. Sin driver organizacional ni de escala que lo justifique hoy.

## Detalle

**Contexto**: solo un desarrollador, posibilidad de crecer equipo más adelante, datos financieros sensibles que exigen buenas prácticas evolucionables.

**Alternativa rechazada**: microservicios desde el día uno — transacciones distribuidas, latencia de red, múltiples pipelines, observabilidad mucho más compleja, sin beneficio real a esta escala.

**Consecuencias positivas**: un solo CI/CD y artefacto, menor costo/carga operativa, consistencia transaccional simple (una sola base de datos, ACID).

**A vigilar**: sin disciplina interna puede degenerar en "big ball of mud" — mitigado por [[decisiones/0002-spring-modulith]]. Si aparece un driver real (crecimiento de equipo, necesidad de escalar un módulo específico), los límites ya definidos permiten extraer un servicio sin reescribir todo.

## Relaciones
- ADR original: `docs/adr/0001-monolito-modular-en-lugar-de-microservicios.md`
- Decisión relacionada: [[decisiones/0002-spring-modulith]]
- Overview: [[overview]]

## Preguntas abiertas / pendientes
- Ninguna — decisión aceptada, revisar solo si aparece un driver real de escala/organización.
