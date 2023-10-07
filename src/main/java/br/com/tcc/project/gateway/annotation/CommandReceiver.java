package br.com.tcc.project.gateway.annotation;

import br.com.tcc.project.command.interfaces.Command;
import java.lang.annotation.*;
import org.springframework.stereotype.Component;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CommandReceiver {

  @SuppressWarnings("java:S1452")
  Class<? extends Command<?, ?>> value();
}
