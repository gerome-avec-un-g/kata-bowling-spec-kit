package fr.geromeavecung.bowling;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bowlingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bowling Score Calculator API")
                        .description("API pour calculer le score d'un jeu de bowling à partir d'une chaîne de frames")
                        .version("0.0.1")
                        .contact(new Contact().name("Maintainer").email("noreply@example.com"))
                        .license(new License().name("MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Bowling kata")
                        .url("https://codingdojo.org/kata/Bowling/"));
    }
}

