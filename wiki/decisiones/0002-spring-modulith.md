---
tipo: decision
estado: activo
fuentes: [docs/adr/0002-spring-modulith-en-lugar-de-monolito-de-capas.md]
actualizado: 2026-07-13
---

# Decisión: Spring Modulith en lugar de monolito de capas plano

## Resumen
El código se estructura por módulos de negocio (`users`, `income`, `expenses`, `credits`, `investments`, `banking`, `compliance`, `reporting`), no por capas técnicas globales.

## Detalle

**Contexto**: dado el monolito modular ([[decisiones/0001-monolito-modular]]), un monolito de capas técnicas tiende a difuminar límites de dominio con el tiempo, sin nada que lo impida o detecte.

**Alternativas rechazadas**:
- Monolito de capas plano — sin verificación automática de límites.
- DDD/hexagonal armado a mano — beneficios similares pero con más ceremonia manual, sin la verificación que Modulith da de fábrica.

**Consecuencias positivas**: límites verificados con `ApplicationModules.verify()` (una *fitness function* arquitectónica), Event Publication Registry para comunicación asíncrona transaccional sin infraestructura adicional, extracción a servicio independiente es mecánica si algún día hace falta.

**A vigilar**: disciplina de comunicarse solo vía APIs públicas de cada módulo — mitigado por el test de verificación.

## Relaciones
- ADR original: `docs/adr/0002-spring-modulith-en-lugar-de-monolito-de-capas.md`
- Decisión relacionada: [[decisiones/0001-monolito-modular]]
- Todos los módulos: [[modulos/users]], [[modulos/income]], [[modulos/expenses]], [[modulos/credits]], [[modulos/investments]], [[modulos/banking]], [[modulos/compliance]], [[modulos/reporting]]

## Preguntas abiertas / pendientes
- Ninguna — decisión aceptada.
