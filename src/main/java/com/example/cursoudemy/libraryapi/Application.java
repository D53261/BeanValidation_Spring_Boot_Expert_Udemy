package com.example.cursoudemy.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Ativa o suporte a auditoria JPA (ex: @CreatedDate, @LastModifiedDate)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//context.getBean(AutorRepository.class);
	}

}
