package br.com.tcc.project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  private static final String DESCRIPTION =
      "Sistema que calculará se duas disciplinas de cursos distintos são equivalentes.";

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Equivalência de Disciplinas 1.0.0")
                .description(DESCRIPTION)
                .version("1.0.0"));
  }

  @Bean
  public GroupedOpenApi internal() {
    return GroupedOpenApi.builder()
            .packagesToScan("br.com.tcc.project")
            .group("Local").build();
  }
}
