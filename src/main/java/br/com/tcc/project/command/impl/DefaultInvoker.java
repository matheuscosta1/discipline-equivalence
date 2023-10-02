package br.com.tcc.project.command.impl;

import br.com.tcc.project.command.interfaces.Command;
import br.com.tcc.project.command.interfaces.CommandListener;
import br.com.tcc.project.command.interfaces.Invoker;
import br.com.tcc.project.command.exception.CommandNotSetException;
import br.com.tcc.project.exception.IllegalArgumentExceptionThrowHelper;

import java.util.Optional;

public class DefaultInvoker<P, R> implements Invoker<P, R> {
  private String executionId;
  private Command<P, R> command;
  private P parameter;
  private CommandListener commandListener;

  public R getResult() throws IllegalStateException {
    assertCommand();
    return command.getResult();
  }

  public Invoker<P, R> invoke() throws IllegalStateException {
    assertCommand();
    command.setExecutionId(executionId);
    if (parameter != null) {
      command.setParameter(parameter);
    }
    Optional.ofNullable(commandListener)
        .ifPresent(listener -> listener.beforeExecute().accept(command));
    try {
      command.execute();
    } catch (Exception e) {
      Optional.ofNullable(commandListener)
          .ifPresent(listener -> listener.onError().accept(command, e));
      throw e;
    }
    Optional.ofNullable(commandListener)
        .ifPresent(listener -> listener.afterExecute().accept(command));

    return this;
  }

  @Override
  public Invoker<P, R> setExecutionId(String executionId) {
    this.executionId = executionId;
    return this;
  }

  public Invoker<P, R> setCommand(Command<P, R> command) {
    IllegalArgumentExceptionThrowHelper.throwIfMissingRequiredArgument("command", command);

    this.command = command;
    return this;
  }

  public Invoker<P, R> setParameter(P parameter) throws IllegalArgumentException {
    IllegalArgumentExceptionThrowHelper.throwIfMissingRequiredArgument("parameter", parameter);

    this.parameter = parameter;
    return this;
  }

  @Override
  public Invoker<P, R> setCommandListener(CommandListener commandListener) {
    this.commandListener = commandListener;
    return this;
  }

  protected void assertCommand() {
    if (command == null) {
      throw new CommandNotSetException("Command was not set.");
    }
  }
}
