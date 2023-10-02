package br.com.tcc.project.config;

import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.gateway.ReceiverResolver;
import br.com.tcc.project.gateway.annotation.ApplicationContextAwareReceiverResolver;
import br.com.tcc.project.gateway.impl.DefaultCommandGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfiguration {

  @Bean
  public ReceiverResolver receiverResolver() {
    return new ApplicationContextAwareReceiverResolver();
  }

  @Bean
  public CommandGateway commandGateway() {
    return new DefaultCommandGateway(receiverResolver());
  }

  @Value("${spring.application.name}")
  public String serviceName;
}
