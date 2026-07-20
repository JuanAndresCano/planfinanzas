# Índice

> Catálogo de toda la wiki, por categoría. Se actualiza en cada Ingest — ver `wiki/CLAUDE.md` para el workflow.

## Overview

- [[overview]] — Qué es Kalma, misión, visión, alcance del MVP y roadmap futuro.

## Módulos

- [[modulos/users]] — Autenticación, roles (`admin`, `supervisor`, `user`, `support`) y permisos.
- [[modulos/income]] — Ingresos desde múltiples fuentes, registro opcional.
- [[modulos/expenses]] — Gastos categorizados, fijos/variables, categorías propias.
- [[modulos/credits]] — Créditos con amortización y abonos a capital separados de la cuota.
- [[modulos/investments]] — Inversiones con rendimiento esperado vs. real.
- [[modulos/banking]] — Catálogo de bancos y productos financieros.
- [[modulos/compliance]] — Cumplimiento Ley 1581, derechos ARCO, auditoría.
- [[modulos/reporting]] — Estados financieros: P&G, Flujo de Efectivo, Balance General, desglose de gasto, capacidad de ahorro.

## Pendientes

- [[pendientes]] — Vista centralizada de decisiones aplazadas, preguntas abiertas y tareas de la próxima sesión.

## Entidades

- [[entidades/user]] — El usuario de la plataforma.
- [[entidades/role]] — Los 4 roles fijos y su alcance.
- [[entidades/income-source]] — Catálogo de fuentes de ingreso.
- [[entidades/income]] — Registro individual de ingreso.
- [[entidades/expense-category]] — Catálogo de categorías de gasto.
- [[entidades/expense]] — Registro individual de gasto.
- [[entidades/credit]] — Un crédito del usuario.
- [[entidades/credit-payment]] — Un pago/abono sobre un crédito.
- [[entidades/investment]] — Una inversión del usuario.
- [[entidades/investment-return]] — Un rendimiento real recibido.
- [[entidades/bank]] — Catálogo global de bancos.
- [[entidades/financial-product]] — Catálogo global de productos financieros.
- [[entidades/user-financial-product]] — Producto financiero que el usuario realmente posee.
- [[entidades/consent-record]] — Evidencia de autorización de tratamiento de datos.
- [[entidades/data-subject-request]] — Seguimiento de solicitudes ARCO.
- [[entidades/audit-log]] — Registro de accesos/modificaciones de terceros.

## Decisiones

- [[decisiones/0001-monolito-modular]] — Monolito modular en lugar de microservicios.
- [[decisiones/0002-spring-modulith]] — Spring Modulith en lugar de monolito de capas plano.
- [[decisiones/0003-postgresql]] — PostgreSQL en lugar de NoSQL.
- [[decisiones/0004-vps-autogestionado]] — VPS autogestionado en lugar de cloud gestionado.
- [[decisiones/0005-monorepo]] — Monorepo en lugar de repos separados.
- [[decisiones/0006-keycloak]] — Keycloak autogestionado en lugar de Auth0/Clerk o auth propio.
- [[decisiones/0007-modelo-de-ramas]] — Modelo de ramas: `main` desplegado y `develop` como colchón.

## Glosario

- [[glosario]] — Habeas Data, ARCO, RNBD, SFC, UVT, SMLMV, amortización, cuota de manejo, RLS, fitness function.
