# ADR-0004: VPS autogestionado en lugar de cloud gestionado

- **Fecha**: 2026-07-09
- **Estado**: Aceptada

## Contexto

El proyecto está en etapa temprana de desarrollo, construido por un solo desarrollador con presupuesto acotado (proyecto personal / de portafolio). La Ley 1581 de 2012 exige un "deber de seguridad" sobre cualquier dato personal tratado, activo desde el primer usuario real, sin importar el tamaño de la empresa. Los proveedores de VPS candidatos (Hetzner, Hostinger) alojan sus servidores fuera de Colombia, lo que constituye una transferencia internacional de datos personales bajo el régimen colombiano.

## Decisión

Se despliega en un **VPS autogestionado**, con preferencia por **Hetzner** sobre Hostinger (mejor relación precio/rendimiento e infraestructura más orientada a seguridad), asumiendo directamente el hardening del sistema operativo, TLS, firewall, cifrado de disco y backups. La transferencia internacional de datos se cubre mediante **autorización expresa del titular** dentro del flujo de consentimiento ya modelado (`ConsentRecord`) — una de las excepciones que la propia Ley 1581 contempla — en lugar de perseguir la lista de países con nivel adecuado de la SIC o solicitar una declaración de conformidad.

## Alternativas consideradas

- **Cloud gestionado (AWS/GCP/Azure con RDS, Kubernetes gestionado, etc.)** — rechazada por ahora. El costo es mayor para un proyecto bootstrapped/de portafolio, y buena parte de la conveniencia gestionada (parcheo automático, cifrado por defecto, backups automáticos) no es un requisito duro a esta escala todavía. Al estar la aplicación containerizada, migrar a cloud gestionado más adelante no exige un rediseño mayor si el negocio lo justifica.

## Consecuencias

**Positivas**
- Menor costo operativo mientras el proyecto está en desarrollo/portafolio.
- Control total de la infraestructura, y una demostración concreta de competencia operativa/DevOps para el portafolio.
- Camino legal simple para la transferencia internacional de datos, apoyado en el consentimiento explícito que ya se iba a implementar.

**A vigilar — checklist de seguridad obligatorio antes de onboarding del primer usuario real**
- HTTPS vía nginx + Let's Encrypt.
- Firewall (ufw) y SSH solo por llave, root deshabilitado.
- Actualizaciones automáticas de seguridad del sistema operativo.
- Disco cifrado.
- Backups cifrados, probados periódicamente y almacenados fuera del mismo VPS.
- PostgreSQL nunca expuesto públicamente (solo accesible desde el backend).
- Texto de autorización del `ConsentRecord` debe mencionar explícitamente el almacenamiento en servidores fuera de Colombia.
- Revisar esta decisión si/cuando las necesidades de escala (alta disponibilidad multi-región, etc.) superen lo razonable de autogestionar.
