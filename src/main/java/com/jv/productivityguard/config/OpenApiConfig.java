package com.jv.productivityguard.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  OpenAPI openAPI() {
    return new OpenAPI().info(new Info().title("FocusPilot API").version("v1").description("API de prioridade, foco e acompanhamento de tarefas."));
  }
}
