package com.bruno.livro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LivrosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrosApiApplication.class, args);
	}

}
