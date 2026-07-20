# ADR-0003: PostgreSQL en lugar de NoSQL

- **Fecha**: 2026-07-09
- **Estado**: Aceptada

## Contexto

El modelo de datos es fuertemente relacional: `Income`, `Expense`, `Credit`/`CreditPayment`, `Investment`/`InvestmentReturn`, `Bank`/`FinancialProduct`/`UserFinancialProduct` están conectados por llaves foráneas, y varias operaciones deben ser atómicas (ej. registrar un abono a capital y actualizar el saldo del crédito en la misma transacción). Además, se requiere una capa adicional de aislamiento por usuario a nivel de fila, complementaria al RBAC de la aplicación.

## Decisión

Se usa **PostgreSQL** como almacén de datos principal.

## Alternativas consideradas

- **MongoDB / almacén NoSQL de documentos** — rechazada. El dominio tiene muchas relaciones y exige consistencia fuerte; modelarlo como documentos obligaría a reimplementar en código de aplicación la integridad referencial que una base relacional da nativamente, y no ofrece un equivalente directo a Row-Level Security.
- **Postgres + un NoSQL adicional para necesidades específicas** — rechazada para el MVP. Agregaría un segundo almacén operativo (backups, monitoreo, expertise) sin un driver real todavía. Si en el futuro una necesidad puntual lo justifica (ej. metadata semi-estructurada de la extracción OCR de facturas), se evalúa entonces — probablemente resuelto con una columna `JSONB` en Postgres antes que justificar un motor aparte.

## Consecuencias

**Positivas**
- Transacciones ACID nativas para operaciones financieras que no pueden quedar a medias.
- Row-Level Security como segunda capa de defensa: un `user` no puede leer registros de otro ni por un error de lógica en el código de aplicación.
- Integración madura con Spring Data JPA/Hibernate, sin capas de traducción adicionales.
- Un solo motor de base de datos que operar, respaldar y monitorear.

**A vigilar**
- Menor flexibilidad de esquema que un documento NoSQL para datos futuros poco estructurados (ej. resultado crudo del OCR de facturas) — se resuelve con columnas `JSONB` puntuales en lugar de cambiar de motor.
