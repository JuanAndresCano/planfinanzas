# ADR-0006: Keycloak autogestionado en lugar de Auth0/Clerk o autenticación propia

- **Fecha**: 2026-07-13
- **Estado**: Aceptada

## Contexto

El módulo `users` necesita autenticación y un RBAC de 4 roles fijos (`admin`, `supervisor`,
`user`, `support`), con posibilidad de extenderse a futuro (hogares compartidos, asesor
financiero — ver [[pendientes]] en la wiki, resuelto como un mecanismo de relación/concesión
separado, no como más roles). Se evaluaron tres caminos: construir todo con Spring Security
desde cero, usar un proveedor de identidad SaaS (Auth0 o Clerk), o autogestionar Keycloak.

Un análisis de costo por usuario ya hecho para este proyecto mostró que un IdP SaaS tipo
Auth0 se dispara en costo a escala (del orden de US$7,000/mes a 100,000 usuarios activos),
muy por encima del resto de la infraestructura. Además, un SaaS de identidad introduce un
tercero procesando datos de credenciales fuera de Colombia, sumando otro Encargado del
Tratamiento bajo la Ley 1581 (además del ya aceptado para el hosting en
[ADR-0004](0004-vps-autogestionado-en-lugar-de-cloud-gestionado.md)) y la misma
consideración de transferencia internacional de datos.

Por otro lado, construir autenticación completa desde cero (registro, login, JWT,
recuperación de contraseña, MFA, rate-limiting de intentos) es trabajo real y no trivial,
que no diferencia este proyecto tanto como sí lo hace el dominio financiero (créditos con
amortización, reportes, cumplimiento). El desarrollador además busca aprender una
herramienta usada en la industria.

## Decisión

Se usa **Keycloak autogestionado** como servidor de identidad (OAuth2/OIDC):
- El backend (Spring Boot) actúa como *OAuth2 Resource Server*, validando los JWT que
  emite Keycloak vía `spring-boot-starter-oauth2-resource-server` — sin lógica de
  verificación de tokens escrita a mano.
- El frontend (Next.js) usa el flujo Authorization Code + PKCE a través del provider de
  Keycloak en Auth.js/NextAuth.
- Los 4 roles de plataforma se configuran como *realm roles* en Keycloak, no como un enum
  o tabla propia — Spring Security los lee directamente del JWT (`realm_access.roles`).
- Keycloak corre como otro contenedor en el mismo `docker-compose` del VPS (ADR-0004), con
  su propia base de datos/schema sobre la misma instancia de Postgres.

## Alternativas consideradas

- **Construir autenticación desde cero con Spring Security** — rechazada para el MVP. Alto
  costo de tiempo del desarrollador antes de llegar al dominio de negocio, y "no reinventar
  auth" es una práctica bien establecida en la industria. Reconsiderar solo si aparece un
  driver real que exija flujos de autenticación altamente custom.
- **Auth0 / Clerk (SaaS)** — rechazada. Costo se dispara a escala, y suma un tercero
  procesando credenciales fuera de Colombia (nuevo Encargado del Tratamiento, transferencia
  internacional de datos), sobre el ya aceptado para el hosting.

## Consecuencias

**Positivas**
- Costo por usuario en autenticación: US$0 siempre, sin importar la escala.
- Ningún tercero externo procesando credenciales — sin Encargado del Tratamiento adicional.
- Registro, login, recuperación de contraseña y MFA resueltos de fábrica, sin construirlos
  a mano.
- Habilidad de industria transferible (Keycloak, respaldado por Red Hat, es común en shops
  Java empresariales).

**A vigilar**
- Superficie operativa adicional: otro contenedor que correr, actualizar y respaldar.
- Curva de aprendizaje de configuración de realms/clients/roles en Keycloak.
- La entidad `User` propia del dominio deja de ser dueña de credenciales — se vuelve una
  posible tabla de extensión de perfil, referenciada por el `sub` (subject) que Keycloak le
  asigna al usuario, en vez de contener `email`/`password_hash` — ver
  `wiki/entidades/user.md`. Aún pendiente definir si hace falta esa tabla de extensión y
  qué campos, si alguno.
