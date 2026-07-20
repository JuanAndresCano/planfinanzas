---
tipo: modulo
estado: activo
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Módulo: reporting

## Resumen
Genera los estados financieros y análisis educativos: Estado de Resultados (P&G), Flujo de Efectivo, Balance General, desglose de gasto por categoría, y estimación de capacidad de ahorro/inversión.

## Detalle
No tiene entidades propias — combina datos de otros módulos vía sus APIs públicas de consulta, sin acceder directamente a sus tablas (ver decisión "Reportes desacoplados", pendiente de página en `decisiones/`).

| Reporte | De dónde sale |
|---|---|
| Estado de Resultados (P&G) | [[modulos/income]] − [[modulos/expenses]], por periodo |
| Flujo de Efectivo | Operativo ([[modulos/income]]/[[modulos/expenses]]), inversión ([[modulos/investments]]), financiación ([[modulos/credits]]) |
| Balance General | Activos ([[modulos/banking]] + [[modulos/investments]]) vs. pasivos ([[modulos/credits]]) = patrimonio neto |
| Desglose de gasto por categoría | [[modulos/expenses]], agregado por categoría |
| Capacidad de ahorro/inversión | Ingresos − gastos fijos − promedio de gastos variables |

## Requerimientos funcionales
- RF-RP-01: ver Estado de Resultados (P&G) por periodo.
- RF-RP-02: ver Flujo de Efectivo por periodo (operativo, inversión, financiación).
- RF-RP-03: ver Balance General con patrimonio neto.
- RF-RP-04: ver desglose de gastos por categoría.
- RF-RP-05: ver estimación de cuánto podría ahorrar o invertir el usuario.
- RF-RP-06: filtrar cualquier reporte por rango de fechas.
- RF-RP-07: exportar un reporte (PDF/Excel). *(candidata no confirmada — ver [[pendientes]])*

## Relaciones
- Overview: [[overview]]
- Lee de: [[modulos/income]], [[modulos/expenses]], [[modulos/credits]], [[modulos/investments]], [[modulos/banking]]

## Preguntas abiertas / pendientes
- ¿Exportar reportes a PDF/Excel entra al MVP? (RF-RP-07). Ver [[pendientes]].
- ¿Se agrega algún reporte adicional a los cuatro definidos?
