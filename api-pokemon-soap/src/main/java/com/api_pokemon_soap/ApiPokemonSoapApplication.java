package com.api_pokemon_soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.api_pokemon_soap.repository")
public class ApiPokemonSoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPokemonSoapApplication.class, args);
	}

}
