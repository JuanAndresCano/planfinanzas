# ADR-0002: Spring Modulith en lugar de monolito de capas plano

- **Fecha**: 2026-07-09
- **Estado**: Aceptada

## Contexto

Habiendo decidido un monolito modular ([ADR-0001](0001-monolito-modular-en-lugar-de-microservicios.md)), falta definir cómo se estructura internamente el código. Un monolito tradicional organizado por capas técnicas (controllers / services / repositories globales) tiende a difuminar los límites de dominio con el tiempo: cualquier clase puede terminar llamando a cualquier otra, y nada lo impide ni lo detecta hasta que el acoplamiento ya es un problema.

## Decisión

Se adopta **Spring Modulith**, estructurando el código por módulos de negocio (`users`, `income`, `expenses`, `credits`, `investments`, `banking`, `compliance`, `reporting`). Cada módulo expone una API pública de consulta y se comunica con otros mediante el Event Publication Registry de Modulith, en lugar de acceder directamente a las clases internas de otro módulo.

## Alternativas consideradas

- **Monolito de capas plano** (organización por capa técnica en vez de por dominio) — rechazada. No ofrece ningún mecanismo de verificación automática de límites; el acoplamiento entre dominios solo se detecta por revisión manual, lo cual no escala ni siquiera para un único desarrollador a largo plazo.
- **DDD/arquitectura hexagonal armada a mano**, sin un framework que la sostenga — rechazada por ahora. Aporta beneficios similares a los módulos de Modulith, pero exige más ceremonia manual (definir puertos, adaptadores, y disciplina de equipo) sin la verificación automática que Modulith da de fábrica.

## Consecuencias

**Positivas**
- Los límites entre módulos se verifican con un test automático (`ApplicationModules.verify()`), no por buena voluntad — es, en efecto, una *fitness function* arquitectónica que falla el build si alguien accede a las internals de otro módulo.
- El Event Publication Registry da comunicación asíncrona transaccional y durable entre módulos (ej. auditoría, notificaciones) sin necesidad de infraestructura de mensajería adicional en el MVP.
- Si más adelante un módulo necesita convertirse en un servicio independiente, el límite ya existe y la extracción es mecánica, no un rediseño.

**A vigilar**
- El equipo (hoy, una persona) debe mantener la disciplina de comunicarse solo a través de las APIs públicas de cada módulo y no acceder a paquetes internos — mitigado porque el test de verificación lo detecta antes de fusionar cualquier cambio.
