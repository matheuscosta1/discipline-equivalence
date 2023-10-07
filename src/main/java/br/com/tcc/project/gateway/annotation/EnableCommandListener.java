package br.com.tcc.project.gateway.annotation;

import br.com.tcc.project.gateway.CommandGateway;
import java.lang.annotation.*;
import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DelegateCommandListernerRegistrar.class)
public @interface EnableCommandListener {
  String commandGatewayBean() default CommandGateway.BEAN_NAME;
}
