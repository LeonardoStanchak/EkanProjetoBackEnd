package br.com.backend.ekan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "API desenvolvida para testes do OpenApi"))public class EkanApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkanApplication.class, args);
	}

}
