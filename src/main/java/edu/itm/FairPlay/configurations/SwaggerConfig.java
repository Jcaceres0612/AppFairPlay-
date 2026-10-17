package edu.itm.FairPlay.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


    @Configuration
    public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("API de FairPlay")
                            .version("1.0")
                            .description("Documentación oficial de los endpoints para el sistema de gestión deportiva FairPlay. Implementación de CRUD y Arquitectura de 4 capas."));
        }
    }

