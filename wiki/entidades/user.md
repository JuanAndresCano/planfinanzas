---
tipo: entidad
estado: activo
modulo_relacionado: [users]
fuentes: [docs/especificacion-proyecto.md, docs/adr/0006-keycloak-en-lugar-de-auth0-o-auth-propio.md]
actualizado: 2026-07-13
---

# Entidad: User

## Resumen
El usuario de la plataforma. Desde [[decisiones/0006-keycloak|ADR-0006]] (Keycloak
autogestionado), **Keycloak es dueño de la identidad y las credenciales** — email,
password (hash), y la asignación de rol viven ahí, no en nuestra base de datos. Esta
entidad, si existe, es una tabla de extensión de perfil, no la fuente de verdad de auth.

## Detalle
Referenciada por el `sub` (subject claim) que Keycloak le asigna al usuario — ese es el
identificador que usan las demás tablas (`owner_id`), no un ID propio generado por
nuestra base de datos.

Campos que **ya no** viven aquí (ahora en Keycloak): email, password_hash, rol.

Campos que **podrían** vivir aquí, solo si el dominio los necesita más allá de lo que
Keycloak ya ofrece (pendiente de confirmar — ver [[pendientes]]):
- preferencias específicas de la app (ej. moneda de despliegue, formato de fecha)
- fecha en que el usuario aceptó cada versión de la política de tratamiento (aunque esto
  ya lo cubre [[entidades/consent-record]])

## Relaciones
- Módulo: [[modulos/users]]
- Rol: [[entidades/role]] (ahora *realm role* en Keycloak, no tabla propia)
- Autorización de tratamiento de datos: [[entidades/consent-record]]
- Decisión: [[decisiones/0006-keycloak]] (pendiente de crear en el próximo Ingest de ADRs)

## Preguntas abiertas / pendientes
- ¿Hace falta siquiera una tabla de extensión propia, o los datos de Keycloak alcanzan
  para el MVP? Ver [[pendientes]].
