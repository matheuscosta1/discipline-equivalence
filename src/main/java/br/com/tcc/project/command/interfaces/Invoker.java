package br.com.tcc.project.command.interfaces;

public interface Invoker<P, R> {
  Invoker<P, R> setExecutionId(String id);

  Invoker<P, R> setCommand(Command<P, R> command);

  Invoker<P, R> setParameter(P parameter);

  Invoker<P, R> setCommandListener(CommandListener commandListener);

  R getResult() throws IllegalStateException;

  Invoker<P, R> invoke() throws IllegalStateException;
}
