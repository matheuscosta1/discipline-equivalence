package br.com.tcc.project.config;

import br.com.tcc.project.email.EmailService;
import br.com.tcc.project.email.SmtpEmailService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class EmailConfiguration {

  @Bean
  public EmailService emailService(){
    return new SmtpEmailService();
  }
}
