# Wiki de Kalma (planfinanzas) — Schema y convenciones

Esta wiki es la base de conocimiento persistente del proyecto, mantenida por Claude,
no por el usuario. El usuario dirige, hace preguntas y decide qué se prioriza;
Claude escribe y mantiene todas las páginas.

## Capas

- **Fuentes crudas**: `docs/` en la raíz del repo (spec, ADRs, requisitos funcionales,
  y cualquier documento "pulido" nuevo). Son legibles por humanos sin pasar por la wiki
  — un reclutador o colaborador nuevo debería poder entender el proyecto solo leyendo
  `docs/`, sin abrir Obsidian.
- **Wiki**: esta carpeta (`wiki/`). Páginas más granulares e interconectadas,
  generadas a partir de las fuentes. Este es el "compilado" — no se re-deriva desde
  cero en cada pregunta.
- **Schema**: este archivo.

## Estructura de carpetas

```
wiki/
├── CLAUDE.md              (este archivo)
├── index.md               (catálogo de todo, por categoría)
├── log.md                 (registro cronológico de ingests/queries/lints)
├── modulos/                (una página por módulo Modulith)
│   ├── users.md
│   ├── income.md
│   ├── expenses.md
│   ├── credits.md
│   ├── investments.md
│   ├── banking.md
│   ├── compliance.md
│   └── reporting.md
├── entidades/               (una página por entidad del modelo de datos)
│   ├── income.md, expense.md, credit.md, credit-payment.md,
│   ├── investment.md, investment-return.md, bank.md, financial-product.md,
│   ├── user-financial-product.md, consent-record.md,
│   ├── data-subject-request.md, audit-log.md
├── decisiones/              (una página por ADR — espejo enlazado de docs/adr/)
│   ├── 0001-monolito-modular.md
│   ├── 0002-spring-modulith.md
│   ├── 0003-postgresql.md
│   ├── 0004-vps-autogestionado.md
├── glosario.md              (términos legales/dominio: habeas data, ARCO, RNBD,
│                             amortización, cuota de manejo, etc.)
└── overview.md              (página de entrada: qué es el proyecto, para quién,
                              estado actual — enlaza a todo lo demás)
```

## Convención de nombres de archivo

- kebab-case, sin mayúsculas ni tildes: `user-financial-product.md`, no `UserFinancialProduct.md`.
- Las páginas de decisiones usan el mismo número que su ADR en `docs/adr/`
  (`0001-monolito-modular.md`), para que el número sea una clave estable entre
  ambas capas.

## Plantilla de página

Toda página de `modulos/`, `entidades/` y `decisiones/` lleva este frontmatter:

```yaml
---
tipo: modulo | entidad | decision | glosario
estado: activo | deprecado | propuesto
modulo_relacionado: [income, reporting]   # para entidades: a qué módulo(s) pertenece
fuentes: [docs/especificacion-proyecto.md, docs/adr/0001-...]  # de dónde salió
actualizado: 2026-07-12
---

# Título

## Resumen
2-3 líneas, qué es esto y por qué importa.

## Detalle
Contenido principal.

## Relaciones
- Ver también: [[otra-pagina]]
- Módulo: [[modulos/income]]
- Decisión relacionada: [[decisiones/0003-postgresql]]

## Preguntas abiertas / pendientes
(si aplica)
```

## index.md — formato

Catálogo por categoría, no cronológico:

```markdown
# Índice

## Módulos
- [[modulos/income]] — Ingresos desde múltiples fuentes, registro opcional
- ...

## Entidades
- [[entidades/credit]] — Créditos con amortización y abonos a capital
- ...

## Decisiones
- [[decisiones/0001-monolito-modular]] — Monolito modular vs. microservicios
- ...

## Glosario
- [[glosario]]
```

Se actualiza en cada Ingest — nunca queda desfasado del contenido real.

## log.md — formato

Append-only, entradas con prefijo parseable:

```markdown
## [2026-07-12] ingest | especificacion-proyecto.md
Páginas creadas/actualizadas: overview.md, modulos/*.md (8), glosario.md
Notas: primera migración de docs/ existente a la wiki.

## [2026-07-12] query | "¿por qué Postgres y no NoSQL?"
Respondido desde decisiones/0003-postgresql.md, sin cambios a la wiki.
```

`grep "^## \[" wiki/log.md | tail -5` da las últimas 5 entradas.

## Workflow: Ingest

Fuente por fuente, con supervisión del usuario en cada paso (nunca en lote):

1. Leer la fuente completa (archivo en `docs/`, o contenido pegado en el chat).
2. **Criterio de qué vale la pena ingerir**:
   ingerir si la fuente contiene al menos uno de:
   - una decisión de arquitectura o de producto (con o sin ADR formal),
   - una definición o cambio de módulo/entidad del modelo de datos,
   - un hallazgo de investigación externa (legal, competencia, mercado) que
     informe una decisión,
   - un bug resuelto con causa raíz no obvia,
   - un cambio de requerimiento funcional/no funcional.
   NO ingerir: conversación efímera sin decisión, preguntas exploratorias que no
   llegaron a una conclusión, ruido.
3. Proponer al usuario un resumen de los puntos clave extraídos, antes de tocar
   ninguna página — el usuario confirma o corrige énfasis.
4. Escribir/actualizar las páginas afectadas (módulo, entidad, decisión, glosario
   según corresponda). Señalar explícitamente si algo contradice una página
   existente, en vez de sobrescribir en silencio.
5. Actualizar `index.md` con cualquier página nueva.
6. Añadir entrada a `log.md`.
7. Mostrar al usuario el diff/resumen de qué cambió antes de darlo por cerrado.

## Workflow: Query

1. Leer `index.md` primero para ubicar páginas candidatas — no releer todo `docs/`
   ni todo `wiki/` de una.
2. Abrir solo las páginas relevantes.
3. Responder con síntesis + citas a las páginas usadas (`[[pagina]]`).
4. Si la respuesta generó contenido nuevo que vale la pena conservar (una
   comparación, un análisis), ofrecer archivarlo como página nueva en vez de
   dejarlo solo en el chat — y seguir el flujo de Ingest para eso.
5. Registrar la query en `log.md` solo si produjo una página nueva o cambió una
   existente; queries puramente informativas no ensucian el log.

## Workflow: Lint

Frecuencia por defecto (ajustable): al cierre de cada sesión de trabajo larga, o
cuando el usuario lo pida explícitamente — no en cada commit.

Revisar:
- Páginas huérfanas (sin enlaces entrantes).
- Enlaces rotos (`[[wikilink]]` a una página que no existe).
- Contradicciones entre páginas (ej. una decisión superada por una ADR más nueva
  sin actualizar).
- Decisiones marcadas "Aceptada" que en la práctica ya fueron reemplazadas.
- Módulos o entidades del código sin página correspondiente.
- Deriva contra el código real: ¿la página de un módulo sigue reflejando lo que
  el código en `backend/`/`frontend/` realmente hace?
- Reportar hallazgos como lista — no corregir nada automáticamente sin mostrarle
  al usuario primero.
