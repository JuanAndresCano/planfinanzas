---
tipo: glosario
estado: activo
fuentes: [docs/especificacion-proyecto.md]
actualizado: 2026-07-12
---

# Glosario

## Resumen
Términos legales, regulatorios y de dominio que aparecen en el proyecto sin explicarse a fondo en cada página donde se usan.

## Detalle

**Habeas Data** — derecho constitucional colombiano a conocer, actualizar y rectificar información recogida sobre una persona en bases de datos. Regulado por la Ley 1581 de 2012 y el Decreto 1377 de 2013. Ver [[modulos/compliance]].

**Derechos ARCO** — Acceso, Rectificación, Cancelación (supresión) y Oposición. Los cuatro derechos que un titular de datos puede ejercer sobre su información personal.

**RNBD** — Registro Nacional de Bases de Datos, administrado por la Superintendencia de Industria y Comercio (SIC). Registro obligatorio solo para empresas con activos totales superiores a 100.000 UVT, o entidades públicas. No aplica al MVP de este proyecto.

**SFC** — Superintendencia Financiera de Colombia. Regula entidades que captan, manejan o invierten recursos del público. No aplica a este proyecto porque la app no mueve dinero real, solo registra información que el usuario ya posee en otras entidades.

**UVT** — Unidad de Valor Tributario, unidad de medida usada en Colombia para fijar umbrales legales (ej. el umbral de activos para el RNBD).

**SMLMV** — Salario Mínimo Legal Mensual Vigente en Colombia, usado como unidad para expresar multas (ej. hasta 2.000 SMLMV por incumplimiento de protección de datos).

**Amortización / abono a capital** — en un crédito, la cuota normal se compone de una parte que paga intereses y otra que reduce el saldo (capital). Un "abono a capital" es un pago extra que reduce el saldo más rápido de lo previsto en el plan de pagos original. Ver [[entidades/credit-payment]].

**Cuota de manejo** — comisión periódica que cobra un banco por mantener un producto financiero (cuenta, tarjeta). Ver [[entidades/user-financial-product]].

**Row-Level Security (RLS)** — mecanismo de PostgreSQL que restringe qué filas puede leer/escribir un usuario a nivel de base de datos, como capa adicional de defensa además del control de acceso de la aplicación.

**Fitness function (arquitectónica)** — un test automatizado que verifica que el código respeta una regla arquitectónica (ej. que un módulo no acceda a las internals de otro), en vez de depender de disciplina manual. En Spring Modulith, `ApplicationModules.verify()` cumple este rol.

## Relaciones
- Módulo: [[modulos/compliance]]
- Overview: [[overview]]

## Preguntas abiertas / pendientes
- Términos adicionales llegarán con el Ingest de las ADRs (ej. detalle de Circular Externa 004 de la SFC sobre Open Finance).
