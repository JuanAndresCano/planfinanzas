package com.kalma.planfinanzas;

import org.springframework.boot.SpringApplication;

public class TestPlanfinanzasApplication {

	public static void main(String[] args) {
		SpringApplication.from(PlanfinanzasApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
