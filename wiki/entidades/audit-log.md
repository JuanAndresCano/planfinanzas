---
tipo: entidad
estado: activo
modulo_relacionado: [compliance]
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Entidad: AuditLog

## Resumen
Registro de quién accedió o modificó qué y cuándo. Obligatorio en accesos de `support`/`supervisor` a datos de terceros.

## Detalle
Sin campos detallados todavía en el spec original — pendiente de definir esquema exacto (actor, acción, entidad afectada, timestamp, IP).

## Relaciones
- Módulo: [[modulos/compliance]]
- Módulo relacionado: [[modulos/users]] (roles con acceso a datos de terceros)

## Preguntas abiertas / pendientes
- ¿El usuario ve su propio historial de auditoría? Ver [[modulos/compliance]] (RF-CP-03, no confirmado).
- Esquema exacto de campos aún no definido.
