package com.ipss.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "EVA3 API",
                version = "v1",
                description = "API REST para EVA 3 (Usuarios, Cursos e Inscripciones)"
        )
)
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("EVA3 API")
                        .version("v1")
                        .description("API REST para EVA 3 (Usuarios, Cursos e Inscripciones)"));
    }
}
