---
tipo: entidad
estado: activo
modulo_relacionado: [users]
fuentes: [docs/especificacion-proyecto.md, docs/adr/0006-keycloak-en-lugar-de-auth0-o-auth-propio.md]
actualizado: 2026-07-13
---

# Entidad: Role

## Resumen
Los 4 roles fijos de la plataforma. Con [[decisiones/0006-keycloak|ADR-0006]] (Keycloak),
esto se configura como **realm roles dentro de Keycloak**, no como un enum o tabla en
nuestra propia base de datos. Spring Security los lee directamente del JWT
(`realm_access.roles`).

**Decidido**: no se necesita una entidad `Permission` granular — el caso que la motivaba
(hogares compartidos) se resuelve con un mecanismo de relación/concesión entre usuarios
específicos, separado de los roles de plataforma. Ver [[pendientes]].

## Detalle
| Rol | Alcance |
|---|---|
| `admin` | Gestiona toda la plataforma (usuarios, catálogos, configuración global). No ve montos financieros crudos por defecto. |
| `supervisor` | Segundo nivel bajo `admin` (ej. gestión por región o segmento). Tampoco ve montos financieros crudos por defecto. |
| `user` | Dueño único de sus propios datos. |
| `support` | Acceso limitado y auditado, solo para resolución de incidencias. |

Roadmap (fuera del MVP): rol de asesor financiero de solo lectura vía consentimiento.

## Relaciones
- Módulo: [[modulos/users]]
- Asignado a: [[entidades/user]]
- Decisión: [[decisiones/0006-keycloak]] (pendiente de crear en el próximo Ingest de ADRs)

## Preguntas abiertas / pendientes
- Ninguna — resuelto vía ADR-0006 (Keycloak) y la decisión de no usar `Permission` granular.
