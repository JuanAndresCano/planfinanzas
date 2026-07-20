---
tipo: pendientes
estado: activo
fuentes: [docs/especificacion-proyecto.md, docs/requisitos-funcionales.md]
actualizado: 2026-07-13
---

# Pendientes

## Resumen
Vista centralizada de decisiones aplazadas, preguntas abiertas y tareas para la próxima sesión. No duplica el detalle — cada ítem enlaza a la página donde vive la discusión completa. Se actualiza en cada Ingest y al cierre de cada sesión de trabajo (ver Workflow: Lint en `wiki/CLAUDE.md`).

## Detalle

### Decisiones de producto/arquitectura pendientes
- Nombre definitivo del proyecto (candidato: Kalma) — pendiente dominio `.co`/`.app` y antecedentes marcarios SIC. Ver [[overview]].
- Estructura concreta de paquetes dentro de Spring Modulith.
- Esqueleto inicial del repo (backend + frontend + Postgres en Docker Compose + Keycloak).
- ¿Se agregan reportes adicionales a los cuatro ya definidos en [[modulos/reporting]]?
- Selección de proveedor de OCR cuando se aborde esa funcionalidad (Textract, Google Document AI, Tesseract u otro).
- Diseño detallado de UI/UX.
- ¿Combinar esta wiki con Jira para trackear tareas, o mantener `pendientes.md` como único lugar mientras el proyecto sea de un solo desarrollador? (discutido 2026-07-13 — recomendación: aplazar Jira, sin driver de equipo todavía).

**Resueltas recientemente:**
- ~~Sistema de permisos: ¿4 roles fijos o `Permission` granular?~~ → resuelto 2026-07-13: no hace falta `Permission`; hogares compartidos se resuelven con un mecanismo de relación/concesión aparte, no con más roles. Ver [[entidades/role]].
- ~~¿Auth propio, Auth0/Clerk, o Keycloak?~~ → resuelto 2026-07-13: Keycloak autogestionado. Ver [ADR-0006](../docs/adr/0006-keycloak-en-lugar-de-auth0-o-auth-propio.md).

### Preguntas abiertas por módulo/entidad
- [[modulos/users]]: RF-US-04/05 (recuperación de contraseña, edición de perfil) — ¿confirmados para el MVP? Asumido que sí por ser básicos de cualquier auth, pendiente de confirmación explícita.
- [[modulos/expenses]] / [[entidades/expense]]: alcance exacto del flag "recurrente" (RF-EX-06) — **dejado pendiente a propósito**, no resolver todavía.
- [[modulos/credits]]: ¿notificación de vencimientos de crédito entra al MVP? (RF-CR-06)
- [[modulos/reporting]]: ¿exportar reportes a PDF/Excel entra al MVP? (RF-RP-07)
- [[modulos/compliance]] / [[entidades/audit-log]]: ¿el usuario ve su propio historial de auditoría? (RF-CP-03)
- [[entidades/user]]: ¿hace falta una tabla de extensión de perfil propia además de Keycloak, y qué campos, si alguno?

### Tareas para la próxima sesión
- Resolver las 5 preguntas abiertas de requisitos funcionales (recuperación de contraseña, gasto recurrente, alertas de crédito, exportar reportes, audit log visible al usuario) — ver arriba, ahora una por una en su página de módulo.
- Configuración inicial de Keycloak: crear realm `kalma`, clients de backend/frontend, realm roles.
- Todas las fuentes existentes en `docs/` ya fueron ingeridas — la próxima fuente será lo que se produzca en la siguiente sesión (código, nuevas ADRs, research).

**Completadas recientemente:**
- ~~Ingest de las 6 ADRs de `docs/adr/` a `decisiones/`~~ → hecho 2026-07-13.
- ~~Ingest de `docs/requisitos-funcionales.md`~~ → hecho 2026-07-13 (integrado como sección "Requerimientos funcionales" en cada página de `modulos/`).

## Relaciones
- Overview: [[overview]]
- Todas las páginas con preguntas abiertas propias enlazan de vuelta aquí.

## Preguntas abiertas / pendientes
(este archivo *es* la lista — no aplica una sección recursiva de pendientes sobre sí mismo)
