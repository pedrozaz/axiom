package io.github.pedrozaz.axiom;

import org.springframework.boot.SpringApplication;

public class TestAxiomEngineApplication {

	public static void main(String[] args) {
		SpringApplication.from(AxiomEngineApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
