package com.bank.risk_engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RiskEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskEngineApplication.class, args);
	}

}
