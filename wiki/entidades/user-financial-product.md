---
tipo: entidad
estado: activo
modulo_relacionado: [banking]
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Entidad: UserFinancialProduct

## Resumen
La instancia de un [[entidades/financial-product]] que un usuario realmente posee.

## Detalle
Campos: producto, alias, número enmascarado (nunca completo — solo últimos 4 dígitos), saldo, cuota de manejo real, fecha de apertura.

## Relaciones
- Módulo: [[modulos/banking]]
- Referencia a: [[entidades/financial-product]]
- Alimenta a: [[modulos/reporting]] (Balance General — activos)

## Preguntas abiertas / pendientes
- Ninguna por ahora.
