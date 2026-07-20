# ADR-0005: Monorepo en lugar de repos separados

- **Fecha**: 2026-07-12
- **Estado**: Aceptada

## Contexto

El proyecto tiene un backend (Java + Spring Boot + Modulith) y un frontend (Next.js +
TypeScript), con una futura app móvil (React Native) sobre la misma API. Es desarrollado
por un solo desarrollador, sin equipos separados dueños de cada parte. Al generar un
cliente TypeScript tipado desde el contrato OpenAPI del backend, conviene poder actualizar
backend + cliente + frontend en un solo cambio atómico.

## Decisión

Un único repositorio (`planfinanzas`) contiene `backend/`, `frontend/`, `docs/` y `wiki/`,
en vez de repos separados por aplicación.

## Alternativas consideradas

- **Repos separados (uno para backend, otro para frontend)** — rechazada. Al igual que en
  [ADR-0001](0001-monolito-modular-en-lugar-de-microservicios.md), separar resuelve un
  problema organizacional (equipos distintos dueños de cada repo) que hoy no existe. Separar
  ahora solo agrega: dos PRs para sincronizar cada cambio de contrato de API, dos historiales
  que trackear, y la wiki/documentación partida o duplicada entre ambos repos.

## Consecuencias

**Positivas**
- Cambios de contrato de API (backend + cliente generado + frontend) en un solo commit
  atómico.
- Una sola wiki/documentación describiendo todo el sistema, sin fragmentar entre repos.
- Un solo lugar para issues/PRs mientras el proyecto es de un solo desarrollador.

**A vigilar**
- El CI necesita triggers por ruta (`paths:` en GitHub Actions) para no correr los tests de
  frontend cuando solo cambia el backend, y viceversa.
- Si el equipo crece con dueños distintos por aplicación, separar en ese momento es mecánico
  — los límites entre `backend/` y `frontend/` ya están claros como carpetas independientes.
