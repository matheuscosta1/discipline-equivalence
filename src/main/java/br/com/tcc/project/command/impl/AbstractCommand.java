package br.com.tcc.project.command.impl;

import br.com.tcc.project.command.interfaces.Command;
import br.com.tcc.project.command.interfaces.Receiver;
import br.com.tcc.project.command.exception.ReceiverNotSetException;
import br.com.tcc.project.exception.IllegalArgumentExceptionThrowHelper;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;

@GenerateCommandFactory
public abstract class AbstractCommand<P, R> implements Command<P, R> {
  private String executionId;
  private String traceId;
  protected Receiver<P, R> receiver;
  private P parameter;
  private R result;

  public String getExecutionId() {
    return executionId;
  }

  public void setExecutionId(String executionId) {
    this.executionId = executionId;
  }

  @Override
  public String getTraceId() {
    return traceId;
  }

  @Override
  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }

  public R getResult() throws IllegalStateException {
    return result;
  }

  public void setParameter(P parameter) throws IllegalArgumentException {
    IllegalArgumentExceptionThrowHelper.throwIfMissingRequiredArgument("parameter", parameter);

    this.parameter = parameter;
  }

  public P getParameter() {
    return parameter;
  }

  public void setReceiver(Receiver<P, R> receiver) throws IllegalArgumentException {
    IllegalArgumentExceptionThrowHelper.throwIfMissingRequiredArgument("receiver", receiver);

    this.receiver = receiver;
  }

  public void execute() throws IllegalStateException {
    if (receiver == null) {
      throw new ReceiverNotSetException(
          "Receiver was not set for command[" + this.getClass().getSimpleName() + ".");
    }

    this.result = receiver.execute(parameter);
  }

  protected void setResult(R result) {
    this.result = result;
  }
}
