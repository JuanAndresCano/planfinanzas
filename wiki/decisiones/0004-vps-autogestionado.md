---
tipo: decision
estado: activo
fuentes: [docs/adr/0004-vps-autogestionado-en-lugar-de-cloud-gestionado.md]
actualizado: 2026-07-13
---

# Decisión: VPS autogestionado en lugar de cloud gestionado

## Resumen
Despliegue en VPS autogestionado (Hetzner preferido sobre Hostinger), asumiendo hardening, TLS, firewall, cifrado y backups directamente.

## Detalle

**Contexto**: proyecto en etapa temprana, presupuesto acotado, "deber de seguridad" de la Ley 1581 (ver [[glosario]]) activo desde el primer usuario real. Los VPS candidatos alojan fuera de Colombia → transferencia internacional de datos.

**Decisión clave**: la transferencia internacional se cubre con autorización expresa del titular dentro de [[entidades/consent-record]] — una excepción que la propia Ley 1581 contempla — en vez de perseguir la lista de países con nivel adecuado de la SIC.

**Alternativa rechazada**: cloud gestionado (AWS/GCP/Azure) — mayor costo para un proyecto bootstrapped, y la conveniencia gestionada no es un requisito duro a esta escala. Migrar después no exige rediseño mayor (app containerizada).

**Consecuencias positivas**: menor costo operativo, control total de infraestructura (valor de portafolio/DevOps), camino legal simple para transferencia internacional.

**Checklist de seguridad obligatorio antes del primer usuario real**:
- HTTPS vía nginx + Let's Encrypt.
- Firewall (ufw), SSH solo por llave, root deshabilitado.
- Actualizaciones automáticas de seguridad del SO.
- Disco cifrado.
- Backups cifrados, probados, fuera del mismo VPS.
- PostgreSQL nunca expuesto públicamente.
- Texto de autorización de [[entidades/consent-record]] debe mencionar servidores fuera de Colombia.

## Relaciones
- ADR original: `docs/adr/0004-vps-autogestionado-en-lugar-de-cloud-gestionado.md`
- Entidad relacionada: [[entidades/consent-record]]
- Módulo relacionado: [[modulos/compliance]]
- Glosario: [[glosario]] (Habeas Data, transferencia internacional)

## Preguntas abiertas / pendientes
- Revisar si/cuando las necesidades de escala (alta disponibilidad multi-región) superen lo razonable de autogestionar.
