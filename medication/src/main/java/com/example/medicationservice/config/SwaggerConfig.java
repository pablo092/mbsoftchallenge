package com.example.medicationservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0")
                        .description("Documentación Swagger de los servicios"));
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder().group("medications").pathsToMatch("/api/medications/**").build();
    }

    @Bean
    public CommandLineRunner logSwaggerUrl() {
        return args -> {
            logger.info("📖 Swagger UI disponible en:");
            logger.info("   ➤ http://localhost:8082/swagger-ui.html  (Medication Service)");
        };
    }
}
