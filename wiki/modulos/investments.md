---
tipo: modulo
estado: activo
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Módulo: investments

## Resumen
Gestión de inversiones (CDT, acciones, fondos, cripto, bienes raíces, etc.) con seguimiento de rendimiento esperado vs. real.

## Detalle
Entidades propias: [[entidades/investment]], [[entidades/investment-return]].

La separación entre tasa/rendimiento *esperado* (en `Investment`) y rendimiento *real* recibido (en `InvestmentReturn`) es deliberada — permite comparar promesa vs. resultado, útil para un perfil financiero/educativo.

## Requerimientos funcionales
- RF-INV-01: registrar inversión con tipo, monto invertido, tasa esperada y fechas.
- RF-INV-02: registrar rendimientos reales recibidos (interés, dividendo, valorización).
- RF-INV-03: comparar tasa/rendimiento esperado contra el real.
- RF-INV-04: editar o eliminar una inversión.

## Relaciones
- Overview: [[overview]]
- Alimenta a: [[modulos/reporting]] (Flujo de Efectivo — actividades de inversión; Balance General — activos)
- Entidades: [[entidades/investment]], [[entidades/investment-return]]

## Preguntas abiertas / pendientes
- Ninguna específica de este módulo por ahora.
