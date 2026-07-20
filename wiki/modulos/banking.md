---
tipo: modulo
estado: activo
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Módulo: banking

## Resumen
Catálogo de bancos y productos financieros (cuentas de ahorro/corriente, CDT), incluyendo cuota de manejo, para que el usuario elija de una lista en vez de digitar todo manualmente.

## Detalle
Entidades propias: [[entidades/bank]], [[entidades/financial-product]], [[entidades/user-financial-product]].

El catálogo (`Bank`, `FinancialProduct`) es global y lo administra `admin`/`supervisor`; cada usuario solo posee instancias (`UserFinancialProduct`) que referencian ese catálogo.

## Requerimientos funcionales
- RF-BK-01: elegir banco y producto financiero de un catálogo precargado.
- RF-BK-02: registrar cuota de manejo real y saldo por producto poseído.
- RF-BK-03: `admin`/`supervisor` gestionan el catálogo global (crear, editar).
- RF-BK-04: editar o eliminar un producto financiero propio.

## Relaciones
- Overview: [[overview]]
- Alimenta a: [[modulos/reporting]] (Balance General — activos vía saldos de productos)
- Entidades: [[entidades/bank]], [[entidades/financial-product]], [[entidades/user-financial-product]]
- Módulo relacionado: [[modulos/users]] (permisos de `admin`/`supervisor` sobre el catálogo)

## Preguntas abiertas / pendientes
- Ninguna específica de este módulo por ahora.
