# Especificación del Proyecto — App de Finanzas Personales

> Nombre definitivo del proyecto: **pendiente** — candidato líder: **Kalma** (ver sección 11).
> Última actualización: 2026-07-09.

## 1. Misión

Ayudar a las personas a llevar el control de sus finanzas personales de forma simple y educativa — ingresos, gastos, créditos e inversiones — permitiéndoles entender en qué están gastando su dinero (incluyendo lo que gastan sin ser conscientes) y cuánto podrían ahorrar o invertir, con un manejo de datos financieros serio, seguro y conforme a la regulación colombiana.

## 2. Visión a futuro

- Empezar como aplicación web responsive; expandir a app móvil (React Native) reutilizando la misma API sin tocar el backend.
- Evolucionar de registro manual a funcionalidades enriquecidas: captura de facturas por cámara con extracción automática de datos (OCR), insights más inteligentes sobre patrones de gasto.
- Habilitar, más adelante, hogares/presupuestos compartidos entre varios usuarios y un rol de asesor financiero con acceso de solo lectura autorizado explícitamente por cada usuario.
- Sostenerse como referencia de buenas prácticas de seguridad y cumplimiento normativo en el manejo de datos financieros personales en Colombia.

## 3. Alcance del MVP

- Registro y autenticación de usuarios, con roles (`admin`, `supervisor`, `user`, `support`).
- Registro de ingresos (opcional) desde múltiples fuentes catalogadas.
- Registro de gastos categorizados (fijos y variables), con categorías predefinidas y personalizadas.
- Gestión de créditos (hipotecario, vehículo, estudiantil, tarjeta, libre inversión, etc.) con seguimiento de amortización y abonos a capital separados de la cuota normal.
- Gestión de inversiones con seguimiento de rendimiento esperado vs. real.
- Catálogo de bancos y productos financieros (cuentas de ahorro/corriente, CDT, cuota de manejo).
- Reportes financieros: Estado de Resultados (P&G), Flujo de Efectivo, Balance General, desglose de gasto por categoría, estimación de capacidad de ahorro/inversión.
- Cumplimiento de la Ley 1581 de 2012 (habeas data): consentimiento explícito, política de tratamiento de datos, atención de derechos ARCO.
- Seguridad: cifrado en tránsito/reposo, RBAC + Row-Level Security, auditoría de accesos, monitoreo del sistema.

## 4. Fuera de alcance del MVP (roadmap futuro, en orden de prioridad)

1. Hogares/presupuestos compartidos entre varios usuarios.
2. Rol de asesor financiero con acceso de solo lectura vía consentimiento explícito del usuario.
3. Captura de facturas por cámara + extracción automática de datos (OCR) — proyecto en sí mismo.
4. Insights más avanzados sobre patrones de gasto (posible machine learning).
5. Integración con Open Finance / APIs bancarias reales para agregación automática de transacciones (sujeto a la Circular Externa 004 de la Superintendencia Financiera).

## 5. Decisiones tecnológicas tomadas

| Componente | Decisión | Motivo |
|---|---|---|
| Backend | Java + Spring Boot + Spring Modulith | Monolito modular: buenas prácticas y límites internos claros sin la carga operativa de microservicios, ideal para un solo desarrollador con miras a crecer el equipo. |
| Base de datos | PostgreSQL | Modelo fuertemente relacional, transacciones ACID para datos financieros, soporte nativo de Row-Level Security. |
| Autenticación | Spring Security + JWT | Stateless, sirve igual para web y la futura app móvil sobre la misma API. |
| Frontend web | Next.js + TypeScript + React | Responsive/PWA-ready; conocimiento reutilizable en React Native más adelante. |
| Frontend móvil (futuro) | React Native | Reutiliza conocimiento de React y, potencialmente, tipos generados desde el contrato OpenAPI del backend. |
| Monitoreo | Spring Boot Actuator + Micrometer + Prometheus + Grafana | Stack estándar del ecosistema Spring, sin herramientas nuevas que aprender. |
| Async interno | Spring Modulith Event Publication Registry | Eventos transaccionales y durables entre módulos sin infraestructura adicional. |
| Cola de mensajes (futuro) | RabbitMQ (Spring AMQP) | Reservado para trabajos pesados/externos como el OCR de facturas, donde sí se justifica desacoplar con una cola real. |
| Almacenamiento de objetos (futuro) | S3 o compatible | Para las facturas/comprobantes que el usuario suba. |
| Reverse proxy | nginx | Terminación TLS, punto único de entrada, rate limiting, balanceo de carga. |
| Arquitectura general | Monolito modular (no microservicios) | No hay hoy un driver organizacional ni de escala que justifique microservicios; Modulith deja los límites listos para partir un módulo si algún día se necesita. |

