package com.planner.godsaeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaAuditing
public class GodSaengApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodSaengApplication.class, args);
	}

}
