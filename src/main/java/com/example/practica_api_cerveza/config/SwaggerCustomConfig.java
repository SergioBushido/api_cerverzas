package com.example.practica_api_cerveza.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerCustomConfig {


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()

                .info(new Info().title("API Cervezas")
                        .description("Spring Integration API.")
                        .version("1.0").contact(new Contact().name("Code With Group-6")
                                .email( "www.group6.com").url("localhost:8080"))
                        .license(new License().name("Licensia de API")
                                .url("API licensia URL")));
    }
}