## 6. Decisiones arquitectónicas

> El razonamiento detallado de cada decisión (contexto, alternativas consideradas, consecuencias) queda registrado como ADR en [`docs/adr/`](adr/README.md).

- **API-first**: el backend expone una API REST consumida tanto por el frontend web como, a futuro, por el móvil.
- **Módulos de negocio** (Spring Modulith): `users`, `income`, `expenses`, `credits`, `investments`, `banking`, `compliance`, `reporting`. Cada módulo expone su API pública de consulta; ningún módulo accede directamente a las internals de otro. La verificación de estos límites es un test automático (`ApplicationModules.verify()`), no un proceso manual.
- **Ownership aislado**: toda tabla financiera (`Income`, `Expense`, `Credit`, `Investment`, `UserFinancialProduct`) referencia a su dueño mediante un `owner_id` desacoplado de la lógica de negocio, y el control de acceso vive en una capa de autorización separada — así, agregar hogares compartidos o el rol de asesor más adelante no implica reescribir estas tablas.
- **Mínimo privilegio en roles**: `admin` y `supervisor` no ven montos financieros crudos de los usuarios por defecto; solo gestionan catálogos, usuarios y configuración.
- **Nunca se almacena un número de cuenta completo** — solo los últimos 4 dígitos.
- **`BigDecimal` para todo cálculo monetario** (nunca `float`/`double`), para evitar errores de redondeo en montos, tasas y saldos.
- **Reportes desacoplados**: el módulo `reporting` no lee tablas de otros módulos directamente, sino que combina las APIs de consulta que cada módulo expone.

## 7. Modelo de datos (entidades principales)

**Ingresos**
- `IncomeSource` — catálogo de fuentes (salario, freelance, arriendo recibido, pensión, otro).
- `Income` — fuente, monto, fecha, frecuencia, recurrente sí/no. Registro completamente opcional para el usuario.

**Gastos**
- `ExpenseCategory` — catálogo editable por admin + categorías propias del usuario (arriendo, servicios, mercado, transporte, salidas, gastos del hogar), con flag de fijo/variable y frecuencia.
- `Expense` — categoría, monto, fecha, nota, recurrente.

**Créditos**
- `Credit` — tipo (hipotecario, vehículo, estudiantil, libre inversión, tarjeta, etc.), monto original, saldo actual, tasa de interés, tipo de tasa (fija/variable), plazo, cuota.
- `CreditPayment` — fecha, valor pagado, parte a capital vs. interés, saldo resultante (permite reconstruir la tabla de amortización y separar abonos a capital de la cuota normal).

**Inversiones**
- `Investment` — tipo (CDT, acciones, fondo, cripto, bienes raíces, etc.), monto invertido, tasa de beneficio esperada, fechas.
- `InvestmentReturn` — fecha, valor, tipo de rendimiento (interés, dividendo, valorización); separa lo esperado de lo real.

