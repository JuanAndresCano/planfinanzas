---
tipo: modulo
estado: activo
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Módulo: credits

## Resumen
Gestión de créditos (hipotecario, vehículo, estudiantil, tarjeta, libre inversión, etc.) con seguimiento de amortización y abonos a capital separados de la cuota normal.

## Detalle
Entidades propias: [[entidades/credit]], [[entidades/credit-payment]].

La distinción entre cuota normal y abono a capital es un requisito central del proyecto — permite reconstruir la tabla de amortización completa y ver el impacto real de un abono extra sobre el saldo, algo que la mayoría de apps de finanzas personales del mercado no ofrecen (ver competencia investigada en el chat — pendiente de Ingest si se decide preservar esa investigación).

## Requerimientos funcionales
- RF-CR-01: registrar crédito con tipo, monto original, tasa, tipo de tasa, plazo y cuota.
- RF-CR-02: registrar pagos/abonos, distinguiendo cuota normal de abono a capital.
- RF-CR-03: calcular y mostrar el saldo vigente tras cada pago.
- RF-CR-04: ver la tabla de amortización completa.
- RF-CR-05: editar o eliminar un crédito.
- RF-CR-06: notificar próximos vencimientos de cuota. *(candidata no confirmada — ver [[pendientes]])*

## Relaciones
- Overview: [[overview]]
- Alimenta a: [[modulos/reporting]] (Flujo de Efectivo — actividades de financiación; Balance General — pasivos)
- Entidades: [[entidades/credit]], [[entidades/credit-payment]]

## Preguntas abiertas / pendientes
- Notificación de próximos vencimientos de cuota (RF-CR-06): ¿entra al MVP? Implica un canal de notificación aún no diseñado. Ver [[pendientes]].
