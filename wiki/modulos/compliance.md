---
tipo: modulo
estado: activo
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Módulo: compliance

## Resumen
Cumplimiento de la Ley 1581 de 2012 (habeas data): consentimiento explícito, política de tratamiento de datos, atención de derechos ARCO, y auditoría de accesos.

## Detalle
Entidades propias: [[entidades/consent-record]], [[entidades/data-subject-request]], [[entidades/audit-log]].

Ver [[glosario]] para los términos legales (Habeas Data, ARCO, RNBD, SFC).

Puntos clave del marco legal (detalle completo pendiente de traer al Ingest de las ADRs / un posible Ingest dedicado del research legal):
- Aplica desde el primer usuario, sin importar tamaño de empresa.
- RNBD (registro ante la SIC) no aplica al MVP — umbral de 100.000 UVT en activos.
- Superintendencia Financiera no aplica al MVP — la app no capta ni maneja recursos del público.

## Requerimientos funcionales
- RF-CP-01: el titular puede solicitar acceso, rectificación o eliminación de sus datos (ARCO).
- RF-CP-02: el sistema registra evidencia de la autorización de tratamiento de datos.
- RF-CP-03: el usuario ve un historial de quién accedió a sus datos. *(candidata no confirmada — ver [[pendientes]])*

## Relaciones
- Overview: [[overview]]
- Módulo relacionado: [[modulos/users]] (auditoría de accesos de `support`/`supervisor` a datos de terceros)
- Entidades: [[entidades/consent-record]], [[entidades/data-subject-request]], [[entidades/audit-log]]
- Glosario: [[glosario]]

## Preguntas abiertas / pendientes
- ¿El usuario debería poder ver su propio historial de `AuditLog` como feature de transparencia? (RF-CP-03). Ver [[pendientes]].
