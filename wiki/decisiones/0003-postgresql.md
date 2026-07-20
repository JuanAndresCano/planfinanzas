---
tipo: decision
estado: activo
fuentes: [docs/adr/0003-postgresql-en-lugar-de-nosql.md]
actualizado: 2026-07-13
---

# Decisión: PostgreSQL en lugar de NoSQL

## Resumen
PostgreSQL como almacén principal — el modelo de datos es fuertemente relacional y exige transacciones ACID.

## Detalle

**Contexto**: entidades conectadas por llaves foráneas ([[entidades/credit]]/[[entidades/credit-payment]], [[entidades/investment]]/[[entidades/investment-return]], [[entidades/bank]]/[[entidades/financial-product]]/[[entidades/user-financial-product]]), operaciones que deben ser atómicas, necesidad de aislamiento por usuario a nivel de fila.

**Alternativas rechazadas**:
- MongoDB/NoSQL de documentos — obligaría a reimplementar integridad referencial en código de aplicación, sin equivalente a Row-Level Security.
- Postgres + un NoSQL adicional — agrega un segundo almacén operativo sin driver real todavía; si surge (ej. metadata de OCR de facturas), se resuelve con `JSONB` antes que con un motor aparte.

**Consecuencias positivas**: ACID nativo, Row-Level Security como segunda capa de defensa además del RBAC, integración madura con Spring Data JPA/Hibernate, un solo motor que operar.

**A vigilar**: menor flexibilidad de esquema que NoSQL para datos futuros poco estructurados — resuelto con columnas `JSONB` puntuales.

## Relaciones
- ADR original: `docs/adr/0003-postgresql-en-lugar-de-nosql.md`
- Entidades relacionadas: [[entidades/credit]], [[entidades/credit-payment]], [[entidades/investment]], [[entidades/investment-return]], [[entidades/bank]], [[entidades/financial-product]], [[entidades/user-financial-product]]

## Preguntas abiertas / pendientes
- Ninguna — decisión aceptada.
