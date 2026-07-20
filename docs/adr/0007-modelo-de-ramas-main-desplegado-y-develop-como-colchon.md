# ADR-0007: Modelo de ramas — `main` desplegado y `develop` como colchón

- **Fecha**: 2026-07-13
- **Estado**: Aceptada

## Contexto

El plan es que `main` sea la rama **continuamente desplegada** al VPS (una vez exista el
pipeline de CD sobre lo definido en
[ADR-0004](0004-vps-autogestionado-en-lugar-de-cloud-gestionado.md)). Con despliegue
automático, cada merge a `main` termina en producción — hace falta un colchón de
integración donde el trabajo se valide antes de llegar ahí. Se evaluaron tres modelos:
GitHub Flow (una sola rama larga, `main`, con features que mergean directo a ella), Git
Flow completo (`develop` + `release/*` + `hotfix/*` + `main`), y un modelo intermedio de
dos ramas permanentes.

## Decisión

Dos ramas permanentes:
- **`main`** — refleja siempre lo que está desplegado en producción.
- **`develop`** — rama de integración/staging; todo el trabajo aterriza acá primero.

Ramas de feature (`feature/*`) nacen de `develop` y vuelven a `develop` vía PR. Cuando
`develop` está estable, un PR/merge a `main` dispara el despliegue (cuando exista el
pipeline de CD). **Esto no es Git Flow completo** — no hay ramas `release/*` ni
`hotfix/*` todavía.

## Alternativas consideradas

- **GitHub Flow (solo `main` + features)** — rechazada. Sin colchón de integración, cada
  feature mergeada llegaría directo a producción vía el despliegue automático, sin
  ventana para detectar problemas de integración antes de que sean visibles para
  usuarios reales.
- **Git Flow completo** (`develop` + `release/*` + `hotfix/*` + `main`) — rechazada por
  ahora. Las ramas de release y hotfix son ceremonia pensada para releases versionados
  con un rol de release manager — no aplica a un solo desarrollador sin versiones
  formales. Se puede adoptar `hotfix/*` más adelante si un incidente real en producción
  lo exige, sin necesitar `release/*` también.

## Consecuencias

**Positivas**
- `main` siempre refleja lo que está realmente vivo — nunca hay sorpresas de qué está en
  producción.
- `develop` da una ventana real para atrapar bugs de integración antes de que lleguen a
  producción, sin la ceremonia completa de Git Flow.
- Sigue siendo liviano: 2 ramas permanentes, no 4+ tipos de rama.

**A vigilar**
- El manejo de hotfixes urgentes en producción no está formalizado todavía — cuando
  ocurra el primer incidente real, probablemente sea una rama corta desde `main`,
  mergeada de vuelta a `main` **y** a `develop`.
- El CI debe correr en PRs hacia ambas ramas; el CD (cuando exista) debe dispararse
  únicamente desde `main`.
