package br.com.tcc.project.gateway.annotation;

import br.com.tcc.project.command.interfaces.Command;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CommandReceiver {

  @SuppressWarnings("java:S1452")
  Class<? extends Command<?, ?>> value();
}
