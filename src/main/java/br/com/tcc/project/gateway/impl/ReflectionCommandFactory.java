package br.com.tcc.project.gateway.impl;

import br.com.tcc.project.command.interfaces.Command;
import br.com.tcc.project.gateway.CommandFactory;
import org.springframework.beans.BeanUtils;

@SuppressWarnings("java:S3740")
public class ReflectionCommandFactory implements CommandFactory<Command> {

  @Override
  public Class<Command> getType() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Command apply(Class<Command> commandClass) {
    return BeanUtils.instantiateClass(commandClass);
  }
}
