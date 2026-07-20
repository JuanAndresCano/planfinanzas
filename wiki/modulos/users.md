---
tipo: modulo
estado: activo
fuentes: [docs/especificacion-proyecto.md, docs/adr/0006-keycloak-en-lugar-de-auth0-o-auth-propio.md]
actualizado: 2026-07-13
---

# Módulo: users

## Resumen
Autenticación (delegada a Keycloak, [[decisiones/0006-keycloak|ADR-0006]]) y autorización
(roles de plataforma) para toda la app. Se llama `users` y no `auth` porque su alcance va
más allá de verificar identidad — incluye roles y, potencialmente, perfil.

## Detalle

Entidades propias: [[entidades/user]] (delgada, ver nota abajo), [[entidades/role]] (ahora
realm role en Keycloak).

Con Keycloak como identity provider, este módulo en el backend queda mayormente como
**autorización**: interpretar el rol que trae el JWT y aplicar las reglas de acceso
(`@PreAuthorize` en Spring Security), no gestionar contraseñas ni sesiones — eso lo resuelve
Keycloak.

### Roadmap de roles (fuera del MVP, en orden)
1. Rol de asesor financiero de solo lectura, vía consentimiento explícito del usuario.
2. Relaciones de hogar compartido entre usuarios — resuelto arquitectónicamente como un
   mecanismo de relación/concesión entre usuarios, no como más roles ni permisos
   granulares (ver [[entidades/role]]).

## Requerimientos funcionales
- RF-US-01: registrar usuario con email y contraseña.
- RF-US-02: autenticarse con email y contraseña.
- RF-US-03: cerrar sesión, invalidando el token activo.
- RF-US-04: recuperar/restablecer contraseña vía email. *(pregunta abierta — ver [[pendientes]])*
- RF-US-05: editar datos básicos de perfil (nombre, email). *(pregunta abierta — ver [[pendientes]])*
- RF-US-06: aceptación explícita de la política de tratamiento de datos antes de usar la app.
- RF-US-07: `admin` puede asignar/cambiar el rol de un usuario.
- RF-AD-01: `admin`/`supervisor` gestionan catálogos sin ver montos financieros de los usuarios.
- RF-AD-02: `support` tiene acceso limitado y auditado, solo para resolución de incidencias.

## Relaciones
- Overview: [[overview]]
- Entidades: [[entidades/user]], [[entidades/role]]
- Módulo relacionado: [[modulos/compliance]] (consentimiento, auditoría de accesos de `support`/`supervisor`)
- Decisión: [[decisiones/0006-keycloak]]

## Preguntas abiertas / pendientes
- ¿Hace falta una tabla de extensión de perfil propia (`User`) además de Keycloak? Ver [[entidades/user]] y [[pendientes]].
- RF-US-04/05 (recuperación de contraseña, edición de perfil): ¿confirmados para el MVP? Ver [[pendientes]].
