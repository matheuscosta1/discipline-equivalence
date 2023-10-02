package br.com.tcc.project.command.impl;

import br.com.tcc.project.command.interfaces.Receiver;
public abstract class AbstractReceiver<P, R> implements Receiver<P, R> {
  public R execute(P parameter) throws IllegalStateException {
    return doExecute(parameter);
  }

  protected abstract R doExecute(P parameter);
}
