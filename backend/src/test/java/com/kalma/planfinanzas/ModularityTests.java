package com.kalma.planfinanzas;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

/**
 * Fitness function arquitectónica: verifica automáticamente que ningún módulo acceda a
 * los paquetes internos de otro. Si alguien rompe un límite, el build falla.
 *
 * <p>Ver ADR-0002 (Spring Modulith en lugar de monolito de capas plano).
 */
class ModularityTests {

	static final ApplicationModules modules = ApplicationModules.of(PlanfinanzasApplication.class);

	@Test
	void verificaLimitesEntreModulos() {
		modules.verify();
	}

	/**
	 * Genera documentación de la arquitectura (diagramas C4 y tabla de módulos) en
	 * {@code build/spring-modulith-docs}, a partir del código real — no de un diagrama
	 * que haya que mantener a mano.
	 */
	@Test
	void generaDocumentacionDeModulos() {
		new Documenter(modules).writeDocumentation();
	}
}
