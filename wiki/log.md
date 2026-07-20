# Log

> Registro cronológico, append-only. Formato: `## [YYYY-MM-DD] tipo | descripción corta`.
> Tipos: `ingest`, `query`, `lint`. Ver `wiki/CLAUDE.md` para el detalle de cada workflow.
> Tip: `grep "^## \[" wiki/log.md | tail -5` da las últimas 5 entradas.

## [2026-07-12] setup | creación del schema de la wiki

Se creó `wiki/CLAUDE.md` (schema, convenciones, workflows de Ingest/Query/Lint),
`wiki/index.md` y este `log.md`. Carpetas `modulos/`, `entidades/`, `decisiones/`
creadas vacías, listas para el primer Ingest.

Próximo paso: Ingest de `docs/especificacion-proyecto.md` como primera fuente.

## [2026-07-12] ingest | docs/especificacion-proyecto.md

Primera fuente migrada a la wiki. Páginas creadas (24):
- `overview.md`
- `modulos/`: users, income, expenses, credits, investments, banking, compliance, reporting (8)
- `entidades/`: income-source, income, expense-category, expense, credit, credit-payment,
  investment, investment-return, bank, financial-product, user-financial-product,
  consent-record, data-subject-request, audit-log (14)
- `glosario.md`

`decisiones/` queda vacía a propósito — el spec remite el razonamiento de arquitectura a
`docs/adr/`, así que esas páginas se crean en el próximo Ingest (las 4 ADRs).

Preguntas abiertas detectadas y anotadas en las páginas correspondientes: entidad `User`
no modelada explícitamente (ver `modulos/users.md`), alcance de "recurrente" en `Expense`,
notificación de vencimientos de crédito, exportación de reportes, y si el usuario ve su
propio `AuditLog` — todas ya vivían como preguntas en `docs/requisitos-funcionales.md`,
solo se enlazaron desde la página correspondiente en vez de duplicarse.

Próximo paso: Ingest de las 4 ADRs de `docs/adr/`.

## [2026-07-13] query | "¿users o auth? ¿entidades User/Role? ¿pendientes centralizados?"

Páginas creadas: `entidades/user.md`, `entidades/role.md`, `pendientes.md` (nueva sección
en la wiki para decisiones aplazadas y tareas de próxima sesión, en vez de Jira mientras
el proyecto sea de un solo desarrollador). Actualizados: `modulos/users.md` (ya no lista
`User` como hueco, enlaza a las entidades nuevas), `index.md`.

Decisión: módulo se llama `users`, no `auth` (cubre perfil y roles, no solo verificación
de identidad). Pendiente de confirmar: si `Permission` se agrega como entidad separada
(sistema de permisos granular) — ver [[pendientes]].

## [2026-07-13] ingest | Discusión de permisos granulares + Keycloak

Fuente: discusión en chat (no un documento existente). Decisiones tomadas:
1. No se necesita `Permission` granular — hogares compartidos se resuelven con un
   mecanismo de relación/concesión entre usuarios, no con más roles.
2. Autenticación delegada a Keycloak autogestionado (no Auth0/Clerk, no Spring Security
   desde cero) — ver ADR-0006.

Archivos nuevos: `docs/adr/0005-monorepo-en-lugar-de-repos-separados.md`,
`docs/adr/0006-keycloak-en-lugar-de-auth0-o-auth-propio.md`.

Páginas de wiki actualizadas: `entidades/user.md` (ahora tabla de extensión delgada,
identidad vive en Keycloak), `entidades/role.md` (roles ahora son realm roles de
Keycloak), `modulos/users.md`, `pendientes.md` (preguntas resueltas movidas a
"Resueltas recientemente", nuevas tareas de próxima sesión agregadas).

`decisiones/` sigue vacía — el Ingest formal de las 6 ADRs sigue pendiente para la
próxima sesión.

## [2026-07-13] ingest | docs/adr/0001 a 0006 (las 6 ADRs)

Páginas creadas en `decisiones/`: 0001-monolito-modular, 0002-spring-modulith,
0003-postgresql, 0004-vps-autogestionado, 0005-monorepo, 0006-keycloak. Cada una
enlazada a los módulos/entidades que le corresponden (ej. 0003 a las entidades
relacionales, 0004 y 0006 a `consent-record` por el tema de transferencia
internacional de datos, 0006 a `modulos/users` y sus entidades).

`index.md` actualizado con la sección de Decisiones completa (ya no "pendiente").

Wiki queda con 33 páginas: overview (1) + módulos (8) + entidades (16) + decisiones (6)
+ glosario (1) + pendientes (1).

## [2026-07-13] ingest | docs/requisitos-funcionales.md

Última fuente pendiente de `docs/`, ya ingerida. No se crearon páginas nuevas — los RF
mapean 1:1 a los módulos existentes, así que se integraron como sección "Requerimientos
funcionales" dentro de cada una de las 8 páginas de `modulos/`.

`pendientes.md` actualizado: se agregó la pregunta que faltaba (RF-US-04/05, recuperación
de contraseña y edición de perfil — no estaba trackeada hasta ahora) y se movieron las
tareas de Ingest de ADRs y de requisitos funcionales a "Completadas recientemente".

Con esto, todas las fuentes existentes en `docs/` (spec, 6 ADRs, requisitos funcionales)
quedaron migradas a la wiki. La próxima fuente será lo que se produzca de aquí en
adelante — código, nuevas decisiones, research externo.

## [2026-07-13] ingest | docs/adr/0007-modelo-de-ramas-main-desplegado-y-develop-como-colchon.md

Página creada: `decisiones/0007-modelo-de-ramas.md`. Enlazada a
`decisiones/0004-vps-autogestionado` (mismo tema de despliegue continuo). `index.md`
actualizado. Este es el primer commit del repo — README.md, `.gitignore`, `docs/` y
`wiki/` completos van juntos a `main`, y de ahí nace `develop` para el trabajo siguiente.
