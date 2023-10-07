package br.com.tcc.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.tcc.project.*")
@ComponentScan(basePackages = { "br.com.tcc.project.*" })
@EntityScan("br.com.tcc.project.*")
public class DisciplineEquivalenceApplication {
  public static void main(String[] args) {
    SpringApplication.run(DisciplineEquivalenceApplication.class, args);
  }
}
