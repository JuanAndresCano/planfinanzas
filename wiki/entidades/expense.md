---
tipo: entidad
estado: activo
modulo_relacionado: [expenses]
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Entidad: Expense

## Resumen
Un registro individual de gasto.

## Detalle
Campos: categoría ([[entidades/expense-category]]), monto, fecha, nota, recurrente.

## Relaciones
- Módulo: [[modulos/expenses]]
- Referencia a: [[entidades/expense-category]]

## Preguntas abiertas / pendientes
- Alcance exacto del flag "recurrente" (¿solo informativo o genera el registro automáticamente?) — ver [[modulos/expenses]].
