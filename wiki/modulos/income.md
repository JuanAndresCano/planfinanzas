---
tipo: modulo
estado: activo
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Módulo: income

## Resumen
Registro de ingresos desde múltiples fuentes catalogadas. Completamente opcional para el usuario — la app debe funcionar sin que nadie declare ingresos.

## Detalle
Entidades propias: [[entidades/income-source]], [[entidades/income]].

El carácter opcional del registro de ingresos es un requisito explícito, no un detalle menor: ningún flujo de onboarding ni validación puede forzar al usuario a declarar cuánto gana.

## Requerimientos funcionales
- RF-IN-01: registrar ingreso indicando fuente, monto, fecha y frecuencia.
- RF-IN-02: registro de ingresos completamente opcional.
- RF-IN-03: editar o eliminar un ingreso ya registrado.
- RF-IN-04: listar y filtrar ingresos por periodo y por fuente.
- RF-IN-05: crear fuentes de ingreso propias, además de las precargadas.

## Relaciones
- Overview: [[overview]]
- Alimenta a: [[modulos/reporting]] (Estado de Resultados, Flujo de Efectivo)
- Entidades: [[entidades/income-source]], [[entidades/income]]

## Preguntas abiertas / pendientes
- Ninguna específica de este módulo por ahora.
