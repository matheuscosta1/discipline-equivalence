package br.com.tcc.project.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebMvcConfiguration {

  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder mapperBuilder) {
    return mapperBuilder.build().setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }
}
