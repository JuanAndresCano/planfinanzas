---
tipo: entidad
estado: activo
modulo_relacionado: [credits]
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Entidad: CreditPayment

## Resumen
Un pago o abono registrado sobre un [[entidades/credit]]. Permite reconstruir la tabla de amortización completa.

## Detalle
Campos: fecha, valor pagado, parte a capital vs. interés, saldo resultante.

La separación explícita entre cuota normal y abono a capital es el requisito central de este módulo — sin ella no se puede mostrar el impacto real de un abono extra.

## Relaciones
- Módulo: [[modulos/credits]]
- Pertenece a: [[entidades/credit]]

## Preguntas abiertas / pendientes
- Ninguna por ahora.
