---
tipo: decision
estado: activo
fuentes: [docs/adr/0007-modelo-de-ramas-main-desplegado-y-develop-como-colchon.md]
actualizado: 2026-07-13
---

# Decisión: Modelo de ramas — `main` desplegado y `develop` como colchón

## Resumen
Dos ramas permanentes: `main` (siempre lo desplegado en producción) y `develop` (colchón de integración). No es Git Flow completo — sin `release/*` ni `hotfix/*` todavía.

## Detalle

**Contexto**: `main` será la rama continuamente desplegada al VPS ([[decisiones/0004-vps-autogestionado]]). Con despliegue automático, cada merge a `main` termina en producción — hace falta un colchón de integración antes de eso.

**Alternativas rechazadas**:
- GitHub Flow (solo `main` + features) — sin colchón, cada feature llegaría directo a producción.
- Git Flow completo (`develop` + `release/*` + `hotfix/*` + `main`) — ceremonia de releases versionados que no aplica a un solo desarrollador sin versiones formales.

**Flujo**: `feature/*` nace de `develop`, PR de vuelta a `develop`. Cuando `develop` está estable, PR/merge a `main` dispara el despliegue.

**Consecuencias positivas**: `main` siempre refleja lo realmente vivo, `develop` da ventana para atrapar bugs de integración, sigue siendo liviano (2 ramas, no 4+ tipos).

**A vigilar**: manejo de hotfixes urgentes aún no formalizado (probablemente rama corta desde `main`, mergeada a `main` y `develop`); CI debe correr en ambas ramas, CD solo desde `main`.

## Relaciones
- ADR original: `docs/adr/0007-modelo-de-ramas-main-desplegado-y-develop-como-colchon.md`
- Decisión relacionada: [[decisiones/0004-vps-autogestionado]] (despliegue continuo al VPS)

## Preguntas abiertas / pendientes
- Formalizar manejo de hotfixes cuando ocurra el primer incidente real en producción.
