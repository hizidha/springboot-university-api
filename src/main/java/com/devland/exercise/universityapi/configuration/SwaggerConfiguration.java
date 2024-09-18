package com.devland.exercise.universityapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    OpenAPI app() {
        Info info = new Info();
        info.setTitle("Cyber Campus Information System");
        info.setDescription("Data Station");
        info.setVersion("1.0.2");

        return new OpenAPI().info(info);
    }
}