**Catálogo bancario**
- `Bank` — catálogo global (nombre, NIT).
- `FinancialProduct` — catálogo global (banco, tipo de producto, cuota de manejo por defecto, tasa por defecto).
- `UserFinancialProduct` — instancia del usuario (producto, alias, número enmascarado, saldo, cuota de manejo real, fecha de apertura).

**Gobernanza y cumplimiento**
- `ConsentRecord` — versión de política aceptada, timestamp, IP (evidencia de autorización exigida por la Ley 1581).
- `DataSubjectRequest` — seguimiento de solicitudes de acceso/rectificación/supresión con sus plazos legales.
- `AuditLog` — quién accedió/modificó qué y cuándo; obligatorio en accesos de `support`/`supervisor` a datos de terceros.

## 8. Roles y permisos

| Rol | Alcance |
|---|---|
| `admin` | Gestiona toda la plataforma (usuarios, catálogos, configuración global). No ve montos financieros crudos por defecto. |
| `supervisor` | Rol interno de segundo nivel bajo `admin` (ej. gestión de usuarios/catálogos por región o segmento). Tampoco ve montos financieros crudos por defecto. |
| `user` | Dueño único de sus propios datos. |
| `support` | Acceso limitado y auditado, únicamente para resolución de incidencias. |

Roadmap de roles (fuera del MVP, en orden): rol de asesor financiero de solo lectura vía consentimiento del usuario; y relaciones de hogar compartido entre usuarios.

## 9. Marco legal y regulatorio (Colombia)

- **Ley 1581 de 2012 (Habeas Data) + Decreto 1377 de 2013**: aplica desde el primer usuario, sin importar el tamaño de la empresa. Obligaciones concretas: autorización previa/expresa/informada, aviso de privacidad publicado, principio de finalidad, deber de seguridad, atención de consultas en máx. 10 días hábiles y reclamos en máx. 15 días hábiles, derechos ARCO (acceso, rectificación, cancelación/supresión, oposición).
- **RNBD (Registro Nacional de Bases de Datos, SIC)**: registro obligatorio solo si la empresa supera 100.000 UVT en activos totales (~5.000 millones COP en 2025) o es entidad pública. No aplica al MVP, pero se debe monitorear a futuro. Las obligaciones sustantivas de la Ley 1581 aplican igual sin necesidad del registro. Multas de hasta 2.000 SMLMV por incumplimiento del deber general de protección de datos.
- **Superintendencia Financiera (SFC)**: no aplica al MVP, ya que la aplicación no capta, maneja ni invierte recursos del público — el usuario registra manualmente información de productos que ya posee en otras entidades. Relevante únicamente si en el futuro se integra Open Finance/agregación bancaria automática (Circular Externa 004 de la SFC sobre estándares de seguridad e intercambio de datos).

