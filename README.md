# Kalma (nombre provisional) — App de Finanzas Personales

Aplicación de finanzas personales para el mercado colombiano: control de ingresos, gastos, créditos e inversiones, con foco educativo (estados financieros personales) y cumplimiento normativo desde el diseño.

> El nombre "Kalma" es un candidato de trabajo, pendiente de confirmar dominio y antecedentes marcarios — ver [pendientes de la wiki](wiki/pendientes.md).

## Qué hace

- Registro de ingresos (opcional) desde múltiples fuentes catalogadas.
- Registro de gastos categorizados (fijos y variables).
- Gestión de créditos con amortización real y abonos a capital separados de la cuota.
- Gestión de inversiones con seguimiento de rendimiento esperado vs. real.
- Catálogo de bancos y productos financieros colombianos.
- Estados financieros personales: Estado de Resultados, Flujo de Efectivo, Balance General, desglose de gasto por categoría y estimación de capacidad de ahorro/inversión.
- Cumplimiento de la Ley 1581 de 2012 (habeas data) desde el diseño, no como añadido posterior.

## Estado actual

En fase de arquitectura y planeación — todavía no hay código de aplicación (`backend/`, `frontend/`), pero sí una base de decisiones y requerimientos ya cerrada (ver Documentación abajo). Repo recién inicializado.

## Stack tecnológico

| Componente | Tecnología |
|---|---|
| Backend | Java + Spring Boot + Spring Modulith (monolito modular) |
| Base de datos | PostgreSQL |
| Autenticación | Keycloak (OAuth2/OIDC) |
| Frontend web | Next.js + TypeScript + React |
| Frontend móvil (futuro) | React Native |
| Monitoreo | Spring Boot Actuator + Micrometer + Prometheus + Grafana |
| Hosting | VPS autogestionado (Hetzner) |
| Reverse proxy | nginx |

## Arquitectura

- **Monolito modular**, no microservicios — sin driver organizacional ni de escala que los justifique hoy.
- 8 módulos de negocio (Spring Modulith): `users`, `income`, `expenses`, `credits`, `investments`, `banking`, `compliance`, `reporting` — con límites verificados automáticamente (`ApplicationModules.verify()`).
- Cada decisión de arquitectura está documentada como ADR, con contexto, alternativas consideradas y consecuencias — ver [`docs/adr/`](docs/adr/README.md).

## Documentación

- [Especificación del proyecto](docs/especificacion-proyecto.md) — misión, visión, alcance del MVP, roles, modelo de datos, marco legal.
- [ADRs](docs/adr/README.md) — decisiones de arquitectura con su razonamiento completo.
- [Requerimientos funcionales](docs/requisitos-funcionales.md) — borrador de RF por módulo.
- [`wiki/`](wiki/overview.md) — base de conocimiento interconectada (Obsidian), con una página por módulo, entidad y decisión, más un glosario y una vista centralizada de pendientes.

## Roadmap (fuera del MVP)

1. Hogares/presupuestos compartidos entre varios usuarios.
2. Rol de asesor financiero de solo lectura, vía consentimiento explícito.
3. Captura de facturas por cámara + extracción automática de datos (OCR).
4. Insights avanzados sobre patrones de gasto.
5. Integración con Open Finance / APIs bancarias reales.
