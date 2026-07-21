/**
 * Autenticación (delegada a Keycloak) y autorización por roles de plataforma.
 *
 * @see <a href="../../../../../../../docs/adr/0006-keycloak-en-lugar-de-auth0-o-auth-propio.md">ADR-0006</a>
 */
@ApplicationModule(displayName = "Usuarios y Roles")
package com.kalma.planfinanzas.users;

import org.springframework.modulith.ApplicationModule;
