---
tipo: modulo
estado: activo
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Módulo: expenses

## Resumen
Registro de gastos categorizados (fijos y variables), con categorías predefinidas y personalizadas por el usuario.

## Detalle
Entidades propias: [[entidades/expense-category]], [[entidades/expense]].

Categorías predefinidas mencionadas en el spec: arriendo, servicios del hogar, mercado, transporte, salidas, gastos del hogar. El usuario puede además crear categorías propias.

## Requerimientos funcionales
- RF-EX-01: registrar gasto indicando categoría, monto y fecha.
- RF-EX-02: editar o eliminar un gasto ya registrado.
- RF-EX-03: crear categorías de gasto propias, además de las predefinidas.
- RF-EX-04: cada categoría marcable como fija o variable.
- RF-EX-05: listar y filtrar gastos por categoría y por periodo.
- RF-EX-06: marcar un gasto como recurrente. *(alcance pendiente — ver [[pendientes]])*

## Relaciones
- Overview: [[overview]]
- Alimenta a: [[modulos/reporting]] (desglose de gasto por categoría, Estado de Resultados)
- Entidades: [[entidades/expense-category]], [[entidades/expense]]

## Preguntas abiertas / pendientes
- El flag "recurrente" en `Expense` (RF-EX-06) — dejado pendiente a propósito, ver [[pendientes]].
