package br.com.tcc.project.command.impl;

import br.com.tcc.project.command.interfaces.Command;
import br.com.tcc.project.command.interfaces.CommandChain;
import br.com.tcc.project.command.interfaces.CommandChainReceiver;

public abstract class AbstractCommandChain<P, R> extends AbstractCommand<P, R>
    implements CommandChain<P, R> {

  @Override
  public <T, V> V invoke(Class<? extends Command<T, V>> commandClass, T parameters) {
    return ((CommandChainReceiver) receiver).invoke(commandClass, parameters, this.getTraceId());
  }
}
