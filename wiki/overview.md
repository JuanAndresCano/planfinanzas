---
tipo: overview
estado: activo
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Overview — Kalma (App de Finanzas Personales)

## Resumen
App de finanzas personales para el mercado colombiano: control de ingresos, gastos, créditos e inversiones, con foco educativo (estados financieros personales) y cumplimiento normativo desde el diseño. Nombre de trabajo: **Kalma** (pendiente de confirmar dominio/marca — ver [[#Preguntas abiertas / pendientes]]).

## Detalle

### Misión
Ayudar a las personas a llevar el control de sus finanzas personales de forma simple y educativa, permitiéndoles entender en qué gastan su dinero (incluyendo lo que gastan sin ser conscientes) y cuánto podrían ahorrar o invertir, con un manejo de datos financieros serio, seguro y conforme a la regulación colombiana.

### Visión a futuro
- Empezar como web responsive; expandir a app móvil (React Native) sobre la misma API.
- Evolucionar hacia captura de facturas por cámara + OCR, insights más inteligentes de gasto.
- Habilitar, más adelante, hogares compartidos y un rol de asesor financiero de solo lectura.
- Sostenerse como referencia de buenas prácticas de seguridad y cumplimiento en Colombia.

### Alcance del MVP
Autenticación con roles, ingresos opcionales multi-fuente, gastos categorizados (fijos/variables), créditos con amortización y abonos a capital, inversiones (esperado vs. real), catálogo bancario, 4 reportes financieros, cumplimiento Ley 1581, seguridad (cifrado, RBAC+RLS, auditoría, monitoreo).

### Fuera de alcance (roadmap, en orden)
1. Hogares/presupuestos compartidos.
2. Rol de asesor financiero (solo lectura, vía consentimiento).
3. Captura de facturas por cámara + OCR.
4. Insights avanzados de gasto (posible ML).
5. Integración Open Finance / APIs bancarias reales.

## Relaciones
- Módulos: [[modulos/users]], [[modulos/income]], [[modulos/expenses]], [[modulos/credits]], [[modulos/investments]], [[modulos/banking]], [[modulos/compliance]], [[modulos/reporting]]
- Glosario: [[glosario]]
- Decisiones de arquitectura: pendientes — se cargan en el próximo Ingest (las 4 ADRs de `docs/adr/`).

## Preguntas abiertas / pendientes
- Nombre definitivo del proyecto (candidato: Kalma; pendiente dominio `.co`/`.app` y antecedentes marcarios SIC).
- Estructura concreta de paquetes dentro de Spring Modulith.
- Esqueleto inicial del repo (backend + frontend + Postgres en Docker Compose).
- Definir si se agregan reportes adicionales a los cuatro ya definidos.
- Selección de proveedor de OCR cuando se aborde esa funcionalidad.
- Diseño detallado de UI/UX.
