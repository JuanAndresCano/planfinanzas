---
tipo: decision
estado: activo
fuentes: [docs/adr/0005-monorepo-en-lugar-de-repos-separados.md]
actualizado: 2026-07-13
---

# Decisión: Monorepo en lugar de repos separados

## Resumen
Un único repositorio (`planfinanzas`) contiene `backend/`, `frontend/`, `docs/` y `wiki/` — no repos separados por aplicación.

## Detalle

**Contexto**: backend (Spring Boot/Modulith) + frontend (Next.js) + futura app móvil, un solo desarrollador, sin equipos separados dueños de cada parte. Al generar un cliente TypeScript desde el OpenAPI del backend, conviene actualizar backend + cliente + frontend en un commit atómico.

**Alternativa rechazada**: repos separados — mismo razonamiento que [[decisiones/0001-monolito-modular]]: resuelve un problema organizacional que hoy no existe. Agregaría dos PRs para sincronizar cada cambio de contrato de API, y la wiki/documentación quedaría partida o duplicada.

**Consecuencias positivas**: cambios de contrato de API en un commit atómico, una sola wiki describiendo todo el sistema, un solo lugar para issues/PRs.

**A vigilar**: el CI necesita triggers por ruta (`paths:` en GitHub Actions) para no correr tests de frontend cuando solo cambia backend, y viceversa. Si el equipo crece con dueños distintos por aplicación, separar después es mecánico.

## Relaciones
- ADR original: `docs/adr/0005-monorepo-en-lugar-de-repos-separados.md`
- Decisión relacionada: [[decisiones/0001-monolito-modular]] (mismo razonamiento de "sin driver organizacional")

## Preguntas abiertas / pendientes
- Ninguna — decisión aceptada.
