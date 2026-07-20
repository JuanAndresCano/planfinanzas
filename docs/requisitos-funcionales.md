# Requerimientos Funcionales (RF) — Borrador en elicitación

> Estado: **borrador**, en proceso de elicitación con el usuario. No usar como definitivo hasta cerrar la ronda de preguntas abiertas (sección final).
> Prefijo de cada RF = módulo Spring Modulith al que pertenece (trazabilidad directa a la arquitectura).

## 1. Usuarios y autenticación (`users`)

- **RF-US-01**: El sistema debe permitir registrar un nuevo usuario con email y contraseña.
- **RF-US-02**: El sistema debe permitir autenticarse con email y contraseña.
- **RF-US-03**: El sistema debe permitir cerrar sesión, invalidando el token activo.
- **RF-US-04**: El sistema debe permitir recuperar/restablecer la contraseña vía email. *(no estaba en las HU originales — ver preguntas abiertas)*
- **RF-US-05**: El usuario debe poder editar sus datos básicos de perfil (nombre, email). *(no estaba explícito)*
- **RF-US-06**: El sistema debe exigir la aceptación explícita de la política de tratamiento de datos antes de permitir el uso de la app.
- **RF-US-07**: Un `admin` debe poder asignar/cambiar el rol de un usuario (`user`, `supervisor`, `support`).

## 2. Ingresos (`income`)

- **RF-IN-01**: El usuario debe poder registrar un ingreso indicando fuente, monto, fecha y frecuencia.
- **RF-IN-02**: El registro de ingresos debe ser completamente opcional — la app debe funcionar sin que el usuario declare ningún ingreso.
- **RF-IN-03**: El usuario debe poder editar o eliminar un ingreso ya registrado. *(no estaba explícito)*
- **RF-IN-04**: El usuario debe poder listar y filtrar sus ingresos por periodo y por fuente. *(no estaba explícito)*
- **RF-IN-05**: El usuario debe poder crear fuentes de ingreso propias, además de las precargadas en el catálogo.

## 3. Gastos (`expenses`)

- **RF-EX-01**: El usuario debe poder registrar un gasto indicando categoría, monto y fecha.
- **RF-EX-02**: El usuario debe poder editar o eliminar un gasto ya registrado. *(no estaba explícito)*
- **RF-EX-03**: El usuario debe poder crear categorías de gasto propias, además de las predefinidas (arriendo, servicios, mercado, transporte, salidas, gastos del hogar).
- **RF-EX-04**: Cada categoría debe poder marcarse como fija o variable.
- **RF-EX-05**: El usuario debe poder listar y filtrar sus gastos por categoría y por periodo. *(no estaba explícito)*
- **RF-EX-06**: Un gasto puede marcarse como recurrente. *(alcance de "recurrente" pendiente de definir — ver preguntas abiertas)*

## 4. Créditos (`credits`)

- **RF-CR-01**: El usuario debe poder registrar un crédito indicando tipo, monto original, tasa, tipo de tasa (fija/variable), plazo y cuota.
- **RF-CR-02**: El usuario debe poder registrar pagos/abonos, distinguiendo cuota normal de abono a capital.
- **RF-CR-03**: El sistema debe calcular y mostrar el saldo vigente tras cada pago.
- **RF-CR-04**: El usuario debe poder ver la tabla de amortización completa de un crédito.
- **RF-CR-05**: El usuario debe poder editar o eliminar un crédito. *(no estaba explícito)*
- **RF-CR-06**: *(candidata, no confirmada)* El sistema podría notificar al usuario sobre próximos vencimientos de cuota.

## 5. Inversiones (`investments`)

- **RF-INV-01**: El usuario debe poder registrar una inversión indicando tipo, monto invertido, tasa de beneficio esperada y fechas.
- **RF-INV-02**: El usuario debe poder registrar rendimientos reales recibidos, indicando tipo (interés, dividendo, valorización).
- **RF-INV-03**: El sistema debe permitir comparar la tasa/rendimiento esperado contra el real.
- **RF-INV-04**: El usuario debe poder editar o eliminar una inversión. *(no estaba explícito)*

## 6. Catálogo bancario (`banking`)

- **RF-BK-01**: El usuario debe poder elegir su banco y producto financiero de un catálogo precargado, en vez de digitarlo manualmente.
- **RF-BK-02**: El usuario debe poder registrar su cuota de manejo real y saldo para cada producto que posea.
- **RF-BK-03**: Un `admin`/`supervisor` debe poder gestionar el catálogo global de bancos y productos (crear, editar).
- **RF-BK-04**: El usuario debe poder editar o eliminar un producto financiero propio. *(no estaba explícito)*

## 7. Reportes (`reporting`)

- **RF-RP-01**: El usuario debe poder ver un Estado de Resultados (P&G) por periodo.
- **RF-RP-02**: El usuario debe poder ver un Flujo de Efectivo por periodo, clasificado en operativo, de inversión y de financiación.
- **RF-RP-03**: El usuario debe poder ver un Balance General con su patrimonio neto.
- **RF-RP-04**: El usuario debe poder ver un desglose de sus gastos por categoría.
- **RF-RP-05**: El sistema debe mostrar una estimación de cuánto podría ahorrar o invertir el usuario.
- **RF-RP-06**: El usuario debe poder filtrar cualquier reporte por rango de fechas. *(no estaba explícito)*
- **RF-RP-07**: *(candidata, no confirmada)* El usuario podría poder exportar un reporte (PDF/Excel).

## 8. Cumplimiento de datos (`compliance`)

- **RF-CP-01**: El titular debe poder solicitar acceso, rectificación o eliminación de sus datos (derechos ARCO).
- **RF-CP-02**: El sistema debe registrar evidencia de la autorización de tratamiento de datos (versión de política, timestamp, IP).
- **RF-CP-03**: *(candidata, no confirmada)* El usuario podría poder ver un historial de quién accedió a sus datos (transparencia sobre accesos de `support`/`supervisor`).

## 9. Administración (`users`/transversal)

- **RF-AD-01**: Un `admin`/`supervisor` debe poder gestionar catálogos (bancos, categorías) sin ver montos financieros de los usuarios.
- **RF-AD-02**: Un `support` debe tener acceso limitado y auditado a cuentas de usuarios, solo para resolución de incidencias.

---

## Preguntas abiertas antes de cerrar esta lista

1. **RF-US-04/05** (recuperación de contraseña, edición de perfil): asumo que sí van en el MVP por ser básicos de cualquier sistema de auth — ¿confirmas, o hay algo distinto en mente?
2. **RF-EX-06** ("gasto recurrente"): ¿solo es una etiqueta informativa, o el sistema debe **generar automáticamente** el registro cada periodo (ej. arriendo se crea solo cada mes)? Esto cambia bastante el alcance.
3. **RF-CR-06** (notificar vencimientos de crédito): ¿entra al MVP o es futuro? Implica un canal de notificación (email/push) que no hemos definido todavía.
4. **RF-RP-07** (exportar reportes a PDF/Excel): ¿lo quieres para el MVP, dado el perfil "financiero" del negocio, o queda para después?
5. **RF-CP-03** (usuario ve su propio historial de auditoría): ¿te interesa como feature de transparencia hacia el usuario, o el `AuditLog` es solo interno/para cumplimiento?
