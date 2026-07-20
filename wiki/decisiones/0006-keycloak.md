---
tipo: decision
estado: activo
fuentes: [docs/adr/0006-keycloak-en-lugar-de-auth0-o-auth-propio.md]
actualizado: 2026-07-13
---

# Decisión: Keycloak autogestionado en lugar de Auth0/Clerk o auth propio

## Resumen
Keycloak autogestionado como servidor de identidad (OAuth2/OIDC) — el backend valida sus JWT como *Resource Server*, el frontend usa el flujo Authorization Code + PKCE, y los 4 roles de plataforma son *realm roles* de Keycloak.

## Detalle

**Contexto**: [[modulos/users]] necesita autenticación y RBAC de 4 roles fijos, con extensibilidad futura resuelta como mecanismo de relación/concesión (no más roles — ver [[entidades/role]]). Un IdP SaaS tipo Auth0 se dispara en costo a escala (~US$7,000/mes a 100k usuarios) y suma un tercero procesando credenciales fuera de Colombia (nuevo Encargado del Tratamiento, sumado al ya aceptado en [[decisiones/0004-vps-autogestionado]]). Construir auth desde cero es trabajo real que no diferencia el proyecto tanto como el dominio financiero.

**Alternativas rechazadas**:
- Construir con Spring Security desde cero — alto costo de tiempo antes de llegar al negocio; "no reinventar auth" es práctica establecida.
- Auth0/Clerk (SaaS) — costo se dispara a escala, y suma un tercero externo procesando credenciales.

**Consecuencias positivas**: costo US$0 siempre, sin tercero externo, registro/login/recuperación de contraseña/MFA resueltos de fábrica, habilidad de industria transferible (Keycloak, respaldado por Red Hat).

**A vigilar**: superficie operativa adicional (otro contenedor), curva de aprendizaje de realms/clients, y [[entidades/user]] deja de ser dueña de credenciales — se vuelve una posible tabla de extensión de perfil.

## Relaciones
- ADR original: `docs/adr/0006-keycloak-en-lugar-de-auth0-o-auth-propio.md`
- Módulo: [[modulos/users]]
- Entidades: [[entidades/user]], [[entidades/role]]
- Decisión relacionada: [[decisiones/0004-vps-autogestionado]] (mismo tema de transferencia internacional de datos y Encargados del Tratamiento)

## Preguntas abiertas / pendientes
- ¿Hace falta una tabla de extensión de perfil propia para `User` además de Keycloak? Ver [[pendientes]].
- Configuración inicial pendiente: realm `kalma`, clients de backend/frontend, realm roles — ver [[pendientes]].