Fuentes consultadas:
- [Ley 1581 de 2012 (Función Pública)](https://www.funcionpublica.gov.co/eva/gestornormativo/norma.php?i=49981)
- [Registro Nacional de Bases de Datos – SIC](https://www.sic.gov.co/registro-nacional-de-bases-de-datos)
- [Obligaciones RNBD 2025 (Holland & Knight)](https://www.hklaw.com/en/insights/publications/2025/01/obligaciones-del-registro-nacional-de-bases-de-datos-personales)
- [Cuáles personas están obligadas a registrarse en el RNBD – SIC](https://sedeelectronica.sic.gov.co/publicaciones/boletin-juridico/concepto/cuales-personas-estan-obligadas-realizar-el-registro-de-bases-de-datos-personales-en-el-rnbd)
- [Regulación financiera en Colombia habilitante para fintech – Superfinanciera](https://www.superfinanciera.gov.co/publicaciones/10115018/regulacion-financiera-en-colombia-ha-sido-habilitante-para-las-fintech/)
- [Fintech Colombia: regulación de pagos y banca abierta 2025 – Infobae](https://www.infobae.com/tecno/2025/02/06/fintech-colombia-conoce-la-regulacion-de-pagos-y-banca-abierta-que-daran-de-que-hablar-en-2025/)

## 10. Requerimientos funcionales iniciales (Historias de Usuario — draft)

**Usuarios y autenticación**
- HU-01: Como usuario, quiero registrarme y autenticarme de forma segura, para acceder a mis datos financieros de forma privada.
- HU-02: Como usuario, quiero aceptar explícitamente una política de tratamiento de datos antes de usar la app, para que mis derechos de habeas data queden garantizados.

**Ingresos**
- HU-03: Como usuario, quiero registrar ingresos desde distintas fuentes catalogadas (salario, freelance, etc.), para llevar el control completo de lo que recibo.
- HU-04: Como usuario, quiero que registrar ingresos sea opcional, para poder usar la app aunque no quiera declarar cuánto gano.

**Gastos**
- HU-05: Como usuario, quiero categorizar mis gastos en fijos y variables, para entender mis patrones de consumo.
- HU-06: Como usuario, quiero crear categorías de gasto propias además de las predefinidas, para adaptar la app a mi realidad.

**Créditos**
- HU-07: Como usuario, quiero registrar mis créditos con tipo, tasa, monto y plazo, para llevar control de mis deudas.
- HU-08: Como usuario, quiero registrar abonos a capital separados de la cuota normal, para ver su impacto real en el saldo.

**Inversiones**
- HU-09: Como usuario, quiero registrar mis inversiones con su tasa esperada, para compararla contra el rendimiento real.
- HU-10: Como usuario, quiero registrar los rendimientos reales que recibo, para hacer seguimiento en el tiempo.

**Catálogo bancario**
- HU-11: Como usuario, quiero elegir mi banco y producto financiero de un catálogo, para no tener que digitarlo manualmente.
- HU-12: Como usuario, quiero ver la cuota de manejo de mis productos, para saber cuánto me están cobrando.

**Reportes**
- HU-13: Como usuario, quiero ver un Estado de Resultados mensual, para saber si estoy ahorrando o gastando de más.
- HU-14: Como usuario, quiero ver un Flujo de Efectivo separado en operativo/inversión/financiación, para entender de dónde sale y a dónde va mi dinero.
- HU-15: Como usuario, quiero ver un Balance General con mi patrimonio neto, para conocer mi situación financiera real.
- HU-16: Como usuario, quiero ver un desglose de gastos por categoría, para identificar en qué gasto sin darme cuenta.
- HU-17: Como usuario, quiero ver una estimación de cuánto podría ahorrar o invertir, para tomar mejores decisiones sin complicarme.

**Cumplimiento y administración**
- HU-18: Como titular de datos, quiero poder solicitar acceso, rectificación o eliminación de mis datos, para ejercer mis derechos ARCO.
- HU-19: Como admin/supervisor, quiero gestionar catálogos (bancos, categorías) sin ver los montos de los usuarios, para respetar su privacidad.
- HU-20: Como support, quiero tener acceso limitado y auditado a las cuentas para resolver incidencias.

## 11. Pendientes / decisiones abiertas

- Nombre definitivo del proyecto — candidato líder: **Kalma**, pendiente de verificar disponibilidad de dominio (`.co`/`.app`) y antecedentes marcarios en la SIC (clase de software financiero).
- ~~Hosting e infraestructura de despliegue~~ — resuelto: VPS autogestionado (Hetzner). Ver [ADR-0004](adr/0004-vps-autogestionado-en-lugar-de-cloud-gestionado.md).
- Estructura concreta de paquetes dentro de Spring Modulith.
- Esqueleto inicial del repo (backend + frontend + Postgres en Docker Compose).
- Definir si se agregan reportes adicionales a los cuatro ya definidos.
- Selección de proveedor de OCR cuando se aborde esa funcionalidad (Textract, Google Document AI, Tesseract u otro).
- Diseño detallado de UI/UX.
