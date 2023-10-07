package br.com.tcc.project.gateway;

import br.com.tcc.project.command.interfaces.Command;
import br.com.tcc.project.command.interfaces.Receiver;

public interface ReceiverResolver {
  <P, R> Receiver<P, R> resolve(Class<? extends Command<P, R>> commandClass);

  @SuppressWarnings("rawtypes")
  <P, R> void bind(Class<? extends Command> commandClass, Receiver<P, R> receiver);
}
